package live.xiaoxu.util.random;

import live.xiaoxu.util.XDateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

class XRandomDateTest {

    @Test
    void get() {

        Set<String> set = Set.of("2000-01-01 00:00:00", "2000-01-01 00:00:01");
        XRandomDate xRandomDate = XRandomDate.init()
                .setLeft(XDateUtils.cast("2000-01-01 00:00:00"))
                .setRight(XDateUtils.cast("2000-01-01 00:00:01"));
        for (int i = 0; i < 10; i++) {
            Assertions.assertTrue(set.contains(XDateUtils.localDateTimeToString(xRandomDate.get())));
        }
        LocalDateTime localDateTime = XRandomDate.init()
                .setLeft(XDateUtils.cast("2000-01-01 00:00:00"))
                .setRight(XDateUtils.cast("2000-01-01 00:00:00"))
                .get();
        Assertions.assertEquals("2000-01-01 00:00:00", XDateUtils.localDateTimeToString(localDateTime));
    }
}