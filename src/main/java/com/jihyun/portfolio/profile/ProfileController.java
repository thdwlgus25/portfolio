package com.jihyun.portfolio.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/myProfile")
    public String myProfile() {
        return "page/profile/MyProfile";
    }
}
