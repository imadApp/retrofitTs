package com.app.english.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.app.english.retrofit.GET.Get;
import com.app.english.retrofit.POST.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    TextView tvData ;
    Button btnGet , btnPost ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = findViewById(R.id.Get) ;
        btnPost = findViewById(R.id.Post) ;

        tvData =findViewById(R.id.textView) ;
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Post.class));
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext() , Get.class));
            }
        });

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        ApiInterface apiInterface = retrofit.create(ApiInterface.class) ;
        Call<Poste> call = apiInterface.getPoste() ;
        call.enqueue(new Callback<Poste>() {
            @Override
            public void onResponse(Call<Poste> call, Response<Poste> response) {
                tvData.setText(response.body().getTitle());

            }

            @Override
            public void onFailure(Call<Poste> call, Throwable t) {
                tvData.setText(t.getMessage());


            }
        });

    } static class Poste {
         private int userId  ;
         private  int  id  ;
         private String title ;
         private  String body ;

        public int getUserId() {
            return userId;
        }
        public void setUserId(int userId) {
            this.userId = userId;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getBody() {
            return body;
        }
        public void setBody(String body) {
            this.body = body;
        }

    }  public interface  ApiInterface {
          @GET("posts/1")
        Call<Poste> getPoste() ;
    }
}