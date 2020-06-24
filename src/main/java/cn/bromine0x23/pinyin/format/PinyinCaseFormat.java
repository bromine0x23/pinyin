package cn.bromine0x23.pinyin.format;

import java.util.Locale;

/**
 * 大小写格式
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public enum PinyinCaseFormat {

	/**
	 * 小写
	 */
	LOWER_CASE {
		@Override
		public String format(String pinyin) {
			return pinyin.toLowerCase(Locale.ENGLISH);
		}
	},

	/**
	 * 大写
	 */
	UPPER_CASE {
		@Override
		public String format(String pinyin) {
			return pinyin.toUpperCase(Locale.ENGLISH);
		}
	},

	/**
	 * 首字母大写
	 */
	TITLE_CASE {
		@Override
		public String format(String pinyin) {
			return UPPER_CASE.format(pinyin.substring(0, 1)) + LOWER_CASE.format(pinyin.substring(1));
		}
	},
	;

	public abstract String format(String pinyin);
}
