package edu.gatech.seclass.jobcompare6300;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class JobOfferActivity extends AppCompatActivity {
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
    private Button returnToMainMenuButton;
    private Button addAnotherOfferButton;
    private Button compareWCurJobButton;
    private boolean saveButtonGetClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joboffer);

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

        saveButton = findViewById(R.id.ButtonSaveCurJob);
        cancelButton = findViewById(R.id.ButtonCancelSaveCurJob);
        returnToMainMenuButton = findViewById(R.id.ButtonReturnTMainMenu);
        addAnotherOfferButton = findViewById(R.id.ButtonAddAnotherOffer);
        compareWCurJobButton = findViewById(R.id.ButtonCompareWCurJob);
        compareWCurJobButton.setEnabled(false);

        JobCompareSystem system = JobCompareSystem.getInstance();
        if (system.GetCurrentJob() != null){
            compareWCurJobButton.setEnabled(true);
        }

        // Add a listener to the "Save" button.
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
                AlertDialog.Builder builder = new AlertDialog.Builder(JobOfferActivity.this);
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
                Job newJobOffer = new Job(new String[]{
                        null,
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
                        "false" // Is not the current Job
                });

                // newJobOffer.SaveJob();
                system.SaveJob(newJobOffer);
                saveButtonGetClick = true;

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Success!");
                builder.setMessage("You have saved your job offer!");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });

        cancelButton.setOnClickListener(view -> {
            // Clean what the user has entered
            titleET.setText("");
            companyET.setText("");
            addressET.setText("");
            colEditText.setText("");
            yearlysalaryET.setText("");
            yearlyBonusET.setText("");
            gymMembershipET.setText("");
            leaveTimeET.setText("");
            f01KET.setText("");
            petInsuranceET.setText("");
        });

        returnToMainMenuButton.setOnClickListener(view -> {
            // Back to the main menu
            Intent mainActivityIntent = new Intent(JobOfferActivity.this, MainActivity.class);
            startActivity(mainActivityIntent);
        });

        addAnotherOfferButton.setOnClickListener(view -> {
            if (saveButtonGetClick) {
                // Clean what the user has entered
                titleET.setText("");
                companyET.setText("");
                addressET.setText("");
                colEditText.setText("");
                yearlysalaryET.setText("");
                yearlyBonusET.setText("");
                gymMembershipET.setText("");
                leaveTimeET.setText("");
                f01KET.setText("");
                petInsuranceET.setText("");
                saveButtonGetClick = false;
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert");
                builder.setMessage("You should save the job before you add another job.");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });

        compareWCurJobButton.setOnClickListener(view -> {
            if (saveButtonGetClick) {
                Job curJob = system.GetCurrentJob();
                List<Job> jobs = system.GetAllRawJobs();

                if (curJob != null && jobs.size() == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Error");
                    builder.setMessage("No Job Offers in Database.");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                } else {
                    Intent compareJobOfferActivityIntent = new Intent(JobOfferActivity.this,
                            CompareJobOfferDetailActivity.class);
                    compareJobOfferActivityIntent.putExtra("selectedJob1", curJob.GetJobDetails());
                    compareJobOfferActivityIntent.putExtra("selectedJob2",
                            jobs.get(jobs.size()-1).GetJobDetails());
                    startActivity(compareJobOfferActivityIntent);
                }
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert");
                builder.setMessage("You should save the job before you compare with the current job.");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
    }
}
