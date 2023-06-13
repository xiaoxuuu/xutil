package live.xiaoxu.util.random;

import live.xiaoxu.util.XDateUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>随机日期</p>
 *
 * @author 小徐
 * @since 2023/4/28 10:45
 */
public class XRandomDate {

    /**
     * 最小时间
     */
    private LocalDateTime left;

    /**
     * 最大时间
     */
    private LocalDateTime right;

    /**
     * 禁止实例化
     */
    private XRandomDate() {
        throw new IllegalAccessError("XRandomDate.class");
    }

    /**
     * <p>初始化</p>
     * <p>left：1000-01-01 00:00:00</p>
     * <p>right：9999:12:31 23:59:59</p>
     *
     * @return this
     */
    public static XRandomDate init() {
        return new XRandomDate()
                .setLeft(LocalDateTime.of(1000, 1, 1, 0, 0, 0))
                .setRight(LocalDateTime.of(9999, 12, 31, 23, 59, 59));
    }

    /**
     * 设置起始时间
     *
     * @param left 起始时间
     * @return this
     */
    public XRandomDate setLeft(LocalDateTime left) {

        this.left = left;
        return this;
    }

    /**
     * 设置结束时间
     *
     * @param right 结束时间
     * @return this
     */
    public XRandomDate setRight(LocalDateTime right) {

        this.right = right;
        return this;
    }

    /**
     * 获取一个随机时间
     *
     * @return 获取的 LocalDateTime
     */
    public LocalDateTime get() {

        long seconds = Duration.between(left, right).getSeconds();
        long randomSeconds = ThreadLocalRandom.current().nextLong(seconds + 1);
        LocalDateTime smaller = XDateUtils.moreThan(left, right) ? right : left;
        return smaller.plusSeconds(randomSeconds);
    }
}