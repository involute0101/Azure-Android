package com.example.azureapp.ui.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azureapp.R;
import com.example.azureapp.ui.virtualmachine.VirtualMachineLogFragment;

import org.jetbrains.annotations.NotNull;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-13 9:06
 **/
public class DBDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @NotNull
    private Context mContext;

    public DBDetailAdapter(Context context){
        mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new DBDetailAdapter.LinearViewHolder_Head(LayoutInflater.from(mContext).inflate(R.layout.db_head,parent,false));
        }
        if(viewType == 1){
            return new DBDetailAdapter.LinearViewHolder_Blog(LayoutInflater.from(mContext).inflate(R.layout.detail_blog,parent,false));
        }
        if(viewType == 2){
            return new DBDetailAdapter.LinearViewHolder_Source(LayoutInflater.from(mContext).inflate(R.layout.detail_source_running,parent,false));
        }
        if(viewType == 3){
            return new DBDetailAdapter.LinearViewHolder_Attribute(LayoutInflater.from(mContext).inflate(R.layout.db_attribute,parent,false));
        }
        else
            return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     *
     */
    class LinearViewHolder_Head extends  RecyclerView.ViewHolder{

        private TextView tvVmName;
        private ImageView imgState;
        private TextView tvVmState;

        public  LinearViewHolder_Head(View itemView){
            super(itemView);
            tvVmName = itemView.findViewById(R.id.tv_db_name);
            imgState = itemView.findViewById(R.id.img_db_state);
            tvVmState = itemView.findViewById(R.id.tv_db_state);
        }
    }

    /**
     *
     */
    class LinearViewHolder_Blog extends  RecyclerView.ViewHolder{
        private TextView tvBlogCount;
        private TextView tvBlogMore;

        public  LinearViewHolder_Blog(View itemView){
            super(itemView);
            tvBlogCount = itemView.findViewById(R.id.tv_blog_count);
            tvBlogMore = itemView.findViewById(R.id.tv_blog_more);
            tvBlogMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment logFragment = new DataBaseLogFragment();
                    AppCompatActivity activity =(AppCompatActivity)itemView.getContext();
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.database_deatilfragment_container, logFragment, null)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    /**
     *
     */
    class LinearViewHolder_Source extends  RecyclerView.ViewHolder{
        private TextView tvSourceState;
        private ImageView imgSourceState;

        public  LinearViewHolder_Source(View itemView){
            super(itemView);
            tvSourceState = itemView.findViewById(R.id.tv_source_state);
            imgSourceState = itemView.findViewById(R.id.img_source_state);
        }
    }

    /**
     *
     */
    class LinearViewHolder_Attribute extends  RecyclerView.ViewHolder{
        public  LinearViewHolder_Attribute(View itemView){
            super(itemView);
        }
    }
}
