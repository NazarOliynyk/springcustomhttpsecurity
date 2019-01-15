package oktenweb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/admin/hello")
    public String hello(){
        return "hi there!";
    }

    @GetMapping("/admin/greeting")
    public String greeting(
            @AuthenticationPrincipal Authentication authentication
            ){
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        System.out.println(authentication1);
        return "hello everyone!";
    }
}
