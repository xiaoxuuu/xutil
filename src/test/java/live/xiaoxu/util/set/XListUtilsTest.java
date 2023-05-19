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

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> list1 = XListUtils.subList(list, 0, 1);
        Assertions.assertEquals(1, list1.get(0));

        List<Integer> list2 = XListUtils.subList(list, 1, 1);
        Assertions.assertEquals(2, list2.get(0));

        List<Integer> list3 = XListUtils.subList(list, 2, 1);
        Assertions.assertEquals(3, list3.get(0));

        List<Integer> list4 = XListUtils.subList(list, 2, 2);
        Assertions.assertEquals(3, list4.get(0));
        Assertions.assertEquals(4, list4.get(1));
    }

    @Test
    void transpose() {

        List<Integer> listA = List.of(1, 2, 3);
        List<Integer> listB = List.of(4, 5, 6);
        List<Integer> listC = List.of(7, 8, 9);
        List<List<Integer>> lists = List.of(listA, listB, listC);
        List<List<Integer>> transpose = XListUtils.transpose(lists);

        Assertions.assertEquals(1, transpose.get(0).get(0));
        Assertions.assertEquals(4, transpose.get(0).get(1));
        Assertions.assertEquals(7, transpose.get(0).get(2));
        Assertions.assertEquals(2, transpose.get(1).get(0));
        Assertions.assertEquals(5, transpose.get(1).get(1));
        Assertions.assertEquals(8, transpose.get(1).get(2));
        Assertions.assertEquals(3, transpose.get(2).get(0));
        Assertions.assertEquals(6, transpose.get(2).get(1));
        Assertions.assertEquals(9, transpose.get(2).get(2));
    }

    @Test
    void repet() {

        List<Integer> list = XListUtils.repet(1, 3);
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(1, list.get(1));
        Assertions.assertEquals(1, list.get(2));

        List<StringBuilder> repeatStringBuilder = XListUtils.repeat(StringBuilder::new, 5);
        List<String> repetString = XListUtils.repeat(String::new, 3);
        Assertions.assertEquals(5, repeatStringBuilder.size());
        Assertions.assertEquals(3, repetString.size());
        Assertions.assertEquals("", repetString.get(0));
        Assertions.assertEquals("", repetString.get(1));
        Assertions.assertEquals("", repetString.get(2));
    }

    @Test
    void splitList() {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

        List<List<Integer>> lists1 = XListUtils.splitList(list, 1);
        Assertions.assertEquals(6, lists1.size());

        List<List<Integer>> lists2 = XListUtils.splitList(list, 2);
        Assertions.assertEquals(3, lists2.size());

        List<List<Integer>> lists3 = XListUtils.splitList(list, 3);
        Assertions.assertEquals(2, lists3.size());

        List<List<Integer>> lists4 = XListUtils.splitList(list, 4);
        Assertions.assertEquals(2, lists4.size());

        List<List<Integer>> lists5 = XListUtils.splitList(list, 5);
        Assertions.assertEquals(2, lists5.size());

        List<List<Integer>> lists6 = XListUtils.splitList(list, 6);
        Assertions.assertEquals(1, lists6.size());

        List<List<Integer>> lists7 = XListUtils.splitList(list, 7);
        Assertions.assertEquals(1, lists7.size());
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