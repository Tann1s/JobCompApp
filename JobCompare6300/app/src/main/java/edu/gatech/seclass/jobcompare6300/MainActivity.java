package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private Button CurrentJob, JobOffer, ComparisonSetting, CompareJobOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JobCompareSystem jobCompareSystem = JobCompareSystem.getInstance();

        CurrentJob = findViewById(R.id.CurrentJob);
        JobOffer = findViewById(R.id.JobOffer);
        ComparisonSetting = findViewById(R.id.ComparisonSetting);
        CompareJobOffer = findViewById(R.id.CompareJobOffer);

        CurrentJob.setOnClickListener(view -> {
            Intent currentJobIntent = new Intent(this, CurrentJobActivity.class);
            startActivity(currentJobIntent);
        });

        JobOffer.setOnClickListener(view -> {
            Intent jobOfferIntent = new Intent(this, JobOfferActivity.class);
            startActivity(jobOfferIntent);
        });

        ComparisonSetting.setOnClickListener(view -> {
            Intent comparisonSettingIntent = new Intent(this, ComparisonSettingActivity.class);
            startActivity(comparisonSettingIntent);
        });

        CompareJobOffer.setOnClickListener(view -> {
            Intent compareJobOfferIntent = new Intent(this, CompareJobOfferActivity.class);
            startActivity(compareJobOfferIntent);
        });
    }
}
