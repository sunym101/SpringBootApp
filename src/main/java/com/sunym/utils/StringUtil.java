package com.sunym.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.List;
import java.util.Vector;

public class StringUtil {

	public static String formatText(String text, String[] param) {
		MessageFormat format = new MessageFormat(text);
		String result = format.format(param);
		return result;
	}

	public static boolean isEmpty(String text) {
		if (text == null || "".equals(text.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String text) {
		return !isEmpty(text);
	}

	public static boolean strCompareNoCase(String strValue1, String strValue2) {
		if (strValue1 == null && strValue2 == null) {
			return true;
		} else if (strValue1 != null && strValue2 == null) {
			return false;
		} else if (strValue1 == null && strValue2 != null) {
			return false;
		} else {
			if (strValue1.toLowerCase().equals(strValue2.toLowerCase())) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static StringBuffer appendLine(StringBuffer stringBuffer, String appendString) {
		stringBuffer.append(appendString).append("\n");
		return stringBuffer;
	}

	/**
	 * List内容合并为字符串内容，以指定的分隔符连接。
	 * 
	 * @param list
	 * @param split
	 * @return
	 */
	public static String listToString(List<String> list, String split) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			String content = list.get(i);
			if (content == null || "".equals(content.trim())) {
				content = "";
			}

			if (i > 0) {
				result += split;
			}
			result += content;
		}
		return result;
	}

	public static String formatStartWithZore(int number, int formatLength) {
		/*
		 * 0 指前面补充零 formatLength 字符总长度为 formatLength d 代表为正数。
		 */
		String newString = String.format("%0" + formatLength + "d", number);
		return newString;
	}
	
	/**
	 * 文字列分割.<BR>
	 * 
	 * @param val
	 *            文字列
	 * 
	 * @param split
	 *            分割符
	 * 
	 * @return 配列
	 */
	public static String[] splitString(String val, String split) {
		Vector<String> vals = new Vector<String>();
		int sPos = 0;
		int ePos = 0;

		while (true) {
			ePos = val.indexOf(split, sPos);
			if (ePos == -1) {
				if (sPos < val.length()) {
					vals.add(val.substring(sPos));
				} else {
					vals.add("");
				}
				break;
			} else {
				vals.add(val.substring(sPos, ePos));
				sPos = ePos + 1;
			}
		}

		String[] list = new String[vals.size()];
		for (int i = 0; i < vals.size(); i++) {
			list[i] = (String) vals.get(i);
		}

		return list;

	}

	/**
	 * 文字列"'"和"''"置换.<BR>
	 * 
	 * @param oldstr
	 *            转换前的值
	 * 
	 * @return newstr 转换后的值
	 * 
	 */
	public static String replaceSinglequote(String oldstr) {

		if (oldstr == null) {
			return oldstr;
		}
		if (oldstr.trim().length() == 0) {
			return oldstr;
		}
		if (oldstr.indexOf("'") == -1) {
			return oldstr;
		}
		StringBuffer newstr = new StringBuffer();
		for (int i = 0; i < oldstr.length(); i++) {
			if (oldstr.charAt(i) == '\'') {
				newstr.append("''");
			} else {
				newstr.append(oldstr.charAt(i));
			}
		}
		return newstr.toString();

	}

	/**
	 * 文字列中换行符削除。<BR>
	 * 
	 * @param oldstr
	 *            转换前的值
	 * 
	 * @return newstr 转换后的值
	 * 
	 */
	public static String replaceCRLF(String oldstr) {

		if (oldstr == null) {
			return oldstr;
		}
		if (oldstr.trim().length() == 0) {
			return oldstr;
		}
		if (oldstr.indexOf("\n") == -1 && oldstr.indexOf("\r") == -1) {
			return oldstr;
		}
		StringBuffer newstr = new StringBuffer();
		for (int i = 0; i < oldstr.length(); i++) {
			if (oldstr.charAt(i) == '\r' || oldstr.charAt(i) == '\n') {
			} else {
				newstr.append(oldstr.charAt(i));
			}
		}
		return newstr.toString();

	}

	/**
	 * 替换指定的字符串里的值.<BR>
	 * 
	 * @param oldstr
	 *            转换前的值
	 * 
	 * @param changefrom
	 *            要替换的值
	 * 
	 * @param changeto
	 *            替换的值
	 * 
	 * @return newstr 转换后的值
	 * 
	 */
	public static String replaceStr(String oldstr, String changefrom, String changeto) {

		if (oldstr == null || changefrom == null || changeto == null) {
			return oldstr;
		}
		if (oldstr.length() == 0 || changefrom.length() == 0) {
			return oldstr;
		}
		if (oldstr.indexOf(changefrom) == -1) {
			return oldstr;
		}
		StringBuffer newstr = new StringBuffer();
		for (int i = 0; i < oldstr.length(); i++) {
			if (oldstr.substring(i, i + 1).equals(changefrom)) {
				newstr.append(changeto);
			} else {
				newstr.append(oldstr.substring(i, i + 1));
			}
		}
		return newstr.toString();

	}

	/**
	 * 字符串截取
	 * 
	 * @param val
	 *            截取前的值
	 * @param len
	 *            截取的长度
	 * @return val 截取后的值
	 */
	public static String cutString(String val, int len) {
		if (val == null || val.length() == 0 || "NULL".equals(val)) {
			return "";
		}

		if (val.length() > len) {
			val = val.substring(0, len);
		}

		return val;
	}

	/**
	 * 字符串头部或者末尾追加其他字符
	 * 
	 * @param oldVal
	 *            追加前的值
	 * @param addChar
	 *            要追加的值
	 * @param len
	 *            最大行数
	 * @param inBegin
	 *            TRUE：头/FALSE：尾追加
	 * @return val 追加后的值
	 */
	public static String addString(String oldVal, char addChar, int len, boolean inBegin) {
		String val = oldVal;
		if (val == null)
			val = "";

		if (val.length() > 0 && val.length() < len) {
			int cnt = len - val.length();
			for (int i = 0; i < cnt; i++) {
				if (inBegin) {
					val = addChar + val;
				} else {
					val = val + addChar;
				}
			}
		}

		return val;
	}

	/**
	 * code变换处理
	 * 
	 * @param val
	 *            变换前的值
	 * @return newStr 变换后的值
	 */
	public static String changeCharCode(String val) {
		String newStr = "";

		if (val == null || val.trim().length() == 0) {
			return newStr;
		}

		// ￢,￡,￠,∥,－
		while (val.indexOf(0xffe2) != -1 || val.indexOf(0xffe1) != -1 || val.indexOf(0xffe0) != -1
				|| val.indexOf(0x2225) != -1 || val.indexOf(0xff0d) != -1) {
			// ￢
			val = val.replace('\uffe2', '\u00ac');
			// ￡
			val = val.replace('\uffe1', '\u00a3');
			// ￠
			val = val.replace('\uffe0', '\u00a2');
			// ∥
			val = val.replace('\uff0d', '\u2212');
			// －
			val = val.replace('\u2225', '\u2016');
		}

		return val;
	}

	/**
	 * 数值保留两位小数
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeNumberType(Double convert) {
		if (convert == null) {
			return "";
		}
		BigDecimal big = new BigDecimal(convert);
		big = big.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("##0.00");
		return format.format(big);
	}

	/**
	 * 数值保留两位小数
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeNumberType(double convert) {
		BigDecimal big = new BigDecimal(convert);
		big = big.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("##0.00");
		return format.format(big);
	}

	/**
	 * 数值保留两位小数
	 * 
	 * @param convert
	 *            转换前数值
	 * @param scale
	 *            保留小数的位数
	 * @return 转换后字符串
	 */
	public static String changeNumberType(double convert, int scale) {
		BigDecimal big = new BigDecimal(convert);
		big = big.setScale(scale, RoundingMode.HALF_UP);
		String strFormatRest = "";
		for (int i = 0; i < scale; i++) {
			strFormatRest = strFormatRest + "0";
		}
		DecimalFormat format = null;
		if (strFormatRest.length() > 0) {
			format = new DecimalFormat("##0." + strFormatRest);
		} else {
			format = new DecimalFormat("##0");
		}
		return format.format(big);
	}

	/**
	 * 数值格式化
	 * 
	 * @param convert
	 *            转换前数值
	 * @param scale
	 *            保留小数的位数
	 * @return 转换后字符串
	 */
	public static String changeNumberType(BigDecimal convert, int scale) {
		BigDecimal big = convert;
		if (big == null) {// 数据为空时，返回null
			return null;
		}
		if (scale < 0) {// 不格式化直接转化成字符串
			return String.valueOf(big);
		}
		big = big.setScale(scale, RoundingMode.HALF_UP);
		String strFormatRest = "";
		for (int i = 0; i < scale; i++) {
			strFormatRest = strFormatRest + "0";
		}
		DecimalFormat format = null;
		if (strFormatRest.length() > 0) {
			format = new DecimalFormat("##0." + strFormatRest);
		} else {
			format = new DecimalFormat("##0");
		}
		return format.format(big);
	}

	public static String changeNumberTypeUp(double convert, int scale) {
		BigDecimal big = new BigDecimal(convert);
		big = big.setScale(scale, RoundingMode.UP);
		String strFormatRest = "";
		for (int i = 0; i < scale; i++) {
			strFormatRest = strFormatRest + "0";
		}
		DecimalFormat format = null;
		if (strFormatRest.length() > 0) {
			format = new DecimalFormat("##0." + strFormatRest);
		} else {
			format = new DecimalFormat("##0");
		}
		return format.format(big);
	}

	/**
	 * 数值保留两位小数并且加千位符
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeNumberCommaType(Double convert) {
		if (convert == null) {
			return "";
		}
		BigDecimal big = new BigDecimal(convert);
		big = big.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("#,##0.00");
		return format.format(big);
	}

	/**
	 * 数值保留两位小数并且加千位符
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeNumberCommaType(double convert, int scale) {
		BigDecimal big = new BigDecimal(convert);
		big = big.setScale(scale, RoundingMode.HALF_UP);
		String strFormatRest = "";
		for (int i = 0; i < scale; i++) {
			strFormatRest = strFormatRest + "0";
		}
		DecimalFormat format = null;
		if (strFormatRest.length() > 0) {
			format = new DecimalFormat("#,##0." + strFormatRest);
		} else {
			format = new DecimalFormat("#,##0");
		}
		return format.format(big);
	}

	/**
	 * 数值保留两位小数并且加千位符
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeNumberCommaType(double convert) {
		BigDecimal big = new BigDecimal(convert);
		big = big.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("#,##0.00");
		return format.format(big);
	}

	/**
	 * 整数并且加千位符
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeIntegerCommaType(Integer convert) {
		BigDecimal big = new BigDecimal(convert);
		big = big.setScale(0, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("#,##0");
		return format.format(big);
	}

	// ADD by jsf@2013-3-12 s(一点清算)
	/**
	 * 利率保留显示所有小数，最多8位，不保留小数里无效的0
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeIntRateType(String convert) {
		BigDecimal big = new BigDecimal(convert);
		DecimalFormat format = new DecimalFormat("#.########");
		return format.format(big);
	}

	// ADD by jsf@2013-3-12 e(一点清算)

	/**
	 * 整数
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeIntegerType(Integer convert) {
		BigDecimal big = new BigDecimal(convert);
		big = big.setScale(0, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("###0");
		return format.format(big);
	}

	/**
	 * 字符串为空白时转换成null
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeBlankToNull(String convert) {
		String result = null;
		if (!StringUtil.isEmpty(convert)) {
			result = convert;
		}

		return result;
	}

	/**
	 * 字符串为空白时转换成null
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String changeNullToString(String convert) {
		String result = "";
		if (!StringUtil.isEmpty(convert)) {
			result = convert;
		}

		return result;
	}

	/**
	 * 字符串加成需要的长度（两边加空格内容）
	 * 
	 * @param convert
	 *            转换前数值
	 * @param extLength
	 *            需要转换的长度
	 * @return 转换后字符串
	 */
	public static String addStringLength(String convert, int extLength) {

		String result = "";

		if (StringUtil.isEmpty(convert)) {
			for (int i = 0; i < extLength; i++) {
				result += " ";
			}
		} else {
			if (convert.length() < extLength) {
				int iLength = extLength - convert.length();
				int pre = 0;
				int lase = 0;

				if ((iLength % 2) == 0) {
					pre = iLength / 2;
					lase = pre;
				} else {
					pre = iLength / 2;
					lase = iLength - pre;
				}

				for (int i = 0; i < pre; i++) {
					result = result + " ";
				}
				result = result + convert;
				for (int j = 0; j < lase; j++) {
					result = result + " ";
				}

			} else {
				result = convert;
			}
		}

		return result;
	}

	/**
	 * 字符串转换成int型
	 * 
	 * @param convert
	 *            转换前数值
	 * 
	 * 
	 * @return 转换后int型
	 */
	public static int changeToInt(String convert) {

		int result = 0;

		if (!StringUtil.isEmpty(convert)) {
			result = Integer.parseInt(convert);
		}
		return result;
	}

	/**
	 * 判断字符是半角英数字还是中日文等
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 半角英数字：true；全角中日文：false
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 转换Excel单元格的数据内容(根据要求的长度改变字符的长度,不够的地方用空格填)
	 * 
	 * @param convert
	 *            转换前数值
	 * @param cellLength
	 *            希望的文字长度
	 * @return 转换后字符类型
	 */
	public static String changePrintCellContent(String convert, int cellLength) {

		int index = 0;
		for (int i = 0; i < convert.length(); i++) {
			if (isLetter(convert.charAt(i))) {
				index++;
			} else {
				index += 2;
			}
		}

		return addStringLength(convert, cellLength - (index - convert.length()));
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static String likeConvert(String strParam) {
		return "%" + strParam + "%";
	}

	public static String singleQuoteConvert(String strParam) {
		return strParam.replaceAll("'", "''");
	}

	public static String changeLine(String strParam, int lineLength) {
		if (strParam == null || strParam.length() == 0) {
			return "";
		}
		char[] charArr = strParam.toCharArray();
		StringBuffer strBuffer = new StringBuffer();

		for (int i = 0; i < charArr.length; i++) {
			strBuffer.append(charArr[i]);
			if (i >= lineLength && i % lineLength == 0) {
				strBuffer.append("\n");
			}
		}

		return strBuffer.toString();
	}

	/**
	 * 数字转换为指定小数位的数字（四舍五入方式处理）
	 * 
	 * @param number
	 *            数字（字符串形式）
	 * @param scale
	 *            小数位数
	 * @return
	 */
	public static String decimalToStr(String number, int scale) {
		if (number == null || number.trim().length() == 0) {
			return "";
		}

		BigDecimal decimal = new BigDecimal(number);
		decimal = decimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return decimal.toPlainString();
	}

	/**
	 * 数字转换为指定小数位的数字（四舍五入方式处理）
	 * 
	 * @param number
	 *            数字（BigDecimal形式）
	 * @param scale
	 *            小数位数
	 * @return
	 */
	public static String decimalToStr(BigDecimal number, int scale) {
		if (number == null) {
			return "";
		}

		BigDecimal decimal = number.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return decimal.toPlainString();
	}

	/**
	 * 从流中获取数据
	 * 
	 * @param is
	 *            InputStream
	 * @return 数据流的字符串内容
	 * @throws IOException
	 */
	public static String getStreamContent(InputStream is, String charset) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = bis.read(buffer)) != -1) {
			sb.append(new String(buffer, 0, read, charset));
		}
		return sb.toString();
	}
}
