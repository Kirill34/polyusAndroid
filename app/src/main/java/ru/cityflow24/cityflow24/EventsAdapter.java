package ru.cityflow24.cityflow24;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import APILayer.APIClient;
import model.Event;
import model.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventHolder> {

    private EventItemClickListener listener;
    private static EventsFragment ef;

    public static ArrayList<Event> MPs = new ArrayList<>();// = MainActivity.localeDB.getAllMapPoints();

    public EventsAdapter(EventsFragment eventsFragment) {
        ef = eventsFragment;
        try {
            MPs = getAllEvents();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EventHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.events_list_tem, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder eventHolder, int i) {
        eventHolder.bind(listener, MPs.get(i));
      //  eventHolder.iv.setImageResource(R.drawable.event3/*MPs.get(i).getImmageID()*/);//Заменить на iv.setImageURI() когда будем получать URL

    }

    @Override
    public int getItemCount() {
        return MPs.size();
    }

    public void setEventClickListener(EventItemClickListener listener) {
        this.listener = listener;
    }

    public interface EventItemClickListener {
        void onEventItemClick(int adapterPosition);
    }

    class EventHolder extends RecyclerView.ViewHolder {

        private final float TEXT_SIZE = 18;
        private final int TEXT_COLOR = Color.BLACK;
        public ImageView iv;

        public EventHolder(@NonNull View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.imageView);
        }

        public void bind(final EventItemClickListener listener, Event mp) {

            TextView titleView = itemView.findViewById(R.id.event_title);
            TextView descriptionView = itemView.findViewById(R.id.description);
            TextView addressView = itemView.findViewById(R.id.address);

            TextView start = itemView.findViewById(R.id.startTime);

            TextView end = itemView.findViewById(R.id.endTime);
            //TextView ratioView = itemView.findViewById(R.id.event_ratio);
            Button moreButton = itemView.findViewById(R.id.moreButton);

            start.setText(""+mp.getDateFrom());
            end.setText(""+mp.getDateTo());

            if(mp.getName().length() >= 28) {
                titleView.setText(mp.getName().substring(0, 27));
            }else{
                titleView.setText(mp.getName());
            }
            //titleView.setTextSize(TEXT_SIZE);

            descriptionView.setText(mp.getDescription());

            //descriptionView.setTextColor(TEXT_COLOR);

            addressView.setText("Ул.Сокрытая, Дом 21д");


            //ratioView.setText("" + mp.getReit());
            //ratioView.setTextColor(TEXT_COLOR);
            //ratioView.setTextSize(TEXT_SIZE);

            //при клике на элемент списка будет вызываться листенер, в который передается ссылка на выбранную статью
            moreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEventItemClick(getAdapterPosition());
                }
            });
        }
    }
    private static ArrayList<Event> getAllEvents() throws InterruptedException, IOException {
       // Global.basicAuth = Credentials.basic(MainActivity.user.getPhone(), MainActivity.user.getPassword());
        Call<List<Event>> call = APIClient.getInstance().getApi().getAllEvents(Global.basicAuth);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful()) {
                    MPs = (ArrayList<Event>) response.body();
                    ef.init();
                }

            }
            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                call.cancel();
                MPs = new ArrayList<>();
                        //MainActivity.localeDB.getAllMapPoints();
            }
        });

        return MPs;
    }

}
