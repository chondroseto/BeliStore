package com.uniple.beli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uniple.beli.adapter.ProductAdapter;
import com.uniple.beli.model.Product;

import java.util.ArrayList;
import java.util.List;

public class buy extends AppCompatActivity {

    private List<Product> buyList = new ArrayList<>();
    // Declare preference
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        if (getSupportActionBar() != null) {
            // Enable back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Set title
            getSupportActionBar().setTitle("Belanja Produk");
        }
        // Getting preference with mystore name
        preferences = getSharedPreferences("mystore", MODE_PRIVATE);
        // Declare gridView & link with layout grid_view id
        GridView gridView = findViewById(R.id.gv);
        // Declare & instantiate products from belanjaJson preference and cast it into List<Product>
        List<Product> products = new Gson().fromJson(preferences.getString("belanjaJson", ""), new TypeToken<List<Product>>() {
        }.getType());
        if (products != null) {
            // If product exist, add all data to buyList
            buyList.addAll(products);
        }
        // Declare & Instantiate productList
        List<Product> productList = new ArrayList<>();
        // Add productList static data
        productList.add(new Product(
                1,
                "https://brain-images-ssl.cdn.dixons.com/7/6/10178467/u_10178467.jpg",
                "TV",
                2500000));
        productList.add(new Product(
                2,
                "https://www.lg.com/au/images/smartphones/md05878255/gallery/V30-medium01.jpg",
                "Smartphone",
                2000000));
        productList.add(new Product(
                3,
                "https://images-na.ssl-images-amazon.com/images/I/71t-J3VJtEL._SX425_.jpg",
                "Laptop",
                1500000));
        productList.add(new Product(
                4,
                "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ma/imac/215/imac-215-selection-hero-201706_GEO_AU?wid=892&hei=820&&qlt=80&.v=1537286645459",
                "Komputer",
                2250000));
        productList.add(new Product(
                5,
                "https://www.projectorcentral.com/images/projectors2/img10453.jpg",
                "Proyektor",
                1000000));
        // Delare & Instantiate adapter with ProductAdapter and give productList as it's data
        ProductAdapter adapter = new ProductAdapter(this, productList);
        // Set adapter for gridview
        gridView.setAdapter(adapter);
        // Make gridView columns as 2
        gridView.setNumColumns(2);
        // Accessing orderOnClick callback
        adapter.orderOnClick(new ProductAdapter.OrderCallback() {
            @Override
            public void orderOnclick(int id, String productName, int productPrice, String productImage) {
                // Instantiate buyList with new data object
                buyList.add(new Product(
                        id,
                        productImage,
                        productName,
                        productPrice));
                // Convert buyList (Array) as JSON String
                String belanjaJson = new Gson().toJson(buyList, new TypeToken<List<Product>>() {
                }.getType());
                // Enable editor for preference
                SharedPreferences.Editor editor = preferences.edit();
                // Storing data belanjaJson
                editor.putString("belanjaJson", belanjaJson);
                // Applying edit
                editor.apply();
                // Close the activity
                finish();
                Log.d("BelanjaActivity", "pesanOnClick id -> " + id);
                Log.d("BelanjaActivity", "belanjaListJson -> " + belanjaJson);
                // Show message
                Toast.makeText(buy.this, productName + " berhasil di pesan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Callback given for listening back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Go back
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}