package ru.cityflow24.cityflow24;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EventsListFragment extends Fragment {

    private final String TAG = "EventListFragment";

    private SearchView searchView;
    private ImageButton menuButton;
    private ViewPager viewPager;

    private EventsListTabsAdapter tabsAdapter;

    public EventsListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_events_list, container, false);

        searchView = view.findViewById(R.id.searchView);
        menuButton = view.findViewById(R.id.menuButton);
        viewPager = view.findViewById(R.id.pager);

        // настройка вкладок
        tabsAdapter = new EventsListTabsAdapter(getChildFragmentManager());
        viewPager.setAdapter(tabsAdapter);

        // установка слушателей SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                HashSet<String> hashtags = new HashSet<>();
                String pureQuery = parseQueryWithHashtags(query, hashtags);

                Log.d(TAG, "started query : " + pureQuery + " with hashtags: ");
                for (String str : hashtags) {
                    Log.d(TAG, str);
                }

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

        // устанавливаем слушателей для fab'ов
        view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Нажатие FAB \"+\"");
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static String parseQueryWithHashtags(String query, @NonNull HashSet<String> hashtags) {
        Pattern hashtagPattern = Pattern.compile("(#)[^\\s#]+");
        Matcher matcher = hashtagPattern.matcher(query);

        while (matcher.find()) {
            String foundTag = matcher.group();
            query = query.replace(foundTag, "");
            hashtags.add(foundTag);
        }

        return query.trim();
    }
}
