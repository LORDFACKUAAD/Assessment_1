package com.example.assessment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {
    String message;
    String name;
    String price;
    CoffeeDBHanler dbHanler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dbHanler = new CoffeeDBHanler(this, null, null, 1);
        Intent newIntent = getIntent();
        name = newIntent.getStringExtra("name");
        message = newIntent.getStringExtra("message");
        price = newIntent.getStringExtra("price");

        TextView displayMessage = (TextView)findViewById(R.id.textint);
        displayMessage.setText(message);
    }
    public void addButtonClicked(View view){
        Order order = new Order(name, Integer.parseInt(price));
        dbHanler.addOrder(order);
        Toast.makeText(getApplicationContext(),"Data Saved!",Toast.LENGTH_SHORT).show();
    }
    public void salesReport(View view){
        String dbString = dbHanler.databaseToString();
        Intent salesIntent = new Intent(this, DisplaySalesDetails.class);
        salesIntent.putExtra("db", dbString);
        startActivity(salesIntent);
    }
    public void sendEmail(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT,message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}