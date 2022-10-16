package ru.cityflow24.cityflow24;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import APILayer.DBAPI;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private DBAPI dbapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button)findViewById(R.id.signInButton);

        dbapi = new DBAPI(this);

        // создаем обработчик нажатия
        View.OnClickListener oclBtnLogin = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = ((EditText) findViewById(R.id.loginEdit)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordEdit)).getText().toString();
                login(login,password);
            }
        };

        btnLogin.setOnClickListener(oclBtnLogin);
    }

    private void login(String login, final String password){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
       // Global.basicAuth = Credentials.basic(login, password);
      //  Call<User> call = APIClient.getInstance().getApi().login(Global.basicAuth);
      /*  call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
           //     if (response.isSuccessful()) {
                  //  Global.ingelogd = response.body();
                  //  if(Global.ingelogd.getActive()){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    //    Global.ingelogd.setPassword(password);
                    //    dbapi.setUser(Global.ingelogd);

               //         startActivity(intent);
              //      }
            //    }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Toast.makeText(getApplicationContext(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });*/
    }
}
