package com.kazimoto.digitalbackend.service.company;

import com.kazimoto.digitalbackend.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company saveCompany(Company company);
}
