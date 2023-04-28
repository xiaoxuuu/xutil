package live.xiaoxu.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class XDateUtilsTest {

    @Test
    void moreThan() {
        Assertions.assertFalse(XDateUtils.moreThan(LocalDateTime.of(2000, 1, 1, 1, 1, 1), LocalDateTime.of(2000, 1, 1, 1, 1, 1)));
        Assertions.assertTrue(XDateUtils.moreThan(LocalDateTime.of(2000, 1, 1, 1, 1, 2), LocalDateTime.of(2000, 1, 1, 1, 1, 1)));
        Assertions.assertFalse(XDateUtils.moreThan(LocalDateTime.of(2000, 1, 1, 1, 1, 1), LocalDateTime.of(2000, 1, 1, 1, 1, 2)));
    }
}