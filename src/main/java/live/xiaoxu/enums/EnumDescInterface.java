package live.xiaoxu.enums;

/**
 * <p><font color="#FF9000">枚举描述接口</font></p>
 * <p>实现此接口即可在 {@link ApiModelEnumProperty ApiModelEnumProperty} 中丰富文档描述<p>
 *
 * @author 小徐
 * @since 2022/12/30 10:39
 */
public interface EnumDescInterface {

    /**
     * 增强 api 描述
     */
    String enhanceApiDesc();

    default String enhanceApiDesc(String name, String desc) {
        return name + "(" + desc + ")";
    }
}