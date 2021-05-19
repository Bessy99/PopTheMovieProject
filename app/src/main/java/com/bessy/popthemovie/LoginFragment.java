package com.bessy.popthemovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bessy.popthemovie.databinding.FragmentListeUtenteBinding;
import com.bessy.popthemovie.databinding.FragmentLoginBinding;
import com.bessy.popthemovie.utils.Constants;
import com.bessy.popthemovie.viewModel.LoginViewModel;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private static LoginFragment instance;
    private LoginViewModel viewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        if(instance==null){
            instance = new LoginFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registratiButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_signInFragment);
            }
        });

        binding.accediButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = binding.passwordLogin.getText().toString();
                String username = binding.userLogin.getText().toString();
                if(password!=null && username!=null) {
                    viewModel.userExist(username, password);
                }

            }
        });
        Observer<Boolean> exists = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean exists) {
                if(exists){
                    String password = binding.passwordLogin.getText().toString();
                    String username = binding.userLogin.getText().toString();
                    SharedPreferences sharedPref = getActivity().getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putString(Constants.SHARED_PREFERENCES_USERNAME, username);
                    editor.putString(Constants.SHARED_PREFERENCES_PASSWORD, password);

                    editor.apply();

                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_mainActivity);
                }
                else {
                    binding.userTextInputLayout.setError(" ");
                    binding.passwordTextInputLayout.setError("Username o password errate");
                }
            }
        };
        viewModel.exists().observe(getViewLifecycleOwner(),exists);

    }
}