package live.xiaoxu.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数学计算工具类 - 链式编程
 *
 * @author XiaoXu
 * @since 2022/11/9 10:29
 */
public class XMathChain {

    /**
     * 计算过程的缓存数据
     */
    private BigDecimal value;

    /**
     * 禁止空参构造
     */
    private XMathChain() {
    }

    private XMathChain(Object val) {

        value = XMath.toBigDecimal(val);
    }

    /**
     * 加法，内部调用{@link XMath#sum(Object...)}
     *
     * @param valList 被加数集合
     * @return 两个参数的和(String)
     */
    public XMathChain sum(Object... valList) {

        this.value = XMath.sum(value, valList);
        return this;
    }

    /**
     * 减法，内部调用{@link XMath#subtract(Object, Object)}
     *
     * @param v1 减数
     * @return 两个参数的差(String)
     */
    public XMathChain subtract(Object v1) {

        this.value = XMath.subtract(value, v1);
        return this;
    }

    /**
     * 乘法，内部调用{@link XMath#multiply(Object...)}
     *
     * @param valList 乘数
     * @return 两个参数的积(String)
     */
    public XMathChain multiply(Object... valList) {

        this.value = XMath.multiply(value, valList);
        return this;
    }

    /**
     * 除法，内部调用{@link XMath#divide(Object, Object, Integer)}
     *
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位
     * @return 两个参数的商(BigDecimal)
     */
    private XMathChain divide(BigDecimal v2, Integer scale) {

        this.value = XMath.multiply(value, v2, scale);
        return this;
    }

    /**
     * 除法，内部调用{@link XMath#divide(Object, Object)}
     *
     * @param v2 除数
     * @return 两个参数的商(String)
     */
    public XMathChain divide(Object v2) {

        this.value = XMath.divide(value, v2);
        return this;
    }


    /**
     * 次方，内部调用{@link XMath#power(Object, int)}
     *
     * @param num 次方根，大于等于 0
     * @return 结果
     */
    public XMathChain power(int num) {

        this.value = XMath.power(value, num);
        return this;
    }

    /**
     * 开方（牛顿迭代法），内部调用{@link XMath#sqrt(Object)}
     *
     * @return 结果
     */
    public XMathChain sqrt() {

        this.value = XMath.sqrt(value);
        return this;
    }

    /**
     * 比较大小，内部调用{@link XMath#moreThan(Object, Object)}
     *
     * @param o2 参数2
     * @return value 大于 o2 返回 true
     */
    public boolean moreThan(Object o2) {

        return XMath.moreThan(value, o2);
    }

    /**
     * 比较大小，内部调用{@link XMath#lessThan(Object, Object)}
     *
     * @param o2 参数2
     * @return o1 小于 o2 返回 true
     */
    public boolean lessThan(Object o2) {

        return XMath.lessThan(value, o2);
    }

    /**
     * 比较大小，内部调用{@link XMath#equal(Object, Object)}
     *
     * @param o2 参数2
     * @return o1 等于 o2 返回 true
     */
    public boolean equal(Object o2) {

        return XMath.equal(value, o2);
    }

    /**
     * 格式化为字符串，内部调用{@link XMath#toString(Object, int, RoundingMode, boolean)}
     *
     * @param newScale          保留小数位数
     * @param roundingMode      四舍五入规则
     * @param alwaysKeepDecimal 是否严格保留小数位数，0 展示为 0.00
     * @return 格式化结果
     */
    public String toString(int newScale, RoundingMode roundingMode, boolean alwaysKeepDecimal) {

        return XMath.toString(value, newScale, roundingMode, alwaysKeepDecimal);
    }

    /**
     * 格式化为字符串，内部调用{@link XMath#toString(Object, int, boolean)}
     *
     * @param newScale          保留小数位数
     * @param alwaysKeepDecimal 是否严格保留小数位数，0 展示为 0.00
     * @return 格式化结果
     */
    public String toString(int newScale, boolean alwaysKeepDecimal) {

        return XMath.toString(value, newScale, alwaysKeepDecimal);
    }

    /**
     * 格式化为字符串，内部调用{@link XMath#toString(Object, int)}
     *
     * @param newScale 保留小数位数
     * @return 结果
     */
    public String toString(int newScale) {

        return XMath.toString(value, newScale);
    }

    /**
     * 格式化为字符串，内部调用{@link XMath#toString(Object)}
     *
     * @return 结果
     */
    public String format() {

        return XMath.toString(value);
    }

    /**
     * FIXME 将不同类型数字转为数字，直接输出。null 输出为 0
     *
     * @return 结果
     */
    public Integer toInteger() {

        return XMath.toInteger(value);
    }

    /**
     * 转为 BigDecimal，内部调用{@link XMath#toBigDecimal(Object)}
     *
     * @return 结果
     */
    public BigDecimal toBigDecimal() {

        return XMath.toBigDecimal(value);
    }
}