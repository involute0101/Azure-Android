package com.example.azureapp.ui.resource;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 * Update by 刘非凡 on 2021/7/13 21:24
 */
public class ResourceFragment extends Fragment {

    private RecyclerView mRvResources;
    private List<Resource> resourceList = new ArrayList<>();

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_resource, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTestResource();
        mRvResources = requireActivity().findViewById(R.id.rv_resource);
        mRvResources.setLayoutManager(new LinearLayoutManager(requireActivity()));
        ResourceListAdapter adapter = new ResourceListAdapter(requireActivity(), resourceList);
        mRvResources.setAdapter(adapter);
    }

    public void setTestResource(){
        resourceList.add(new Resource("aaa","虚拟机","正在运行"));
        resourceList.add(new Resource("bbb","数据库","stop"));
        resourceList.add(new Resource("ccc","资源组","stop"));
        resourceList.add(new Resource("ddd","资源组","正在运行"));
        resourceList.add(new Resource("eee","虚拟机","stop"));
        resourceList.add(new Resource("fff","数据库","正在运行"));
    }
}