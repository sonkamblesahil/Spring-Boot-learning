package com.example.JobApplication.company;

import com.example.JobApplication.job.JobRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
