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
     * @throws IOException
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

    public static File base64ToFile(String base64Image) throws IOException {

        base64Image = XBase64Utils.fixBase64Image(base64Image);
        File tempFile = File.createTempFile(String.valueOf(UUID.randomUUID()), XBase64Utils.getFileSuffix(base64Image, "jpeg"));
        byte[] bytes = XBase64Utils.decodeBase64Image(base64Image);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        inputStreamToFile(inputStream, tempFile);
        return tempFile;
    }
}