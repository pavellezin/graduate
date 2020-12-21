package pro.paullezin.graduate.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.paullezin.graduate.model.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController extends AbstractUserController{

    @Override
    @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        return super.create(user);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable int id) {
        super.update(user, id);
    }

    @Override
    @GetMapping("/by")
    public User getByMail(@RequestParam String email) {
        return super.getByMail(email);
    }
}
