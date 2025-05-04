package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompareJobOfferDetailActivity extends AppCompatActivity {
    private Button PerformAnotherCompare;
    private Button GoBackToMain;
    private TextView job1title;
    private TextView job2title;
    private TextView job1company;
    private TextView job2company;
    private TextView job1location;
    private TextView job2location;
    private TextView job1salaryadjusted;
    private TextView job2salaryadjusted;
    private TextView job1bonusadjusted;
    private TextView job2bonusadjusted;
    private TextView job1gymmembership;
    private TextView job2gymmembership;
    private TextView job1leavetime;
    private TextView job2leavetime;
    private TextView job1401k;
    private TextView job2401k;
    private TextView job1petinsurance;
    private TextView job2petinsurance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparejobofferdetail);

        JobCompareSystem jobCompareSystem = JobCompareSystem.getInstance();

        PerformAnotherCompare = (Button) findViewById(R.id.Compare);
        GoBackToMain = (Button) findViewById(R.id.GoBackToMain);
        job1title = (TextView) findViewById(R.id.job1title);
        job2title = (TextView) findViewById(R.id.job2title);
        job1company = (TextView) findViewById(R.id.job1company);
        job2company = (TextView) findViewById(R.id.job2company);
        job1location = (TextView) findViewById(R.id.job1location);
        job2location = (TextView) findViewById(R.id.job2location);
        job1salaryadjusted = (TextView) findViewById(R.id.job1salaryadjusted);
        job2salaryadjusted = (TextView) findViewById(R.id.job2salaryadjusted);
        job1bonusadjusted = (TextView) findViewById(R.id.job1bonusadjusted);
        job2bonusadjusted = (TextView) findViewById(R.id.job2bonusadjusted);
        job1gymmembership = (TextView) findViewById(R.id.job1gymmembership);
        job2gymmembership = (TextView) findViewById(R.id.job2gymmembership);
        job1leavetime = (TextView) findViewById(R.id.job1leavetime);
        job2leavetime = (TextView) findViewById(R.id.job2leavetime);
        job1401k = (TextView) findViewById(R.id.job1401k);
        job2401k = (TextView) findViewById(R.id.job2401k);
        job1petinsurance = (TextView) findViewById(R.id.job1petinsurance);
        job2petinsurance = (TextView) findViewById(R.id.job2petinsurance);

        Intent intent = getIntent();
        String[] selectedJob1 = intent.getStringArrayExtra("selectedJob1");
        String[] selectedJob2 = intent.getStringArrayExtra("selectedJob2");

        job1title.setText(selectedJob1[1]);
        job2title.setText(selectedJob2[1]);

        job1company.setText(selectedJob1[2]);
        job2company.setText(selectedJob2[2]);

        job1location.setText(selectedJob1[3]);
        job2location.setText(selectedJob2[3]);

        Float salaryadjusted1 = Float.parseFloat(selectedJob1[5]) * 100 /Float.parseFloat(selectedJob1[4]);
        job1salaryadjusted.setText(salaryadjusted1.toString());
        Float salaryadjusted2 = Float.parseFloat(selectedJob2[5]) * 100 /Float.parseFloat(selectedJob2[4]);
        job2salaryadjusted.setText(salaryadjusted2.toString());
        Float bonusadjusted1 = Float.parseFloat(selectedJob1[6]) * 100 /Float.parseFloat(selectedJob1[4]);
        job1bonusadjusted.setText(bonusadjusted1.toString());
        Float bonusadjusted2 = Float.parseFloat(selectedJob2[6]) * 100 /Float.parseFloat(selectedJob2[4]);
        job2bonusadjusted.setText(bonusadjusted2.toString());

        job1gymmembership.setText(selectedJob1[6]);
        job2gymmembership.setText(selectedJob2[6]);

        job1leavetime.setText(selectedJob1[7]);
        job2leavetime.setText(selectedJob2[7]);

        job1401k.setText(selectedJob1[8]);
        job2401k.setText(selectedJob2[8]);

        job1petinsurance.setText(selectedJob1[9]);
        job2petinsurance.setText(selectedJob2[9]);
    }

    public void goToMain(View view){
        startActivity(new Intent(CompareJobOfferDetailActivity.this,MainActivity.class));
    }

    public void goBackToCompare(View view){
        startActivity(new Intent(CompareJobOfferDetailActivity.this, CompareJobOfferActivity.class));
    }

}