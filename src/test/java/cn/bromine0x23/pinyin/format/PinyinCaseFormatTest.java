package cn.bromine0x23.pinyin.format;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PinyinCaseFormatTest {

	@Test
	void lowercase() {
		String pinyin = PinyinCaseFormat.LOWER_CASE.format("han4");

		assertEquals("han4", pinyin);
	}

	@Test
	void uppercase() {
		String pinyin = PinyinCaseFormat.UPPER_CASE.format("han4");

		assertEquals("HAN4", pinyin);
	}

	@Test
	void capitalcase() {
		String pinyin = PinyinCaseFormat.TITLE_CASE.format("han4");

		assertEquals("Han4", pinyin);
	}
}