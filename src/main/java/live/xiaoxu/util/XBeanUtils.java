package live.xiaoxu.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
     * <p>判断指定 class 是否实现指定 interface</p>
     *
     * @param clazz          待判断类
     * @param interfaceClass 待判断 interface
     * @return 实现 true
     */
    public static boolean implementsInterface(Class<?> clazz, Class<?> interfaceClass) {

        for (Class<?> anInterface : clazz.getInterfaces()) {
            if (anInterface.equals(interfaceClass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>递归读取所有字段，继承的类也可读取</p>
     *
     * @param clazz 类
     * @return 包含的字段
     */
    public static List<Field> getAllField(Class<?> clazz) {

        List<Field> fieldList = new ArrayList<>();
        if (null == clazz) {
            return fieldList;
        }
        fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        List<Field> superClassFieldList = getAllField(clazz.getSuperclass());
        fieldList.addAll(superClassFieldList);
        return fieldList;
    }

    /**
     * 获取字段
     *
     * @param clazz     类
     * @param fieldName 字段名
     * @param <T>       泛型
     * @return 字段方法
     */
    public static <T> Field getField(Class<T> clazz, String fieldName) {

        for (Field field : getAllField(clazz)) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    /**
     * <p>根据属性名获取属性值</p>
     * <p>本质是调用 get 方法，故如果没有 get 方法则无法获取</p>
     *
     * @param fieldName 属性名字
     * @param o         对象
     * @return 属性值
     */
    public static Object getFieldValueByFieldName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <p>设值，可根据类型进行自动转换</p>
     *
     * @param o     待设置类
     * @param value 值
     * @throws NoSuchFieldException   无此字段异常
     * @throws IllegalAccessException 请求权限错误异常
     */
    public static void setValue(Object o, String value) throws NoSuchFieldException, IllegalAccessException {

        Class<?> clazz = o.getClass();
        List<Field> fs = getAllField(clazz);

        for (Field f : fs) {
            if (Modifier.isFinal(f.getModifiers())) {
                // 被 final 修饰，跳过
                continue;
            }
            // 获取属性名
            String name = f.getName();
            String type = f.getType().getName();
            if (null != getFieldValueByFieldName(name, o)) {
                // 有属性值，跳过
                continue;
            }
            Field d = clazz.getDeclaredField(name);
            d.setAccessible(true);
            switch (type) {
                case "byte":
                case "java.lang.Byte":
                    d.set(o, Byte.parseByte(value));
                    break;
                case "short":
                case "java.lang.Short":
                    d.set(o, Short.parseShort(value));
                    break;
                case "int":
                case "java.lang.Integer":
                    d.set(o, Integer.parseInt(value));
                    break;
                case "long":
                case "java.lang.Long":
                    d.set(o, Long.parseLong(value));
                    break;
                case "float":
                case "java.lang.Float":
                    d.set(o, Float.parseFloat(value));
                    break;
                case "double":
                case "java.lang.Double":
                    d.set(o, Double.parseDouble(value));
                    break;
                case "java.lang.String":
                    d.set(o, value);
                    break;
                case "java.math.BigDecimal":
                    d.set(o, new BigDecimal(value));
                    break;
                case "java.util.concurrent.atomic.AtomicInteger":
                    d.set(o, new AtomicInteger(Integer.parseInt(value)));
                    break;
                default:
                    break;
            }
        }
    }
}