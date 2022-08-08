package com.rii.latihan1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String FILENAME = "namafile.txt";
    Button buatFile, bacaFile, ubahFile, hapusFile;
    TextView tvBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buatFile = findViewById(R.id.btnBuat);
        bacaFile = findViewById(R.id.btnBaca);
        ubahFile = findViewById(R.id.btnUbah);
        hapusFile = findViewById(R.id.btnHapus);

        tvBaca = findViewById(R.id.tvBaca);

        buatFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        hapusFile.setOnClickListener(this);
    }

    void buatFile()
    {
        String isiFile = "Coba isi Data File Text.";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(MainActivity.this, "Berhasil! buat file.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void bacaFile()
    {
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while(line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            tvBaca.setText(text.toString());
        }
    }

    void ubahFile()
    {

    }
    void HapusFile()
    {

    }

    @Override
    public void onClick(View view) {
        jalankanPerintah(view.getId());
    }

    public void jalankanPerintah(int id)
    {
        switch (id) {
            case R.id.btnBuat:
                buatFile();
                break;
            case R.id.btnBaca:
                bacaFile();
                break;
        }
    }
}