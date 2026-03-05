package vod.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
/*@Controller*/
public class HelloRest {
    /*@RequestMapping(value="/hello", method= RequestMethod.GET)
    @ResponseBody String sayHello() {return "Hey Universe!";}*/
    @GetMapping("/hello")
    String sayHello() {return "Hey Universe";}
}
