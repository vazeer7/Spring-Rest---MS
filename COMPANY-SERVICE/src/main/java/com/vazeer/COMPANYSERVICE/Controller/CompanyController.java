package com.vazeer.COMPANYSERVICE.Controller;

import com.vazeer.COMPANYSERVICE.Model.Company;
import com.vazeer.COMPANYSERVICE.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping("/addCompany")
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyWithJob(@PathVariable("companyId") Integer companyId){
        return new ResponseEntity<>(companyService.getCompanyWithJob(companyId), HttpStatus.CREATED);
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<String> updateCompany(@PathVariable("companyId") Integer companyId, @RequestBody Company updatedCompany){
        boolean updated = companyService.updateCompany(companyId, updatedCompany);
        if (updated)
            return new ResponseEntity<>("Company details updated successfully", HttpStatus.ACCEPTED);

        return new ResponseEntity<>("company details not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable("companyId") Integer companyId){
        boolean delete = companyService.deleteCompany(companyId);
        if (delete){
            return new ResponseEntity<>("company deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("company not found", HttpStatus.NOT_FOUND);
    }
}
