package com.example.azureapp.ui.home;
/**
 * fileDesc
 * Created by wzk on 2021/7/7.
 * Email 1403235458@qq.com
 */
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    //动态数据
    private MutableLiveData<String> mText;

    /**
     * 构造函数
     */
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    /**
     * 获取数据
     * @return 数据
     */
    public LiveData<String> getText() {
        return mText;
    }
}