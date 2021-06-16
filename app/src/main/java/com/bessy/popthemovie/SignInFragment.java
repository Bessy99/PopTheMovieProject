package com.bessy.popthemovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bessy.popthemovie.databinding.FragmentSignInBinding;
import com.bessy.popthemovie.models.User;
import com.bessy.popthemovie.utils.Constants;
import com.bessy.popthemovie.viewModel.MainActivityViewModel;

import org.jetbrains.annotations.NotNull;

public class SignInFragment extends Fragment {
    private MainActivityViewModel viewModel;
    private static SignInFragment instance;
    private FragmentSignInBinding binding;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        if(instance==null){
            instance = new SignInFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        binding.registratiButtonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetErrorMessages();
                if(binding.nomeSignin.getText()!=null && binding.cognomeSignin.getText()!=null && binding.usernameSignin.getText()!=null && binding.passwordSignin.getText()!= null) {
                    String nome = binding.nomeSignin.getText().toString();
                    String cognome = binding.cognomeSignin.getText().toString();
                    String username = binding.usernameSignin.getText().toString();
                    String password = binding.passwordSignin.getText().toString();
                    if (!nome.equals("") && !cognome.equals("") && !username.equals("") && !password.equals("")) {
                        User u = new User(username, password, nome, cognome, null, null);
                        viewModel.saveUser(u);
                    } else {
                        if (nome.equals("")) {
                            binding.nomeTextInputLayoutSignin.setError("inserisci il tuo nome");
                            Log.d("d error", "error nome");
                        }
                        if (cognome.equals(""))
                            binding.cognomeTextInputLayoutSignin.setError("inserisci il tuo cognome");
                        if (username.equals(""))
                            binding.usernameTextInputLayoutSignin.setError("inserisci la tua email");
                        if (password.equals(""))
                            binding.passwordTextInputLayoutSignin.setError("inserisci la password");
                    }
                }
            }
        });

        Observer<User> userSaved = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null) {
                    String password = user.getPassword();
                    String username = user.getEmail();
                    SharedPreferences sharedPref = getActivity().getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putString(Constants.SHARED_PREFERENCES_USERNAME, username);
                    editor.putString(Constants.SHARED_PREFERENCES_PASSWORD, password);

                    editor.apply();

                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signInFragment_to_mainActivity);
                }
            }
        };

        viewModel.getUser().observe(getViewLifecycleOwner(),userSaved);
    }

    private void resetErrorMessages() {
        if(binding.passwordTextInputLayoutSignin.isErrorEnabled())
            binding.passwordTextInputLayoutSignin.setErrorEnabled(false);
        if(binding.nomeTextInputLayoutSignin.isErrorEnabled())
            binding.nomeTextInputLayoutSignin.setErrorEnabled(false);
        if(binding.usernameTextInputLayoutSignin.isErrorEnabled())
            binding.usernameTextInputLayoutSignin.setErrorEnabled(false);
        if(binding.cognomeTextInputLayoutSignin.isErrorEnabled())
            binding.cognomeTextInputLayoutSignin.setErrorEnabled(false);
    }

}