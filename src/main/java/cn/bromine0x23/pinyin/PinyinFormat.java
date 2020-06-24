package cn.bromine0x23.pinyin;

import cn.bromine0x23.pinyin.format.PinyinCaseFormat;
import cn.bromine0x23.pinyin.format.PinyinToneFormat;
import cn.bromine0x23.pinyin.format.PinyinVFormat;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

/**
 * 拼音格式
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 * @see PinyinCaseFormat
 * @see PinyinToneFormat
 * @see PinyinVFormat
 */
@With
@Value(staticConstructor = "of")
public class PinyinFormat {

	private static final PinyinCaseFormat DEFAULT_CASE_FORMAT = PinyinCaseFormat.LOWER_CASE;
	private static final PinyinToneFormat DEFAULT_TONE_FORMAT = PinyinToneFormat.TONE_NUMBER;
	private static final PinyinVFormat    DEFAULT_V_FORMAT    = PinyinVFormat.V;

	/**
	 * 大小写格式
	 */
	@Getter
	@NonNull
	PinyinCaseFormat caseFormat;

	/**
	 * 声调格式
	 */
	@Getter
	@NonNull
	PinyinToneFormat toneFormat;

	/**
	 * ü 格式
	 */
	@Getter
	@NonNull
	PinyinVFormat vFormat;

	public static PinyinFormat of() {
		return of(DEFAULT_CASE_FORMAT, DEFAULT_TONE_FORMAT, DEFAULT_V_FORMAT);
	}

	public static PinyinFormat of(PinyinCaseFormat caseFormat) {
		return of(caseFormat, DEFAULT_TONE_FORMAT, DEFAULT_V_FORMAT);
	}

	public static PinyinFormat of(PinyinToneFormat toneFormat) {
		return of(DEFAULT_CASE_FORMAT, toneFormat, DEFAULT_V_FORMAT);
	}

	public static PinyinFormat of(PinyinVFormat vFormat) {
		return of(DEFAULT_CASE_FORMAT, DEFAULT_TONE_FORMAT, vFormat);
	}

	public static PinyinFormat of(PinyinCaseFormat caseFormat, PinyinToneFormat toneFormat) {
		return of(caseFormat, toneFormat, DEFAULT_V_FORMAT);
	}

	public static PinyinFormat of(PinyinCaseFormat caseFormat, PinyinVFormat vFormat) {
		return of(caseFormat, DEFAULT_TONE_FORMAT, vFormat);
	}

	public static PinyinFormat of(PinyinToneFormat toneFormat, PinyinVFormat vFormat) {
		return of(DEFAULT_CASE_FORMAT, toneFormat, vFormat);
	}

	public String format(String pinyin) {
		if ((PinyinToneFormat.TONE_MARK == getToneFormat()) && PinyinVFormat.V == getVFormat()) {
			throw new IllegalArgumentException("Tone marks cannot be added to v.");
		}

		pinyin = getToneFormat().format(pinyin);
		pinyin = getVFormat().format(pinyin);
		pinyin = getCaseFormat().format(pinyin);

		return pinyin;
	}
}
