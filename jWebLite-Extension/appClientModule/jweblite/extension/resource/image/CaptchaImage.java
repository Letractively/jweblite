package jweblite.extension.resource.image;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jweblite.resource.DynamicWebResource;
import jweblite.web.SkipException;
import jweblite.web.application.JWebLiteApplication;
import jweblite.web.wrapper.FormModel;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class CaptchaImage extends DynamicWebResource {

	private static final long serialVersionUID = 1L;
	private static final Log _cat = LogFactory.getLog(CaptchaImage.class);

	private String challenge = null;
	private Font font = new Font("SansSerif", Font.PLAIN, 36);
	private int width = 200;
	private int height = 50;
	private int padding = 20;

	/**
	 * Default constructor.
	 */
	public CaptchaImage() {
		super();
	}

	public String getContentType() {
		return "image/jpeg";
	}

	public String getFileName() {
		return null;
	}

	@Override
	public byte[] loadData(HttpServletRequest req, FormModel fm) {
		if (this.font == null) {
			return null;
		}
		ByteArrayOutputStream baos = null;
		try {
			BufferedImage bi = new BufferedImage(this.getWidth(),
					this.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bi.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			if (this.font != null) {
				g2.setFont(this.font);
			}
			// paint background
			this.paintBackground(g2);
			// paint noise
			this.paintNoise(g2);
			// paint challenge
			this.paintChallenge(g2);
			// dispose
			g2.dispose();
			// output
			baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "JPEG", baos);
			baos.flush();
		} catch (Exception e) {
			_cat.warn("Write data failed!", e);
		} finally {
			IOUtils.closeQuietly(baos);
		}
		return (baos != null ? baos.toByteArray() : null);
	}

	@Override
	public void doHeader(HttpServletRequest req, HttpServletResponse resp,
			FormModel fm) throws SkipException {
		// get challenge from session
		this.challenge = getChallenge(req);
		if (this.challenge == null) {
			throw new SkipException();
		}
		super.doHeader(req, resp, fm);
	}

	/**
	 * Create Challenge
	 * 
	 * @return String
	 */
	public static String createChallenge(HttpServletRequest req,
			String challenge) {
		// set challenge to the session
		HttpSession session = req.getSession(true);
		String attrPrefix = JWebLiteApplication.get().getFilterConfig()
				.getAttrPrefix();
		String captchaImageId = String.valueOf(System.currentTimeMillis());
		session.setAttribute(attrPrefix.concat("CaptchaImageId"),
				captchaImageId);
		session.setAttribute(attrPrefix.concat("CaptchaImageChallenge_")
				.concat(captchaImageId), challenge);
		return challenge;
	}

	/**
	 * Get Challenge (could be a wrong challenge by another captcha image
	 * request)
	 * 
	 * @return String
	 */
	public static String getChallenge(HttpServletRequest req) {
		// set challenge to the session
		HttpSession session = req.getSession(true);
		String attrPrefix = JWebLiteApplication.get().getFilterConfig()
				.getAttrPrefix();
		String captchaId = (String) session.getAttribute(attrPrefix
				.concat("CaptchaImageId"));
		if (captchaId == null) {
			return null;
		}
		return (String) session.getAttribute(attrPrefix.concat(
				"CaptchaImageChallenge_").concat(captchaId));
	}

	/**
	 * Paint Background
	 * 
	 * @param g2
	 *            Graphics2D
	 */
	public void paintBackground(Graphics2D g2) {
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Paint Noise
	 * 
	 * @param g2
	 *            Graphics2D
	 */
	public void paintNoise(Graphics2D g2) {
		Stroke originalStroke = g2.getStroke();
		Color originalColor = g2.getColor();

		g2.setStroke(new BasicStroke(3));
		for (int i = 0; i < 100; i++) {
			g2.setColor(new Color((int) (Math.random() * 256), (int) (Math
					.random() * 256), (int) (Math.random() * 256), (int) (Math
					.random() * 100)));
			g2.drawLine((int) (Math.random() * this.getWidth()),
					(int) (Math.random() * this.getHeight()),
					(int) (Math.random() * this.getWidth()),
					(int) (Math.random() * this.getHeight()));
		}

		g2.setStroke(originalStroke);
		g2.setColor(originalColor);
	}

	/**
	 * Paint Challenge
	 * 
	 * @param g2
	 *            Graphics2D
	 */
	public void paintChallenge(Graphics2D g2) {
		if (this.challenge == null || this.challenge.length() <= 0) {
			return;
		}
		AffineTransform originalAffineTransform = g2.getTransform();

		char[] charArray = this.challenge.toCharArray();
		int charArraySize = charArray.length;
		// calculate rectangle
		// get dimension
		FontMetrics fm = g2.getFontMetrics();
		Dimension d = new Dimension(fm.stringWidth(this.challenge)
				+ ((charArraySize - 1) * padding), fm.getHeight());
		// get position
		Point position = this.getPosition(g2, d);
		// drawing
		double baseCharWidth = d.getWidth() / charArraySize;
		for (int i = 0; i < charArraySize; i++) {
			int x = (int) (i * baseCharWidth + position.getX() + (this.padding / 2));
			int y = (int) position.getY();
			g2.setTransform(AffineTransform.getRotateInstance(
					Math.toRadians((int) (Math.random() * 60 - 30)), x, y));
			g2.setXORMode(new Color((int) (Math.random() * 256), (int) (Math
					.random() * 256), (int) (Math.random() * 256)));
			g2.drawString(String.valueOf(charArray[i]), x, y);
		}

		g2.setTransform(originalAffineTransform);
		g2.setPaintMode();
	}

	/**
	 * Get Position
	 * 
	 * @param g2
	 *            Graphics2D
	 * @param d
	 *            Dimension
	 * @return Point
	 */
	public Point getPosition(Graphics2D g2, Dimension d) {
		if (d == null) {
			return null;
		}
		double centerX = this.getWidth() / 2.0;
		double centerY = this.getHeight() / 2.0;
		return new Point((int) (centerX - d.getWidth() / 2),
				(int) (centerY + d.getHeight() / 4));
	}

	/**
	 * Get Font
	 * 
	 * @return Font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Set Font
	 * 
	 * @param font
	 *            Font
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * Get Width
	 * 
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set Width
	 * 
	 * @param width
	 *            int
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Get Height
	 * 
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set Height
	 * 
	 * @param height
	 *            int
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Get Padding
	 * 
	 * @return int
	 */
	public int getPadding() {
		return padding;
	}

	/**
	 * Set Padding
	 * 
	 * @param padding
	 *            int
	 */
	public void setPadding(int padding) {
		this.padding = padding;
	}

	/**
	 * Get Challenge
	 * 
	 * @return String
	 */
	public String getChallenge() {
		return challenge;
	}

}