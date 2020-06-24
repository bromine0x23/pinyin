package cn.bromine0x23.pinyin.format;

/**
 * ü 格式
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public enum PinyinVFormat {

	/**
	 * 使用 v 表示
	 */
	V {
		@Override
		public String format(String pinyin) {
			return pinyin;
		}
	},

	/**
	 * 使用 ü 表示
	 */
	Ü {
		@Override
		public String format(String pinyin) {
			return pinyin.replace('v', 'ü');
		}
	},
	;

	public abstract String format(String pinyin);
}
