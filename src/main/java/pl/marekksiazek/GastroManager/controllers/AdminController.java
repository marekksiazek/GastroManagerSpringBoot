package pl.marekksiazek.GastroManager.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Admin controller", description = "Panel for webapp administrators")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminProfile(){
        return "Admin panel page";
    }
}
