package jweblite.util;

public class LogUtils {

	/**
	 * Format Debug Log
	 * 
	 * @param reason
	 *            String
	 * @param infoFormat
	 *            String
	 * @param args
	 *            Object...
	 * @return String
	 */
	public static String formatDebugLog(String reason, String infoFormat,
			Object... args) {
		if (reason == null) {
			reason = "Log";
		}
		StringBuilder log = new StringBuilder();
		log.append(reason);
		log.append(": ");
		if (infoFormat != null) {
			if (args != null && args.length > 0) {
				log.append("{");
				log.append(String.format(infoFormat, args));
				log.append("}");
			} else {
				log.append(infoFormat);
			}
		}
		return log.toString();
	}

}