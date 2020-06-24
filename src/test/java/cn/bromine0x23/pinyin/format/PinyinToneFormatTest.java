package cn.bromine0x23.pinyin.format;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PinyinToneFormatTest {

	@Test
	void toneMarkNormalA() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("han4");
		assertEquals("hàn", pinyin);
	}

	@Test
	void toneMarkNormalE() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("wen2");
		assertEquals("wén", pinyin);
	}

	@Test
	void toneMarkNormalI() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("zi4");
		assertEquals("zì", pinyin);
	}

	@Test
	void toneMarkNormalO() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("zhong1");
		assertEquals("zhōng", pinyin);
	}

	@Test
	void toneMarkNormalU() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("yu3");
		assertEquals("yŭ", pinyin);
	}

	@Test
	void toneMarkNormalV() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("lv4");
		assertEquals("lǜ", pinyin);
	}

	@Test
	void toneMarkNormalM() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("m2");
		assertEquals("ḿ", pinyin);
	}

	@Test
	void toneMarkNormalN() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("n2");
		assertEquals("ń", pinyin);
	}

	@Test
	void toneMarkNotPinyin() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("12345");

		assertEquals("12345", pinyin);
	}

	@Test
	void toneMarkNoneTone() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("e");

		assertEquals("e", pinyin);
	}

	@Test
	void toneMarkError() {
		String pinyin = PinyinToneFormat.TONE_MARK.format("b1");

		assertEquals("b1", pinyin);
	}

	@Test
	void toneNumber() {
		String pinyin = PinyinToneFormat.TONE_NUMBER.format("han4");

		assertEquals("han4", pinyin);
	}

	@Test
	void none() {
		String pinyin = PinyinToneFormat.NONE.format("han4");

		assertEquals("han", pinyin);
	}

}