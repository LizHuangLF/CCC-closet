package com.exam.closet_f.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.exam.closet_f.R;

public class dialogFrag extends DialogFragment {
    private Button btn;
    private final static String ShareDialog_TAG = "Dialog";
    private volatile static dialogFrag dialog;

    public static dialogFrag getInstance() {
        if (dialog == null) {
            dialog = new dialogFrag();
        }
        return dialog;
    }

    public static dialogFrag getInstance(String title) {
        dialog = new dialogFrag();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置dialog的基本样式参数
        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getDialog().getWindow();
        //去掉dialog默认的padding
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置dialog的位置在底部
        lp.gravity = Gravity.BOTTOM;
        //设置dialog的动画
        lp.windowAnimations = R.style.BottomToTopAnim;
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());

        View view = inflater.inflate(R.layout.frag_dialog, container, false);
//        ButterKnife.bind(this, view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

//    @OnClick({R.id.wechartShareLayout, R.id.circleShareLayout, R.id.cancleText})
    void onViewClick(View view){
        switch (view.getId()) {
//            case R.id.wechartShareLayout:
//                Log.e("TAG", "点击了微信分享");
//                break;
//            case R.id.circleShareLayout:
//                Log.e("TAG", "点击了朋友圈分享");
//                break;
            case R.id.cancleText:
                dismiss();
                break;
        }
  }

    public void show(FragmentManager manager){
        show(manager,ShareDialog_TAG);
    }

    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.frag_dialog,container);
//        btn = view.findViewById(R.id.btn_dialog_colse);
//        return view;
//    }



}
