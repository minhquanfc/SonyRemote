package com.poly.sonyremote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button powerButton;
    private Button volumeUpButton;
    private Button volumeDownButton;
    private Button muteButton;
    private Button channelUpButton;
    private Button channelDownButton;
    private Button inputButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        powerButton = findViewById(R.id.power_button);
        volumeUpButton = findViewById(R.id.volume_up_button);
        volumeDownButton = findViewById(R.id.volume_down_button);
        muteButton = findViewById(R.id.mute_button);
        channelUpButton = findViewById(R.id.channel_up_button);
        channelDownButton = findViewById(R.id.channel_down_button);
        inputButton = findViewById(R.id.input_button);

        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("AAAAAQAAAAEAAAAvAw==");
            }
        });

        volumeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("AAAAAQAAAAEAAAASAw==");
            }
        });

        volumeDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("AAAAAQAAAAEAAAATAw==");
            }
        });

        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("AAAAAQAAAAEAAAAUAw==");
            }
        });

        channelUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("AAAAAQAAAAEAAAAQAw==");
            }
        });

        channelDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("AAAAAQAAAAEAAAARAw==");
            }
        });

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("AAAAAQAAAAEAAAAlAw==");
            }
        });
    }

    private void sendCommand(String command) {
        String url = "ircc://" + command;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.sony.dtv.osat");
        startActivity(intent);
    }
    private void setButtonEnabled(boolean enabled) {
        powerButton.setEnabled(enabled);
        volumeUpButton.setEnabled(enabled);
        volumeDownButton.setEnabled(enabled);
        muteButton.setEnabled(enabled);
        channelUpButton.setEnabled(enabled);
        channelDownButton.setEnabled(enabled);
        inputButton.setEnabled(enabled);
    }
    private boolean isSonyTVAvailable() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("ircc://"));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.sony.dtv.osat");
        List<ResolveInfo> activities = getPackageManager()
                .queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }
    private void updateButtonState() {
        boolean enabled = isSonyTVAvailable();
        setButtonEnabled(enabled);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtonState();
    }
}