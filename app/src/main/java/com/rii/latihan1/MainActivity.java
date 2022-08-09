package com.rii.latihan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //deklarasi sebuah variable filename.
    public static final String FILENAME = "namafile.txt";

    //deklarasi variable button.
    Button buatFile, bacaFile, ubahFile, hapusFile;
    //deklarasi variable textview baca, hasil yang akan ditampilkan saat read sebuah file.
    TextView tvBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi button yang telah didesain kedalam variable
        buatFile = findViewById(R.id.btnBuat);
        bacaFile = findViewById(R.id.btnBaca);
        ubahFile = findViewById(R.id.btnUbah);
        hapusFile = findViewById(R.id.btnHapus);

        //inisialisasi textview baca kedalam variable
        tvBaca = findViewById(R.id.tvBaca);

        //mengatur button saat diberi event klik
        buatFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        hapusFile.setOnClickListener(this);

        isExists();
    }


    //method untuk mengecek apakah ada filenya atau tidak
    void isExists()
    {
        File file = new File(getFilesDir(), FILENAME);
        if(!file.exists())
        {
            tvBaca.setText("");
            bacaFile.setEnabled(false);
            ubahFile.setEnabled(false);
            hapusFile.setEnabled(false);
        }
    }

    //method event klik untuk button buatfile
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

            bacaFile.setEnabled(true);
            ubahFile.setEnabled(true);
            hapusFile.setEnabled(true);
            Toast.makeText(MainActivity.this, "Berhasil buat file!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method event klik untuk button bacafile
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
        } else {
            tvBaca.setText("");
            Toast.makeText(this, "file tidak ada!", Toast.LENGTH_SHORT).show();
        }
    }

    void ubahFile()
    {
        String ubahText = "Update isi data file text.";
        File file = new File(getFilesDir(), FILENAME);

        if(file.exists()) {
            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file, false);
                outputStream.write(ubahText.getBytes());
                outputStream.flush();
                outputStream.close();
                Toast.makeText(MainActivity.this, "Berhasil ubah data file!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            Toast.makeText(this, "buat file terlebih dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusFile()
    {
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists())
        {
            file.delete();
            tvBaca.setText("");
            bacaFile.setEnabled(false);
            ubahFile.setEnabled(false);
            hapusFile.setEnabled(false);
            Toast.makeText(this, "File berhasil dihapus!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        //menjalankan method saat button di klik
        jalankanPerintah(view.getId());
    }

    //method yang digunakan saat method di klik
    public void jalankanPerintah(int id)
    {
        //kondisi jika button di klik
        switch (id) {
            case R.id.btnBuat:
                buatFile();
                break;
            case R.id.btnBaca:
                bacaFile();
                break;
            case R.id.btnUbah:
                ubahFile();
                break;
            case R.id.btnHapus:
                hapusFile();
                break;
        }
    }
}