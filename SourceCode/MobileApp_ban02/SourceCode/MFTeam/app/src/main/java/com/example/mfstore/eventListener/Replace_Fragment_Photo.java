package com.example.mfstore.eventListener;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mfstore.fragment.PhotoFragment;
import com.example.mfstore.model.Photo;

import java.util.List;

public class Replace_Fragment_Photo extends FragmentStateAdapter {

    private List<Photo> listPhoto;

    public Replace_Fragment_Photo(@NonNull FragmentActivity fragmentActivity, List<Photo> list)
    {
        super(fragmentActivity);
        this.listPhoto = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //có nhiệm vụ lấy đối tượng Photo từ danh sách listPhoto tại vị trí position cụ thể.
        Photo photo =  listPhoto.get(position);

        /*Bundle trong Android là một lớp được sử dụng để truyền và lưu trữ dữ liệu dưới dạng cặp khóa-giá trị. Nó được sử dụng trong
        nhiều tình huống khác nhau, như truyền dữ liệu giữa các thành phần khác nhau của ứng dụng (như các Activity, Fragment, Service, */
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_photo",photo);

        //được sử dụng để tạo một thể hiện mới của lớp PhotoFragment. Đây là một ví dụ về việc khởi tạo một đối tượng từ một lớp và gán cho biến.
        PhotoFragment photoFragment = new PhotoFragment();
        photoFragment.setArguments(bundle);

        return photoFragment;
    }

    @Override
    public int getItemCount() {
        if(listPhoto!=null){
            return  listPhoto.size();
        }
        return 0;
    }


}
