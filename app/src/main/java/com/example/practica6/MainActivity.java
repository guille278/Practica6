package com.example.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText info;
    private static final String ARCHIVO = "guardado.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = findViewById(R.id.et_info);
        String[] archivos = fileList();

        if (archivoExiste(archivos, ARCHIVO)) {
            try {
                InputStreamReader reader = new InputStreamReader(openFileInput(ARCHIVO));
                BufferedReader buffer = new BufferedReader(reader);
                String data = "";
                String line = buffer.readLine();
                while (line != null) {
                    data += line+"\n";
                    line = buffer.readLine();
                }
                buffer.close();
                reader.close();
                info.setText(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean archivoExiste(String tarch[], String archivo) {
        for (int i = 0; i < tarch.length; i++) {
            if (archivo.equals(tarch[i])) return true;
        }
        return false;
    }

    public void guardar(View view){
        try {
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(ARCHIVO, MODE_PRIVATE));
            writer.write(info.getText().toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Archivo guardado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}