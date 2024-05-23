package Pack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
public class Tiger {
    @RequestMapping("/")
    public String func00(){
        System.out.println("index controller");
        return "index";
    }

    @RequestMapping("/t1")
    public String func01(){
        System.out.println("func01 call");
        return "TigerView";
    }

    @RequestMapping("/t2")
    public String func02(){
        System.out.println("func02 call");
        RestTemplate rt = new RestTemplate();
        rt.getForObject("http://localhost:8082/bpp/3000", String.class);

        return "redirect:/";
    }
}
