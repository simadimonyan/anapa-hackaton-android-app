package com.conference.hackcaton.src.ui.dashboard;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.conference.hackcaton.R;
import com.conference.hackcaton.databinding.FragmentDashboardBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.ScreenPoint;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private MapView mapView = null;
    private ImageButton user = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        MapKitFactory.initialize(root.getContext());
        mapView = root.findViewById(R.id.mapView);
        user = root.findViewById(R.id.imageButton);

        user.setOnClickListener(e -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
            builder.setMessage("Hello, World!")
                    .setTitle("Title");
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mapView != null) {
            mapView.onStart();
            MapKitFactory.getInstance().onStart();

            mapView.getMapWindow().getMap().move(
                    new CameraPosition(new Point(44.892789, 37.316780), 14.60f, 113.60f, 130.0f),
                    new Animation(Animation.Type.SMOOTH, 0),
                    null);


            //mapView.getMapWindow().setFocusPoint(new ScreenPoint((float) 44.892789, (float) 37.316780));
        }
    }



    @Override
    public void onStop() {
        super.onStop();
        if (mapView != null) {
            mapView.onStop();
            MapKitFactory.getInstance().onStop();
       }
    }

    //    @Override
//    public void onStart() {
//        super.onStart();
//        if (mapView != null) {
//            mapView.onStart();
//            MapKitFactory.getInstance().onStart();
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mapView != null) {
//            mapView.onStop();
//            MapKitFactory.getInstance().onStop();
//       }
//    }
}