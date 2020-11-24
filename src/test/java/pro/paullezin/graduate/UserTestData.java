package pro.paullezin.graduate;

import pro.paullezin.graduate.model.Role;
import pro.paullezin.graduate.model.User;

import static pro.paullezin.graduate.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered", "password");

    public static final int USER_ID = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ + 2;

    public static final User USER = new User(USER_ID, "user2", "user2@paullezin.pro", "user2", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@paullezin.pro", "admin", Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Role.USER);
    }
}
