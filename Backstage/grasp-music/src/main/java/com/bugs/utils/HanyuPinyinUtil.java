package com.bugs.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.stereotype.Component;

/**
 * 汉语转换成拼音类
 */
@Component
public class HanyuPinyinUtil {

    /**
     * 获取拼音字符串
     * 如果字符是拼音或者数字则不进行转换
     *
     * @param chCharacters 汉字
     */
    private String getPinyinString(String chCharacters) {
        char[] clChars = chCharacters.trim().toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (int i = 0; i < clChars.length; i++) {
                String str = String.valueOf(clChars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    pinyin.append(PinyinHelper.toHanyuPinyinStringArray(clChars[i], defaultFormat)[0]);
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    pinyin.append(clChars[i]);
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    pinyin.append(clChars[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            //字符不能转成汉语拼音
        }
        return pinyin.toString();
    }
    /**
     * 获取歌手姓名所对应的拼音的的第一个字母
     */
    public Character getFirstCharByName(String sinName) {
        return getPinyinString(sinName).toUpperCase().substring(0, 1).charAt(0);
    }

}