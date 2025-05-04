package edu.gatech.seclass.jobcompare6300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import android.database.Cursor;
import android.content.ContentValues;

public class JobCompareSystem {
    private static JobCompareSystem instance = null;
    private Job CurrentJob;
    private List<Job> JobOffers;
    private ComparisonSetting ComparisonSetting;
    private DBHelper dbHelper;

    private JobCompareSystem(){
        this.dbHelper = DBHelper.getInstance(ApplicationProvider.getContext());
        this.ComparisonSetting = new ComparisonSetting();
    }

    public static JobCompareSystem getInstance(){
        if (instance == null) {
            instance = new JobCompareSystem();
        }
        return instance;
    }

    public Job GetCurrentJob() {
        if(this.CurrentJob == null){
            String[] currentJob = dbHelper.ReadCurrentJob();
            if (currentJob != null){
                this.CurrentJob = new Job(currentJob);
            }
        }
        return this.CurrentJob;
    }

    public List<Job> GetAllRankedJobs(){
//        if (JobOffers == null) {
//            JobOffers = dbHelper.read();
//        }
        JobOffers = dbHelper.read();

        if (JobOffers == null || JobOffers.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<Float> jobScores = new ArrayList<>();
            for (Integer i = 0; i < JobOffers.size(); i++) {
                jobScores.add(JobOffers.get(i).CalculateJobScore(this.ComparisonSetting));
            }

            List<Job> jobs = new ArrayList<>(JobOffers);
            jobs.sort(Comparator.comparingDouble(job -> jobScores.get(jobs.indexOf(job))).reversed());

            return jobs;
        }
    }

    public List<Job> GetAllRawJobs() {
//        if (JobOffers == null) {
//            JobOffers = dbHelper.read();
//            if (JobOffers == null || JobOffers.isEmpty()) {
//                return new ArrayList<>();
//            }
//        }
        JobOffers = dbHelper.read();
        if (JobOffers == null || JobOffers.isEmpty()) {
            return new ArrayList<>();
        }
        return JobOffers;
    }

    public void SaveJob(Job job){
        if(job.GetJobDetails()[0] == null){
            dbHelper.AddJob(job);
        }
        else{
            dbHelper.UpdateJob(job);
        }

        if(job.GetJobDetails()[11] == "true"){
            this.CurrentJob = new Job(dbHelper.ReadCurrentJob());
        }
        else{
            this.JobOffers = dbHelper.read();
        }

    }

    public void AdjustComparisonSetting(ComparisonSetting comparisonSetting){
        this.ComparisonSetting = comparisonSetting;
    }

    public ComparisonSetting GetComparisonSetting (){
        return ComparisonSetting;
    }
}
