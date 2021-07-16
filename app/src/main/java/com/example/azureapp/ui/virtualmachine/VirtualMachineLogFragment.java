package com.example.azureapp.ui.virtualmachine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.azureapp.R;
import com.example.azureapp.databinding.FragmentVirtualMachineLogBinding;
import com.example.azureapp.databinding.SubscribeFragmentBinding;
import com.example.azureapp.ui.Log;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VirtualMachineLogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VirtualMachineLogFragment extends Fragment {

    RecyclerView recyclerView;
    FragmentVirtualMachineLogBinding binding;
    VirtualMachineLogAdapter logAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VirtualMachineLogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VirtualMachineLogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VirtualMachineLogFragment newInstance(String param1, String param2) {
        VirtualMachineLogFragment fragment = new VirtualMachineLogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    /**
     * 实例化日志的recycleView和adapter
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = binding.virtualmachineLogRecycleView;
        logAdapter = new VirtualMachineLogAdapter();

        logAdapter.logs.add(new Log("test","today","true"));
        logAdapter.logs.add(new Log("test1","today","false"));
        getLogs();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(logAdapter);
    }

    /**
     * 从服务器端取得虚拟机的日志
     */
    public void getLogs() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVirtualMachineLogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}