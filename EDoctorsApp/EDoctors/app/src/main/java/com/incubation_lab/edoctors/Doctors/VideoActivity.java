package com.incubation_lab.edoctors.Doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.incubation_lab.edoctors.Models.AppointmentDataModel;
import com.incubation_lab.edoctors.R;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class VideoActivity extends AppCompatActivity {
    private Button joinBtn;
    private String roomId;
    private AppointmentDataModel appointmentDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        appointmentDataModel= (AppointmentDataModel) getIntent().getSerializableExtra("appointmentData");
        roomId= appointmentDataModel.getLink();


        joinBtn = findViewById(R.id.joinBtn);

        URL serverURL;


        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }





        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(roomId)
                        .setFeatureFlag("invite.enabled", false)
                        .setFeatureFlag("meeting-name.enabled", false)
                        .setFeatureFlag("help.enabled",false)
                        .setAudioMuted(true)
                        .setVideoMuted(true)
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(VideoActivity.this, options);
            }
        });
    }
}