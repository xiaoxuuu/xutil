package live.xiaoxu.util.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class XListUtilsTest {

    @Test
    void isEmpty() {

        Assertions.assertTrue(XListUtils.isEmpty(null));
        Assertions.assertTrue(XListUtils.isEmpty(new ArrayList<>()));
        Assertions.assertTrue(XListUtils.isEmpty(new HashSet<>()));
        Assertions.assertFalse(XListUtils.isEmpty(List.of("")));
    }

    @Test
    void isNotEmpty() {

        Assertions.assertFalse(XListUtils.isNotEmpty(null));
        Assertions.assertFalse(XListUtils.isNotEmpty(new ArrayList<>()));
        Assertions.assertFalse(XListUtils.isNotEmpty(new HashSet<>()));
        Assertions.assertTrue(XListUtils.isNotEmpty(List.of("")));
    }

    @Test
    void divideList() {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<List<Integer>> lists0 = XListUtils.divideList(list, 0);
        // 验证长度
        Assertions.assertEquals(0, lists0.get(0).size());
        Assertions.assertEquals(9, lists0.get(1).size());

        List<List<Integer>> lists1 = XListUtils.divideList(list, 1);
        // 验证长度
        Assertions.assertEquals(1, lists1.get(0).size());
        Assertions.assertEquals(8, lists1.get(1).size());
        // 验证头尾数据
        Assertions.assertEquals(2, lists1.get(1).get(0));
        Assertions.assertEquals(9, lists1.get(1).get(7));

        List<List<Integer>> lists4 = XListUtils.divideList(list, 4);
        // 验证长度
        Assertions.assertEquals(4, lists4.get(0).size());
        Assertions.assertEquals(5, lists4.get(1).size());

        List<List<Integer>> lists10 = XListUtils.divideList(list, 10);
        // 验证长度
        Assertions.assertEquals(9, lists10.get(0).size());
        Assertions.assertEquals(0, lists10.get(1).size());
    }

    @Test
    void subList() {
    }

    @Test
    void transpose() {
    }

    @Test
    void addMultiple() {
    }

    @Test
    void splitList() {
    }

    @Test
    void page() {
    }

    @Test
    void testPage() {
    }

    @Test
    void match() {
    }

    @Test
    void create() {
    }

    @Test
    void testSubList() {
    }

    @Test
    void reduce() {
    }

    @Test
    void concat() {
    }

    @Test
    void convertToArray() {
    }

    @Test
    void intersection() {
    }

    @Test
    void resort() {
    }
}