package com.incubationlab.edoctors_doctors.Main.ui.Appointments;

import static com.incubationlab.edoctors_doctors.Repository.RemoteAPI.RetroInstance.BASE_URL;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.incubationlab.edoctors_doctors.Models.AppointmentDataModel;
import com.incubationlab.edoctors_doctors.R;
import com.incubationlab.edoctors_doctors.Repository.RemoteRequestInterface;
import com.squareup.picasso.Picasso;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class AppointmentDetailsFragment extends Fragment {

    private TextView tvName,tvAge,tvGender,tvSerial;
    private ImageView ivPatientImage;
    private AppointmentDataModel appointmentData;
    private String roomId;
    private Button joinBtn;
    private AppointmentsViewModel appointmentsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root =inflater.inflate(R.layout.fragment_appointment_details, container, false);

        appointmentsViewModel = new ViewModelProvider(this).get(AppointmentsViewModel.class);

        tvName = root.findViewById(R.id.appointment_details_patient_name);
        tvAge = root.findViewById(R.id.appointment_details_patient_age);
        tvGender = root.findViewById(R.id.appointment_details_patient_gender);
        tvSerial = root.findViewById(R.id.appointment_details_appointment_serial);
        ivPatientImage = root.findViewById(R.id.appointment_details_patient_image);

        appointmentData = (AppointmentDataModel) getArguments().getSerializable("appointmentData");
        tvName.setText(appointmentData.getPatientData().getFirstName() + " " +appointmentData.getPatientData().getLastName());
        tvAge.setText(appointmentData.getPatientData().getAge());
        tvGender.setText(appointmentData.getPatientData().getGender());
        tvSerial.setText(appointmentData.getSerial());
        Picasso.get().load(BASE_URL+ "/"+appointmentData.getPatientData().getImageUrl()).into(ivPatientImage);
        roomId= appointmentData.getLink();



        joinBtn = root.findViewById(R.id.joinBtn);



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
                        .setFeatureFlag("raise-hand.enabled",false)
                        .setFeatureFlag("recording.enabled",false)
                        .setFeatureFlag("android.screensharing.enabled",false)
                        .setFeatureFlag("live-streaming.enabled",false)
                        .setFeatureFlag("lobby-mode.enabled",false)
                        .setAudioMuted(true)
                        .setVideoMuted(true)
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(getContext(), options);

                appointmentsViewModel.updateCurrentSerial(new AppointmentDataModel(appointmentData.getDoctorId(), appointmentData.getSerial()), new RemoteRequestInterface() {
                    @Override
                    public void onSuccess(int code, String msg) {
                        Log.d("Update Serial","Updated");
                    }

                    @Override
                    public void onFailure(String msg) {
                        appointmentsViewModel.updateCurrentSerial(new AppointmentDataModel(appointmentData.getDoctorId(),appointmentData.getSerial()),this);
                    }
                });

            }
        });



        return root;
    }
}