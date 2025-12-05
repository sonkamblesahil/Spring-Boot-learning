package com.example.JobApplication.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface JobService {

    boolean updateJob(Long id, Jobs updatedJob);


    Jobs getJobById(Long id);
    List<Jobs> findAll();
    void createJobs(Jobs job);
    boolean deleteJobById(Long id);

}
