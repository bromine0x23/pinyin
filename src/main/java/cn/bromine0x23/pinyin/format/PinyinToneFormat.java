package cn.bromine0x23.pinyin.format;

import java.util.regex.Pattern;

/**
 * 声调格式
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
public enum PinyinToneFormat {

	/**
	 * 使用声调标记（tone mark）：汉 -> hàn
	 */
	TONE_MARK {
		private final Pattern patternPinyin = Pattern.compile("[a-z]*[1-5]?");
		private final Pattern patternPinyinWithToneNumber = Pattern.compile("[a-z]*[1-5]");
		private final String[][] markedSymbols = {
			{"ā", "á", "ă", "à", "a"},
			{"ē", "é", "ĕ", "è", "e"},
			{"ī", "í", "ĭ", "ì", "i"},
			{"ō", "ó", "ŏ", "ò", "o"},
			{"ū", "ú", "ŭ", "ù", "u"},
			{"ǖ", "ǘ", "ǚ", "ǜ", "ü"},
			{"m̄", "ḿ", "m̌", "m̀", "m"},
			{"n̄", "ń", "ň", "ǹ", "n"},
		};

		@Override
		public String format(String pinyin) {
			if (!patternPinyin.matcher(pinyin).matches()) { // bad format
				return pinyin;
			}
			if (!patternPinyinWithToneNumber.matcher(pinyin).matches()) { // input string has no any tune number
				return pinyin.replace('v', 'ü'); // only replace v with ü (umlaut) character
			}

			char unmarked        = '\u0000';
			int  indexOfUnmarked = -1;

			int tuneNumber = Character.getNumericValue(pinyin.charAt(pinyin.length() - 1));

			for (int i = 0; i < pinyin.length(); ++i) {
				char c = pinyin.charAt(i);
				if (c == 'a' || c == 'e' || c == 'o') {
					indexOfUnmarked = i;
					unmarked        = c;
					break;
				}
			}
			if (indexOfUnmarked == -1) {
				for (int i = pinyin.length() - 1; i >= 0; --i) {
					char c = pinyin.charAt(i);
					if (c == 'i' || c == 'u' || c == 'v') {
						indexOfUnmarked = i;
						unmarked        = c;
						break;
					}
				}
			}

			if (indexOfUnmarked == -1) {
				char c = pinyin.charAt(0);
				if (c == 'n' || c == 'm') {
					indexOfUnmarked = 0;
					unmarked        = c;
				}
			}

			if (indexOfUnmarked != -1) {
				int markedIndex;
				switch (unmarked) {
					case 'a':
						markedIndex = 0;
						break;
					case 'e':
						markedIndex = 1;
						break;
					case 'i':
						markedIndex = 2;
						break;
					case 'o':
						markedIndex = 3;
						break;
					case 'u':
						markedIndex = 4;
						break;
					case 'v':
						markedIndex = 5;
						break;
					case 'm':
						markedIndex = 6;
						break;
					case 'n':
						markedIndex = 7;
						break;
					default:
						throw new IllegalStateException("unreachable");
				}
				String marked = markedSymbols[markedIndex][tuneNumber - 1];

				return pinyin.substring(0, indexOfUnmarked).replace('v', 'ü')
					+ marked
					+ pinyin.substring(indexOfUnmarked + 1, pinyin.length() - 1).replace('v', 'ü');
			}

			return pinyin;
		}
	},

	/**
	 * 使用调号（tone number）：汉 -> han4
	 */
	TONE_NUMBER {
		@Override
		public String format(String pinyin) {
			return pinyin;
		}
	},

	/**
	 * 无声调表示：汉 -> han
	 */
	NONE {
		private final Pattern replacePattern = Pattern.compile("[1-5]");

		@Override
		public String format(String pinyin) {
			return replacePattern.matcher(pinyin).replaceAll("");
		}
	},
	;

	public abstract String format(String pinyin);
}
