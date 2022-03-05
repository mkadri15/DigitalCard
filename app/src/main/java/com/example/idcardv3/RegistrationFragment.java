package com.example.idcardv3;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private EditText Name,UserName,UserPassword,UserEmail,UserPhone;
    private Button BnRegister;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        Name = view.findViewById(R.id.txt_name);
        UserName = view.findViewById(R.id.txt_username);
        UserPassword = view.findViewById(R.id.txt_password);
        UserEmail = view.findViewById(R.id.txt_email);
        UserPhone = view.findViewById(R.id.txt_phone);
        BnRegister = view.findViewById(R.id.button);

        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performRegistration();

            }
        });

        return view;
    }

    public void performRegistration()
    {

        String name = Name.getText().toString();
        String username = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        String email = UserEmail.getText().toString();
        String phone = UserPhone.getText().toString();

        Call<User> call = MainActivity.apiInterface.performRegistration(name,username,password,email,phone);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("Registration success...");


                }
                else if (response.body().getResponse().equals("exist"))
                {
                    MainActivity.prefConfig.displayToast("User already exist...");
                }
                else if (response.body().getResponse().equals("error"))
                {
                    MainActivity.prefConfig.displayToast("Something went wrong...");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        Name.setText("");
        UserName.setText("");
        UserPassword.setText("");
        UserEmail.setText("");
        UserPhone.setText("");
    }

}
