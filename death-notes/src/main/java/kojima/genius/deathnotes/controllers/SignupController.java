package kojima.genius.deathnotes.controllers;

import kojima.genius.deathnotes.entities.Role;
import kojima.genius.deathnotes.entities.User;
import kojima.genius.deathnotes.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Controller
public class SignupController {

    @Autowired
    UserRepository userRep;

    @GetMapping("/signup")
    public String getPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(User user, Map<String, Object> model) {
        User existedUser = userRep.findByUsername(user.getUsername());

        if(existedUser != null) {
            model.put("messsage", "This username is already used");
            return "signup";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRep.save(user);
        return "redirect:/";
    }
}
