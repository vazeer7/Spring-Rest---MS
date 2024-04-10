package com.vazeer.COMPANYSERVICE.Service;

import com.vazeer.COMPANYSERVICE.Model.Company;
import com.vazeer.COMPANYSERVICE.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private RestTemplate restTemplate;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public boolean updateCompany(Integer companyId, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setCompanyName(updatedCompany.getCompanyName());
            company.setCompanyDescription(updatedCompany.getCompanyDescription());
            companyRepository.save(updatedCompany);

            return true;
        }
        return false;
    }

    public Company getCompanyWithJob(Integer companyId) {
        Company company = companyRepository.findByCompanyId(companyId);
        return company;
    }

    public boolean deleteCompany(Integer companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()){
            companyRepository.deleteById(companyId);
            return true;
        }
        return false;
    }
}
