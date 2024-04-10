package com.vazeer.COMPANYSERVICE.Repository;

import com.vazeer.COMPANYSERVICE.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByCompanyId(Integer companyId);
}
