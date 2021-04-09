package com.exam.closet_f.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exam.closet_f.R;
import com.exam.closet_f.adapter.HeartAdapter;
import com.exam.closet_f.bean.Heart;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HeartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HeartFragment#} factory method to
 * create an instance of this fragment.
 */
public class HeartFragment extends Fragment {
    private ListView listView;

    private int[] colors = new int[]{R.color.green,R.color.yellow,R.color.brown,R.color.grey};
    private int[] fitPic = new int[]{R.drawable.up_vest,R.drawable.up_coat_black,
            R.drawable.up_coat_brown,R.drawable.up_coat_cow,};
    private String[] hearts = new String[]{"100","214","55","99"};

    private String[] details = new String[]{"“春色正好，一起出游吧！”",
            "“清凉夏日，冰淇淋和这套很搭哦~~”",
            "秋日穿搭","冬日穿搭",};
    private String[] unames = new String[]{"春","夏","秋","冬"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HeartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment HeartFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static HeartFragment newInstance(String param1, String param2) {
//        HeartFragment fragment = new HeartFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

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
        View view = inflater.inflate(R.layout.fragment_heart, container, false);
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

    private void initData(View view){
        listView = view.findViewById(R.id.listv_heart);

        List<Heart> htList = new ArrayList<>();
        for(int i = 0; i < unames.length;i++){
            Heart heart = new Heart();
            heart.setUserName(unames[i]);
            heart.setDetail(details[i]);
            heart.setOutfitPic(fitPic[i]);
            heart.setHeadPic(colors[i]);
            heart.setHeartNum(hearts[i]);
            htList.add(heart);
        }
        HeartAdapter adapter = new HeartAdapter(this,htList,getContext());
        listView.setAdapter(adapter);
    }
}

