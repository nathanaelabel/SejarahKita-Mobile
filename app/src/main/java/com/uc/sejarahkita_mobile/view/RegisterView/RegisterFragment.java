package com.uc.sejarahkita_mobile.view.RegisterView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.sejarahkita_mobile.R;

public class RegisterFragment extends Fragment {
    TextView login_btn;

    TextInputLayout email_register, pass_register, cpass_register, username_register, name_register, school_register, city_register, birthyear_register;
    Button btn_reg;

    private RegisterViewModel registerViewModel;

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login_btn = getActivity().findViewById(R.id.buttonLog);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

        email_register = view.findViewById(R.id.email_register);
        pass_register = view.findViewById(R.id.pass_register);
        cpass_register = view.findViewById(R.id.cpass_register);
        username_register = view.findViewById(R.id.username_register);
        name_register = view.findViewById(R.id.name_register);
        school_register = view.findViewById(R.id.school_register);
        city_register = view.findViewById(R.id.city_register);
        birthyear_register = view.findViewById(R.id.birthyear_register);
        btn_reg = view.findViewById(R.id.btn_reg);

        registerViewModel = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email_register.getEditText().getText().toString().isEmpty()
                        && !pass_register.getEditText().getText().toString().isEmpty()
                        && !cpass_register.getEditText().getText().toString().isEmpty()
                        && !username_register.getEditText().getText().toString().isEmpty()
                        && !name_register.getEditText().getText().toString().isEmpty()
                        && !school_register.getEditText().getText().toString().isEmpty()
                        && !city_register.getEditText().getText().toString().isEmpty()
                        && !birthyear_register.getEditText().getText().toString().isEmpty()) {
                    String email = email_register.getEditText().getText().toString().trim();
                    String pass = pass_register.getEditText().getText().toString().trim();
                    String cpass = cpass_register.getEditText().getText().toString().trim();
                    String username = username_register.getEditText().getText().toString().trim();
                    String name = name_register.getEditText().getText().toString().trim();
                    String school = school_register.getEditText().getText().toString().trim();
                    String city = city_register.getEditText().getText().toString().trim();
                    String birthyear = birthyear_register.getEditText().getText().toString().trim();
                    registerViewModel.register(email, pass, cpass, username, name, school, city, birthyear).observe(requireActivity(), registerResponse -> {
                        if (registerResponse != null) {
                            NavDirections actions = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment();
                            Navigation.findNavController(view).navigate(actions);
                            Toast.makeText(requireActivity(), "Register Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity(), "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(requireActivity(), "Please fill all field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}