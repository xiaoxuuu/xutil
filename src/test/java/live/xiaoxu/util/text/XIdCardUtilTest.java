package live.xiaoxu.util.text;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class XIdCardUtilTest {

    @Test
    void isValidatedAllIdCard() {

        Assertions.assertTrue(XIdCardUtil.validate("110101199909090908"));
        Assertions.assertFalse(XIdCardUtil.validate("110101199909090909"));
        Assertions.assertFalse(XIdCardUtil.validate("121212121212121212"));
    }
}