package live.xiaoxu.enums;

/**
 * <p>枚举功能拓展接口</p>
 * <p>实现此接口即可使用 {@link live.xiaoxu.util.XEnumUtils XEnumUtils} 中的方法</p>
 * <p>样例枚举请查看 {@link live.xiaoxu.enums.DemoEnum DemoEnum}</p>
 *
 * @author 小徐
 * @since 2022/12/9 13:45
 */
public interface EnumInterface<T> {

    /**
     * 枚举固定字段 code 的重写方法，
     * <p>
     * code 字段数据应为枚举唯一标识，且数据不应重复
     *
     * @return code 字段对应数据
     */
    T getCode();
}