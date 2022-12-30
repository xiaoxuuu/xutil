package live.xiaoxu.util;

/**
 * bean工具
 * </p>
 *
 * @author Matt
 * @date 2021/9/3 21:42
 */
public final class XBeanUtils {

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