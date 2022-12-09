package live.xiaoxu.util;

import live.xiaoxu.enums.EnumInterface;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>枚举常用工具类</p>
 * <p>使用该枚举工具类需要指定的枚举实现 {@link EnumInterface} 接口</p>
 *
 * @author 小徐
 * @since 2022/4/8 14:24
 */
public final class XEnumUtils {

    /**
     * <p>查找枚举</p>
     * <p>会根据枚举的 name、code 以及枚举的名称进行搜寻，满足上述任意条件即会认为判断成功</p>
     * <p>会返回找到的第一个枚举</p>
     * <p>不满足则抛出异常{@link java.util.NoSuchElementException NoSuchElementException}</p>
     *
     * @param value 待查找枚举值
     * @param enums 待查找枚举枚举数组
     * @return 查找到的枚举
     */
    public static <T> EnumInterface<T> getByValue(Object value, EnumInterface<T>[] enums) {

        Objects.requireNonNull(value);
        Objects.requireNonNull(enums);

        String valueString = String.valueOf(value);
        return Arrays.stream(enums)
                .filter(e -> Objects.equals(valueString, e.getName()) ||
                        Objects.equals(valueString, String.valueOf(e.getCode())) ||
                        Objects.equals(valueString, e.toString()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("未找到指定枚举"));
    }

    /**
     * 判断枚举是否存在，拓展自方法 {@link XEnumUtils#getByValue getByValue}
     *
     * @param value 待查找枚举值
     * @param enums 待查找枚举枚举数组
     * @return 存在返回 true
     */
    public static <T> boolean containsByValue(Object value, EnumInterface<T>[] enums) {


        try {
            getByValue(value, enums);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * <p>查找枚举</p>
     * <p>会根据枚举的 name、code 以及枚举的名称进行搜寻，满足上述任意条件即会认为判断成功</p>
     * <p>会返回找到的第一个枚举</p>
     * <p>不满足则抛出异常{@link java.util.NoSuchElementException NoSuchElementException}</p>
     *
     * @param value     待查找枚举值
     * @param enumClass 枚举 class
     * @return 查找到的枚举
     */
    public static <E extends Enum<E> & EnumInterface<V>, V> E getByClass(Object value, Class<E> enumClass) {

        Objects.requireNonNull(value);
        Objects.requireNonNull(enumClass);

        String valueString = String.valueOf(value);
        EnumSet<E> all = EnumSet.allOf(enumClass);
        return all.stream()
                .filter(e -> Objects.equals(valueString, e.getName()) ||
                        Objects.equals(valueString, String.valueOf(e.getCode())) ||
                        Objects.equals(valueString, e.toString()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("未找到指定枚举"));
    }

    /**
     * 判断枚举是否存在，拓展自方法 {@link XEnumUtils#getByClass getByClass}
     *
     * @param enumClass 枚举类
     * @param value     枚举值
     * @param <E>       枚举类型
     * @param <V>       值类型
     * @return true：存在
     */
    public static <E extends Enum<E> & EnumInterface<V>, V> boolean containsByClass(Object value, Class<E> enumClass) {

        try {
            getByClass(value, enumClass);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}