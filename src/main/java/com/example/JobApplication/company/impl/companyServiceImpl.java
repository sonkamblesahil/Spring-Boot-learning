package com.example.JobApplication.company.impl;

import com.example.JobApplication.company.Company;
import com.example.JobApplication.company.CompanyRepository;
import com.example.JobApplication.company.CompanyService;
import com.example.JobApplication.job.Jobs;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

@Service
public class companyServiceImpl implements CompanyService {
     CompanyRepository companyRepository;

    public companyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public boolean updateCompany(Company updatedcompany,Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()) {
            Company company = companyOptional.get();

            company.setDescription(updatedcompany.getDescription());
            company.setName(updatedcompany.getName());
            company.setDescription(updatedcompany.getDescription());
            company.setJobs(updatedcompany.getJobs());
            companyRepository.save(company);
            return true;
        }


        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

}
