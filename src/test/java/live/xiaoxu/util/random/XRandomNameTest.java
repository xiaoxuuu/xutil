package live.xiaoxu.util.random;

import org.junit.jupiter.api.Test;

class XRandomNameTest {

    @Test
    void get() {

        System.out.println(XRandomName.init().setUseSurname(false).setDesensitizationLength(1).get());
    }
}