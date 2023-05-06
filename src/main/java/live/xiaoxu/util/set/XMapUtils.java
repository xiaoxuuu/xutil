package live.xiaoxu.util.set;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * <p>map 工具类</p>
 *
 * @author 小徐
 * @since 2023/4/18 09:14
 */
public class XMapUtils {

    private XMapUtils() {
        throw new IllegalAccessError("XMapUtils.class");
    }

    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return {@code Map<String, Object>}
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Map转成实体对象
     *
     * @param map   map实体对象包含属性
     * @param clazz 实体对象类型
     * @return Object
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 递归读取所有字段
     *
     * @param clazz 待读取类
     * @param <T>   泛型
     * @return 所有字段集合
     */
    private static <T> List<Field> getAllFields(Class<? super T> clazz) {

        List<Field> fieldList = new ArrayList<>();
        if (null == clazz) {
            return fieldList;
        }
        fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        List<Field> superClassFieldList = getAllFields(clazz.getSuperclass());
        fieldList.addAll(superClassFieldList);
        return fieldList;
    }

    /**
     * 将两个 map 集合数据相加 TODO 报错
     *
     * @param map1 第一个 map
     * @param map2 第二个 map
     * @param <K>  map 的 key
     * @param <V>  map 的 value
     * @return 想加的结果
     */
    public static <K, V> Map<K, Collection<V>> plusMap(Map<K, Collection<V>> map1, Map<K, Collection<V>> map2) {

        Map<K, Collection<V>> finalMap = new HashMap<>(map1);
        map2.forEach((k, v) -> {
            if (!finalMap.containsKey(k)) {
                finalMap.put(k, v);
            } else {
                Collection<V> collection = finalMap.get(k);
                Set<V> setFinal = new HashSet<>(collection);
                setFinal.addAll(v);
                finalMap.put(k, setFinal);
            }
        });
        return finalMap;
    }
}