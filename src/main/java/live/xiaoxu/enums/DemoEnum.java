package live.xiaoxu.enums;

/**
 * 枚举示例
 *
 * @author 小徐
 * @since 2022/12/9 13:46
 */
public enum DemoEnum implements EnumInterface<Long> {

    /**
     * 示例枚举
     */
    ONE(1L, "一"),
    TWO(2L, "二"),
    ;

    private final Long code;
    private final String introduction;

    DemoEnum(Long code, String name) {
        this.code = code;
        this.introduction = name;
    }

    @Override
    public Long getCode() {
        return code;
    }

    @Override
    public String getIntroduction() {
        return introduction;
    }
}