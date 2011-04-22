package jweblite.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

	/**
	 * To Fixed Double
	 * 
	 * @param d
	 *            double
	 * @param scale
	 *            int
	 * @return double
	 */
	public static double toFixedDouble(double d, int scale) {
		return new BigDecimal(d).setScale(scale, RoundingMode.HALF_UP)
				.doubleValue();
	}

}