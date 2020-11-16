package pro.paullezin.graduate;

import pro.paullezin.graduate.model.Role;
import pro.paullezin.graduate.model.User;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered", "password");

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Role.USER);
    }
}
