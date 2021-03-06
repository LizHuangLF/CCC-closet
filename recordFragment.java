package com.exam.closet_f.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.exam.closet_f.R;
import com.exam.closet_f.activity.MatchActivity;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link recordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link recordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recordFragment extends Fragment {
    private TextView tv;
    private static recordFragment rf;
    private ImageView ivOutfit;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public recordFragment() {
        // Required empty public constructor
    }
//    public static recordFragment getRecordFragment(){
//        if(rf == null){
//            rf = new recordFragment();
//        }
//        return rf;
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment recordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static recordFragment newInstance(String param1, String param2) {
        recordFragment fragment = new recordFragment();
        Bundle args = new Bundle();
        args.putString("1",param1);
        args.putString("2",param2);
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static recordFragment newInstance(String title) {
        //?????? Fragment ??????
        recordFragment fragment = new recordFragment();
        //?????? Bundle
        Bundle lBundle = new Bundle();
        lBundle.putString("title", title);
        fragment.setArguments(lBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("1");
            mParam2 = getArguments().getString("2");
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }else
            Log.i("-----------","null");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        initData(view);
        tv = view.findViewById(R.id.tv);
        //??????Bundle ??????????????????
        Bundle bundle =this.getArguments();//?????????Activity???????????????
        String title = null;
        if(bundle!=null){
            title = bundle.getString("title");
        }
//        tv.setText(title);
//        tv.setText(mParam1);
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
        ivOutfit = v.findViewById(R.id.iv_outfit);
        ivOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MatchActivity.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });
    }
}
