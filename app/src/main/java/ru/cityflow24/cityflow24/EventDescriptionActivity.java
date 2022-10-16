package ru.cityflow24.cityflow24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
public class EventDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView descrView = (TextView)findViewById(R.id.description);
       // ImageView iv = (ImageView)findViewById(R.id.imageView3);
        TextView startTime = (TextView)findViewById(R.id.startTime);
        TextView endTime = (TextView)findViewById(R.id.endTime);
        int curIndex = getIntent().getIntExtra("SHOWED_EVENT", -1);
        if(curIndex != -1) {
            descrView.setText(EventsAdapter.MPs.get(curIndex).getDescription());
          //  iv.setImageResource(/*EventsAdapter.MPs.get(curIndex).getImmageID()*/R.drawable.event3);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy в HH:mm");

            String str = EventsAdapter.MPs.get(curIndex).getDateFrom();
            startTime.setText(str);

            str = EventsAdapter.MPs.get(curIndex).getDateTo();

            endTime.setText(str);

            String tmp = "";
        }
    }
}
