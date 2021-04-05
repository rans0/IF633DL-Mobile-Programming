package kharansyah.mobilel.week8;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StoragePracticeActivity extends AppCompatActivity {
    private RadioGroup rgJenis;
    private EditText etFileName;
    private EditText etText;

    private Context context;
    private Button btnOpen;
    private static PopupMenu pilihFile;

    private File tempDir;
    private File localDir;
    private File externalDir;
    private File currentDir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        tempDir = getCacheDir();
        localDir = getFilesDir();
        if (Environment.MEDIA_MOUNTED.equals(
                Environment.getExternalStorageState())) {
            externalDir = getExternalFilesDir(null);
        } else {
            findViewById(R.id.rbExternal).setEnabled(false);
        }

        currentDir = localDir;
        rgJenis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                String pilihan = ((RadioButton)
                findViewById(rgJenis.getCheckedRadioButtonId())).getText().toString();

                if (pilihan.equalsIgnoreCase("Temporary")) currentDir = tempDir;
                else if (pilihan.equalsIgnoreCase("Internal")) currentDir = localDir;
                else currentDir = externalDir;
            }
        });

        context = this;
        btnOpen = (Button) findViewById(R.id.btnOpen);
        pilihFile = new PopupMenu(context, btnOpen);
        pilihFile.getMenuInflater().inflate(R.menu.menu_kosong, pilihFile.getMenu());
        pilihFile.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                etFileName.setText(item.getTitle().toString());
                etFileName.setText("");
                etText.setText("");
                return true;
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_storage_practice);
        rgJenis = (RadioGroup) findViewById(R.id.rgJenis);
        etFileName = (EditText) findViewById(R.id.etNamaFile);
        etText = (EditText) findViewById(R.id.etText);

    }

    protected void onDestroy() {
        super.onDestroy();
        // hapus semua file di cache
        File[] tempFiles = tempDir.listFiles();
        for (File temp : tempFiles) {
            if(temp.isFile()) temp.delete();
        }
    }


    protected void readFile() {
        if (etFileName.getText().toString().length() > 0 ) {
            File file = new File(currentDir, etFileName.getText().toString());
            String isiFile = "";
            try {
                InputStream inStream = new FileInputStream(file);
                if (inStream != null) {
                    InputStreamReader read = new InputStreamReader(inStream);
                    BufferedReader br = new BufferedReader(read);
                    String receiveString = "";
                    StringBuilder str = new StringBuilder();
                    while ((receiveString = br.readLine()) != null) {
                        str.append(receiveString).append("\n");
                    }
                    inStream.close();
                    isiFile = str.toString();
                    etText.setText(isiFile);
                }
            } catch (FileNotFoundException e) {
                Toast.makeText(context,"File tidak Ditemukan", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(context,"Error di I/O", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void openFile(View view){
        File[] files = null;
        if (currentDir != null) files = currentDir.listFiles();
        if (files != null) {
            int n = files.length;
            pilihFile.getMenu().clear();
            for (int i = 0; i < files.length; i++) {
                pilihFile.getMenu().add(files[i].getName());
                pilihFile.show();
                readFile();
            }
        } else {
            Toast.makeText(context, "Ada masalah akses folder" +
                    "atau folder masih kosong",Toast.LENGTH_LONG).show();
        }
    }

    public void saveFile(View view){
        String nFile = etFileName.getText().toString();
        String isiText = etText.getText().toString();
        if (nFile.length() > 0 && isiText.length() > 0 && currentDir != null) {
            File file = new File (currentDir, nFile);
            try {
                OutputStreamWriter writer = new OutputStreamWriter(
                        new FileOutputStream(file));
                writer.write(isiText);
                writer.close();
                Toast.makeText(this, "Text sudah tersimpan", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                Toast.makeText(this, "File tidak ditemukan", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(this, "Ada kesalahan I/O", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void deleteFile(View view){
        if (etFileName.getText().toString().length() > 0) {
            boolean success = false;
            if (currentDir != null && currentDir == localDir) {
                success = context.deleteFile(etFileName.getText().toString());
            } else {
                success = new File(currentDir, etFileName.getText().toString()).delete();
            }

            if (success) Toast.makeText(context, "File BERHASIL dihapus", Toast.LENGTH_LONG).show();
            else Toast.makeText(context, "File GAGAL dihapus", Toast.LENGTH_LONG).show();

            etFileName.setText("");
            etText.setText("");
        }
    }

    public void exitApp(View view) {
        finishAffinity();
    }

    public void clearText(View view) {
        etText.setText("");
        etFileName.setText("");
    }

}
