package com.example.azureapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.databinding.FragmentHomeBinding;
import com.example.azureapp.ui.database.DataBaseActivity;
import com.example.azureapp.ui.virtualmachine.VirtualMachineActivity;

/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 */

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    ImageView vmImg,dbImg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vmImg = root.findViewById(R.id.img_vm);
        dbImg = root.findViewById(R.id.img_sql);

        vmImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VirtualMachineActivity.class);
                startActivity(intent);
            }
        });

        dbImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataBaseActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}