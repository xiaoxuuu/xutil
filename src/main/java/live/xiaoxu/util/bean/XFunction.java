package live.xiaoxu.util.bean;

import java.io.Serializable;

@FunctionalInterface
public interface XFunction<T, R> extends Serializable {
    R get(T source);
}