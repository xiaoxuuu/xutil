package live.xiaoxu.util.random;

import org.junit.jupiter.api.Test;

class XRandomNameTest {

    @Test
    void get() {

        XRandomName.init().setUseSurname(false).setDesensitizationLength(1).getBatch(10).forEach(System.out::println);
    }
}