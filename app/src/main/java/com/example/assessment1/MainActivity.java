package com.example.assessment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int noOfCoffee = 0;
    int priceOfCoffee = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        //display(noOfCoffee);
        //int price = noOfCoffee * noOfCoffee;
//        TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
//        priceTextView.setText("Total $"+price+"\n"+"Thank You - Visit Us Again");

        //1. We need the name of the customer to be stored into a variable
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        //2. We need to store the boolean values for toppings (Whipped cream and Chocolate)
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        //3. We need to calculate the total price
        int price = calculateOrderSummary(hasWhippedCream,hasChocolate);
        String message = createOrderSummary(name,hasWhippedCream,hasChocolate,noOfCoffee,price);
        //4. We need to have an intent to send information to the second screen
        Intent newIntent = new Intent(this,MainActivity2.class);
        newIntent.putExtra("name", name);
        newIntent.putExtra("message", message);
        newIntent.putExtra("price", Integer.toString(price));
        startActivity(newIntent);

    }

    public String createOrderSummary(String n,boolean hWC,boolean hC,int nOC,int p){

            String orderMessage = "Name: "+n+"\n"+
                "Add Whipped Cream?"+hWC+"\n"+
                "Add Chocolate?"+hC+"\n"+
                "Quantity: "+nOC+"\n"+
                "Total: $" +p+"\n"+
                "Thank you!"+"\n";
                    return orderMessage;


}
        public int calculateOrderSummary(boolean hwc, boolean hc){
            //if the user wants whipped cream we need to add $1 to the price of coffee
            if (hwc == true)
                priceOfCoffee = priceOfCoffee+1;
            //if the user wants chocolate we need to add $2 to the price of coffee
            if (hc == true)
                priceOfCoffee = priceOfCoffee+2;
            //return the price
            int totalPrice = noOfCoffee*priceOfCoffee;
            return priceOfCoffee;
    }
        public void display(int number){
            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantityTextView.setText(""+number);
    }
    public void increment(View view){
        noOfCoffee = noOfCoffee +1;
        if (noOfCoffee >=10 ){
            noOfCoffee = 10;
        }
        display(noOfCoffee);

    }
    public void decrement(View view){
        noOfCoffee = noOfCoffee -1;
        if (noOfCoffee<=0){
            noOfCoffee = 0;
        }
        display(noOfCoffee);

    }
}