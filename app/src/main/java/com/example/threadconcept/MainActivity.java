package com.example.threadconcept;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CardAdapter cardAdapter;

    Button getData;

    ProgressBar progressBar;

    List<Card> cardList;

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
        runOnUiThread(() -> {
            recyclerView.setVisibility(View.GONE);
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonResponse = HttpRequest.get(url);
                    Card[] usersArray = gson.fromJson(jsonResponse,Card[].class);
                    List<Card> users = Arrays.asList(usersArray);
                    runOnUiThread(() -> {
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
//                        cardList = new ArrayList<>(users);
                        cardAdapter.setCards(users);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(() ->
                            Toast.makeText(MainActivity.this, "API fetch failed", Toast.LENGTH_SHORT).show()
                    );
                }
            }
        }).start();
    }
}