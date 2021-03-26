package com.dr.psycho.ryuk;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.dr.psycho.ryuk.Adapter.ColorAdapter;
import com.dr.psycho.ryuk.Interface.BrushFragmnetListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BrushFragment extends BottomSheetDialogFragment implements ColorAdapter.ColorAdapterListener {

    SeekBar seekBar_brush_size, seekBar_opacity_state;
    RecyclerView recycler_color;
    ToggleButton btn_brush_state;

    ColorAdapter colorAdapter;

    BrushFragmnetListener listener;

    static BrushFragment instance;

    public static BrushFragment getInstance() {
        if (instance == null)
            instance = new BrushFragment();
        return instance;
    }

    public BrushFragmnetListener getListener() {
        return listener;
    }

    public void setListener(BrushFragmnetListener listener) {
        this.listener = listener;
    }

    public BrushFragment() {
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
        View itemView =  inflater.inflate(R.layout.fragment_brush, container, false);

        seekBar_brush_size = (SeekBar) itemView.findViewById(R.id.seekbar_brush_size);
        seekBar_opacity_state = (SeekBar) itemView.findViewById(R.id.seekbar_brush_opacity);
        btn_brush_state = (ToggleButton) itemView.findViewById(R.id.btn_brush_state);
        recycler_color = (RecyclerView) itemView.findViewById(R.id.recycler_color);
        recycler_color.setHasFixedSize(true);
        recycler_color.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        colorAdapter = new ColorAdapter(getContext(),genColorList(), this);
        recycler_color.setAdapter(colorAdapter);

        //Event
        seekBar_opacity_state.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                listener.onBrushOpacityChangedListener(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_brush_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                listener.onBrushSizeChangedListener(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn_brush_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onBrushStateChangedListener(isChecked);
            }
        });
        return itemView;
    }

    private List<Integer> genColorList() {
        List<Integer> colorList = new ArrayList<>();

        colorList.add(Color.parseColor("#1a1a30"));
        colorList.add(Color.parseColor("#01011a"));
        colorList.add(Color.parseColor("#4080ff"));
        colorList.add(Color.parseColor("#f2e9cd"));
        colorList.add(Color.parseColor("#cdd6f2"));
        colorList.add(Color.parseColor("#9ba95b"));
        colorList.add(Color.parseColor("#b8c8e9"));

        colorList.add(Color.parseColor("#b8e1e9"));
        colorList.add(Color.parseColor("#c1b8e9"));
        colorList.add(Color.parseColor("#b8c8e9"));
        colorList.add(Color.parseColor("#9ba95b"));
        colorList.add(Color.parseColor("#f2b727"));
        colorList.add(Color.parseColor("#136978"));
        colorList.add(Color.parseColor("#01011a"));

        colorList.add(Color.parseColor("#00529c"));
        colorList.add(Color.parseColor("#123123"));
        colorList.add(Color.parseColor("#008080"));
        colorList.add(Color.parseColor("#eeae02"));
        colorList.add(Color.parseColor("#ffdab9"));
        colorList.add(Color.parseColor("#f00000"));
        colorList.add(Color.parseColor("#ff8040"));
        return  colorList;
    }

    @Override
    public void onColorSelected(int color) {
        listener.onBrushColorChangedListener(color);

    }
}