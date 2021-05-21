package com.incubation_lab.edoctors.Login.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.incubation_lab.edoctors.Login.LoginActivity;
import com.incubation_lab.edoctors.Models.UserDataModel;
import com.incubation_lab.edoctors.R;
import com.incubation_lab.edoctors.Repository.Remote.RetroInstance;
import com.incubation_lab.edoctors.Repository.Remote.RetroInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.incubation_lab.edoctors.StaticData.STATUS_REGISTERED;


public class RegisterFragment extends Fragment implements View.OnClickListener {


    TextView dateOfBirth;
    Button signUpBtn;

    EditText firstName;
    EditText lastName;
    Spinner gender;
    EditText password;
    EditText re_password;
    EditText email;


    String phone;

    LoginViewModel loginViewModel;


    public RegisterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);

        phone = getArguments().getString("number");


        firstName=root.findViewById(R.id.first_name);
        lastName=root.findViewById(R.id.last_name);
        dateOfBirth=root.findViewById(R.id.date_of_birth);
        gender=root.findViewById(R.id.gender);
        password=root.findViewById(R.id.password);
        re_password=root.findViewById(R.id.retype_password);
        email=root.findViewById(R.id.email);
        signUpBtn =root.findViewById(R.id.sign_up);

        dateOfBirth.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);

        loginViewModel=new ViewModelProvider(this).get(LoginViewModel.class);




        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.date_of_birth:
                chooseDate();
                break;
            case R.id.sign_up:
                validateData();
        }
    }

    private void validateData() {
        Date doB;
        try {
            doB=new SimpleDateFormat("dd MMM,yyyy").parse(dateOfBirth.getText().toString());
        } catch (ParseException e) {
            dateOfBirth.setError("select date");
            dateOfBirth.requestFocus();
            return;
        }

        if(!checkIfEmpty(firstName)||!checkIfEmpty(lastName)||!checkIfEmpty(email)||!checkIfEmpty(password)||!checkIfEmpty(re_password)){
            if(!password.getText().toString().equals(re_password.getText().toString())){
                password.setError("Passwords Don't match");
                password.requestFocus();
                return;
            }
            String first_name=firstName.getText().toString();
            String last_name=lastName.getText().toString();
            String emailAddress=email.getText().toString();
            String passwordString=password.getText().toString();
            String re_passwordString=re_password.getText().toString();
            String genderString=gender.getSelectedItem().toString();

            loginViewModel.register(new UserDataModel(first_name,last_name,phone,emailAddress,dateOfBirth.getText().toString(),genderString,passwordString));

            loginViewModel.getLoginStatus().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    if(s.equals(STATUS_REGISTERED)){
                        Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                        ((LoginActivity) getActivity()).setFragment(new LoginFragment());
                    }
                }
            });

        }


    }

    private Boolean checkIfEmpty(EditText v) {
        if(v.getText().toString().isEmpty())
        {
            v.setError("Field Required");
            v.requestFocus();
            return true;
        }
        return false;
    }

    private void chooseDate() {
        final Calendar myCalendar = Calendar.getInstance();


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd MMM,yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateOfBirth.setText(sdf.format(myCalendar.getTime()));
            }

        };
        new DatePickerDialog(getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();



    }
}