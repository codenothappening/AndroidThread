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

public class MainActivity extends AppCompatActivity {
    RecyclerView rvCards;
    CardAdapter cardAdapter;
    Button btnGetData;
    ProgressBar pbLoadData;
    String url = "https://api.github.com/users";
    private final Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initUI();
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLoadData.setVisibility(View.VISIBLE);
                fetchDataFromAPI();
            }
        });
    }
    private void initUI(){
        rvCards = findViewById(R.id.rv_Cards);
        rvCards.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardAdapter();
        rvCards.setAdapter(cardAdapter);
        btnGetData = findViewById(R.id.btn_getData);
        pbLoadData = findViewById(R.id.pb_loadData);
        pbLoadData.setVisibility(View.GONE);
    }

    private void fetchDataFromAPI() {
        hideRecyclerView();
        new Thread(() -> {
            try {
                fetchDataFromAPISuccess();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    private void hideRecyclerView(){
        runOnUiThread(() -> {
            rvCards.setVisibility(View.GONE);
        });
    }
    private void fetchDataFromAPISuccess() throws IOException {
        String jsonResponse = HttpRequest.get(url);
        Card[] usersArray = gson.fromJson(jsonResponse,Card[].class);
        List<Card> users = Arrays.asList(usersArray);
        updateUI(users);
    }

    private void updateUI(List<Card> users){
        runOnUiThread(() -> {
            pbLoadData.setVisibility(View.GONE);
            rvCards.setVisibility(View.VISIBLE);
            cardAdapter.setCards(users);
        });

    }
}