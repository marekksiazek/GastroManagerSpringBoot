package pl.marekksiazek.GastroManager.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.marekksiazek.GastroManager.entity.Company;
import pl.marekksiazek.GastroManager.repository.CompanyRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(name = "Company", description = "Company controller")
@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    @Operation(
            summary = "Fetch all companies",
            description = "Fetch all company entities and their data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = Company.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/companies/{id}")
    @Operation(
            summary = "Fetch one company",
            description = "Fetch company entities and his data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = Company.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<Company> getCompanyById(@Parameter(description = "Id of company to be searched") @PathVariable Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/companies")
    @Transactional
    @Operation(
            summary = "Create one company",
            description = "Create company entities and add his data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created company", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = Company.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<Company> createCompany(@Parameter(description = "Company entity") @RequestBody Company company) {
        Company savedCompany = companyRepository.save(company);
        return ResponseEntity.created(URI.create("/companies/" + savedCompany.getId())).body(savedCompany);
    }

}
