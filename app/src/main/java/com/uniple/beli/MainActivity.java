package com.uniple.beli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uniple.beli.adapter.ProductAdapter;
import com.uniple.beli.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize preference with mystore name
        preferences = getSharedPreferences("mystore", MODE_PRIVATE);
        // Declare & link fabBuy with layout fab_buy id
        FloatingActionButton fabBuy = findViewById(R.id.float_btn);
        // Set onClickListener for fabBuy
        fabBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to BuyActivity
                Intent intent = new Intent(MainActivity.this, buy.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get stored data from preference
        String belanjaJson = preferences.getString("belanjaJson", "");
        Log.d("MainActivity", "belanjaJson -> " + belanjaJson);
        // Convert json data to data object and cast it to Product[]0
        List<Product> orderList = new Gson().fromJson(belanjaJson, new TypeToken<List<Product>>() {
        }.getType());
        // Check orderList is not null
        if (orderList != null) {
            // Declare & link listView with layout list_view id
            ListView listView = findViewById(R.id.lv);
            // Declare & instantiate adapter with ProductAdapter and give orderList as the array data
            ProductAdapter adapter = new ProductAdapter(MainActivity.this, orderList);
            // Set adapter for listView
            listView.setAdapter(adapter);
            Log.d("MainActivity", "belanjaList.size() -> " + orderList.size());
        }
    }
}