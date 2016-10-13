package com.example.root.registration;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Precision.Component.FP.BiomSDK.BiometricComponent;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    Button scan;
    EditText USN;
    byte[] isoTemplate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BiometricComponent biometricComponent = new BiometricComponent(MainActivity.this);
        scan = (Button) findViewById(R.id.scan);
        USN = (EditText) findViewById(R.id.USN);

        scan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int Result = biometricComponent.FPCapture(1);
                if (Result == 0 && USN.getText().toString().equals("")) {
                    String filename = "fingerprints";
                    isoTemplate = biometricComponent.getISOTemplate();
                    String name = USN.getText().toString();
                    try {
                        FileOutputStream fOut = openFileOutput(filename, Context.MODE_PRIVATE);
                        fOut.write(name.getBytes());
                        fOut.close();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Execption in writing file", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });






    }
}
