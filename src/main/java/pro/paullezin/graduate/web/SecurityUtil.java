package pro.paullezin.graduate.web;

import pro.paullezin.graduate.model.AbstractBaseEntity;
import pro.paullezin.graduate.model.Role;
import pro.paullezin.graduate.model.User;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static boolean authUserIsAdmin(User user) {
        return user.getRoles().contains(Role.ADMIN);
    }

}
