package live.xiaoxu.util;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <p>异常捕获</p>
 *
 * @author 小徐
 * @since 2023/6/29 14:16
 */
public class XCatch<T> {

    /**
     * 消费者
     */
    private final Supplier<T> supplier;

    /**
     * 禁止空参实例化
     */
    private XCatch() {
        throw new IllegalAccessError(this.getClass().getName());
    }

    private XCatch(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    /**
     * 构造，传递待捕获代码段
     *
     * @param supplier 待捕获代码段
     * @param <T>      代码执行结果类型
     * @return 代码执行结果
     */
    public static <T> XCatch<T> of(Supplier<T> supplier) {

        Objects.requireNonNull(supplier);
        return new XCatch<>(supplier);
    }

    /**
     * 执行代码，如果出现异常，执行 supplierException
     *
     * @param supplierException 出现异常后的处理代码
     * @return 结果
     */
    public T handle(Supplier<T> supplierException) {
        try {
            return supplier.get();
        } catch (Exception e) {
            e.printStackTrace();
            return supplierException.get();
        }
    }
}