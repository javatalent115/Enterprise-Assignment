package main.java;
import main.java.entity.Drug;
import main.java.entity.Producers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model){
        List list = Handler.getAllDrugs();
        String key ="";
        model.addAttribute("message", Integer.toString(list.size()));
        for (Object o : list) {
            Producers drug = (Producers) o;
            key += drug.toString() + "~";
        }
        model.addAttribute("key", key);
        return "Json";
    }
}
