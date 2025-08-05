package com.example.threadconcept;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
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
        btnGetData.setOnClickListener(v -> {
            pbLoadData.setVisibility(View.VISIBLE);
            new FetchAPIAsync().execute(url);
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

    private void hideRecyclerView(){
        runOnUiThread(() -> {
            rvCards.setVisibility(View.GONE);
        });
    }
    private List<Card> fetchDataFromAPI() throws IOException {
        String jsonResponse = HttpRequest.get(url);
        Card[] usersArray = gson.fromJson(jsonResponse,Card[].class);
        List<Card> users = List.of(usersArray);
        return users;
    }

    private void updateUI(List<Card> users){
        pbLoadData.setVisibility(View.GONE);
        rvCards.setVisibility(View.VISIBLE);
        cardAdapter.setCards(users);
    }
    private class FetchAPIAsync extends AsyncTask<String, Void, List<Card>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            hideRecyclerView();
        }


        @Override
        protected List<Card> doInBackground(String... url) {
            try {
                return fetchDataFromAPI();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<Card> users) {
            super.onPostExecute(users);
            updateUI(users);
        }
    }
}