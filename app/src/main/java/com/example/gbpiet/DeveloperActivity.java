package com.example.gbpiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DeveloperActivity extends AppCompatActivity {


    private ImageView myPht, Instagram, githubImage, linkedinImage;
    private TextView instagram, github, linkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        getSupportActionBar().setTitle("About Developer");
        myPht = findViewById(R.id.myPht);
        Instagram = findViewById(R.id.Instagram);
        githubImage = findViewById(R.id.githubImage);
        linkedinImage = findViewById(R.id.linkedinImage);


        instagram = findViewById(R.id.instagram);
        github = findViewById(R.id.github);
        linkedIn = findViewById(R.id.linkedIn);

        openInsta();
        openGithub();
        openLinkedIn();

    }


    private void openLinkedIn() {
        linkedIn.setMovementMethod(LinkMovementMethod.getInstance());
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkedinIntent = new Intent(Intent.ACTION_VIEW);
                linkedinIntent.setData(Uri.parse("https://www.linkedin.com/in/krishna-pant-139883155"));
                startActivity(linkedinIntent);
            }
        });
    }


    private void openGithub() {


        github.setMovementMethod(LinkMovementMethod.getInstance());
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent githubIntent = new Intent(Intent.ACTION_VIEW);
                githubIntent.setData(Uri.parse("https://github.com/krishnapant13"));
                startActivity(githubIntent);
            }
        });
    }



    private void openInsta() {

        instagram.setMovementMethod(LinkMovementMethod.getInstance());
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instaIntent = new Intent(Intent.ACTION_VIEW);
                instaIntent.setData(Uri.parse("https://www.instagram.com/nth__user"));
                startActivity(instaIntent);
            }
        });
    }

}
