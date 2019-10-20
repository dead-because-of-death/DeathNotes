package kojima.genius.deathnotes.controllers;

import kojima.genius.deathnotes.entities.User;
import kojima.genius.deathnotes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AddFriendController {

    @Autowired
    UserRepository userRep;

    @GetMapping("/addfriend")
    String getPage(User user, HttpServletRequest request, Model userData) {
        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute("user") != null) {
            user = userRep.findByUsername( (String) session.getAttribute("user"));
            userData.addAttribute("username", user.getUsername());
            userData.addAttribute("logged", true);
        }else {
            userData.addAttribute("logged", false);
        }

        return "addfriend";
    }

    @PostMapping("/addfriend")
    String addFriend(User user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User friend = userRep.findByUsername(user.getUsername());
        User currentUser = userRep.findByUsername( (String) session.getAttribute("user"));

        if ( (friend != null) && (friend.getUsername() != currentUser.getUsername()) && (!currentUser.getFriends().contains(friend)) ) {
            currentUser.getFriends().add(friend);
            userRep.save(currentUser);
        }else {
            return "redirect:/addfriend?error";
        }

        return "redirect:/";
    }
}
