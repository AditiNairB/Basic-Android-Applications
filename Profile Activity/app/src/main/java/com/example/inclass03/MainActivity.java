/**
 * Assigment    : InClass03
 * Group No     : 9
 * Group Members: Aditi Balachandran, Luckose Manuel
 */

package com.example.inclass03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    ImageView profilePhoto;
    User user = new User();
    Button save;

    public static final String USER = "USER";

    public static Integer REQ_CODE_SELECT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("My Profile");

        firstName   = findViewById(R.id.et_firstname);
        lastName    = findViewById(R.id.et_lastname);
        profilePhoto = findViewById(R.id.iv_profile);

        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectAvatar.class);
                startActivityForResult(intent, REQ_CODE_SELECT);
            }
        });

        Log.d("demo","profile photo done");
        save = findViewById(R.id.bt_savebutton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
                Matcher matcher = pattern.matcher(firstName.getText().toString());


                if(firstName.getText().toString().isEmpty()){
                    firstName.setError("CANNOT BE EMPTY");
                    return;
                }if(lastName.getText().toString().isEmpty()){
                    lastName.setError("CANNOT BE EMPTY");
                    return;
                }if(user.getGender().equalsIgnoreCase("Select Gender")){
                    Toast.makeText(MainActivity.this,"Select Avatar",Toast.LENGTH_LONG).show();
                    return;
                }if(firstName.getText().toString().matches("\\d+\\S*")){
                    firstName.setError("Name cannot start with numbers");
                    return;
                }else if(firstName.getText().toString().matches("\\d*\\S*\\d+\\S+") ||
                        firstName.getText().toString().matches("\\d*\\S*\\d+\\S+\\d+")){
                    firstName.setError("Name cannot have numbers inside");
                    return;
                }else if(matcher.find()){
                    firstName.setError("Cannot have special characters");
                    return;
                }matcher = pattern.matcher(lastName.getText().toString());
                if(lastName.getText().toString().matches("\\d+\\S*")){
                    lastName.setError("Name cannot start with numbers");
                    return;
                }else if(lastName.getText().toString().matches("\\d*\\S*\\d+\\S+") ||
                        lastName.getText().toString().matches("\\d*\\S*\\d+\\S+\\d+")){
                    lastName.setError("Name cannot have numbers inside");
                    return;
                }else if(matcher.find()){
                    lastName.setError("Cannot have special characters");
                    return;
                }
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                Intent display = new Intent(MainActivity.this, DisplayProfile.class);
                display.putExtra(USER,user);
                startActivity(display);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQ_CODE_SELECT && resultCode == RESULT_OK && data !=null){
            String gender = data.getExtras().getString(SelectAvatar.GENDER);
            user.setGender(gender);
            if(gender.equals("Male")){
                profilePhoto.setImageDrawable(getDrawable(R.drawable.male));
            }
            if(gender.equals("Female")){
                profilePhoto.setImageDrawable(getDrawable(R.drawable.female));
            }
        }
    }
}
