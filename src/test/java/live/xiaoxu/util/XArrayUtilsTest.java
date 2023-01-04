package live.xiaoxu.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class XArrayUtilsTest {

    @Test
    void isEmpty() {

        // 空数组
        Assertions.assertTrue(XArrayUtils.isEmpty(new String[0]));
        Assertions.assertTrue(XArrayUtils.isEmpty(null));

        // 非空数组
        Assertions.assertFalse(XArrayUtils.isEmpty(new String[]{"1"}));
    }

    @Test
    void contains() {

        // 构建数据
        String[] array = {"1", "a", "A"};

        // 包含
        Assertions.assertTrue(XArrayUtils.contains(array, "a"));

        // 不包含
        Assertions.assertFalse(XArrayUtils.contains(array, "c"));
        Assertions.assertFalse(XArrayUtils.contains(array, null));
        Assertions.assertFalse(XArrayUtils.contains(array, ""));
        Assertions.assertFalse(XArrayUtils.contains(null, ""));
        Assertions.assertFalse(XArrayUtils.contains(new Object[0], ""));
    }
}