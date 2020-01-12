package com.melnichuk.udpandroidclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText ipAddress;
    private EditText editText;
    private Button button;

    private UDPClient client;

    private static final String SERVER_ADDRESS = "192.168.0.101";
    private static final int SERVER_PORT = 9090;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipAddress = findViewById(R.id.ipAddress);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    client = new UDPClient(ipAddress.getText().toString().trim(), SERVER_PORT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            client.send(editText.getText().toString().trim());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
