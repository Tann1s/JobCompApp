package edu.gatech.seclass.jobcompare6300;



public class ComparisonSetting {
    private Integer SalaryWeight = 1;
    private Integer BonusWeight = 1;
    private Integer GymWeight = 1;
    private Integer VacationWeight = 1;
    private Integer Match401KWeight = 1;
    private Integer PetInsuranceWeight = 1;

    public ComparisonSetting(){

    }

    public ComparisonSetting(
            Integer salaryWeight,
            Integer bonusWeight,
            Integer gymWeight,
            Integer vacationWeight,
            Integer match401KWeight,
            Integer petInsuranceWeight){
        this.SalaryWeight = salaryWeight;
        this.BonusWeight = bonusWeight;
        this.GymWeight = gymWeight;
        this.VacationWeight = vacationWeight;
        this.Match401KWeight = match401KWeight;
        this.PetInsuranceWeight = petInsuranceWeight;
    }

    public Integer[] GetSettings(){
        Integer[] settings = new Integer[] {
                SalaryWeight,
                BonusWeight,
                GymWeight,
                VacationWeight,
                Match401KWeight,
                PetInsuranceWeight
        };
        return settings;
    }
}
