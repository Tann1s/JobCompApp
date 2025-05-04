package edu.gatech.seclass.jobcompare6300;


import android.content.Context;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// add check function, if user didn't do anything and click save

public class ComparisonSettingActivity extends AppCompatActivity {
    private ComparisonSetting comparisonSetting;
    private JobCompareSystem jobCompareSystem;
    private EditText SalaryWeight;
    private EditText BonusWeight;
    private EditText GymWeight;
    private EditText VacationWeight;
    private EditText Match401KWeight;
    private EditText PetInsuranceWeight;
    private Button saveButton;
    private Button resetButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jobCompareSystem = JobCompareSystem.getInstance();
        comparisonSetting = jobCompareSystem.GetComparisonSetting();

        Integer[] storedSetting = comparisonSetting.GetSettings();

        setContentView(R.layout.activity_comparisonsetting);
        SalaryWeight = (EditText) findViewById(R.id.SalaryWeightEntry);
        BonusWeight = (EditText) findViewById(R.id.BonusWeightEntry);
        GymWeight = (EditText) findViewById(R.id.GymWeightEntry);
        VacationWeight = (EditText) findViewById(R.id.VacationWeightEntry);
        Match401KWeight = (EditText) findViewById(R.id.Match401KWeightEntry);
        PetInsuranceWeight = (EditText) findViewById(R.id.PetInsuranceWeightEntry);
        saveButton = (Button) findViewById(R.id.saveButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        SalaryWeight.setText(String.valueOf(storedSetting[0]));
        BonusWeight.setText(String.valueOf(storedSetting[1]));
        GymWeight.setText(String.valueOf(storedSetting[2]));
        VacationWeight.setText(String.valueOf(storedSetting[3]));
        Match401KWeight.setText(String.valueOf(storedSetting[4]));
        PetInsuranceWeight.setText(String.valueOf(storedSetting[5]));

        // save button function
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(view -> {
            onClickSave();
        });

        // reset button function change this
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(view -> {
                onClickReset();
        });

        // cancel button function
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(view -> {
                finish();
        });

    }

    // button functions below

    public void onClickSave() {
        boolean noError = true;
        String salaryWeightInput = SalaryWeight.getText().toString();
        String bonusWeightInput = BonusWeight.getText().toString();
        String gymWeightInput = GymWeight.getText().toString();
        String vacationWeightInput = VacationWeight.getText().toString();
        String match401KWeightInput = Match401KWeight.getText().toString();
        String petInsuranceWeightInput = PetInsuranceWeight.getText().toString();

        if(salaryWeightInput.isEmpty()){
            SalaryWeight.setError("Invalid Salary Weight!");
            noError = false;
        }
        if(bonusWeightInput.isEmpty()){
            BonusWeight.setError("Invalid Bonus Weight!");
            noError = false;
        }
        if(gymWeightInput.isEmpty()){
            GymWeight.setError("Invalid Gym Weight!");
            noError = false;
        }
        if(vacationWeightInput.isEmpty()){
            VacationWeight.setError("Invalid Vacation Weight!");
            noError = false;
        }
        if(match401KWeightInput.isEmpty()){
            Match401KWeight.setError("Invalid 401K Weight!");
            noError = false;
        }
        if(petInsuranceWeightInput.isEmpty()) {
            PetInsuranceWeight.setError("Invalid Pet Insurance Weight!");
            noError = false;
        }

        if(noError) {
            int salaryWeightValue = Integer.parseInt(salaryWeightInput);
            int bonusWeightValue = Integer.parseInt(bonusWeightInput);
            int gymWeightValue = Integer.parseInt(gymWeightInput);
            int vacationWeightValue = Integer.parseInt(vacationWeightInput);
            int match401KWeightValue = Integer.parseInt(match401KWeightInput);
            int petInsuranceWeightValue = Integer.parseInt(petInsuranceWeightInput);

            // check if input is integer from 1-10
            if(salaryWeightValue == 0 || bonusWeightValue == 0 || gymWeightValue == 0 || vacationWeightValue == 0 || match401KWeightValue == 0 || petInsuranceWeightValue == 0 || salaryWeightValue > 10 || bonusWeightValue > 10 || gymWeightValue > 10 || vacationWeightValue > 10 || match401KWeightValue > 10 || petInsuranceWeightValue > 10) {
                Toast.makeText(this, "Weights must be an 1-10 integer.", Toast.LENGTH_SHORT).show();
                return;
            }

            ComparisonSetting newSetting = new ComparisonSetting(
                    salaryWeightValue, bonusWeightValue, gymWeightValue, vacationWeightValue, match401KWeightValue, petInsuranceWeightValue
            );

            // save the new setting
            jobCompareSystem.AdjustComparisonSetting(newSetting);

            // save and back to main menu

            Toast.makeText(this, "Settings saved successfully!", Toast.LENGTH_SHORT).show();
            finish();

        }

    }

    public void onClickReset() {
        // Update UI to reflect the reset settings.
        SalaryWeight.setText("1");
        BonusWeight.setText("1");
        GymWeight.setText("1");
        VacationWeight.setText("1");
        Match401KWeight.setText("1");
        PetInsuranceWeight.setText("1");
    }

}
