package com.dr.psycho.ryuk;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.psycho.ryuk.Adapter.ThumbnailAdapter;
import com.dr.psycho.ryuk.Interface.FlitersListFragmentListener;
import com.dr.psycho.ryuk.Utils.BitmapUtils;
import com.dr.psycho.ryuk.Utils.SpacesItemDecoration;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.zomato.photofilters.FilterPack;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.utils.ThumbnailItem;
import com.zomato.photofilters.utils.ThumbnailsManager;

import java.util.ArrayList;
import java.util.List;


public class FiltersListFragment extends BottomSheetDialogFragment implements  FlitersListFragmentListener{

    RecyclerView recyclerView;
    ThumbnailAdapter adapter;
    List<ThumbnailItem> thumbnailItems;

    FlitersListFragmentListener listener;

    static FiltersListFragment instance;
    static Bitmap bitmap;

    public static FiltersListFragment getInstance(Bitmap bitmapSave) {
        bitmap = bitmapSave;

        if (instance == null)
        {
            instance = new FiltersListFragment();
        }
        return instance;
    }

    public void setListener(FlitersListFragmentListener listener) {
        this.listener = listener;
    }

    public FiltersListFragment() {
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
        View itemView = inflater.inflate(R.layout.fragment_filters_list, container, false);
        thumbnailItems = new ArrayList<>();
        adapter = new ThumbnailAdapter(thumbnailItems, this, getActivity());

        recyclerView = (RecyclerView)itemView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8 , getResources().getDisplayMetrics());
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        recyclerView.setAdapter(adapter);

        displayThumbnail(bitmap);
        return itemView;
    }

    public void displayThumbnail(Bitmap bitmap)
    {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Bitmap thumbImg;
                if (bitmap == null)
                    thumbImg = BitmapUtils.getBitmapFromAssets(getActivity(), MainActivity.pictureName, 100, 100);
                else
                    thumbImg = Bitmap.createScaledBitmap(bitmap , 100, 100, false);
                if (thumbImg == null)
                    return;
                ThumbnailsManager.clearThumbs();
                thumbnailItems.clear();

                //add Normal Bitmap first
                ThumbnailItem thumbnailItem =new ThumbnailItem();
                thumbnailItem.image = thumbImg;
                thumbnailItem.filterName = "Normal";
                ThumbnailsManager.addThumb(thumbnailItem);

                List<Filter> filters = FilterPack.getFilterPack(getActivity());

                for (Filter filter : filters){
                    ThumbnailItem tI = new ThumbnailItem();
                    tI.image = thumbImg;
                    tI.filter = filter;
                    tI.filterName = filter.getName();
                    ThumbnailsManager.addThumb(tI);
                }

                thumbnailItems.addAll(ThumbnailsManager.processThumbs(getActivity()));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        };
        new Thread(r).start();
    }

    @Override
    public void onFilterSelected(Filter filter) {
        if (listener != null)
            listener.onFilterSelected(filter);
    }
}