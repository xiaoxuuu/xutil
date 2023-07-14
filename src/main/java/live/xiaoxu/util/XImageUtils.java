package live.xiaoxu.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

/**
 * <p>图片工具类</p>
 *
 * @author 小徐
 * @since 2023/7/7 14:24
 */
public class XImageUtils {

    /**
     * 禁止实例化
     */
    private XImageUtils() {
        throw new IllegalAccessError(this.getClass().getName());
    }

    /**
     * 将图片流编码为 Base64
     *
     * @param imageUrl 图片 URL
     * @return 编码后图片
     * @throws Exception 异常
     */
    public static String encodeToBase64(String imageUrl) throws Exception {

        URL url = new URL(imageUrl);
        InputStream inputStream = url.openStream();
        byte[] bytes = toByteArray(inputStream);
        inputStream.close();
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 流转数组
     *
     * @param inputStream 流
     * @return 数组
     * @throws Exception 异常
     */
    private static byte[] toByteArray(InputStream inputStream) throws Exception {

        byte[] buffer = new byte[1024];
        int len;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream.toByteArray();
        }
    }
}