package com.app.english.retrofit.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.english.retrofit.MainActivity;
import com.app.english.retrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Get extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        TextView textGet =findViewById(R.id.textGet) ;

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

       ApiInterface apiInterface = retrofit.create(ApiInterface.class) ;
        Call<List<Poste>> call = apiInterface.getPost("1");
        call.enqueue(new Callback<List<Poste>>() {
            @Override
            public void onResponse(Call<List<Poste>> call, Response<List<Poste>> response) {
                //
                textGet.setText(response.body().get(3).getTitle());
            }

            @Override
            public void onFailure(Call<List<Poste>> call, Throwable t) {
             textGet.setText(t.getMessage());
            }
        });

    } class Poste {
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

    }   interface  ApiInterface {
        @GET("posts")
        // @Query الاستعلام
        public Call<List<Poste>> getPost(@Query("Id") String Id) ;


    }
}
