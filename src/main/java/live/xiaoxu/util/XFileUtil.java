package live.xiaoxu.util;

import java.io.*;

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
}