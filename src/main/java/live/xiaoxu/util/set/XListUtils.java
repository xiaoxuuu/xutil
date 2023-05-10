package live.xiaoxu.util.set;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XListUtils {

    private XListUtils() {
        throw new IllegalAccessError("XListUtils.class");
    }

    /**
     * <p>集合判空，null 或集合 size 为 0 都返回 true</p>
     *
     * @param coll 带判断集合
     * @return null 或集合 size 为 0 都返回 true
     */
    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * <p>集合判非空，null 或集合 size 为 0 都返回 false</p>
     *
     * @param coll 带判断集合
     * @return null 或集合 size 为 0 都返回 false
     */
    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * <p>将传入的 List 按照给定的 index 拆分成 2 个子 List</p>
     * <p>例如：{@code list = [1, 2, 3, 4, 5], index = 3 ===>>> [[1, 2, 3],[4, 5]]}</p>
     * <p>例如：{@code list = [1, 2, 3, 4, 5]  , index = 2 ===>>> [[1, 2], [3, 4, 5]]}</p>
     * <p>例如：{@code list = [1, 2, 3, 4, 5]  , index <= 0 ===>>> [[1, 2, 3, 4, 5]]}</p>
     *
     * @param list  待拆分集合
     * @param index 拆分为几个集合
     * @param <T>   类型
     * @return 拆分结果
     */
    public static <T> List<List<T>> subList(List<T> list, Integer index) {

        if (0 == index) {
            return new ArrayList<>();
        }
        List<List<T>> lists = new ArrayList<>();
        if (list.size() <= index || index < 0) {
            lists.add(list);
            return lists;
        }
        List<T> listLeft = new ArrayList<>();
        List<T> listRight = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < index) {
                listLeft.add(list.get(i));
            } else {
                listRight.add(list.get(i));
            }
        }
        lists.add(listLeft);
        lists.add(listRight);
        return lists;
    }

    /**
     * TODO
     *
     * @param listList
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> transpose(List<List<T>> listList) {

        List<List<T>> newListList = new ArrayList<>();
        for (List<T> list : listList) {
            for (int j = 0; j < list.size(); j++) {
                T t = list.get(j);
                if (newListList.size() <= j) {
                    List<T> newList = new ArrayList<>();
                    newList.add(t);
                    newListList.add(newList);
                } else {
                    List<T> newList = newListList.get(j);
                    newList.add(t);
                    newListList.set(j, newList);
                }
            }
        }
        return newListList;
    }

    /**
     * TODO
     *
     * @param t
     * @param num
     * @param <T>
     * @return
     */
    public static <T> Collection<T> addMultiple(T t, int num) {

        if (num < 0) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(t);
        }
        return list;
    }

    /**
     * <p>将传入的 List 按照给定的 size 拆分成多个子 List</p>
     * <p>例如：{@code list = [], per = ? ===>>> []}</p>
     * <p>例如：{@code list = [1, 2, 3, 4, 5], per <= 0 ===>>> [[1, 2, 3, 4, 5]]}</p>
     * <p>例如：{@code list = [1, 2, 3, 4, 5], per = 1  ===>>> [[1], [2], [3], [4], [5]]}</p>
     * <p>例如：{@code list = [1, 2, 3, 4, 5], per = 2  ===>>> [[1, 2], [3, 4], [5]]}</p>
     * <p>例如：{@code list = [1, 2, 3, 4, 5], per = 3  ===>>> [[1, 2, 3],[4, 5]]}</p>
     * <p>例如：{@code list = [1, 2, 3, 4, 5], per >= 5  ===>>> [[1, 2, 3, 4, 5]]}</p>
     *
     * @param list 待分割数据
     * @param per  每个集合大小
     * @param <T>  类型
     * @return 分割结果
     */
    public static <T> List<List<T>> splitList(List<T> list, int per) {

        List<List<T>> returnList = new ArrayList<>();
        if (isEmpty(list)) {
            return returnList;
        }
        if (per <= 0) {
            returnList.add(list);
            return returnList;
        }
        int count = list.size() / per;
        int yu = list.size() % per;
        for (int i = 0; i <= count; i++) {
            List<T> subList;
            if (i == count) {
                subList = list.subList(i * per, i * per + yu);
            } else {
                subList = list.subList(i * per, per * (i + 1));
            }
            if (isEmpty(subList)) {
                continue;
            }
            returnList.add(subList);
        }
        return returnList;
    }

    public static <T> List<T> page(List<T> list, long current, long size) {

        return page(list, (int) current, (int) size);
    }

    public static <T> List<T> page(List<T> list, int current, int size) {

        if (current < 0 || size < 0) {
            return new ArrayList<>();
        }
        List<List<T>> lists = splitList(list, size);
        if (lists.size() < current) {
            return new ArrayList<>();
        }
        return lists.get(current - 1);
    }


    public static <T> boolean match(Collection<T> left, Collection<T> right) {

        List<T> leftList = new LinkedList<>(left);
        List<T> rightList = new LinkedList<>(right);
        if (leftList.size() != rightList.size()) {
            return false;
        }
        Iterator<T> leftIterator = leftList.iterator();
        while (leftIterator.hasNext()) {
            T next = leftIterator.next();
            if (!rightList.contains(next)) {
                return false;
            }
            leftIterator.remove();
            rightList.remove(next);
        }
        return 0 == rightList.size();
    }

    /**
     * 数组转集合
     *
     * @param array 数组
     * @param <T>   泛型
     * @return 集合
     */
    public static <T> List<T> create(T[] array) {

        return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * 取出集合指定位置数据
     *
     * @param list   原集合
     * @param index  指定位置
     * @param length 取出个数
     * @param <T>    泛型
     * @return 结果
     */
    public static <T> List<T> subList(List<T> list, int index, int length) {

        if (list.size() == 0 || index < 0 || length < 0) {
            return new ArrayList<>();
        }
        List<T> returnList = new ArrayList<>(length);
        if (list.size() < index || list.size() < length) {
            return returnList;
        }

        for (int i = 0; i < length; i++) {
            returnList.add(list.get(index + i));
        }
        return returnList;
    }

    /**
     * <p>集合相减</p>
     * <p>集合 A - 集合 B</p>
     *
     * @param source      集合 A
     * @param destination 集合 B
     * @param <T>         泛型
     * @return 结果
     */
    public static <T> List<T> reduce(List<T> source, List<T> destination) {

        List<T> result = new ArrayList<>();
        Set<T> destinationSet = new HashSet<>(destination);
        for (T t : source) {
            if (!destinationSet.contains(t)) {
                result.add(t);
            }
        }
        return result;
    }

    @SafeVarargs
    public static <T> List<T> concat(List<T>... lists) {

        List<T> list = new ArrayList<>();
        for (List<T> tList : lists) {
            if (isEmpty(tList)) {
                continue;
            }
            list.addAll(tList);
        }
        return list;
    }

    /**
     * 将 List 转换为数组
     *
     * @param list  list
     * @param array 目标数组
     * @param <T>   类型
     * @return 结果{@code String[]}
     */
    public static <T> T[] convertToArray(List<T> list, T[] array) {

        if (isEmpty(list)) {
            return array;
        }
        return list.toArray(array);
    }

    /**
     * 取两集合交集
     *
     * @param left  集合1
     * @param right 集合2
     * @param <T>   数据类型
     * @return 相交数据
     */
    public static <T> List<T> intersection(List<T> left, List<T> right) {

        List<T> list = new ArrayList<>();
        if (left == null || right == null || left.size() == 0 || right.size() == 0) {
            return list;
        }
        HashSet<T> set = new HashSet<>(left);
        right.forEach(k -> {
            if (set.contains(k)) {
                list.add(k);
            }
        });
        return list;
    }

    /**
     * <p>将一个集合按照某种规则重新排序 TODO 如果 ruleList 数据不完整可能导致数据丢失</p>
     * <p>例如：User(int id)</p>
     * <p>{@code List<User> userList = Arrays.asList(new User(2), new User(3), new User(1));}</p>
     * <p>{@code List<Integer> idList = Arrays.asList(3, 2, 1);}</p>
     * <p>{@code List<User> sortedList = resort(userList, idList, User::getId);}</p>
     * <p>{@code sortedList：User[3], User[2], User[1]}</p>
     *
     * @param source       待排序集合
     * @param sortRuleList 排序柜子
     * @param keyMapper    待排序集合所依赖数据
     * @param <T>          泛型
     * @param <K>          泛型，泛型 T 的一个属性
     * @return 排序后集合
     */
    public static <T, K> List<T> resort(List<T> source, List<K> sortRuleList, Function<? super T, ? extends K> keyMapper) {

        List<T> result = new ArrayList<>(source.size());
        Map<? extends K, T> collect = source.stream().collect(Collectors.toMap(keyMapper, a -> a));
        for (K k : sortRuleList) {
            result.add(collect.get(k));
        }
        return result;
    }
}