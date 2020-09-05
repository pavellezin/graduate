package pro.paullezin.graduate.web.user;

import org.springframework.stereotype.Controller;
import pro.paullezin.graduate.model.User;

import static pro.paullezin.graduate.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController{

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}
