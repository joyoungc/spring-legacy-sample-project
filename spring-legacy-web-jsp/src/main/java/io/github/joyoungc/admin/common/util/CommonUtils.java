package io.github.joyoungc.admin.common.util;

public class CommonUtils {
	
	/***
	 * Byte 단위로 문자열 자르기
	 * @param str
	 * @param byteLength
	 * @param sizePerLetter
	 * @return
	 */
	public static String subStringBytes(String str, int byteLength, int sizePerLetter) {
		
		int retLength = 0;
		int tempSize = 0;
		int asc;
		if (str == null || "".equals(str) || "null".equals(str)) {
			str = "";
		}

		int length = str.length();

		for (int i = 1; i <= length; i++) {
			asc = (int) str.charAt(i - 1);
			if (asc > 127) {
				if (byteLength >= tempSize + sizePerLetter) {
					tempSize += sizePerLetter;
					retLength++;
				}
			} else {
				if (byteLength > tempSize) {
					tempSize++;
					retLength++;
				}
			}
		}

		return str.substring(0, retLength);
	}

}
