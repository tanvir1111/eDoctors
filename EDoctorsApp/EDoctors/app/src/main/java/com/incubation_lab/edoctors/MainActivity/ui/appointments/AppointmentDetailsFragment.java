package com.incubation_lab.edoctors.MainActivity.ui.appointments;

import static com.incubation_lab.edoctors.Repository.Remote.RetroInstance.BASE_URL;
import static com.incubation_lab.edoctors.Repository.Remote.RetroInstance.getRetro;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.incubation_lab.edoctors.Models.AppointmentDataModel;
import com.incubation_lab.edoctors.R;
import com.squareup.picasso.Picasso;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class AppointmentDetailsFragment extends Fragment {

    private ProgressBar liveProgressBar;
    private ObjectAnimator progressAnimator;
    private int animCycleCount=0;
    private Button joinBtn;
    private String roomId;

    private TextView doctorName,doctorDesignation,doctorQualifications, tvCurrentSerial,tvUserSerial;
    private ImageView doctorImg;
    private AppointmentDataModel appointmentDataModel;
    private AppointmentsViewModel appointmentsViewModel;

    private Handler handler = new Handler();
    private Runnable runnable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_appointment_details,container,false);
        liveProgressBar = root.findViewById(R.id.mf_progress_bar);
        forwardAnimation();
        appointmentsViewModel = new ViewModelProvider(this).get(AppointmentsViewModel.class);

        joinBtn = root.findViewById(R.id.joinBtn);
        doctorName = root.findViewById(R.id.tv_chamber_doctor_name);
        doctorDesignation = root.findViewById(R.id.tv_chamber_doctor_designation);
        doctorQualifications = root.findViewById(R.id.tv_chamber_doctor_qualification);
        doctorImg = root.findViewById(R.id.iv_chamber_doctor_img);
        tvCurrentSerial = root.findViewById(R.id.tv_chamber_current_serial);
        tvUserSerial = root.findViewById(R.id.tv_chamber_user_serial);

        appointmentDataModel= (AppointmentDataModel) getArguments().getSerializable("appointmentData");

        doctorName.setText(appointmentDataModel.getDoctorDataModel().getName());
        doctorDesignation.setText(appointmentDataModel.getDoctorDataModel().getCurrentDesignation());
        doctorQualifications.setText(appointmentDataModel.getDoctorDataModel().getQualifications());
        tvUserSerial.setText(appointmentDataModel.getSerial());
        if(!appointmentDataModel.getDoctorDataModel().getImageUrl().equals("not set"))
            Picasso.get().load( BASE_URL +"/" + appointmentDataModel.getDoctorDataModel().getImageUrl()).into(doctorImg);
        setupConference();

        joinBtn.setEnabled(false);


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinConference();
            }
        });

        handler.postDelayed(runnable =new Runnable() {
            @Override
            public void run() {
                setCurrentSerial();


            }
        },10);




        return root;
    }

    private void reverseAnimation(){
        animCycleCount++;
        liveProgressBar.setRotation(animCycleCount*180);
        if (animCycleCount ==2)
        {
            animCycleCount=0;
        }
        progressAnimator = ObjectAnimator.ofInt(liveProgressBar,"progress",100,0);
        progressAnimator.start();
        progressAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                forwardAnimation();


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void forwardAnimation(){
        progressAnimator = ObjectAnimator.ofInt(liveProgressBar,"progress",0,100);
        progressAnimator.setDuration(1000);
        progressAnimator.start();

        progressAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {


            }

            @Override
            public void onAnimationEnd(Animator animation) {



                reverseAnimation();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void setupConference(){

        roomId= appointmentDataModel.getLink();




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





    }
    private void joinConference(){
        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                .setRoom(roomId)
                .setFeatureFlag("invite.enabled", false)
                .setFeatureFlag("meeting-name.enabled", false)
                .setFeatureFlag("help.enabled",false)
                .setAudioMuted(true)
                .setVideoMuted(true)
                .setWelcomePageEnabled(false)
                .build();

        JitsiMeetActivity.launch(getContext(), options);
    }

    private void setCurrentSerial(){
        try {
            appointmentsViewModel.getCurrentSerial(appointmentDataModel.getDoctorId()).observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    tvCurrentSerial.setText(s);
                    int distance = Integer.parseInt(appointmentDataModel.getSerial())-Integer.parseInt(s);
                    if(distance >=0 && distance <3 ){
                        joinBtn.setEnabled(true);
                    }
                    else {
                        joinBtn.setEnabled(false);
                    }
                }
            });
            handler.postDelayed(runnable,10000);
        } catch (Exception e){

            e.printStackTrace();

        }

    }

}
