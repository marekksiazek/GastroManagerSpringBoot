package pl.marekksiazek.GastroManager.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Company owner controller", description = "Administration panel for company owner")
@RestController
@RequestMapping("/owner")
public class OwnersPanelController {

    @GetMapping("/")
    public void redirect(HttpServletResponse response) throws Exception {
        response.sendRedirect("https://google.com");
    }
}
