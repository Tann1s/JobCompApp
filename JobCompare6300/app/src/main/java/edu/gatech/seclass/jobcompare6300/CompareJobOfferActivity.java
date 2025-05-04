package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class CompareJobOfferActivity extends AppCompatActivity {
    private TableLayout RankedJobTable;
    private Button ButtonCompare;
    private Button ButtonReSelect;
    private TextView SelectedJobOffer1;
    private TextView SelectedJobOffer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparejoboffer);
        System.out.println("Compare Job OnCreate");

        JobCompareSystem jobCompareSystem = JobCompareSystem.getInstance();

        RankedJobTable = (TableLayout) findViewById(R.id.RankedJobTable);
        ButtonCompare = (Button) findViewById(R.id.buttonCompare);
        ButtonReSelect = (Button) findViewById(R.id.buttonReSelect);
        SelectedJobOffer1 = (TextView) findViewById(R.id.SelectedJobOffer1);
        SelectedJobOffer2 = (TextView) findViewById(R.id.SelectedJobOffer2);

        //display the rows
        RankedJobTable = (TableLayout) findViewById(R.id.RankedJobTable);

        List<Job> jobOffers = jobCompareSystem.GetAllRankedJobs();
        List<Job> selectedJobs = new ArrayList<>();
        for (int i = 0; i < jobOffers.size(); i++) {
            String[] jobDetails = jobOffers.get(i).GetJobDetails();
            TableRow row = new TableRow(this);
            CheckBox checkBox = new CheckBox(this);
            TextView jobNumber = new TextView(this);

            TextView JobTitle = new TextView(this);
            JobTitle.setText(jobDetails[1]);
            TextView JobCompany = new TextView(this);

            checkBox.setId(i);
            if (Boolean.parseBoolean(jobOffers.get(i).GetJobDetails()[11])) {
                jobNumber.setText("Current Job");
            } else {
                jobNumber.setText(String.valueOf(i + 1));
            }

            JobTitle.setText(jobDetails[1]);
            JobCompany.setText(jobDetails[2]);

            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            params.width = 200;
            params.height = TableRow.LayoutParams.WRAP_CONTENT;

            params.setMargins(50, 5, 30, 5);

            checkBox.setLayoutParams(params);
            jobNumber.setLayoutParams(params);
            JobTitle.setLayoutParams(params);
            JobCompany.setLayoutParams(params);

            checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if (isChecked) {
                    if (selectedJobs.size() < 2) {
                        selectedJobs.add(jobOffers.get(checkBox.getId()));
                        UpdateSelectedJobText(selectedJobs);
                    } else {
                        checkBox.setError("You can only select two jobs");
                        checkBox.setChecked(false);
                    }

                    if (!SelectedJobOffer1.getText().toString().isEmpty() && !SelectedJobOffer2.getText().toString().isEmpty()) {
                        ButtonCompare.setEnabled(true);
                    } else {
                        ButtonCompare.setEnabled(false);
                    }
                } else {
                    selectedJobs.remove(jobOffers.get(checkBox.getId()));
                    UpdateSelectedJobText(selectedJobs);
                }
            });
            row.addView(checkBox);
            row.addView(jobNumber);
            row.addView(JobTitle);
            row.addView(JobCompany);
            RankedJobTable.addView(row);
        }

        ButtonReSelect.setOnClickListener(view -> {
            for (int i = 0; i < jobOffers.size(); i++) {
                CheckBox checkBox = findViewById(i);
                checkBox.setChecked(false);
                selectedJobs.clear();
                checkBox.setError(null);
                UpdateSelectedJobText(selectedJobs);
            }
        });

        ButtonCompare.setOnClickListener(view -> {
            Intent compareJobOfferDetailIntent = new Intent(CompareJobOfferActivity.this, CompareJobOfferDetailActivity.class);
            compareJobOfferDetailIntent.putExtra("selectedJob1", selectedJobs.get(0).GetJobDetails());
            compareJobOfferDetailIntent.putExtra("selectedJob2", selectedJobs.get(1).GetJobDetails());
            startActivity(compareJobOfferDetailIntent);
        });
    }

    private void UpdateSelectedJobText(List<Job> selectedJobs) {
        Integer size = selectedJobs.size();
        if (size == 1) {
            SelectedJobOffer1.setText(selectedJobs.get(0).GetJobDetails()[2]);
            SelectedJobOffer2.setText("");
        } else if (size == 2) {
            SelectedJobOffer1.setText(selectedJobs.get(0).GetJobDetails()[2]);
            SelectedJobOffer2.setText(selectedJobs.get(1).GetJobDetails()[2]);
        } else {
            SelectedJobOffer1.setText("");
            SelectedJobOffer2.setText("");
        }
    }
}
