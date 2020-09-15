package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayProfile extends AppCompatActivity {


    ImageView profileImg;
    TextView name;
    TextView gender;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        setTitle("Display Profile");

        name = findViewById(R.id.tv_name);
        gender = findViewById(R.id.tv_gender);
        profileImg = findViewById(R.id.iv_displayPic);
        edit = findViewById(R.id.bt_edit);

        if(getIntent()!=null && getIntent().getExtras() != null){
            User user = (User) getIntent().getExtras().getSerializable(MainActivity.USER);
            name.setText("Name: " + user.getFirstName() + " " + user.getLastName());
            gender.setText(user.getGender());

            Log.d("demo",user.toString());


            if(user.getGender().equals("Female"))
                profileImg.setImageDrawable(getDrawable(R.drawable.female));
            if(user.getGender().equals("Male"))
                profileImg.setImageDrawable(getDrawable(R.drawable.male));
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
