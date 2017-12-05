package com.altran.brastlewark.main.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.altran.brastlewark.R;
import com.altran.brastlewark.common.view.BaseView;
import com.altran.brastlewark.user.domain.model.Filters;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.altran.brastlewark.main.view.activity.MainActivity.MAX_AGE;
import static com.altran.brastlewark.main.view.activity.MainActivity.MIN_AGE;

/**
 * Created by Athmos on 02/12/2017.
 */

public class FilterActivity extends AppCompatActivity implements BaseView {

    @BindView(R.id.Max_Age_Value_Min)
    protected TextView maxAgeValueMin;

    @BindView(R.id.Max_Age_Value_Max)
    protected TextView maxAgeValueMax;

    @BindView(R.id.Min_Age_Value_Min)
    protected TextView minAgeValueMin;

    @BindView(R.id.Min_Age_Value_Max)
    protected TextView minAgeValueMax;

    @BindView(R.id.Age_Max)
    protected TextView ageMax;

    @BindView(R.id.Age_Min)
    protected TextView ageMin;

    @BindView(R.id.Seek_Age_Min)
    protected SeekBar seekAgeMin;

    @BindView(R.id.Seek_Age_Max)
    protected SeekBar seekAgeMax;

    private int maxAge;
    private Filters filters = new Filters();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        Intent mIntent = getIntent();
        maxAge = mIntent.getIntExtra(MAX_AGE, 0);
        ButterKnife.bind(this);
        onInit();
    }

    @Override
    public void onInit() {
        filters.setMaxAge(maxAge);
        filters.setMinAge(0);
        maxAgeValueMin.setText("0");
        minAgeValueMin.setText("0");
        maxAgeValueMax.setText(maxAge + "");
        minAgeValueMax.setText(maxAge + "");
        ageMax.setText(maxAge + "");
        ageMin.setText("0");
        seekAgeMin.setMax(maxAge);
        seekAgeMax.setMax(maxAge);
        seekAgeMax.setProgress(maxAge);
        setSeekBarsListeners();
    }

    private void setSeekBarsListeners() {
        seekAgeMax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageMax.setText(progress + "");
                if (progress < seekAgeMin.getProgress()) {
                    if (progress > 0)
                        seekAgeMin.setProgress(progress - 1);
                }
                filters.setMaxAge(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        seekAgeMin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageMin.setText(progress + "");
                if (progress > seekAgeMax.getProgress()) {
                    if (progress < maxAge)
                        seekAgeMax.setProgress(progress + 1);
                }
                filters.setMinAge(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @OnClick(R.id.Button_Apply)
    public void onClickApply() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(MAX_AGE, filters.getMaxAge());
        resultIntent.putExtra(MIN_AGE, filters.getMinAge());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
