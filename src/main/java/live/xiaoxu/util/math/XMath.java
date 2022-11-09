package live.xiaoxu.util.math;

import live.xiaoxu.util.XString;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数学计算工具类：提供加减乘除、平均值、最大值、最小值计算
 * <p>
 * 2022/11/9 10:19
 *
 * @author XiaoXu
 */
public class XMath {

    /**
     * 默认的除法精确度
     */
    private static final int DEF_DIV_SCALE = 20;

    private XMath() {
    }

    /**
     * 精确加法运算，计算多个数值总和，null 会被视为 0
     *
     * @param valList 被加数集合
     * @return 两个参数的和(String)
     */
    public static BigDecimal sum(Object... valList) {

        BigDecimal b = BigDecimal.ZERO;
        if (null == valList || valList.length == 0) {
            return b;
        }
        List<Object> list = tranObjToList(valList);
        if (list.size() == 0) {
            return BigDecimal.ZERO;
        }
        for (Object val : list) {
            b = b.add(tranObjectToBigDecimal(val));
        }
        return b;
    }

    /**
     * 精确减法运算，null 会被视为 0
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差(String)
     */
    public static BigDecimal subtract(Object v1, Object v2) {

        if (null == v2) {
            throw new RuntimeException("除数不能为 0");
        }
        return tranObjectToBigDecimal(v1).subtract(tranObjectToBigDecimal(v2));
    }

    /**
     * 精确乘法运算，计算多个数值的积，null 会被视为 0
     *
     * @param valList 乘数
     * @return 两个参数的积(String)
     */
    public static BigDecimal multiply(Object... valList) {

        BigDecimal b1 = BigDecimal.ZERO;
        if (null == valList || valList.length == 0) {
            return b1;
        }
        b1 = BigDecimal.ONE;
        List<Object> list = tranObjToList(valList);
        if (list.size() == 0) {
            return BigDecimal.ZERO;
        }
        for (Object val : list) {
            b1 = b1.multiply(tranObjectToBigDecimal(val));
        }
        return b1;
    }

    /**
     * 次方
     *
     * @param o   原始数据
     * @param num 大于等于 0
     * @return 结果
     */
    public static BigDecimal power(Object o, int num) {

        if (0 == num) {
            return BigDecimal.ONE;
        }
        BigDecimal original = tranObjectToBigDecimal(o);
        if (1 == num) {
            return original;
        }
        BigDecimal resultValue = tranObjectToBigDecimal(o);
        for (int i = 1; i < num; i++) {
            resultValue = multiply(resultValue, original);
        }
        return resultValue;
    }

