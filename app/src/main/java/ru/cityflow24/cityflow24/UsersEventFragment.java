package ru.cityflow24.cityflow24;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class UsersEventFragment extends Fragment {

    private final String TAG = "EventListFragment";

    private UserEventListTabsAdapter tabsAdapter;

    private ViewPager viewPager;

    public UsersEventFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_mainpage, container, false);

        ConstraintLayout layout = view.findViewById(R.id.constraintLayout);

        Button btn1 = new Button(this.getContext());
        btn1.setText("НА КАРТУ");
        btn1.getOffsetForPosition(10,300);
        layout.addView(btn1);

        //btn1.setOnClickListener(Intent.CATEGORY_APP_MAPS);

        TextView name = view.findViewById(R.id.name);
        name.setText(MainActivity.user.getName());
        name.setTextColor(Color.parseColor("#696969"));

        TextView descr = view.findViewById(R.id.description);
      //  descr.setText(MainActivity.user.getDescription());
        descr.setTextColor(Color.GRAY);

        viewPager = view.findViewById(R.id.pager);

        TextView textViewCurrent = new TextView(this.getContext());
        textViewCurrent.setText("Current order");
        viewPager.addView(textViewCurrent);

        // настройка вкладок
        tabsAdapter = new UserEventListTabsAdapter(getChildFragmentManager());
        viewPager.setAdapter(tabsAdapter);

        TabLayout tl = view.findViewById(R.id.tab_layout);
        tl.setupWithViewPager(viewPager);

        ImageButton menuButton = view.findViewById(R.id.menuButton);
        // настройка кнопки меню
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getDrawerLayout().openDrawer(Gravity.LEFT);
            }
        });


        // TextView name2 = view.findViewById(R.id.name2);
       // name2.setText("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
       // View baseLayout = viewPager.findViewWithTag(R.layout.pager);
     //   TextView city = (TextView) baseLayout.findViewById(R.id.name2);


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

}
