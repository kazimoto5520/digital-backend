package com.kazimoto.digitalbackend.controller;

import com.kazimoto.digitalbackend.entity.Company;
import com.kazimoto.digitalbackend.entity.Region;
import com.kazimoto.digitalbackend.service.company.CompanyService;
import com.kazimoto.digitalbackend.service.region.RegionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RegionService regionService;

    @GetMapping("/companies/all")
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @PostMapping("/companies")
    public ResponseEntity<Object> saveCompany(@Valid @RequestBody Company company, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("message", HttpStatus.BAD_REQUEST.value());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
        Company savedCompany = companyService.saveCompany(company);
        System.out.println("Saved Company: " + savedCompany.getName());
        return ResponseEntity.ok(savedCompany);
    }

    @GetMapping("/regions/all")
    public ResponseEntity<List<Region>> getAllRegions(){
        List<Region> regions = regionService.getAllRegions();
        return ResponseEntity.ok(regions);
    }

    @PostMapping("/regions")
    public ResponseEntity<Object> saveRegion(@Valid @RequestBody Region region, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("message", HttpStatus.BAD_REQUEST.value());
            response.put("errors", errors);

            return new  ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Region savedRegion = regionService.saveRegion(region);
        return ResponseEntity.ok(savedRegion);
    }

    @GetMapping("/regions/{id}")
    public ResponseEntity<Object> getSingleRegion(@PathVariable(name = "id") Long id){
        Region region = regionService.getSingleRegion(id);
        return ResponseEntity.ok(region);
    }

    @PutMapping("/regions/update/{id}")
    public ResponseEntity<Object> updateRegion(@PathVariable(name = "id") Long id, @Valid @RequestBody Region region){
        Region existedRegion = regionService.getSingleRegion(id);
        if (existedRegion == null){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Region not found with id: " + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Region updatedRegion = regionService.updateRegion(id, region);

        return ResponseEntity.ok(updatedRegion);
    }


}
