package live.xiaoxu.util.bean;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class XFieldUtilsTest {

    @Test
    void getField() throws IllegalAccessException {

        User user = new User();
        user.setName("123");
        Field field = XFieldUtils.getField(User::getName);
        Object o = field.get(user);
        System.out.println(o);
    }

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
        user.setName("123");
        Field field = XFieldUtils.getField(User::getName);
        Object o = field.get(user);
        System.out.println(o);
    }

    public static class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}