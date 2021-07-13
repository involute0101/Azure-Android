package com.example.azureapp.ui.user;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.azureapp.LoginActivity;
import com.example.azureapp.R;

public class UserFragment extends Fragment {

    private UserViewModel mViewModel;
    Button quitButton;
    TextView userEmailTV,userInfoTV;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        // TODO: Use the ViewModel
        quitButton = requireActivity().findViewById(R.id.quit_button);
        userEmailTV = requireActivity().findViewById(R.id.user_email_tv);
        userInfoTV = requireActivity().findViewById(R.id.user_info_tv);

        /*userEmailTV.setText("");
        userInfoTV.setText("");*/

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}