# [拼音实用工具](https://github.com/bromine0x23/pinyin)
当前版本：`0.0.1`

## 使用
使用 `cn.bromine0x23.pinyin.Pinyin` 工具类

```java
import cn.bromine0x23.pinyin.Pinyin;
import cn.bromine0x23.pinyin.PinyinFormat;
import cn.bromine0x23.pinyin.PinyinCaseFormat;
import cn.bromine0x23.pinyin.PinyinToneFormat;
import cn.bromine0x23.pinyin.PinyinVFormat;

public class Sample {
    public static void main(String[] arguments) {
        // 获取汉字读音的拼音
        Pinyin.toPinyins('中'); // ["zhong1", "zhong4"]
        Pinyin.toPinyins('绿'); // ["lv4"]

        // 获取汉字读音的拼音，指定输出格式
        Pinyin.toFormattedPinyins('中', PinyinFormat.of(PinyinCaseFormat.UPPER_CASE)); // ["ZHONG1", "ZHONG4"]
        Pinyin.toFormattedPinyins('中', PinyinFormat.of(PinyinToneFormat.TONE_MARK)); // ["zhōng", "zhòng"]
        Pinyin.toFormattedPinyins('中', PinyinFormat.of(PinyinCaseFormat.UPPER_CASE, PinyinToneFormat.TONE_MARK)); // ["ZHŌNG", "ZHÒNG"]
        Pinyin.toFormattedPinyins('绿', PinyinFormat.of(PinyinVFormat.Ü)); // ["lǜ"]

        // 获取文本汉字的拼音，选择记录中位于首位的常用读音
        Pinyin.toPrimaryPinyins("汉语拼音"); // ["han4", "yu3", "pin1", "yin1"]

        // 获取文本汉字的拼音，选择记录中位于首位的常用读音
        Pinyin.toFormattedPrimaryPinyins("汉语拼音", PinyinFormat.of(PinyinCaseFormat.UPPER_CASE, PinyinToneFormat.TONE_MARK, PinyinVFormat.Ü)); // ["HÀN", "YŬ", "PĪN", "YĪN"]
    }
}
```

## 数据
拼音数据 [CodepointToPinyins.properties](src\main\resources\cn\bromine0x23\pinyin\CodepointToPinyins.properties) 来源于 [mozillazg/pinyin-data](https://github.com/mozillazg/pinyin-data) 的 `pinyin.txt`，使用 [pinyin.rb](pinyin.rb) 生成 