package com.kazimoto.digitalbackend.service.company;

import com.kazimoto.digitalbackend.entity.Company;
import com.kazimoto.digitalbackend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    public final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company saveCompany(Company company) {
        Company savedCompany = new Company();
        savedCompany.setName(company.getName());
        savedCompany.setEmail(company.getEmail());
        savedCompany.setAddress(company.getAddress());
        savedCompany.setPhone(company.getPhone());
        savedCompany.setDistrict(company.getDistrict());
        savedCompany.setRegion(company.getRegion());
        savedCompany.setPassword(company.getPassword());
        savedCompany.setDomainUrl(company.getDomainUrl());
        savedCompany.setTinNumber(company.getTinNumber());
        savedCompany.setImgUrl(company.getImgUrl());

        return companyRepository.save(savedCompany);
    }
}
