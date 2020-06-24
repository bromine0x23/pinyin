package cn.bromine0x23.pinyin;

import cn.bromine0x23.pinyin.format.PinyinCaseFormat;
import cn.bromine0x23.pinyin.format.PinyinToneFormat;
import cn.bromine0x23.pinyin.format.PinyinVFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PinyinFormatTest {

	@Test
	void with() {
		PinyinFormat format = PinyinFormat.of();

		format = format.withCaseFormat(PinyinCaseFormat.UPPER_CASE);
		assertEquals(PinyinCaseFormat.UPPER_CASE, format.getCaseFormat());

		format = format.withToneFormat(PinyinToneFormat.TONE_MARK);
		assertEquals(PinyinToneFormat.TONE_MARK, format.getToneFormat());

		format = format.withVFormat(PinyinVFormat.Ü);
		assertEquals(PinyinVFormat.Ü, format.getVFormat());
	}

	@Test
	void withSame() {
		PinyinFormat format = PinyinFormat.of();
		PinyinFormat newFormat;

		newFormat = format.withCaseFormat(format.getCaseFormat());
		assertSame(format, newFormat);
		assertEquals(format.getCaseFormat(), newFormat.getCaseFormat());

		newFormat = format.withToneFormat(format.getToneFormat());
		assertSame(format, newFormat);
		assertEquals(format.getToneFormat(), newFormat.getToneFormat());

		newFormat = format.withVFormat(format.getVFormat());
		assertSame(format, newFormat);
		assertEquals(format.getVFormat(), newFormat.getVFormat());
	}

	@Test
	void withNull() {
		PinyinFormat format = PinyinFormat.of();

		assertThrows(NullPointerException.class, () -> format.withCaseFormat(null));
		assertThrows(NullPointerException.class, () -> format.withToneFormat(null));
		assertThrows(NullPointerException.class, () -> format.withVFormat(null));
	}

	@Test
	void ofDefault() {
		PinyinFormat format = PinyinFormat.of();
		assertEquals(PinyinCaseFormat.LOWER_CASE, format.getCaseFormat());
		assertEquals(PinyinToneFormat.TONE_NUMBER, format.getToneFormat());
		assertEquals(PinyinVFormat.V, format.getVFormat());
	}

	@Test
	void ofCase() {
		PinyinFormat format = PinyinFormat.of(PinyinCaseFormat.UPPER_CASE);
		assertEquals(PinyinCaseFormat.UPPER_CASE, format.getCaseFormat());
		assertEquals(PinyinToneFormat.TONE_NUMBER, format.getToneFormat());
		assertEquals(PinyinVFormat.V, format.getVFormat());
	}

	@Test
	void ofTone() {
		PinyinFormat format = PinyinFormat.of(PinyinToneFormat.NONE);
		assertEquals(PinyinCaseFormat.LOWER_CASE, format.getCaseFormat());
		assertEquals(PinyinToneFormat.NONE, format.getToneFormat());
		assertEquals(PinyinVFormat.V, format.getVFormat());
	}

	@Test
	void ofV() {
		PinyinFormat format = PinyinFormat.of(PinyinVFormat.Ü);
		assertEquals(PinyinCaseFormat.LOWER_CASE, format.getCaseFormat());
		assertEquals(PinyinToneFormat.TONE_NUMBER, format.getToneFormat());
		assertEquals(PinyinVFormat.Ü, format.getVFormat());
	}

	@Test
	void ofCaseTone() {
		PinyinFormat format = PinyinFormat.of(PinyinCaseFormat.TITLE_CASE, PinyinToneFormat.NONE);
		assertEquals(PinyinCaseFormat.TITLE_CASE, format.getCaseFormat());
		assertEquals(PinyinToneFormat.NONE, format.getToneFormat());
		assertEquals(PinyinVFormat.V, format.getVFormat());
	}

	@Test
	void ofCaseV() {
		PinyinFormat format = PinyinFormat.of(PinyinCaseFormat.TITLE_CASE, PinyinVFormat.Ü);
		assertEquals(PinyinCaseFormat.TITLE_CASE, format.getCaseFormat());
		assertEquals(PinyinToneFormat.TONE_NUMBER, format.getToneFormat());
		assertEquals(PinyinVFormat.Ü, format.getVFormat());
	}

	@Test
	void ofToneV() {
		PinyinFormat format = PinyinFormat.of(PinyinToneFormat.TONE_MARK, PinyinVFormat.Ü);
		assertEquals(PinyinCaseFormat.LOWER_CASE, format.getCaseFormat());
		assertEquals(PinyinToneFormat.TONE_MARK, format.getToneFormat());
		assertEquals(PinyinVFormat.Ü, format.getVFormat());
	}

	@Test
	void formatError() {
		PinyinFormat format = PinyinFormat.of(PinyinToneFormat.TONE_MARK, PinyinVFormat.V);
		assertThrows(IllegalArgumentException.class, () -> format.format("han4"));
	}
}