package jweblite.web.tag.corner;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import jweblite.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CornerTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(this.getClass());

	private String width = null;
	private String borderColor = null;

	private String title = null;
	private String titleColor = null;
	private String titleBackgroundColor = null;
	private String titleStyle = null;

	private String bodyColor = null;
	private String bodyBackgroundColor = null;
	private String bodyStyle = null;

	/**
	 * Default constructor.
	 */
	public CornerTag() {
		super();
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter jw = this.pageContext.getOut();
			jw.print("<div");
			if (this.width != null) {
				jw.print(String.format(
						" style=\"float: left;clear: both;width: %s;\"",
						this.width));
			}
			jw.println(">");
			// header
			this.paintHeader(jw, this.title);
			// body
			this.paintBody(jw, this.getBodyContent().getString());
			// footer
			this.paintFooter(jw);
			jw.println("</div>");
		} catch (Exception e) {
			log.warn("Do end tag failed!", e);
		}
		return BodyTagSupport.EVAL_PAGE;
	}

	/**
	 * Paint Header
	 * 
	 * @param jw
	 *            JspWriter
	 * @param title
	 *            String
	 * @throws IOException
	 */
	public void paintHeader(JspWriter jw, String title) throws IOException {
		String borderColor = StringUtils.getStringValue(this.borderColor,
				"#4578D4");
		String titleColor = StringUtils.getStringValue(this.titleColor, "");
		String titleBackgroundColor = StringUtils.getStringValue(
				this.titleBackgroundColor, "#739DEA");
		String bodyBackgroundColor = StringUtils.getStringValue(
				this.bodyBackgroundColor, "white");
		String titleStyle = StringUtils.getStringValue(this.titleStyle, "");
		// corner panel
		jw.println("<div style=\"height: 6px;border-width: 0;font-size: 0;overflow: hidden;\">");
		jw.println(String
				.format("<div style=\"height: 1px;margin: 0 8px;background-color: %s;overflow: hidden;\"></div>",
						borderColor));
		int[] heightArray = { 1, 1, 1, 2 };
		int[] marginArray = { 5, 3, 2, 1 };
		int[] borderWidthArray = { 3, 2, 1, 1 };
		for (int i = 0; i < 4; i++) {
			jw.println(String
					.format("<div style=\"height: %dpx;margin: 0 %dpx;border-style: solid;border-width: 0 %dpx;border-color: %s;background-color: %s;overflow: hidden;\"></div>",
							heightArray[i], marginArray[i],
							borderWidthArray[i], borderColor,
							(title != null ? titleBackgroundColor
									: bodyBackgroundColor)));
		}
		jw.println("</div>");
		// title
		if (title != null) {
			jw.println(String
					.format("<div style=\"background-color: %s;border: 1px solid %s;border-top: 0 none;color: %s;font-size: small;font-weight: bold;\"><div style=\"margin: 0 10px;%s;\">",
							titleBackgroundColor, borderColor, titleColor,
							titleStyle));
			jw.println(StringUtils.getStringValue(title, "&nbsp;", true));
			jw.println("</div></div>");
		}
	}

	/**
	 * Paint Body
	 * 
	 * @param jw
	 *            JspWriter
	 * @param content
	 *            String
	 * @throws IOException
	 */
	public void paintBody(JspWriter jw, String content) throws IOException {
		String bodyBackgroundColor = StringUtils.getStringValue(
				this.bodyBackgroundColor, "white");
		String borderColor = StringUtils.getStringValue(this.borderColor,
				"#4578D4");
		String bodyColor = StringUtils.getStringValue(this.bodyColor, "");
		String bodyStyle = StringUtils.getStringValue(this.bodyStyle, "");
		// content
		jw.println(String
				.format("<div style=\"background-color: %s;border-left: 1px solid %2s;border-right: 1px solid %2$s;color: %s;text-align: center;%s;\">",
						bodyBackgroundColor, borderColor, bodyColor, bodyStyle));
		jw.println(content);
		jw.println("</div>");
	}

	/**
	 * Paint Footer
	 * 
	 * @param jw
	 *            JspWriter
	 * @throws IOException
	 */
	public void paintFooter(JspWriter jw) throws IOException {
		String bodyBackgroundColor = StringUtils.getStringValue(
				this.bodyBackgroundColor, "white");
		String borderColor = StringUtils.getStringValue(this.borderColor,
				"#4578D4");
		// corner panel
		jw.println("<div style=\"height: 6px;border-width: 0;font-size: 0;overflow: hidden;\">");
		int[] heightArray = { 2, 1, 1, 1 };
		int[] marginArray = { 1, 2, 3, 5 };
		int[] borderWidthArray = { 1, 1, 2, 3 };
		for (int i = 0; i < 4; i++) {
			jw.println(String
					.format("<div style=\"height: %dpx;margin: 0 %dpx;border-width: 0 %dpx;border-style: solid;border-color: %s;background-color: %s;overflow: hidden;\"></div>",
							heightArray[i], marginArray[i],
							borderWidthArray[i], borderColor,
							bodyBackgroundColor));
		}
		jw.println(String
				.format("<div style=\"height: 1px;margin: 0 8px;background-color: %s;overflow: hidden;\"></div>",
						borderColor));
		jw.println("</div>");
	}

	/**
	 * Get Width
	 * 
	 * @return String
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Set Width
	 * 
	 * @param width
	 *            String
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * Get Border Color
	 * 
	 * @return String
	 */
	public String getBorderColor() {
		return borderColor;
	}

	/**
	 * Set Border Color
	 * 
	 * @param borderColor
	 *            String
	 */
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * Get Title
	 * 
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set Title
	 * 
	 * @param title
	 *            String
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get Title Color
	 * 
	 * @return String
	 */
	public String getTitleColor() {
		return titleColor;
	}

	/**
	 * Set Title Color
	 * 
	 * @param titleColor
	 *            String
	 */
	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	/**
	 * Get Title Background Color
	 * 
	 * @return String
	 */
	public String getTitleBackgroundColor() {
		return titleBackgroundColor;
	}

	/**
	 * Set Title Background Color
	 * 
	 * @param titleBackgroundColor
	 *            String
	 */
	public void setTitleBackgroundColor(String titleBackgroundColor) {
		this.titleBackgroundColor = titleBackgroundColor;
	}

	/**
	 * Get Title Style
	 * 
	 * @return String
	 */
	public String getTitleStyle() {
		return titleStyle;
	}

	/**
	 * Set Title Style
	 * 
	 * @param titleStyle
	 *            String
	 */
	public void setTitleStyle(String titleStyle) {
		this.titleStyle = titleStyle;
	}

	/**
	 * Get Body Color
	 * 
	 * @return String
	 */
	public String getBodyColor() {
		return bodyColor;
	}

	/**
	 * Set Body Color
	 * 
	 * @param bodyColor
	 *            String
	 */
	public void setBodyColor(String bodyColor) {
		this.bodyColor = bodyColor;
	}

	/**
	 * Get Body Background Color
	 * 
	 * @return String
	 */
	public String getBodyBackgroundColor() {
		return bodyBackgroundColor;
	}

	/**
	 * Set Body Background Color
	 * 
	 * @param bodyBackgroundColor
	 *            String
	 */
	public void setBodyBackgroundColor(String bodyBackgroundColor) {
		this.bodyBackgroundColor = bodyBackgroundColor;
	}

	/**
	 * Get Body Style
	 * 
	 * @return String
	 */
	public String getBodyStyle() {
		return bodyStyle;
	}

	/**
	 * Set Body Style
	 * 
	 * @param bodyStyle
	 *            String
	 */
	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

}