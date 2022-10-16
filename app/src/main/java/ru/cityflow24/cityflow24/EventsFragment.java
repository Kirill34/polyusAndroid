package ru.cityflow24.cityflow24;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventsFragment extends Fragment {
    private final String TAG = "EventsFragment";
    private View view = null;
    private EventsAdapter adapter;

    private RecyclerView eventsList;
    private SwipeRefreshLayout refreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events, container, false);
        // адаптер для списка
        adapter = new EventsAdapter(this);
        adapter.setEventClickListener(new EventsAdapter.EventItemClickListener() {
            @Override
            public void onEventItemClick(int adapterPosition) {
                Intent intent = new Intent(getContext(), EventDescriptionActivity.class);
                intent.putExtra("SHOWED_EVENT", adapterPosition);
                startActivity(intent);
                Log.d(TAG, "Клик на элемент списка событий");
            }
        });
        return view;
    }

    public void init(){

        eventsList = view.findViewById(R.id.events_list);
        refreshLayout = view.findViewById(R.id.events_refresh);

        //  настройка обновления списка
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "on refresh events");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        // layout элементов списка
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        eventsList.setLayoutManager(layoutManager);


        eventsList.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
