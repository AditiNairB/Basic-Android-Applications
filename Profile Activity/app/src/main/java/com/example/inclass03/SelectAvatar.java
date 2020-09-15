package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectAvatar extends AppCompatActivity implements View.OnClickListener {

    public static final String GENDER = "GENDER";
    ImageView tv_female;
    ImageView tv_male;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        setTitle("Select Avatar");

        tv_female = findViewById(R.id.iv_female);
        tv_male = findViewById(R.id.iv_male);

        tv_female.setOnClickListener(this);
        tv_male.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == tv_female){
            Intent female = new Intent();
            female.putExtra(GENDER,"Female");
            setResult(RESULT_OK,female);
            finish();
        }else if(view == tv_male){
            Intent male = new Intent();
            male.putExtra(GENDER,"Male");
            setResult(RESULT_OK,male);
            finish();
        }
    }
}
