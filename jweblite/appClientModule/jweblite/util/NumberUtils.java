package jweblite.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NumberUtils {

	private static final Log _cat = LogFactory.getLog(NumberUtils.class);

	/**
	 * To Fixed Float
	 * 
	 * @param f
	 *            float
	 * @param scale
	 *            int
	 * @return float
	 */
	public static float toFixedFloat(float f, int scale) {
		return new BigDecimal(f).setScale(scale, RoundingMode.HALF_UP)
				.floatValue();
	}

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

	/**
	 * Count Double Add
	 * 
	 * @param values
	 *            Number...
	 * @return Double
	 */
	public static Double countDoubleAdd(Number... values) {
		if (values == null) {
			return null;
		}
		boolean isNull = true;
		double result = 0;
		for (Number value : values) {
			if (value == null) {
				continue;
			}
			isNull = false;
			result += value.doubleValue();
		}
		return (!isNull ? result : null);
	}

	/**
	 * Count Integer Add
	 * 
	 * @param values
	 *            Integer...
	 * @return Integer
	 */
	public static Integer countIntegerAdd(Integer... values) {
		if (values == null) {
			return null;
		}
		boolean isNull = true;
		int result = 0;
		for (Integer value : values) {
			if (value == null) {
				continue;
			}
			isNull = false;
			result += value.intValue();
		}
		return (!isNull ? result : null);
	}

	/**
	 * Count Double Subtract
	 * 
	 * @param mainValue
	 *            Number
	 * @param values
	 *            Number...
	 * @return Double
	 */
	public static Double countDoubleSubtract(Number mainValue, Number... values) {
		boolean isNull = true;
		double result = 0;
		if (mainValue != null) {
			isNull = false;
			result = mainValue.doubleValue();
		}
		if (values != null) {
			for (Number value : values) {
				if (value == null) {
					continue;
				}
				isNull = false;
				result -= value.doubleValue();
			}
		}
		return (!isNull ? result : null);
	}

	/**
	 * Count Integer Subtract
	 * 
	 * @param mainValue
	 *            Integer
	 * @param values
	 *            Integer...
	 * @return Integer
	 */
	public static Integer countIntegerSubtract(Integer mainValue,
			Integer... values) {
		boolean isNull = true;
		int result = 0;
		if (mainValue != null) {
			isNull = false;
			result = mainValue.intValue();
		}
		if (values != null) {
			for (Integer value : values) {
				if (value == null) {
					continue;
				}
				isNull = false;
				result -= value.intValue();
			}
		}
		return (!isNull ? result : null);
	}

}