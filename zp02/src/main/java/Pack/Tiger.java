package Pack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Tiger {
    @GetMapping("/bpp/{num}")
    public String func00(@PathVariable String num){
        System.out.println("index"+num);
        return "bpp";
    }

}
