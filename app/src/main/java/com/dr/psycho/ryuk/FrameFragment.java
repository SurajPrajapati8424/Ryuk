package com.dr.psycho.ryuk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.psycho.ryuk.Adapter.FrameAdapter;
import com.dr.psycho.ryuk.Interface.AddFrameListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class FrameFragment extends BottomSheetDialogFragment implements FrameAdapter.FrameAdapterListener {

    RecyclerView recycler_frame;
    Button btn_add_frame;

    int frame_select = -1;

    AddFrameListener listener;

    public void setListener(AddFrameListener listener) {
        this.listener = listener;
    }

    static FrameFragment instance;

    public static FrameFragment getInstance() {
        if (instance == null)
            instance = new FrameFragment();
        return instance;
    }

    public FrameFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_frame, container, false);

        recycler_frame = (RecyclerView)itemView.findViewById(R.id.recycler_frame);
        btn_add_frame = (Button)itemView.findViewById(R.id.btn_add_frame);

        recycler_frame.setHasFixedSize(true);
        recycler_frame.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recycler_frame.setAdapter(new FrameAdapter(getContext(), this));

        btn_add_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddFrame(frame_select);
            }
        });

        return  itemView;
    }

    @Override
    public void onFrameSelected(int frame) {
        frame_select = frame;
    }
}