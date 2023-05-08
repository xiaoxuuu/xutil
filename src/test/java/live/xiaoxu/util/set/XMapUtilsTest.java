package live.xiaoxu.util.set;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class XMapUtilsTest {

    @Test
    void plus() {

        Map<String, Collection<String>> map1 = new HashMap<>();
        map1.put("1", List.of("1", "2"));
        map1.put("2", List.of("1"));

        Map<String, Collection<String>> map2 = new HashMap<>();
        map2.put("1", List.of("3"));
        map2.put("5", List.of("1"));

        Map<String, Collection<String>> plus = XMapUtils.plus(map1, map2);
        Collection<String> collection1 = plus.get("1");
    }
}