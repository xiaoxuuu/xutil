package live.xiaoxu.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>条件执行器，可根据某条件的真假执行</p>
 *
 * @author 小徐
 * @since 2023/6/26 16:21
 */
public class XCondition<T> {

    /**
     * 待操作数据
     */
    private final T t;

    /**
     * 操作
     */
    private final Function<T, Boolean> function;

    /**
     * 默认构造
     *
     * @param t        数据
     * @param function 操作
     */
    private XCondition(T t, Function<T, Boolean> function) {
        this.t = t;
        this.function = function;
    }

    /**
     * 禁止实例化
     */
    private XCondition() {
        throw new IllegalAccessError("XBeanUtils.class");
    }

    /**
     * 构建条件执行器
     *
     * @param t        待操作数据
     * @param function 操作
     * @param <T>      数据类型
     * @return 条件执行器
     */
    public static <T> XCondition<T> of(T t, Function<T, Boolean> function) {

        Objects.requireNonNull(t);
        Objects.requireNonNull(function);
        return new XCondition<T>(t, function);
    }

    /**
     * 获取操作结果
     *
     * @return true false
     */
    public Boolean get() {
        return function.apply(t);
    }

    /**
     * 当操作结果为 true 执行 consumer
     *
     * @param consumer 待执行 consumer
     */
    public void handle(Consumer<T> consumer) {
        if (function.apply(t)) {
            consumer.accept(t);
        }
    }

    /**
     * 根据操作结果执行 consumer
     *
     * @param consumerTrue  操作结果为 true 执行
     * @param consumerFalse 操作结果为 false 执行
     */
    public void handle(Consumer<T> consumerTrue, Consumer<T> consumerFalse) {
        if (function.apply(t)) {
            consumerTrue.accept(t);
        } else {
            consumerFalse.accept(t);
        }
    }
}