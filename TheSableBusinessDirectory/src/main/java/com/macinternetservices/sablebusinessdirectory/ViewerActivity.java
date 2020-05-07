package com.macinternetservices.sablebusinessdirectory;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bashizip.bhlib.BusinessHours;

import java.util.List;

public class ViewerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        //BusinessHoursWeekView businessHoursWeekView = findViewById(R.id.bh_view1);

        Intent intent = getIntent();
        List<BusinessHours> businessHoursList = (List<BusinessHours>) intent.getSerializableExtra(AddListingActivity.BH_LIST);

        //businessHoursWeekView.setModel(businessHoursList);
    }
}
