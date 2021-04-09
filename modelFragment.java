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
 * {@link modelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link modelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

//头像框 https://blog.csdn.net/weixin_43977664/article/details/113872166?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522161650821916780261998768%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=161650821916780261998768&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~rank_v29-11-113872166.pc_search_result_cache&utm_term=android+%E5%A4%B4%E5%83%8F%E6%A1%86
public class modelFragment extends Fragment {
    private TextView tvHeart,tvIns;
    private ViewPager pager;
    InspiraFragment insFrag;
    HeartFragment heartFrag;
    private List<Fragment> list = new ArrayList<Fragment>();
    private fragAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public modelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment modelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static modelFragment newInstance(String param1, String param2) {
        modelFragment fragment = new modelFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_model, container, false);
        initData(view);
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
        void onFragmentInteraction(Uri uri);
    }

    private void initData(View v){
       pager = v.findViewById(R.id.pager);
       tvHeart = v.findViewById(R.id.tv_heart);
       tvIns = v.findViewById(R.id.tv_ins);

       tvIns.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                tvIns.setTextColor(getResources().getColor(R.color.white));
                pager.setCurrentItem(0,false);
           }
       });
       tvHeart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tvHeart.setTextColor(getResources().getColor(R.color.white));
               pager.setCurrentItem(1,false);
           }
       });
       list.add(new InspiraFragment());
       list.add(new HeartFragment());

        adapter = new fragAdapter(getChildFragmentManager(), list);
        pager.setAdapter(adapter);
    }

//    public void onPageSelected(int arg0) {
//        clear();
//        switch (arg0) {
//            case 0:
//                mBtn1.setTextColor(Color.GREEN);
//                pager.setCurrentItem(0, false);
//                break;
//            case 1:
//                mBtn2.setTextColor(Color.GREEN);
//                pager.setCurrentItem(1, false);
//                break;
//        }
//
//    }
//
//    private void clear() {
//        mBtn1.setTextColor(Color.GRAY);
//        mBtn2.setTextColor(Color.GRAY);
//    }


}

