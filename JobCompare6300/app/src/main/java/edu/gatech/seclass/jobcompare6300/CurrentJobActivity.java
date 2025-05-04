package edu.gatech.seclass.jobcompare6300;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class CurrentJobActivity extends AppCompatActivity {
    private EditText titleET;
    private EditText companyET;
    private EditText addressET;
    private EditText colEditText;
    private EditText yearlysalaryET;
    private EditText yearlyBonusET;
    private EditText gymMembershipET;
    private EditText leaveTimeET;
    private EditText f01KET;
    private EditText petInsuranceET;
    private Button saveButton;
    private Button cancelButton;
    private JobCompareSystem system;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentjob);

        titleET = (EditText) findViewById(R.id.TextTitle);
        companyET = (EditText) findViewById(R.id.TextCompany);
        addressET = (EditText) findViewById(R.id.TextAddress);
        colEditText = (EditText) findViewById(R.id.TextCOLIndex);
        yearlysalaryET = (EditText) findViewById(R.id.TextYrSalary);
        yearlyBonusET = (EditText) findViewById(R.id.TextYrBonus);
        gymMembershipET = (EditText) findViewById(R.id.TextGymMemAllow);
        leaveTimeET = (EditText) findViewById(R.id.TextLeaveTime);
        f01KET = (EditText) findViewById(R.id.Text401KMatch);
        petInsuranceET = (EditText) findViewById(R.id.TextPetInsurance);

        // Declare current job
        this.system = JobCompareSystem.getInstance();
        Job curJob = system.GetCurrentJob();

        // if we have currentJob in the database, display them in the screen, otherwise show ""
        if (curJob != null) {
            titleET.setText(curJob.GetJobDetails()[1]);
            companyET.setText(curJob.GetJobDetails()[2]);
            addressET.setText(curJob.GetJobDetails()[3]);
            colEditText.setText(curJob.GetJobDetails()[4]);
            yearlysalaryET.setText(curJob.GetJobDetails()[5]);
            yearlyBonusET.setText(curJob.GetJobDetails()[6]);
            gymMembershipET.setText(curJob.GetJobDetails()[7]);
            leaveTimeET.setText(curJob.GetJobDetails()[8]);
            f01KET.setText(curJob.GetJobDetails()[9]);
            petInsuranceET.setText(curJob.GetJobDetails()[10]);
        }

        // Add a listener to the "Save" button.
        saveButton = findViewById(R.id.ButtonSaveCurJob);
        saveButton.setOnClickListener(view -> {
            String titleInput = titleET.getText().toString();
            String companyInput = companyET.getText().toString();
            String addressInput = addressET.getText().toString();
            String colInput = colEditText.getText().toString();
            String yearlySalaryInput = yearlysalaryET.getText().toString();
            String yearlyBonusInput = yearlyBonusET.getText().toString();
            String gymInput = gymMembershipET.getText().toString();
            String leaveTimeInput = leaveTimeET.getText().toString();
            String match401Input = f01KET.getText().toString();
            String petInsuranceInput = petInsuranceET.getText().toString();

            boolean hasErrors = false;

            if (titleInput.isEmpty() || companyInput.isEmpty() || addressInput.isEmpty() ||
                    colInput.isEmpty() || yearlySalaryInput.isEmpty() ||
                    yearlyBonusInput.isEmpty() || gymInput.isEmpty() ||
                    leaveTimeInput.isEmpty() || match401Input.isEmpty() ||
                    petInsuranceInput.isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert");
                builder.setMessage("You must enter all the infomation; otherwise please enter " +
                        "NA for text" +
                        "or 0 for number.");
                builder.setPositiveButton("OK", null);
                builder.show();

                hasErrors = true;
            } else {
                float colInputF = parseFloat(colInput);
                float gymInputF = parseFloat(gymInput);
                float leaveTimeInputF = parseFloat(leaveTimeInput);
                float match401InputF = parseFloat(match401Input);
                float petInsuranceInputF = parseFloat(petInsuranceInput);

                if (colInputF <= 0 || colInputF >= 250) {
                    colEditText.setError("Invalid Cost of Living Index, should be 0-250.");
                    hasErrors = true;
                }
                if (gymInputF < 0 || gymInputF > 500) {
                    gymMembershipET.setError("Invalid gym membership allowance, should be $0-$500.");
                    hasErrors = true;
                }
                if (leaveTimeInputF < 0 || leaveTimeInputF > 365) {
                    leaveTimeET.setError("Invalid leave time value, should be 0-365.");
                    hasErrors = true;
                }
                if (match401InputF < 0 || match401InputF > 20) {
                    f01KET.setError("Invalid 401K Match, should be 0-20.");
                    hasErrors = true;
                }
                if (petInsuranceInputF < 0 || petInsuranceInputF > 5000) {
                    petInsuranceET.setError("Invalid pet insurance value, should be $0-$5000.");
                    hasErrors = true;
                }
            }

            if (!hasErrors) {
                String[] attributes = new String[]{
                        curJob == null ? null : curJob.GetJobDetails()[0],
                        titleInput,
                        companyInput,
                        addressInput,
                        colInput,
                        yearlySalaryInput,
                        yearlyBonusInput,
                        gymInput,
                        leaveTimeInput,
                        match401Input,
                        petInsuranceInput,
                        "true" // IsCurrentJob
                };

                Job newCurJob = new Job(attributes);
                system.SaveJob(newCurJob);

                // Back to the main menu
                Intent mainActivityIntent = new Intent(CurrentJobActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }


        });

        cancelButton = findViewById(R.id.ButtonCancelSaveCurJob);
        cancelButton.setOnClickListener(view -> {
            // Back to the main menu
            Intent mainActivityIntent = new Intent(CurrentJobActivity.this, MainActivity.class);
            startActivity(mainActivityIntent);
        });
    }
}