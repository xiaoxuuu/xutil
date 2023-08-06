package live.xiaoxu.util;

import java.io.*;
import java.util.UUID;

/**
 * <p>文件工具类</p>
 *
 * @author 小徐
 * @since 2023/7/20 13:59
 */
public class XFileUtil {

    /**
     * 禁止实例化
     */
    private XFileUtil() {
        throw new IllegalAccessError(this.getClass().getName());
    }

    /**
     * 流转文件
     *
     * @param ins  流
     * @param file 文件
     * @throws IOException 异常
     */
    public static void inputStreamToFile(InputStream ins, File file) throws IOException {

        OutputStream os = new FileOutputStream(file);
        int bytesRead;
        byte[] buffer = new byte[1024];
        while ((bytesRead = ins.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

    /**
     * base64 转文件
     *
     * @param base64 字符串
     * @return 文件
     * @throws IOException 异常
     */
    public static File base64ToFile(String base64) throws IOException {

        File tempFile = File.createTempFile(String.valueOf(UUID.randomUUID()), XBase64Utils.getFileSuffix(base64));
        byte[] bytes = XBase64Utils.decodeBase64Image(base64);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        inputStreamToFile(inputStream, tempFile);
        return tempFile;
    }
}