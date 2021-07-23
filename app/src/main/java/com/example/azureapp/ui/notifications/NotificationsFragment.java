package com.example.azureapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.azureapp.R;
import com.example.azureapp.databinding.FragmentNotificationsBinding;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 */
public class NotificationsFragment extends Fragment {
    //
    private NotificationsViewModel notificationsViewModel;
    //
    private FragmentNotificationsBinding binding;

    /**
     *通知视图创建
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            //textView.setText(s);
        });
        return root;

    }

    /**
     * 设置viewPager
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> list = new ArrayList<>();
        list.add("警报");
        list.add("服务运行情况");

        prepareViewPager(binding.notificationViewPager, list);

        binding.notificationTabLayout.setupWithViewPager(binding.notificationViewPager);
       // tabLayout.setTabsFromPagerAdapter(viewPager.getAdapter());
        Bundle bundle = getArguments();
        if (bundle!=null&&bundle.getString("condition").equals("condition"))binding.notificationViewPager.setCurrentItem(1);
    }

    /**
     * 将警报界面以及服务运行状况界面添加到notification界面
     * @param viewPager
     * @param list
     */
    private void prepareViewPager(ViewPager viewPager, ArrayList<String> list) {
        NotificationAdapter notificationAdapter = new NotificationAdapter(getFragmentManager());
        //fragment
        AlertFragment alertFragment = new AlertFragment();
        ConditionFragment conditionFragment = new ConditionFragment();

        Bundle bundleAlert = new Bundle();
        bundleAlert.putString("title",list.get(0));
        alertFragment.setArguments(bundleAlert);
        notificationAdapter.addFragment(alertFragment,list.get(0));

        Bundle bundleCondition = new Bundle();
        bundleCondition.putString("title",list.get(1));
        alertFragment.setArguments(bundleCondition);
        notificationAdapter.addFragment(conditionFragment,list.get(1));

        viewPager.setAdapter(notificationAdapter);


    }

    /**
     *销毁试图
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     *通知控制管理类
     */
    private class NotificationAdapter extends FragmentStatePagerAdapter {
        //页面标题列表
        ArrayList<String> list = new ArrayList<>();
        //页面列表
        List<Fragment> fragments = new ArrayList<>();

        public NotificationAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        /**
         *添加界面和标题
         * @param fragment
         * @param title
         */
        public void addFragment(Fragment fragment,String title){
            list.add(title);
            fragments.add(fragment);
        }

        /**
         *获取界面位置
         * @param position
         * @return 界面位置
         */
        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        /**
         *销毁界面项目
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {

        }

        /**
         *获取界面数量
         * @return 界面数
         */
        @Override
        public int getCount() {
            return fragments.size();
        }

        /**
         *获取当前界面标题
         * @param position
         * @return
         */
        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);

        }
    }
}