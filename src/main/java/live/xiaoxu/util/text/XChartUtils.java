package live.xiaoxu.util.text;

/**
 * <p>字符工具</p>
 *
 * @author 小徐
 * @since 2023/9/25 17:58
 */
public class XChartUtils {
    public static final char UNDERLINE = '_';

    private XChartUtils() {
        throw new IllegalAccessError();
    }

    /**
     * 驼峰转下划线
     *
     * @param param    源数据
     * @param charType 大小写区分 1-小写;2-大写;
     * @return String
     */
    public static String camelToUnderline(String param, Integer charType) {
        if (XStringUtils.isBlank(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
            }
            // 统一都转大写
            if (charType == 2) {

                sb.append(Character.toUpperCase(c));
            }
            // 统一都转小写
            else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }
}