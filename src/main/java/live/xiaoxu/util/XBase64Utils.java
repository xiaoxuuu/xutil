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

    /**
     * 获取文件后缀正则表达式
     */
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
     * @param base64 字符串
     * @return byte 数组
     */
    public static byte[] decodeBase64(String base64) {

        String[] parts = base64.split(",");
        String base64String = parts[1];
        return Base64.getDecoder().decode(base64String);
    }

    /**
     * 获取文件后缀，默认返回空
     *
     * @param base64 文件
     * @return 获取的后缀
     */
    public static String getFileSuffix(String base64) {

        return getFileSuffix(base64, "");
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

    /**
     * 补全 base64 中的后缀
     *
     * @param base64 文件
     * @return 补全后的数据
     */
    public static String fixBase64Image(String base64) {

        return fixBase64("data:image/jpeg;base64,", base64);
    }

    /**
     * 补全 base64 中的后缀
     *
     * @param prefix 前缀
     * @param file   文件
     * @return 补全后的数据
     */
    public static String fixBase64(String prefix, String file) {

        if (XStringUtils.isBlank(prefix)) {
            throw new RuntimeException("base64 前缀数据为空");
        }
        if (XStringUtils.isBlank(file)) {
            throw new RuntimeException("base64 数据为空");
        }
        if (!file.startsWith("data:")) {
            file = prefix + file;
        }
        return file;
    }

    /**
     * base64 数组转字符串
     *
     * @param bytes 数组
     * @return 字符串
     */
    public static String byteToBase64(byte[] bytes) {

        return Base64.getEncoder().encodeToString(bytes);
    }
}