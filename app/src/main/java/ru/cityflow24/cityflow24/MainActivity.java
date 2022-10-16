package ru.cityflow24.cityflow24;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import APILayer.DBAPI;
import model.User;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    public static DBAPI localeDB;

    public static User user;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        localeDB = new DBAPI(this);

        user = localeDB.getUser();
        // установка слушателя левого свайп меню
        final NavigationView navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        View header = navigationView.getHeaderView(0);
        TextView userName = header.findViewById(R.id.userNameView);
        userName.setText(user.getName());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // не переоткрываем фрагмент, если он уже открыт
                if (navigationView.getCheckedItem() != menuItem) {
                    viewFragmentForMenuItem(menuItem);
                    menuItem.setChecked(true);
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        navigationView.setCheckedItem(R.id.nav_user_mainpage); // чтобы начальный пункт меню подсвечивался как выбранный
    }

    @Override
    protected void onStart(){
        super.onStart();

        Class<? extends Fragment> fragmentClass = null;
        fragmentClass = UsersEventFragment.class;
        Fragment fragment = null;
        try {
            fragment = fragmentClass.newInstance();

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment1, fragment).commit();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Показывает фрагмент, соответствующий пункту меню
     * @param menuItem выбранный пункт меню
     */
    private void viewFragmentForMenuItem(MenuItem menuItem) {
        Class<? extends Fragment> fragmentClass = null;

        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_home:
                fragmentClass = MapFragment.class;
                break;
            case R.id.nav_events:
                fragmentClass = EventsListFragment.class;
                break;
            case R.id.nav_user_mainpage:
                fragmentClass = UsersEventFragment.class;
                break;
            default:
                Log.d(TAG, "Нажатие " + menuItem.getTitle());
        }

        try {
            if (fragmentClass != null) {

                Fragment fragment = fragmentClass.newInstance();

                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragment1, fragment).commit();

                //FloatingActionButton addButton = findViewById(R.id.addButton);
                //if (fragmentClass != MapFragment.class) {
                //    addButton.hide();
                //} else {
                //    addButton.show();
                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }
}