package ru.cityflow24.cityflow24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        routeToAppropriateActivity();
        finish();
    }

    private void routeToAppropriateActivity() {
        // здесь логика выбора активити для показа
        // если сессия не сохранена - показать LoginActivity
        // иначе сразу к главному активити
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
