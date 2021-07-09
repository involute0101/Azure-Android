package com.example.azureapp.ui.virtualmachine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.azureapp.R;
import com.example.azureapp.ui.VirtualMachine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VMFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VMFragment extends Fragment {

    RecyclerView recyclerView;
    VMAdapter vmAdapter;
    private List<VirtualMachine> vms;
    FloatingActionButton addVMButton;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VMFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VMFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VMFragment newInstance(String param1, String param2) {
        VMFragment fragment = new VMFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_v_m, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = requireActivity().findViewById(R.id.vm_recycle_view);
        vmAdapter = new VMAdapter();
        vmAdapter.vms.add(new VirtualMachine("test1"));
        vmAdapter.vms.add(new VirtualMachine("test2"));
        vmAdapter.vms.add(new VirtualMachine("test3"));

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(vmAdapter);
        addVMButton = requireActivity().findViewById(R.id.addVMButton);
        addVMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_VMFragment_to_VMAddFragment);
            }
        });
    }

/*    public void addVM(VirtualMachine vm ){
        vmAdapter.vms.add(vm);
        Log.d("vms", "1");


    }*/

    public void getVMS(){
        //调用API
        // vmAdapter.vms.
        // vmAdapter.notifyDataSetChanged();

    }
}