    /**
     * 标准差σ=sqrt(s^2)
     * 结果精度：scale
     * 牛顿迭代法求大数开方
     *
     * @param value 原数据
     * @return 结果
     */
    public static BigDecimal sqrt(BigDecimal value) {
        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        return deviation.setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * （相对）精确除法运算。当发生除不尽情况时，由 scale 参数指 定精度，以后数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位
     * @return 两个参数的商(BigDecimal)
     */
    private static BigDecimal divide(BigDecimal v1, BigDecimal v2, Integer scale) {

        if (null == v1) {
            return BigDecimal.ZERO;
        }
        if (null == v2) {
            v2 = BigDecimal.ONE;
        }
        if (v2.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("除数不能为 0");
        }
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于 0");
        }
        return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * （相对）精确除法运算。当发生除不尽情况时，精确到小数点以后 2 位，以后数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商(String)
     */
    public static BigDecimal divide(Object v1, Object v2) {

        return divide(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * （相对）精确除法运算。当发生除不尽情况时，由 scale 参数指 定精度，以后数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位
     * @return 两个参数的商(String)
     */
    public static BigDecimal divide(Object v1, Object v2, Integer scale) {

        return divide(tranObjectToBigDecimal(v1), tranObjectToBigDecimal(v2), scale);
    }

    /**
     * 平均数
     *
     * @param valList 参数
     * @return 结果
     */
    public static BigDecimal avg(Object... valList) {

        if (null == valList || valList.length == 0) {
            return BigDecimal.ZERO;
        }
        List<Object> list = tranObjToList(valList);
        if (list.size() == 0) {
            return BigDecimal.ZERO;
        }
        return divide(sum(valList), list.size());
    }

    /**
     * 最大值
     *
     * @param valList 参数
     * @return 结果
     */
    public static BigDecimal max(Object... valList) {

        BigDecimal max = BigDecimal.ZERO;
        if (null == valList || valList.length == 0) {
            return max;
        }
        List<Object> list = tranObjToList(valList);
        if (list.size() == 0) {
            return BigDecimal.ZERO;
        }
        max = tranObjectToBigDecimal(list.get(0));
        for (Object val : list) {
            if (null == val) {
                continue;
            }
            BigDecimal temp = tranObjectToBigDecimal(val);
            if (temp.compareTo(max) > 0) {
                max = temp;
            }
        }
        return max;
    }

    /**
     * 最小值
     *
     * @param valList 参数
     * @return 结果
     */
    public static BigDecimal min(Object... valList) {

        BigDecimal min = BigDecimal.ZERO;
        if (null == valList || valList.length == 0) {
            return min;
        }
        List<Object> list = tranObjToList(valList);
        if (list.size() == 0) {
            return BigDecimal.ZERO;
        }
        min = tranObjectToBigDecimal(list.get(0));
        for (Object val : list) {
            if (null == val) {
                continue;
            }
            BigDecimal temp = tranObjectToBigDecimal(val);
            if (temp.compareTo(min) < 0) {
                min = temp;
            }
        }
        return min;
    }

    /**
     * 比较大小
     *
     * @param o1 参数1
     * @param o2 参数2
     * @return o1 大于 o2 返回 true
     */
    public static boolean moreThan(Object o1, Object o2) {

        return tranObjectToBigDecimal(o1).compareTo(tranObjectToBigDecimal(o2)) > 0;
    }

    /**
     * 比较大小
     *
     * @param o1 参数1
     * @param o2 参数2
     * @return o1 小于 o2 返回 true
     */
    public static boolean lessThan(Object o1, Object o2) {

        return tranObjectToBigDecimal(o1).compareTo(tranObjectToBigDecimal(o2)) < 0;
    }

    /**
     * 比较大小
     *
     * @param o1 参数1
     * @param o2 参数2
     * @return o1 等于 o2 返回 true
     */
    public static boolean equal(Object o1, Object o2) {

        return tranObjectToBigDecimal(o1).compareTo(tranObjectToBigDecimal(o2)) == 0;
    }

    /**
     * 将不同类型数字转为字符串
     *
     * @param o                 数字
     * @param newScale          保留小数位数
     * @param roundingMode      四舍五入规则
     * @param alwaysKeepDecimal 是否严格保留小数位数，0 展示为 0.00
     * @return 格式化结果
     */

    public static String format(Object o, int newScale, int roundingMode, boolean alwaysKeepDecimal) {

        if (null == o) {
            return null;
        }
        BigDecimal bigDecimal = tranObjectToBigDecimal(o);
        return alwaysKeepDecimal ?
                bigDecimal.setScale(newScale, roundingMode).toPlainString() :
                bigDecimal.setScale(newScale, roundingMode).stripTrailingZeros().toPlainString();
    }

    /**
     * 将不同类型数字转为字符串，默认保留 2 位小数
     *
     * @param o                 数字
     * @param newScale          是否取整，true 小数舍去，false 小数
     * @param alwaysKeepDecimal 是否严格保留小数位数，0 展示为 0.00
     * @return 格式化结果
     */
    public static String format(Object o, int newScale, boolean alwaysKeepDecimal) {

        if (null == o) {
            return null;
        }
        return format(o, newScale, BigDecimal.ROUND_HALF_UP, alwaysKeepDecimal);
    }

    /**
     * 将不同类型数字转为字符串，当指定位数后，0 会处理为 0.00
     *
     * @param o        数字
     * @param newScale 是否取整，true 小数舍去，false 小数
     * @return 结果
     */
    public static String format(Object o, int newScale) {

        return format(o, newScale, true);
    }

    /**
     * 将不同类型数字转为字符串，默认保留 2 位小数
     *
     * @param o 数字
     * @return 结果
     */
    public static String format(Object o) {

        if (null == o) {
            return null;
        }
        return format(o, 2, false);
    }

    /**
     * 将不同类型数字转为字符串，直接输出。null 输出为 0
     *
     * @param o 数字
     * @return 结果
     */
    public static String toString(Object o) {

        if (o == null) {
            return "0";
        }
        return tranObjectToBigDecimal(o).toPlainString();
    }

    /**
     * 将 obj 转为 BigDecimal，null 默认返回 null
     *
     * @param o 参数
     * @return 结果
     */
    public static BigDecimal toBigDecimal(Object o) {

        return tranObjectToBigDecimal(o);
    }

    /**
     * 将常见数字自动转换为 {@link BigDecimal}
     *
     * @param o 参数
     * @return 结果
     */
    private static BigDecimal tranObjectToBigDecimal(Object o) {

        BigDecimal b = null;
        if (null == o) {
            return b;
        }
        if (o instanceof String) {
            b = XString.isBlank((String) o) ? b : new BigDecimal((String) o);
        } else if (o instanceof BigDecimal) {
            b = (BigDecimal) o;
        } else if (o instanceof Float) {
            b = BigDecimal.valueOf((Float) o);
        } else if (o instanceof Double) {
            /*
              此方法内部其实执行了 {@link java.lang.Double} 的 {@link java.lang.Double#toString(double) toString()}
              而 {@link java.lang.Double} 的 {@link java.lang.Double#toString(double) toString()} 按 double 的实际能表达的精度对尾数进行了截断。
             */
            b = BigDecimal.valueOf((Double) o);
        } else if (o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long) {
            b = new BigDecimal(o.toString());
        } else if (o instanceof AtomicInteger) {
            b = new BigDecimal(String.valueOf(((AtomicInteger) o).get()));
        } else {
            throw new RuntimeException("数据转换时遇到暂不支持的数据类型");
        }
        return b;
    }

    /**
     * 将集合拆分转换
     *
     * @param valList 参数
     * @return 结果
     */
    private static List<Object> tranObjToList(Object... valList) {

        List<Object> list = new ArrayList<>();
        if (null == valList) {
            return list;
        }
        for (Object val : valList) {
            // TODO 支持 Set 等 Collection
            if (val instanceof List) {
                List<Object> valObjList = (List) val;
                list.addAll(valObjList);
            } else {
                list.add(val);
            }
        }
        return list;
    }
}