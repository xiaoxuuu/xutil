package live.xiaoxu.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>map 工具类</p>
 *
 * @author 小徐
 * @since 2023/4/18 09:14
 */
public class XMapUtils {

    private XMapUtils() {
        throw new IllegalAccessError("MapUtils.class");
    }

    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return Map<String, Object>
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
}