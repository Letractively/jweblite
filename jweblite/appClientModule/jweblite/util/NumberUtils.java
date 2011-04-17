package jweblite.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

	/**
	 * To Scale Double
	 * 
	 * @param d
	 *            double
	 * @param scale
	 *            int
	 * @return double
	 */
	public static double toScaleDouble(double d, int scale) {
		return new BigDecimal(d).setScale(scale, RoundingMode.HALF_UP)
				.doubleValue();
	}

}