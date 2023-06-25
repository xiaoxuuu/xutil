package live.xiaoxu.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class XCondition<T> {

    private final T t;
    private final Function<T, Boolean> function;

    private XCondition(T t, Function<T, Boolean> function) {
        this.t = t;
        this.function = function;
    }

    private XCondition() {
        throw new IllegalArgumentException("错误的构造请求");
    }

    public static <T> XCondition<T> of(T t, Function<T, Boolean> function) {

        Objects.requireNonNull(t);
        Objects.requireNonNull(function);
        return new XCondition<T>(t, function);
    }

    public T orElse(T other) {
        return function.apply(t) ? t : other;
    }

    public void handle(Consumer<T> consumer) {
        if (function.apply(t)) {
            consumer.accept(t);
        }
    }
}