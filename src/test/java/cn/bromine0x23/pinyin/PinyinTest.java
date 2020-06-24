package cn.bromine0x23.pinyin;

import cn.bromine0x23.pinyin.format.PinyinCaseFormat;
import cn.bromine0x23.pinyin.format.PinyinToneFormat;
import cn.bromine0x23.pinyin.format.PinyinVFormat;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.hasItemInArray;

class PinyinTest {

	@Test
	void toPinyins() {
		String[] pinyins = Pinyin.toPinyins('汉');
		assertThat(pinyins, hasItemInArray("han4"));
	}

	@Test
	void toFormattedPinyins() {
		PinyinFormat outputFormat = PinyinFormat.of(PinyinCaseFormat.LOWER_CASE, PinyinToneFormat.TONE_MARK, PinyinVFormat.Ü);
		String[]     pinyins      = Pinyin.toFormattedPinyins('汉', outputFormat);
		assertThat(pinyins, hasItemInArray("hàn"));
	}

	@Test
	void toPrimaryPinyins() {
		String[] pinyins = Pinyin.toPrimaryPinyins("汉语拼音");
		assertThat(pinyins, arrayContaining("han4", "yu3", "pin1", "yin1"));
	}

	@Test
	void toPrimaryPinyinsWithNoChineseCharacters() {
		String[] pinyins = Pinyin.toPrimaryPinyins("汉语拼音!");
		assertThat(pinyins, arrayContaining("han4", "yu3", "pin1", "yin1", null));
	}

	@Test
	void toFormattedPrimaryPinyins() {
		PinyinFormat outputFormat = PinyinFormat.of(PinyinCaseFormat.LOWER_CASE, PinyinToneFormat.NONE, PinyinVFormat.V);
		String[]     pinyins      = Pinyin.toFormattedPrimaryPinyins("汉语拼音", outputFormat);
		assertThat(pinyins, arrayContaining("han", "yu", "pin", "yin"));
	}

	@Test
	void toFormattedPrimaryPinyinsNoChineseCharacters() {
		PinyinFormat outputFormat = PinyinFormat.of(PinyinCaseFormat.LOWER_CASE, PinyinToneFormat.NONE, PinyinVFormat.V);
		String[]     pinyins      = Pinyin.toFormattedPrimaryPinyins("汉语拼音!", outputFormat);
		assertThat(pinyins, arrayContaining("han", "yu", "pin", "yin", null));
	}
}