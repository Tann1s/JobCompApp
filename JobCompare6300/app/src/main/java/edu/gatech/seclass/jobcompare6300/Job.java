package edu.gatech.seclass.jobcompare6300;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Job {
    private Integer Id;
    private String Title;
    private String Company;
    private String Location;
    private Integer CostOfLiving;
    private Float YearlySalary;
    private Float YearlyBonus;
    private Float GymMembership;
    private Integer Vacation;
    private Float Match401K;
    private Float PetInsurance;
    private Boolean IsCurrentJob;

    public Job(String[] jobDetails){
        this.Id = jobDetails[0] == null ? null : Integer.valueOf(jobDetails[0]);
        this.Title = jobDetails[1];
        this.Company = jobDetails[2];
        this.Location = jobDetails[3];
        this.CostOfLiving = Integer.valueOf(jobDetails[4]);
        this.YearlySalary = Float.valueOf(jobDetails[5]);
        this.YearlyBonus = Float.valueOf(jobDetails[6]);
        this.GymMembership = Float.valueOf(jobDetails[7]);
        this.Vacation = Integer.valueOf(jobDetails[8]);
        this.Match401K = Float.valueOf(jobDetails[9]);
        this.PetInsurance = Float.valueOf(jobDetails[10]);
        this.IsCurrentJob = Boolean.valueOf(jobDetails[11]);
    }

    public Float CalculateJobScore(ComparisonSetting comparisonSetting){
        Float ays = YearlySalary * 100 / CostOfLiving;
        Float ayb = YearlyBonus * 100 / CostOfLiving;
        Integer[] settings = comparisonSetting.GetSettings();
        Integer totalWeight = IntStream.of(Arrays.stream(settings).mapToInt(Integer::intValue).toArray()).sum();

        Float score = ( settings[0] * ays +
                settings[1] * ayb +
                settings[2] * GymMembership +
                settings[3] * Vacation * ays / 260 +
                settings[4] * ays * Match401K / 100 +
                settings[5] * PetInsurance) / totalWeight;

        return score;
    }

    public String[] GetJobDetails(){
        String[] jobDetails = new String[] {
                Id == null ? null : String.valueOf(Id),
                Title,
                Company,
                Location,
                String.valueOf(CostOfLiving),
                String.valueOf(YearlySalary),
                String.valueOf(YearlyBonus),
                String.valueOf(GymMembership),
                String.valueOf(Vacation),
                String.valueOf(Match401K),
                String.valueOf(PetInsurance),
                String.valueOf(IsCurrentJob)
        };
        return jobDetails;
    }
}
