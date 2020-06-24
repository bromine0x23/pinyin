package cn.bromine0x23.pinyin;

import lombok.experimental.UtilityClass;

/**
 * 汉字-拼音转换工具
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@UtilityClass
public class Pinyin {

	/**
	 * 获取汉字所有可能读音的拼音，使用原始记录格式（小写、调号、v）输出拼音
	 *
	 * @param codepoint 汉字码点
	 * @return 所有可能读音的拼音
	 */
	public static String[] toPinyins(int codepoint) {
		return CodepointToPinyins.pinyinsOf(codepoint);
	}

	/**
	 * 获取汉字所有可能读音的拼音，使用原始记录格式（小写、调号、v）输出拼音
	 *
	 * @param character 汉字字符
	 * @return 所有可能读音的拼音
	 */
	public static String[] toPinyins(char character) {
		return CodepointToPinyins.pinyinsOf(character);
	}

	/**
	 * 获取汉字所有可能读音的拼音，使用 {@code format} 指定的格式输出拼音
	 *
	 * @param codepoint 汉字码点
	 * @param format    输出格式
	 * @return 所有可能读音的拼音
	 * @see PinyinFormat
	 */
	public static String[] toFormattedPinyins(int codepoint, PinyinFormat format) {
		String[] pinyins = toPinyins(codepoint);
		for (int i = 0; i < pinyins.length; ++i) {
			pinyins[i] = format.format(pinyins[i]);
		}
		return pinyins;
	}

	/**
	 * 获取汉字所有可能读音的拼音，使用 {@code format} 指定的格式输出拼音
	 *
	 * @param character 汉字字符
	 * @param format    输出格式
	 * @return 所有可能读音的拼音
	 * @see PinyinFormat
	 */
	public static String[] toFormattedPinyins(char character, PinyinFormat format) {
		String[] pinyins = toPinyins(character);
		for (int i = 0; i < pinyins.length; ++i) {
			pinyins[i] = format.format(pinyins[i]);
		}
		return pinyins;
	}

	/**
	 * 将文本中的每个汉字转化为对应首选（对应记录的首个）读音的拼音，使用原始记录格式（小写、调号、v）输出拼音
	 *
	 * @param text 文本
	 * @return 所有汉字的首选读音拼音
	 */
	public static String[] toPrimaryPinyins(String text) {
		String[] result = new String[text.codePointCount(0, text.length())];
		int      offset = 0;
		for (int count = 0; offset < text.length(); ++count) {
			int      codepoint = text.codePointAt(offset);
			String[] pinyins   = toPinyins(codepoint);
			if (pinyins.length > 0) {
				result[count] = pinyins[0];
			}
			offset += Character.charCount(codepoint);
		}
		return result;
	}

	/**
	 * 将文本中的每个汉字转化为对应首选（对应记录的首个）读音的拼音，使用 {@code format} 指定的格式输出拼音
	 *
	 * @param text   文本
	 * @param format 输出格式
	 * @return 所有汉字的首选读音拼音
	 * @see PinyinFormat
	 */
	public static String[] toFormattedPrimaryPinyins(String text, PinyinFormat format) {
		String[] result = new String[text.codePointCount(0, text.length())];
		int      offset = 0;
		for (int count = 0; offset < text.length(); ++count) {
			int      codepoint = text.codePointAt(offset);
			String[] pinyins   = toFormattedPinyins(codepoint, format);
			if (pinyins.length > 0) {
				result[count] = pinyins[0];
			}
			offset += Character.charCount(codepoint);
		}
		return result;
	}
}
