package live.xiaoxu.util;

import live.xiaoxu.util.text.XStringUtils;

import java.util.Base64;
import java.util.regex.Matcher;
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

    /**
     * String 类型转 byte 数组
     *
     * @param base64Image 字符串
     * @return byte 数组
     */
    public static byte[] decodeBase64Image(String base64Image) {

        String[] parts = base64Image.split(",");
        String imageString = parts[1];
        return Base64.getDecoder().decode(imageString);
    }

    /**
     * 获取 base64 文件类型
     *
     * @param base64        文件
     * @param defaultSuffix 默认后缀
     * @return 文件类型
     */
    public static String getFileSuffix(String base64, String defaultSuffix) {

        if (XStringUtils.isBlank(base64)) {
            return defaultSuffix;
        }
        Matcher fileSuffixMatcher = FILE_SUFFIX_PATTERN.matcher(base64);
        if (!fileSuffixMatcher.find()) {
            return defaultSuffix;
        }
        return fileSuffixMatcher.group("fileSuffix");
    }

    public static String fixBase64Image(String base64Image) {

        return fixBase64("data:image/jpeg;base64,", base64Image);
    }

    public static String fixBase64(String prefix, String type) {

        if (XStringUtils.isBlank(prefix)) {
            throw new RuntimeException("base64 前缀数据为空");
        }
        if (XStringUtils.isBlank(type)) {
            throw new RuntimeException("base64 数据为空");
        }
        if (!type.startsWith("data:")) {
            type = prefix + type;
        }
        return type;
    }
}