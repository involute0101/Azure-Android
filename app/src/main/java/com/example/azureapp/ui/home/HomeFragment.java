package com.example.azureapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.LoginActivity;
import com.example.azureapp.R;
import com.example.azureapp.databinding.FragmentHomeBinding;
import com.example.azureapp.ui.database.DataBaseActivity;
import com.example.azureapp.ui.home.resourceGroup.ResourceGroupActivity;
import com.example.azureapp.ui.home.resourceGroup.ResourceGroupAdapter;
import com.example.azureapp.ui.virtualmachine.VirtualMachineActivity;

/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 */

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    LinearLayout llVm,llSql,llResourceGroup;
    LinearLayout llAd1,llAd2,llAd3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //虚拟机和数据库
        llVm = root.findViewById(R.id.ll_vm);
        llSql = root.findViewById(R.id.ll_sql);
        //三个广告
        llAd1 = root.findViewById(R.id.ll_ad1);
        llAd2 = root.findViewById(R.id.ll_ad2);
        llAd3 = root.findViewById(R.id.ll_ad3);
        //资源组
        llResourceGroup = root.findViewById(R.id.ll_resource_group);

        llVm.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), VirtualMachineActivity.class);
            startActivity(intent);
        });

        llSql.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DataBaseActivity.class);
            startActivity(intent);
        });

        llAd1.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri privacy_url = Uri.parse("https://azure.microsoft.com/zh-cn/free/");
            intent.setData(privacy_url);
            startActivity(intent);
        });

        llAd2.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri privacy_url = Uri.parse("https://azure.microsoft.com/zh-cn/services/active-directory/");
            intent.setData(privacy_url);
            startActivity(intent);
        });

        llAd3.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri privacy_url = Uri.parse("https://azure.microsoft.com/zh-cn/developer/students/");
            intent.setData(privacy_url);
            startActivity(intent);
        });

        llResourceGroup.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ResourceGroupActivity.class);
            startActivity(intent);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}