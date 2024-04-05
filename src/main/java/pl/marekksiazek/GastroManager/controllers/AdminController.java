package pl.marekksiazek.GastroManager.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/adminProfile")
    @PreAuthorize("hasAuthority('admin')")
    public String adminProfile(){
        return "Admin panel page";
    }
}
