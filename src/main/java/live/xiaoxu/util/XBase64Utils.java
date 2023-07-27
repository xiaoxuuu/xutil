package live.xiaoxu.util;

import java.util.regex.Pattern;

/**
 * <p>Base64 工具类</p>
 *
 * @author 小徐
 * @since 2023/7/7 14:24
 */
public class XBase64Utils {

    private static final Pattern FILE_SUFFIX_PATTERN = Pattern.compile("(?<=/)(?<fileSuffix>\\w.*?)(?=;)");

    /**
     * 禁止实例化
     */
    private XBase64Utils() {
        throw new IllegalAccessError(this.getClass().getName());
    }
}