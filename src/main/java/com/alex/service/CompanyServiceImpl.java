package com.alex.service;

import com.alex.domain.Company;
import com.alex.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company addCompany(Company company) {
        if(company != null) {
            company = companyRepository.saveAndFlush(company);
        }
        return company;
    }

    @Override
    public void delete(long id) {
        companyRepository.delete(id);
    }

    @Override
    public Company getByName(String name) {
        Company company = companyRepository.findByName(name);
        return company;
    }

    @Override
    public Company editCompany(Company company) {
        if(company != null){
            company = companyRepository.saveAndFlush(company);
        }
        return company;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
