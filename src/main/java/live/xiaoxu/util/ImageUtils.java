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
public class ImageUtils {

    public static String encodeToBase64(String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        InputStream inputStream = url.openStream();
        byte[] bytes = toByteArray(inputStream);
        inputStream.close();
        return Base64.getEncoder().encodeToString(bytes);
    }

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
