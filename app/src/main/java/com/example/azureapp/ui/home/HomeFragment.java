package com.example.azureapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.LoginActivity;
import com.example.azureapp.R;
import com.example.azureapp.databinding.FragmentHomeBinding;
import com.example.azureapp.ui.database.DataBaseActivity;
import com.example.azureapp.ui.home.resourceGroup.ResourceGroupActivity;
import com.example.azureapp.ui.home.resourceGroup.ResourceGroupAdapter;
import com.example.azureapp.ui.virtualmachine.VirtualMachineActivity;
import com.example.azureapp.ui.virtualmachine.VirtualMachineDetailActivity;

import java.util.zip.Inflater;

/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 */

/**
 * 主界面
 */
public class HomeFragment extends Fragment {
    //
    private HomeViewModel homeViewModel;
    //主机名数
    private FragmentHomeBinding binding;
    //线性布局
    LinearLayout llVm,llSql,llResourceGroup,llServiceCondition;
    LinearLayout llAd1,llAd2,llAd3;

    /**
     * 主页视图创建
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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
        //服务运行状况
        llServiceCondition = root.findViewById(R.id.service_condition_linear_ayout);
        //虚拟机点击
        llVm.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), VirtualMachineActivity.class);
            startActivity(intent);
        });
        //数据库点击
        llSql.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DataBaseActivity.class);
            startActivity(intent);
        });
        //广告点击按钮函数
        llAd1.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri privacy_url = Uri.parse("https://azure.microsoft.com/zh-cn/free/");
            intent.setData(privacy_url);
            startActivity(intent);
        });
        //广告点击按钮函数
        llAd2.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri privacy_url = Uri.parse("https://azure.microsoft.com/zh-cn/services/active-directory/");
            intent.setData(privacy_url);
            startActivity(intent);
        });
        //广告点击按钮函数
        llAd3.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri privacy_url = Uri.parse("https://azure.microsoft.com/zh-cn/developer/students/");
            intent.setData(privacy_url);
            startActivity(intent);
        });
        //资源组点击
        llResourceGroup.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ResourceGroupActivity.class);
            startActivity(intent);
        });
        //服务运行状况点击
        llServiceCondition.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putString("condition","condition");
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_navigation_home_to_navigation_notifications,bundle);


        });
        return root;
    }

    /**
     * 销毁视图
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}