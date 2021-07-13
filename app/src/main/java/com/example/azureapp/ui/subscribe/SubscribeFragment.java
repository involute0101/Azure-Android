package com.example.azureapp.ui.subscribe;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.azureapp.R;
import com.example.azureapp.databinding.SubscribeFragmentBinding;
import com.example.azureapp.ui.Subscribe;
import com.example.azureapp.ui.VirtualMachine;

import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 */
public class SubscribeFragment extends Fragment {

    private SubscribeViewModel mViewModel;
    RecyclerView recyclerView;
    SubscribeAdapter subscribeAdapter;
    private List<VirtualMachine> vms;
    SubscribeFragmentBinding binding;

    public static SubscribeFragment newInstance() {
        return new SubscribeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SubscribeFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SubscribeViewModel.class);
        // TODO: Use the ViewModel
        recyclerView = binding.subscribeRecycleView;
        subscribeAdapter = new SubscribeAdapter();
        subscribeAdapter.subscribes.clear();
        subscribeAdapter.subscribes.add(new Subscribe("免费订阅","ec269b4d-93af-43c5-9fd6-9a5185235344"));
        subscribeAdapter.subscribes.add(new Subscribe("免费订阅2","ec269b4d-93af-43c5-9fd6-9a5185235344"));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(subscribeAdapter);
    }

}