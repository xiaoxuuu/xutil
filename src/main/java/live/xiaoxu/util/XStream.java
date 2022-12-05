package live.xiaoxu.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>Stream 工具类</p>
 * <p>对 Stream 进行封装，简化代码</p>
 *
 * @author 小徐
 * @since 2022/12/5 14:04
 */
public class XStream {

    /**
     * 禁止实例化
     */
    private XStream() {
    }

    /**
     * <p>将继承自{@link java.util.Collection Collection}的集合经过一个操作转换为{@link java.util.List List}</p>
     * <p>源集合为 null 或者操作为 null 均默认返回{@link java.util.ArrayList ArrayList}</p>
     * <p>例如：List<String> userNameList = XStream.getList(userList, User::getUserName);</p>
     *
     * @param collection 源集合
     * @param operation  操作
     * @param <T>        源集合类型
     * @param <R>        取出的类型
     * @return 取出的结果
     */
    public static <T, R> List<R> getList(Collection<T> collection, Function<? super T, ? extends R> operation) {

        if (Objects.isNull(collection) || Objects.isNull(operation)) {
            return new ArrayList<>();
        }
        return collection.stream().map(operation).collect(Collectors.toList());
    }
}