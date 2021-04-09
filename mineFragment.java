package com.exam.closet_f.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exam.closet_f.R;
import com.exam.closet_f.adapter.fragAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mineFragment extends Fragment{
    private TextView tvName;
    private TextView tvData,tvShoper,tvAccount,tvCloset;
    ViewPager pager;
    private List<Fragment> list = new ArrayList<Fragment>();
    DataFragment dataFrag;
    ShoperFragment shoperFrag;
    AccountFragment accountFrag;
    ClosetFragment closetFrag;
    private fragAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public mineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static mineFragment newInstance(String param1, String param2) {
        mineFragment fragment = new mineFragment();
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
        View view =inflater.inflate(R.layout.fragment_mine, container, false);
        initData(view);
        tvName = view.findViewById(R.id.tv_name);
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        String uname = null;
        if (bundle != null) {
            uname = bundle.getString("uname");
        }
        ///设置显示数据
        tvName.setText(uname);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
//        void onFragmentInteraction(Uri uri);
    }

    private void initData(View v){
        tvData = v.findViewById(R.id.tv_data); tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0,false);
            }
        });
        tvShoper = v.findViewById(R.id.tv_shoper);  tvShoper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1,false);
            }
        });
        tvAccount = v.findViewById(R.id.tv_account);  tvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2,false);
            }
        });
        tvCloset = v.findViewById(R.id.tv_closet);  tvCloset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(3,false);
            }
        });

        pager = v.findViewById(R.id.pager_mine);
        list.add(new DataFragment());
        list.add(new ShoperFragment());
        list.add(new AccountFragment());
        list.add(new ClosetFragment());

        adapter = new fragAdapter(getChildFragmentManager(), list);
        pager.setAdapter(adapter);
    }
}

}
