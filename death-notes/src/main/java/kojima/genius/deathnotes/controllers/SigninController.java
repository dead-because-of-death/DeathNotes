package kojima.genius.deathnotes.controllers;

import kojima.genius.deathnotes.Role;
import kojima.genius.deathnotes.User;
import kojima.genius.deathnotes.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class SigninController {

    @Autowired
    UserRepository userRep;

    @GetMapping("/signin")
    public String getPage() {

        return "signin";
    }

    @PostMapping("/signin")
    public String signIn(User user, Map<String, Object> model) {
        User existedUser = userRep.findByUsername(user.getUsername());

        if(existedUser != null) {
            model.put("messsage", "This username is already used");
            return "signin";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRep.save(user);
        return "redirect:/";
    }
}
