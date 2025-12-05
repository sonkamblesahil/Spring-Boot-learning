package com.example.JobApplication.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company,@PathVariable Long id){
        boolean updated = companyService.updateCompany(company,id);
        if(updated) return new ResponseEntity<>("Company updated Successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @PostMapping
    public  ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("company creates successfully",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
       boolean deleted = companyService.deleteCompanyById(id);
       if(deleted){
           return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
       }else{
           return new ResponseEntity<>("No such company exists",HttpStatus.NOT_FOUND);
       }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyByid(@PathVariable Long id){
       Company res =  companyService.findCompanyById(id);
       return new ResponseEntity<>(res,HttpStatus.FOUND);
    }


}
