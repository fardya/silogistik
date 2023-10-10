package apap.ti.silogistik2106702005.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    public String home() { return "home"; }
}
