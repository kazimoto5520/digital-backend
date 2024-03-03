package com.kazimoto.digitalbackend.controller;

import com.kazimoto.digitalbackend.dto.DistrictDto;
import com.kazimoto.digitalbackend.dto.DistrictResponseDto;
import com.kazimoto.digitalbackend.dto.RegionDto;
import com.kazimoto.digitalbackend.entity.Company;
import com.kazimoto.digitalbackend.entity.District;
import com.kazimoto.digitalbackend.entity.Region;
import com.kazimoto.digitalbackend.service.company.CompanyService;
import com.kazimoto.digitalbackend.service.district.DistrictService;
import com.kazimoto.digitalbackend.service.region.RegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000/")
public class HomeController {

    private final CompanyService companyService;
    private final RegionService regionService;
    private final DistrictService districtService;

    @GetMapping("/companies/all")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @PostMapping("/companies")
    public ResponseEntity<Object> saveCompany(@Valid @RequestBody Company company, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("message", BAD_REQUEST.value());
            response.put("errors", errors);
            return new ResponseEntity<>(response, BAD_REQUEST);

        }
        Company savedCompany = companyService.saveCompany(company);
        System.out.println("Saved Company: " + savedCompany.getName());
        return ResponseEntity.ok(savedCompany);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/regions/all")
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionService.getAllRegions();
        return ResponseEntity.ok(regions);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/regions")
    public ResponseEntity<?> saveRegion(@Valid @RequestBody RegionDto region, BindingResult result) {
        if (result.hasErrors()) {
            FieldError error = result.getFieldError("regionName");
            if (error != null) {
                String errorMessage = error.getDefaultMessage();
                Map<String, String> response = new HashMap<>();
                response.put("error", errorMessage);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

        try {
            Region savedRegion = regionService.saveRegion(region);
            return ResponseEntity.status(CREATED).body(savedRegion);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/regions/{id}")
    public ResponseEntity<?> getSingleRegion(@PathVariable(name = "id") Long id) {
        try {
            Region region = regionService.getSingleRegion(id);
            return ResponseEntity.ok(region);
        } catch (NoSuchElementException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Region with ID " + id + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error retrieving region: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/regions/update/{id}")
    public ResponseEntity<?> updateRegion(@PathVariable(name = "id") Long id, @Valid @RequestBody RegionDto region) {
        try {
            Region updatedRegion = regionService.updateRegion(id, region);
            return ResponseEntity.ok(updatedRegion);
        } catch (NoSuchElementException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Region with ID " + id + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error updating region: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/regions/delete/{id}")
    public ResponseEntity<?> deleteRegion(@PathVariable(name = "id") Long id) {
        try {
            regionService.deleteRegion(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Region deleted");
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Region with ID " + id + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error updating region: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

//    District API

    @GetMapping("/districts/all")
    public ResponseEntity<List<DistrictResponseDto>> getAllDistricts() {
        List<DistrictResponseDto> districts = districtService.getAllDistrict();
        return ResponseEntity.ok(districts);
    }

    @PostMapping("/districts")
    public ResponseEntity<?> saveDistrict(@Valid @RequestBody DistrictDto district, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        try {
            DistrictResponseDto savedDistrict = districtService.saveDistrict(district);
            return ResponseEntity.status(CREATED).body(savedDistrict);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<?> getSingleDistrict(@PathVariable(name = "id") Long id) {
        try {
            DistrictResponseDto district = districtService.getSingleDistrict(id);
            return ResponseEntity.status(OK).body(district);
        } catch (NoSuchElementException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(NOT_FOUND).body(response);
        }
    }

    @PutMapping("/districts/update/{id}")
    public ResponseEntity<?> updateDistrict(@PathVariable(name = "id") Long id, @Valid @RequestBody DistrictDto dto) {
        try {
            DistrictResponseDto district = districtService.updateDistrict(id, dto);
            return ResponseEntity.status(CREATED).body(district);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/districts/delete/{id}")
    public ResponseEntity<?> deleteDistrict(@PathVariable(name = "id") Long id) {
        try {
            districtService.deleteDistrict(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "District deleted");
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "District with ID " + id + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error updating district: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
