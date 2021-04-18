package kharansyah.mobileumn.week10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnTutorialSatu, btnTutorialDua, btnTutorialTiga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTutorialSatu = findViewById(R.id.tutorialSatu);
        btnTutorialDua = findViewById(R.id.tutorialDua);
        btnTutorialTiga = findViewById(R.id.tutorialTiga);

        btnTutorialSatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TutorialSatu.class));
            }
        });

        btnTutorialDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TutorialDua.class));
            }
        });

        btnTutorialTiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TutorialTiga.class));
            }
        });
    }

}