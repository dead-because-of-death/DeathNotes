package kojima.genius.deathnotes.controllers;

import kojima.genius.deathnotes.entities.Dictionary;
import kojima.genius.deathnotes.entities.User;
import kojima.genius.deathnotes.repositories.DictionaryRepository;
import kojima.genius.deathnotes.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DictionaryMaintainingController {

    DictionaryRepository dictionaryRep;

    UserRepository userRep;

    public DictionaryMaintainingController(DictionaryRepository dictionaryRep, UserRepository userRep) {
        this.dictionaryRep = dictionaryRep;
        this.userRep = userRep;
    }

    @PostMapping("/dictionary/{index}/delete")
    public String deleteDictionary(@PathVariable String index, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = userRep.findByUsername( (String) session.getAttribute("user"));
        Dictionary neededDictionary = user.getDictionaries().get(new Integer(index));
        user.getDictionaries().remove(neededDictionary);
        userRep.save(user);
        dictionaryRep.delete(neededDictionary);
        return "redirect:/";
    }

}
