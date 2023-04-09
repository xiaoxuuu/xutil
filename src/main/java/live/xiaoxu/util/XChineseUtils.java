package live.xiaoxu.util;

/**
 * <p>汉字处理工具类</p>
 *
 * @author 小徐
 * @since 2023/4/9 11:58
 */
public final class XChineseUtils {

    /**
     * 字母转数字  A-Z ：1-26
     */
    public static int letterToNumber(String letter) {

        int length = letter.length();
        int num;
        int number = 0;
        for (int i = 0; i < length; i++) {
            char ch = letter.charAt(length - i - 1);
            num = ch - 'A' + 1;
            num *= Math.pow(26, i);
            number += num;
        }
        return number;
    }
}
