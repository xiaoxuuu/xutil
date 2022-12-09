package live.xiaoxu.util;

/**
 * 字符串工具类
 * <p>
 * 2022/11/9 15:04
 *
 * @author XiaoXu
 */
public class XStringUtils {

    /**
     * 禁止实例化
     */
    private XStringUtils() {
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 参数
     * @return 字符串返回 true
     */
    public static boolean isBlank(String str) {

        return null == str || str.trim().length() == 0;
    }
}