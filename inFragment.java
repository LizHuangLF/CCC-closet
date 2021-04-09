package com.exam.closet_f.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exam.closet_f.R;
import com.exam.closet_f.activity.ItemAddActivity;
import com.exam.closet_f.activity.ItemAlbumActivity;
import com.exam.closet_f.activity.ItemShowActivity;
import com.exam.closet_f.util.FragCallBack;

import org.w3c.dom.Text;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link inFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link inFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class inFragment extends Fragment {
    private Button btnIn;
    private LinearLayout ltAll;
    static inFragment iff;
    private TextView tv;
    private TextView tvUpper,tvBottom,tvFootgear,tvBagcap,tvSuit,tvOther;
    int itemID;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public inFragment() {
        // Required empty public constructor
    }

//    public static inFragment getInFragment(){
//
//        if(iff == null){
//            iff = new inFragment();
//        }
//        return iff;
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment inFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static inFragment newInstance(String param1, String param2) {
        inFragment fragment = new inFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_in, container, false);
        initData(view);
//        tv = view.findViewById(R.id.tv);
//        //获取Bundle 然后获取数据
//        Bundle bundle = this.getArguments();//得到从Activity传来的数据
//        String title = null;
//        if (bundle != null) {
//            title = bundle.getString("title");
//        }
//        ///设置显示数据
//        tv.setText(title);
        if(mFragCallBack !=null){
            mFragCallBack.test(111);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    //监听回调
    FragCallBack mFragCallBack;
    ///onAttach 当 Fragment 与 Activity 绑定时调用
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ///获取绑定的监听
        if (context instanceof FragCallBack) {
            mFragCallBack = (FragCallBack) context;
        }
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    ///onDetach 当 Fragment 与 Activity 解除绑定时调用
    @Override
    public void onDetach() {
        super.onDetach();
        mFragCallBack = null;
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initData(View v){
        btnIn = v.findViewById(R.id.btn_in);
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ItemAddActivity.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });

        ltAll = v.findViewById(R.id.lt_all);
        ltAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ItemAlbumActivity.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });

        tvUpper = v.findViewById(R.id.tv_upper);
        tvUpper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemID =0; goItemActivity(itemID);
            }
        });
        tvBottom = v.findViewById(R.id.tv_bottom);
        tvFootgear = v.findViewById(R.id.tv_footgear);
        tvBagcap = v.findViewById(R.id.tv_bagcap);
        tvSuit = v.findViewById(R.id.tv_suit);
        tvOther = v.findViewById(R.id.tv_other);


    }

//    public void showItem0( View v){ itemID = 0; goItemActivity(itemID); }
//    public void showItem1( View v){ itemID = 1; goItemActivity(itemID); }
//    public void showItem2( View v){ itemID = 2; goItemActivity(itemID); }
//    public void showItem3( View v){ itemID = 3; goItemActivity(itemID); }
//    public void showItem4( View v){ itemID = 4; goItemActivity(itemID); }
//    public void showItem5( View v){ itemID = 5; goItemActivity(itemID); }




    public void goItemActivity(int ID){
        Intent intent = new Intent(getActivity(),ItemShowActivity.class);
        intent.putExtra("itemID",ID);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }


}
