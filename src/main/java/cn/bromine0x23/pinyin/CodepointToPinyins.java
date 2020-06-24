package cn.bromine0x23.pinyin;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 存储汉字码点至拼音的映射
 * <p>
 * 考虑到汉字码点的连续性和性能优化，没有使用直接使用 {@link Map} 容器存储。
 * 拼音直接使用数组存储，查询时码点通过一个预先编制的索引数组转换为对应拼音数组的下标（当然这相当于手动）。
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@UtilityClass
public class CodepointToPinyins {

	private static final String  RESOURCE_FILENAME = "CodepointToPinyins.properties";
	private static final Storage STORAGE;

	static {
		try (InputStream is = CodepointToPinyins.class.getResourceAsStream(RESOURCE_FILENAME)) {
			Properties properties = new Properties();
			properties.load(is);
			STORAGE = Storage.build(new TreeMap<>(properties));
		} catch (IOException exception) {
			throw new IllegalStateException("Failed to initializing.", exception);
		}
	}

	public static String[] pinyinsOf(int codepoint) {
		String[] pinyins = STORAGE.find(codepoint);
		if (pinyins.length > 0) {
			pinyins = Arrays.copyOf(pinyins, pinyins.length);
		}
		return pinyins;
	}

	public static String[] pinyinsOf(char character) {
		return pinyinsOf((int) character);
	}

	private static class Storage {

		private static final String[] EMPTY       = {};
		private static final int      INDEX_SLOTS = 0x4000;

		private final String[][] pinyins;
		private final int[][]    indexes;

		private Storage(int count) {
			this.pinyins = new String[count][];
			this.indexes = new int[INDEX_SLOTS][];
		}

		private String[] find(int codepoint) {
			int   indexSlot = indexSlotOf(codepoint);
			int[] index     = indexes[indexSlot];
			if (index != null) {
				for (int i = 0; i < index.length; i += 2) {
					if (index[i] == codepoint) {
						return pinyins[index[i + 1]];
					}
				}
			}
			return EMPTY;
		}

		private static Storage build(SortedMap<Object, Object> data) {
			Storage storage      = new Storage(data.size());
			int     pinyinsIndex = 0;
			for (Map.Entry<Object, Object> entry : data.entrySet()) {
				int      codepoint = Integer.parseInt((String) entry.getKey(), 16);
				String[] pinyins   = ((String) entry.getValue()).split(",");

				storage.pinyins[pinyinsIndex] = pinyins;

				int   indexSlot = indexSlotOf(codepoint);
				int[] oldIndex  = storage.indexes[indexSlot];
				int[] newIndex;
				if (oldIndex == null) {
					newIndex = new int[]{codepoint, pinyinsIndex};
				} else {
					newIndex = new int[oldIndex.length + 2];
					System.arraycopy(oldIndex, 0, newIndex, 0, oldIndex.length);
					newIndex[oldIndex.length]     = codepoint;
					newIndex[oldIndex.length + 1] = pinyinsIndex;
				}
				storage.indexes[indexSlot] = newIndex;
				++pinyinsIndex;
			}
			return storage;
		}

		private static int indexSlotOf(int codepoint) {
			return codepoint % INDEX_SLOTS;
		}

	}
}
