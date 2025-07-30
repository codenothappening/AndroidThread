package com.example.threadconcept;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CardAdapter cardAdapter;

    Button getData;

    ProgressBar progressBar;

    String url = "https://api.github.com/users";

    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardAdapter();
        recyclerView.setAdapter(cardAdapter);
        getData = findViewById(R.id.getButton);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                fetchDataFromAPI();
            }
        });
    }

    private void fetchDataFromAPI() {
        runOnUiThread(() -> recyclerView.setVisibility(View.GONE));

        Single.create(emitter -> {
            try {
                String jsonResponse = HttpRequest.get(url);
                Card[] userArray = gson.fromJson(jsonResponse,Card[].class);
                List<Card> users = Arrays.asList(userArray);
                emitter.onSuccess(users);
            } catch (IOException e) {
                e.printStackTrace();
            }
            })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        users -> {
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            cardAdapter.setCards((List<Card>) users);
                        },
                        throwable -> {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "API fetch failed", Toast.LENGTH_SHORT).show();
                            throwable.printStackTrace();
                        }
                );
    }
}