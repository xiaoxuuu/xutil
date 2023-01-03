package live.xiaoxu.util;

/**
 * <p>bean 工具类</p>
 *
 * @author 小徐
 * @since 2023/1/3 11:26
 */
public final class XBeanUtils {

    /**
     * 禁止实例化
     */
    private XBeanUtils() {
    }

    /**
     * 判断指定 class 是否实现指定 interface
     *
     * @param clazz      待判断类
     * @param interfaces 待判断 interface
     * @return 实现 true
     */
    public static boolean implementsInterface(Class<?> clazz, Class<?> interfaces) {

        for (Class<?> anInterface : clazz.getInterfaces()) {
            if (anInterface.equals(interfaces)) {
                return true;
            }
        }
        return false;
    }
}