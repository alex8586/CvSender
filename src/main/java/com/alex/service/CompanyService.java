package com.alex.service;

import com.alex.domain.Company;

import java.util.List;

public interface CompanyService {

    Company create(Company company);

    void delete(long id);

    Company getByName(String name);

    Company editCompany(Company company);

    List<Company> getAll();

    Company getById(long id);
}
