package com.example.rikit.bmicalc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbarmain);
        setSupportActionBar(toolbar);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolBarBlue)));

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setStatusBarColor(getResources().getColor(R.color.blueStatus));

        TextView title = (TextView) toolbar.findViewById(R.id.textView);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Tip.class);
                startActivity(intent);
            }
        });
    }

    public void getBMI(View v){
        final TableLayout layout = (TableLayout) findViewById(R.id.tableLayout);
        final EditText weight = (EditText) findViewById(R.id.weight);
        TextView result = (TextView) findViewById(R.id.result);
        TextView numMeaning = (TextView) findViewById(R.id.numMeaning);
        weight.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            public void onFocusChange( View v, boolean hasFocus){
                if(hasFocus){
                    weight.setText("", TextView.BufferType.EDITABLE);
                    layout.setVisibility(View.INVISIBLE);
                }
            }
        });
        final EditText height = (EditText) findViewById(R.id.height);
        height.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            public void onFocusChange( View v, boolean hasFocus){
                if(hasFocus){
                    height.setText("", TextView.BufferType.EDITABLE);
                    layout.setVisibility(View.INVISIBLE);
                }
            }
        });

        DecimalFormat df = new DecimalFormat("#.0");

        double numWeight = Double.parseDouble(weight.getText().toString());
        double numHeight = Double.parseDouble(height.getText().toString());

        double numResult = CalcBMI(numWeight, numHeight);
        String finalResult = df.format(numResult);

        layout.setVisibility(View.VISIBLE);
        result.setText(finalResult);
        numMeaning.setText(BMIRange(numResult));
    }
    public double CalcBMI(double w, double h)
    {
        return (w/Math.pow(h, 2)) * 703;
    }
    public String BMIRange(double bmi){
        if(bmi < 18.5) {
            return "UnderWeight";
        }
        else if(bmi >= 18.5 && bmi <= 24.9) {
            return "Normal Weight";
        }
        else if(bmi >= 25 && bmi <= 29.9){
            return "OverWeight";
        }
        else{
            return "Obese";
        }
    }
}
