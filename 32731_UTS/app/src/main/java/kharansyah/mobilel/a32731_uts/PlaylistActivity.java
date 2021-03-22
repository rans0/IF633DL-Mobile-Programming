package kharansyah.mobilel.a32731_uts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static kharansyah.mobilel.a32731_uts.HomeActivity.musicFiles;
import static kharansyah.mobilel.a32731_uts.LoginActivity.hasLogin;

public class PlaylistActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LaguAdapter laguAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_lagu);
        getSupportActionBar().setTitle("Daftar Musik");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.listLagu);
        recyclerView.setHasFixedSize(true);

        if (!(musicFiles.size() < 1)){
            laguAdapter = new LaguAdapter(this, musicFiles);
            recyclerView.setAdapter(laguAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }

        if(hasLogin){
            showStartDialog();
            hasLogin = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if (id == R.id.menuProfile){
            Intent intent = new Intent(PlaylistActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.menuLogOut){
            Intent intent = new Intent(PlaylistActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showStartDialog(){
        new AlertDialog.Builder(this).setTitle("Selamat Datang").setMessage("Kharansyah TS - 00000032731")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

}
