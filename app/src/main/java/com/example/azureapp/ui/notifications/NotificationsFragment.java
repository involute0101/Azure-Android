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

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

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

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> list = new ArrayList<>();
        list.add("警报");
        list.add("服务运行情况");

        prepareViewPager(binding.notificationViewPager, list);

        binding.notificationTabLayout.setupWithViewPager(binding.notificationViewPager);
       // tabLayout.setTabsFromPagerAdapter(viewPager.getAdapter());
    }

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



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class NotificationAdapter extends FragmentStatePagerAdapter {

        ArrayList<String> list = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();

        public NotificationAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            list.add(title);
            fragments.add(fragment);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {

        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);

        }
    }
}