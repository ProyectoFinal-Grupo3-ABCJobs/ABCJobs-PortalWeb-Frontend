package com.example.proyectoabc.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.proyectoabc.R;
import com.example.proyectoabc.service.ApiAutenticacion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_ingresar=findViewById(R.id.ingresar_login);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            EditText edit_usuario=findViewById(R.id.usuario_login);
            EditText edit_contrasena=findViewById(R.id.contrasena_login);
            HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
            @Override
            public void onClick(View view) {
                String usuario = edit_usuario.getText().toString().trim();
                String contrasena = edit_contrasena.getText().toString().trim();

                loggin.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(loggin);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();
                ApiAutenticacion apiAutenticacion = retrofit.create(ApiAutenticacion.class);
                Call<Usuario> call = apiAutenticacion.LOGIN_CALL(usuario, contrasena);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        edit_usuario.getText().clear();
                        edit_contrasena.getText().clear();
                        if(response.isSuccessful() && response.body() != null){
                            String token = response.body().getToken();
                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {

                    }
                });
            }
        });
    }
}