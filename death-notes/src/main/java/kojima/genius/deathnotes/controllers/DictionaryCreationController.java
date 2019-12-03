package kojima.genius.deathnotes.controllers;

import kojima.genius.deathnotes.entities.Dictionary;
import kojima.genius.deathnotes.entities.Pair;
import kojima.genius.deathnotes.entities.User;
import kojima.genius.deathnotes.repositories.DictionaryRepository;
import kojima.genius.deathnotes.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DictionaryCreationController {

    DictionaryRepository dictionaryRep;

    UserRepository userRep;

    DictionaryCreationController(DictionaryRepository dictionaryRep, UserRepository userRep) {
        this.dictionaryRep = dictionaryRep;
        this.userRep = userRep;
    }

    @GetMapping("/createdictionary")
    String getPage(HttpServletRequest request, Model userData) {
        HttpSession session = request.getSession(false);
        User user;

        if(session != null && session.getAttribute("user") != null) {
            user = userRep.findByUsername( (String) session.getAttribute("user"));
            userData.addAttribute("username", user.getUsername());
            userData.addAttribute("logged", true);
        }else {
            userData.addAttribute("logged", false);
        }

        return "create-dictionary";
    }

    @PostMapping("/createdictionary")
    String writeDictionary(Dictionary dictionary, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = userRep.findByUsername( (String) session.getAttribute("user"));
        user.getDictionaries().add(dictionary);
        dictionary.setUser(user);
        userRep.save(user);
        return "redirect:/";
    }
}
