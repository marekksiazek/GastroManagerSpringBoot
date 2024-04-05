package pl.marekksiazek.GastroManager.controllers;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnersPanelController {

    @GetMapping("/")
    public void redirect(HttpServletResponse response) throws Exception {
        response.sendRedirect("https://google.com");
    }
}
