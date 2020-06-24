package cn.bromine0x23.pinyin.format;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PinyinVFormatTest {

	@Test
	void v() {
		String pinyin = PinyinVFormat.V.format("lv4");

		assertEquals("lv4", pinyin);
	}

	@Test
	void Ü() {
		String pinyin = PinyinVFormat.Ü.format("lv4");

		assertEquals("lü4", pinyin);
	}
}