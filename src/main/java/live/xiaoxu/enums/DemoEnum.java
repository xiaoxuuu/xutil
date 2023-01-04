package live.xiaoxu.enums;

/**
 * 枚举示例
 *
 * @author 小徐
 * @since 2022/12/9 13:46
 */
public enum DemoEnum implements EnumInterface<Long>, EnumDescInterface {

    /**
     * 示例枚举
     */
    ONE(1L, "一", "数字 1"),
    TWO(2L, "二", "数字 2"),
    ;

    private final Long code;
    private final String introduction;
    private final String desc;

    DemoEnum(Long code, String introduction, String desc) {
        this.code = code;
        this.introduction = introduction;
        this.desc = desc;
    }

    @Override
    public Long getCode() {
        return code;
    }

    @Override
    public String getIntroduction() {
        return introduction;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String enhanceApiDesc() {
        return enhanceApiDesc(name(), introduction);
    }
}