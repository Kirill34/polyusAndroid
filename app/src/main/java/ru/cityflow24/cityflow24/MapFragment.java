package ru.cityflow24.cityflow24;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;

import APILayer.DBAPI;
import model.mapPoint;

public class MapFragment extends Fragment {

    private final String TAG = "MapFragment";

    private MapView mapView;
    private SearchView searchView;
    private ImageButton menuButton;

    private final String YMAP_API_KEY = "0e139a0e-4e48-4e9e-b7d9-8685d9363257";
    private final Point VOLGOGRAD = new Point(48.71939, 44.50183);
    private final Point POLYUS = new Point(39.2448936,63.6228555);
    private final float ZOOM = 14.0f;
    private final float PLACEMARK_SCALE = 0.2f;

    //private DBAPI localeDB;

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "on attach");
        super.onAttach(context);

        MapKitFactory.setApiKey(YMAP_API_KEY);
        MapKitFactory.initialize(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "on create view");

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = view.findViewById(R.id.mapview);
        searchView = view.findViewById(R.id.searchView);
        menuButton = view.findViewById(R.id.menuButton);

        // устанавливаем карту на Волгоград
        mapView.getMap().move(new CameraPosition(POLYUS, ZOOM, 0.0f, 0.0f));

        MainActivity.localeDB = new DBAPI(getContext());
        MainActivity.localeDB.deleteAllEvents();
        MainActivity.localeDB.init();

        //Здесь выводим все точки событий на карту
        ArrayList<mapPoint> points = MainActivity.localeDB.getAllMapPoints();
        for(int i = 0; i < points.size(); i++){
            ImageProvider iconProvider = ImageProvider.fromResource(getContext(), R.drawable.placemark);
            IconStyle iconStyle = new IconStyle().setScale(PLACEMARK_SCALE);
            mapView.getMap().getMapObjects().addPlacemark(new Point(points.get(i).getCoordinate().first,points.get(i).getCoordinate().second), iconProvider, iconStyle);
        }

        // устанавливаем слушателей для fab'ов
        /*view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Нажатие FAB \"+\"");
            }
        });*/

        // установка слушателей SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "query: " + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // настройка кнопки меню
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getDrawerLayout().openDrawer(Gravity.LEFT);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "on start");
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }


    @Override
    public void onStop() {
        Log.d(TAG, "on stop");
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }
}
