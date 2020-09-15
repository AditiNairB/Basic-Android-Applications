/**
 * Assigment : In Class 04
 * Group No  : 9
 * Name      : Aditi Balachandran, Luckose Manuel
 */

package com.example.inclass04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private SeekBar seekbar;
    private TextView tv_min;
    private TextView tv_max;
    private TextView tv_avg;
    private TextView tv_complexity;
    private Button button_calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = findViewById(R.id.seekBar);
        button_calc = findViewById(R.id.button);
        tv_min = findViewById(R.id.tv_min);
        tv_max = findViewById(R.id.tv_max);
        tv_avg = findViewById(R.id.tv_avg);
        tv_complexity = findViewById(R.id.tv_complexityValue);
        progressBar = findViewById(R.id.progressBar);

        tv_complexity.setText("0 Times");
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("demo",i+"");
                tv_complexity.setText(i+" Times");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int complexity = seekbar.getProgress();
                if(0!=complexity) {
                    new GetNumbers().execute(complexity);
                }else{
                    Toast.makeText(MainActivity.this,"Complexity cannot be 0",Toast.LENGTH_SHORT).show();
                    tv_min.setText("Minimum:    ");
                    tv_avg.setText("Average:     ");
                    tv_max.setText("Maximum:   ");
                }

            }
        });


    }

    class GetNumbers extends AsyncTask<Integer, Void, ArrayList<Double>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected ArrayList<Double> doInBackground(Integer... integers) {
            return HeavyWork.getArrayNumbers(integers[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Double> doubles) {
            super.onPostExecute(doubles);
            Log.d("demo","onPostExecute" + doubles.toString());
            progressBar.setVisibility(ProgressBar.INVISIBLE);

            double min = findMin(doubles);
            double max = findMax(doubles);
            double avg = findAvg(doubles);

            Log.d("demo","onPostExecute" + min);
            Log.d("demo","onPostExecute" + max);

            tv_min.setText("Minimum:    "+ String.format("%.8f",min));
            tv_avg.setText("Average:      "+ String.format("%.8f",avg));
            tv_max.setText("Maximum:   "+ String.format("%.8f",max));
        }
    }

    private double findMin(ArrayList<Double> doubles) {

        double min = Double.MAX_VALUE;
        for(double d: doubles){
            if(min > d){
                min = d;
            }
        }
        return min;
    }
    private double findAvg(ArrayList<Double> doubles) {

        double sum = 0.0;
        int count = 0;
        for(double d: doubles){
            sum += d;
            count++;
        }
        return sum/count;
    }
    private double findMax(ArrayList<Double> doubles) {

        double max = doubles.get(0);
        for(double d: doubles){
            if(max < d){
                max = d;
            }
        }
        return max;
    }
}
