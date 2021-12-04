package com.uc.sejarahkita_mobile.view.LoginView;

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
import com.uc.sejarahkita_mobile.helper.SharedPreferenceHelper;
import com.uc.sejarahkita_mobile.view.MainActivity;

public class LoginFragment extends Fragment {

    TextView reg_btn;

    TextInputLayout email_login, pass_login;
    Button btn_login;

    private LoginViewModel loginViewModel;
    private SharedPreferenceHelper helper;

    public LoginFragment() {
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email_login = view.findViewById(R.id.email_input);
        pass_login = view.findViewById(R.id.pass_input);
        btn_login = view.findViewById(R.id.btn_login);

        reg_btn = view.findViewById(R.id.buttonReg);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        btn_login.setOnClickListener(view1 -> {
            if (!email_login.getEditText().getText().toString().isEmpty()
                    && !pass_login.getEditText().getText().toString().isEmpty()) {
                String email = email_login.getEditText().getText().toString().trim();
                String pass = pass_login.getEditText().getText().toString().trim();
                loginViewModel.login(email, pass).observe(requireActivity(), tokenResponse -> {
                    if (tokenResponse != null) {
                        helper.saveAccessToken(tokenResponse.getAuthorization());
                        NavDirections actions = LoginFragmentDirections.actionLoginFragmentToProfileFragment();
                        Navigation.findNavController(view1).navigate(actions);
                        Toast.makeText(requireActivity(), "Login Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(requireActivity(), "Please fill all field", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).getSupportActionBar().show();
    }
}