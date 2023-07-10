package live.xiaoxu.util;

import live.xiaoxu.util.set.XListUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class XListUtilsTest {

    @Test
    void subList() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Assertions.assertEquals(XListUtils.splitList(list, 1).size(), 8);
        Assertions.assertEquals(XListUtils.splitList(list, 2).size(), 4);
        Assertions.assertEquals(XListUtils.splitList(list, 3).size(), 3);
        Assertions.assertEquals(XListUtils.splitList(list, 4).size(), 2);
        Assertions.assertEquals(XListUtils.splitList(list, 5).size(), 2);
        Assertions.assertEquals(XListUtils.splitList(list, 6).size(), 2);
        Assertions.assertEquals(XListUtils.splitList(list, 7).size(), 2);
        Assertions.assertEquals(XListUtils.splitList(list, 8).size(), 1);
        Assertions.assertEquals(XListUtils.splitList(list, 9).size(), 1);
    }
}