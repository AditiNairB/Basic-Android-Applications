/**
 * Assignment   : InClass02
 * File Name    : MainActivity.java
 * Full Name    : Aditi Balachandran and Luckose Manuel
 * Group Number : 9
 */

package com.example.inclass02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText et_height_inches;
    private EditText et_height_feet;
    private EditText et_weight;

    private TextView tv_result;
    private TextView tv_verdict;

    private Button button_calculate;

    DecimalFormat df = new DecimalFormat("##.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_weight = findViewById(R.id.et_weight);
        et_height_feet = findViewById(R.id.et_height_feet);
        et_height_inches = findViewById(R.id.et_height_inches);

        tv_result = findViewById(R.id.tv_result);
        tv_verdict = findViewById(R.id.tv_verdict);

        button_calculate = findViewById(R.id.buttonCalculate);

        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                final double[] BMI = {0.0};

                final double[] weight = {0.0};
                final int[] feet = {0};
                final int[] inches ={0};

                String weight_text = null!=et_weight.getText()? et_weight.getText().toString():"";
                String feet_text = null!=et_height_feet.getText()? et_height_feet.getText().toString():"";
                String inches_text = null!=et_height_inches.getText()?et_height_inches.getText().toString():"";

                if(weight_text.equals("")){
                    et_weight.setError("Can't be empty");
                    Toast.makeText(MainActivity.this,"Invalid Inputs", Toast.LENGTH_SHORT).show();
                    tv_result.setText("Your BMI: Not Calculated");
                    tv_verdict.setText("No Result");
                    return;
                }else if(!weight_text.equals("")){
                    try {
                        weight[0] = Double.parseDouble(weight_text);
                    }catch(Exception e) {
                        Log.d("demo", "ERROR: " + e.getMessage());
                        et_weight.setError("Value is too long");
                        Toast.makeText(MainActivity.this,"Invalid Inputs", Toast.LENGTH_SHORT).show();
                        tv_result.setText("Your BMI: Not Calculated");
                        tv_verdict.setText("No Result");
                        return;
                    }

                }
                if(feet_text.equals("")){
                    et_height_feet.setError("Can't be empty");
                    Toast.makeText(MainActivity.this,"Invalid Inputs", Toast.LENGTH_SHORT).show();
                    tv_result.setText("Your BMI: Not Calculated");
                    tv_verdict.setText("No Result");
                    return;
                }else if(!et_height_feet.equals("")){
                    try {
                          feet[0] = Integer.parseInt(feet_text);
                      }catch(Exception e) {
                        Log.d("demo", "ERROR: " + e.getMessage());
                        et_height_feet.setError("Value is too long");
                        Toast.makeText(MainActivity.this,"Invalid Inputs", Toast.LENGTH_SHORT).show();
                        tv_result.setText("Your BMI: Not Calculated");
                        tv_verdict.setText("No Result");
                        return;
                    }
                }
                if(inches_text.equals("")){
                    et_height_inches.setError("Can't be empty");
                    Toast.makeText(MainActivity.this,"Invalid Inputs", Toast.LENGTH_SHORT).show();
                    tv_result.setText("Your BMI: Not Calculated");
                    tv_verdict.setText("No Result");
                    return;
                }else if(!inches_text.equals("")){
                    try {
                        inches[0] = Integer.parseInt(inches_text);
                    }catch(Exception e){
                        Log.d("demo","ERROR: " + e.getMessage());
                        et_height_inches.setError("Value is too long");
                        Toast.makeText(MainActivity.this,"Invalid Inputs", Toast.LENGTH_SHORT).show();
                        tv_result.setText("Your BMI: Not Calculated");
                        tv_verdict.setText("No Result");
                        return;
                    }
                }

                //BMI = (Weight/(inches*inches))*703
                //1 foot = 12inches

                try {
                    inches[0] = inches[0] + feet[0] * 12;
                }catch(Exception e){
                    Log.d("demo","ERROR: " + e.getMessage());
                    et_height_inches.setError("Value is too long");
                    et_height_feet.setError("Value is too long");
                    Toast.makeText(MainActivity.this,"Invalid Inputs", Toast.LENGTH_SHORT).show();
                    tv_result.setText("Your BMI: Not Calculated");
                    tv_verdict.setText("No Result");
                    return;
                }

                if(0.0!=weight[0] && 0!=inches[0]) {
                    BMI[0] = (weight[0] / (inches[0] * inches[0])) * 703;
                }
                Log.d("demo","Weight= "+weight[0]+" Feet= "+feet[0]+" Inches"+inches[0]);

                if(0.0<BMI[0] && 1.0>BMI[0]){
                    tv_result.setText("Your BMI: 0" + df.format(BMI[0]));
                }
                else if(1.0<=BMI[0]) {
                    tv_result.setText("Your BMI: " + df.format(BMI[0]));
                }else if(0.0>=BMI[0]){
                    tv_result.setText("Your BMI: 0.0");
                }else{
                    tv_result.setText("Your BMI: Not Calculated");
                }


                if(0.0<BMI[0]){
                    Toast.makeText(MainActivity.this,"BMI Calculated", Toast.LENGTH_SHORT).show();
                if(BMI[0]<18.5){
                    tv_verdict.setText("You are Underweight");
                }else if(BMI[0]<=24.9){
                    tv_verdict.setText("You are Normal Weight");
                }else if(BMI[0]<=29.9){
                    tv_verdict.setText("You are Overweight");
                }else if(BMI[0]>=30){
                    tv_verdict.setText("You are Obese");
                }}else{
                    tv_verdict.setText("No Result");
                }

            }
        });

    }
}
