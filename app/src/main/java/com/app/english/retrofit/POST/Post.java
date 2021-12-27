package com.app.english.retrofit.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.english.retrofit.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class Post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        TextView tvPost =findViewById(R.id.tvPost) ;

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class) ;


         HashMap<Object ,Object> map = new HashMap<>();
         map.put("title" , "title ") ;
         map.put("body" , "body");
         map.put("userId" ,100 ) ;

        Call<Poste> call = apiInterface.StorePost(map) ;
        call.enqueue(new Callback<Poste>() {
            @Override
            public void onResponse(Call<Poste> call, Response<Poste> response) {
            tvPost.setText(response.body().getTitle());
            }
            @Override
            public void onFailure(Call<Poste> call, Throwable t) {

            }
        });


    } class Poste {

        private String title ;
        private  String body ;
        private int userId  ;

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
        public int getUserId() {
            return userId;
        }
        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Poste(String title, String body, int userId) {
            this.title = title;
            this.body = body;
            this.userId = userId;

        }
    }   interface  ApiInterface {
        // Post class
        //
        @POST("posts")
        public Call<Poste> StorePost(@Body HashMap<Object, Object> map) ;


    }
}
