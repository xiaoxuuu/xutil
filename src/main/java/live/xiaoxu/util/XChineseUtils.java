package live.xiaoxu.util;

/**
 * <p>汉字处理工具类</p>
 *
 * @author 小徐
 * @since 2023/4/9 11:58
 */
public final class XChineseUtils {

    /**
     * 数字转字母 1-26 ： A-Z
     */
    public static String numberToLetter(int num) {

        if (num <= 0) {
            return null;
        }
        StringBuilder letter = new StringBuilder();
        num--;
        do {
            if (letter.length() > 0) {
                num--;
            }
            letter.insert(0, ((char) (num % 26 + (int) 'A')));
            num = (num - num % 26) / 26;
        }
        while (num > 0);

        return letter.toString();
    }

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
