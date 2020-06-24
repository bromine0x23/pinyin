package cn.bromine0x23.pinyin;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.hasItemInArray;

class CodepointToPinyinsTest {

	@Test
	void pinyinsOf0() {
		String[] pinyins = CodepointToPinyins.pinyinsOf('中');
		assertThat(pinyins, arrayContainingInAnyOrder("zhong1", "zhong4"));
	}

	@Test
	void pinyinsOf1() {
		String[] pinyins = CodepointToPinyins.pinyinsOf('国');
		assertThat(pinyins, hasItemInArray("guo2"));
	}

	@Test
	void pinyinsOfEmpty() {
		String[] pinyins = CodepointToPinyins.pinyinsOf('ん');
		assertThat(pinyins, emptyArray());
	}
}