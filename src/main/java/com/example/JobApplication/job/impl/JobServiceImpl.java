package com.example.JobApplication.job.impl;

import com.example.JobApplication.job.JobRepository;
import com.example.JobApplication.job.JobService;
import com.example.JobApplication.job.Jobs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    //private static List<Jobs> jobs  = new ArrayList<Jobs>();
    JobRepository jobRepository ;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }




    @Override
    public boolean updateJob(Long id, Jobs updatedJob) {
        Optional<Jobs> jobsOptional = jobRepository.findById(id);

        if(jobsOptional.isPresent()) {
            Jobs job = jobsOptional.get();

            job.setDescription(updatedJob.getDescription());
            job.setTitle(updatedJob.getTitle());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }


        return false;


    }

    @Override
    public Jobs getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public List<Jobs> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJobs(Jobs job) {

        jobRepository.save(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
         jobRepository.deleteById(id);
         return true;

        }catch (Exception e){
            return false;
        }
    }



}
