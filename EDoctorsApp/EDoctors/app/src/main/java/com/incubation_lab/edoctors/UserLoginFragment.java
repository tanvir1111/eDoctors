package com.incubation_lab.edoctors;

import android.os.Bundle;
import android.os.Handler;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;
import androidx.fragment.app.Fragment;

public class UserLoginFragment extends Fragment {
    public static Fragment newInstance() {
        return new UserLoginFragment();
    }

    private Placeholder logoPlaceHolder;
    private ImageView logo;
    private ConstraintLayout bottomContainer;
    private  ConstraintLayout parent;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_user_login, container, false);
        parent= root.findViewById(R.id.parent_container);
        bottomContainer = root.findViewById(R.id.user_login_bottom_container);
        bottomContainer.setVisibility(View.GONE);
        logo= root.findViewById(R.id.user_login_logo);
        logoPlaceHolder= root.findViewById(R.id.user_login_placeholder);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(parent);
                logoPlaceHolder.setContentId(R.id.user_login_logo);
                bottomContainer.setVisibility(View.VISIBLE);
            }
        },100);




        return root;
    }
}
