package com.example.azureapp.ui.resource;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-13 19:40
 **/
public class ResourceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Resource> showList = new ArrayList<>();
    private List<Resource> allList = new ArrayList<>();
    private List<Resource> searchList = new ArrayList<>();

    public ResourceListAdapter(Context context, List<Resource> resourceList){
        mContext = context;
        this.allList = resourceList;
        this.showList = resourceList;
        this.searchList = resourceList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new ResourceListAdapter.LinearViewHolder_Head(LayoutInflater.from(mContext).inflate(R.layout.resources_head,parent,false));
        }
        else{
            return new ResourceListAdapter.LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.resources_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(position!=0 && position <= showList.size()){
            Resource resource = showList.get(position-1);
            if(resource.type.equals("disk"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_disk);
            else if(resource.type.equals("virtualMachine"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_virtualmachine);
            else if(resource.type.equals("networkInterface"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_network_interface);
            else if(resource.type.equals("networkSecurityGroup"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_network_security);
            else if(resource.type.equals("publicIPAddresse"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_public_ip);
            else if(resource.type.equals("virtualNetwork"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_virtual_network);
            else if(resource.type.equals("networkWatcher"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_network_watcher);
            else if(resource.type.equals("resourceGroup"))
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_resource_group);
            else
                ((LinearViewHolder)holder).mResourceImg.setImageResource(R.drawable.icon_unknown_resource);
            ((LinearViewHolder)holder).mResourceNameTv.setText(resource.name);
            ((LinearViewHolder)holder).mResourceTypeTv.setText(resource.type);
        }
    }

    @Override
    public int getItemCount() {
        return showList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class LinearViewHolder_Head extends RecyclerView.ViewHolder{

        private EditText mSearchEt;
        private TextView mCancelTv;

        public LinearViewHolder_Head( View itemView) {
            super(itemView);
            mSearchEt = itemView.findViewById(R.id.et_search_resources);
            mCancelTv = itemView.findViewById(R.id.tv_search_cancel);
            Drawable left = mSearchEt.getCompoundDrawables()[0];
            Drawable right = mSearchEt.getCompoundDrawables()[2];
            left.setBounds(0,0,30,30);
            right.setBounds(0,0,30,30);
            mSearchEt.setCompoundDrawables(left,null,null,null);
            mSearchEt.setOnFocusChangeListener((v, hasFocus) -> {
                if(mSearchEt.hasFocus()){
                    mCancelTv.setText(R.string.resources_search_cancel);
                }else{
                    mCancelTv.setText("");
                }
            });
            mSearchEt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Drawable drawable = mSearchEt.getCompoundDrawables()[2];
                    if(drawable == null)
                        return false;
                    if(event.getAction() != MotionEvent.ACTION_UP)
                        return false;
                    if (event.getX() > mSearchEt.getWidth()
                            - mSearchEt.getPaddingRight()
                            - drawable.getIntrinsicWidth())
                        mSearchEt.setText("");
                    return false;
                }
            });
            mSearchEt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_SEARCH){
                        search(mSearchEt.getText().toString());
                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mSearchEt.getApplicationWindowToken(), 0);
                        mSearchEt.clearFocus();
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            });
            mSearchEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    System.out.println(mSearchEt.getText());
                    if(count==0)
                        mSearchEt.setCompoundDrawables(left,null,null,null);
                    else
                        mSearchEt.setCompoundDrawables(left,null,right,null);
                    search(mSearchEt.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            mCancelTv.setOnClickListener(v -> {
                mCancelTv.setText("");
                mSearchEt.setText("");
                mSearchEt.setCompoundDrawables(left,null,null,null);
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchEt.getApplicationWindowToken(), 0);
                mSearchEt.clearFocus();
            });
        }

        private void search(String searchString){
            searchList = new ArrayList<>();
            System.out.println(allList);
            for (Resource resource:allList) {
                if(resource.name.contains(searchString) || resource.type.contains(searchString)){
                    searchList.add(resource);
                }
            }
            showList = searchList;
            System.out.println(showList);
            notifyDataSetChanged();
        }

    }

    class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView mResourceNameTv;
        private TextView mResourceTypeTv;
        private ImageView mResourceImg;

        public LinearViewHolder( View itemView) {
            super(itemView);
            mResourceNameTv = itemView.findViewById(R.id.tv_resource_name);
            mResourceTypeTv = itemView.findViewById(R.id.tv_resource_type);
            mResourceImg = itemView.findViewById(R.id.img_resource);
        }
    }
}
