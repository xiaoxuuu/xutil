package live.xiaoxu.util;

import java.util.Objects;
import java.util.function.Supplier;

public class XCatch<T> {

    private final Supplier<T> supplier;

    private XCatch(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    private XCatch() {
        throw new IllegalArgumentException("错误的构造请求");
    }

    public static <T> XCatch<T> of(Supplier<T> supplier) {

        Objects.requireNonNull(supplier);
        return new XCatch<>(supplier);
    }

    public T handle(Supplier<T> supplierException) {
        try {
            return supplier.get();
        } catch (Exception e) {
            e.printStackTrace();
            return supplierException.get();
        }
    }
}