package com.example.azureapp.ui.home;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.MainActivity;
import com.example.azureapp.R;
import com.example.azureapp.databinding.FragmentHomeBinding;
import com.example.azureapp.ui.home.virtualmachine.VMAdapter;
import com.example.azureapp.ui.home.virtualmachine.VMFragment;
/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 */

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    ImageButton vmButton,dbButton;
    RecyclerView recyclerView;
    VMAdapter vmAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vmButton = root.findViewById(R.id.vmButton);
        dbButton = root.findViewById(R.id.dbButton);

        vmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, );

                //startActivity(intent);

               /* FragmentManager fm = getActivity().getFragmentManager();
                //注意v4包的配套使用
                Fragment fragment = new VMFragment();
                fm.beginTransaction().replace(,fragment).commit();*/

            }
        });

        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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