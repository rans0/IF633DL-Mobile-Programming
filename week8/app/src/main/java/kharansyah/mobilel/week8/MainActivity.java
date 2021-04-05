package kharansyah.mobilel.week8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button tutorialSatu, tutorialDua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tutorialSatu = (Button) findViewById(R.id.btnTutorialSatu);
        tutorialDua = (Button) findViewById(R.id.btnTutorialDua);

        tutorialSatu.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Intent move = new Intent(MainActivity.this, StoragePracticeActivity.class);
               startActivity(move);
           }
        });

        tutorialDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(MainActivity.this, SavedShared.class);
                startActivity(move);
            }
        });

    }
}