package kharansyah.mobilel.a32731_uts;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText uname, password;
    private AppCompatButton login;
    static boolean hasLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        uname = findViewById(R.id.uname);
        password = findViewById(R.id.password);
        login = (AppCompatButton) findViewById(R.id.btnLogin);

        getSupportActionBar().setTitle("Daftar Musik");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(uname.getText().toString()) ||
                        TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if(Objects.equals(uname.getText().toString(), "uasmobile")
                && Objects.equals(password.getText().toString(), "uasmobilegenap")) {
                    hasLogin = true;
                    openPlayList();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Username atau Password Salah", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openPlayList() {
        Intent intent = new Intent(this, PlaylistActivity.class);
        startActivity(intent);
    }

}
