package com.example.travelersleague;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.util.*;

import static com.example.travelersleague.R.id.start;
import static com.example.travelersleague.R.id.textView2;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 1;
    ArrayList travels;
    Spinner spinner;
    ArrayAdapter spinnerAdapter;
    HashMap costTravels;
    String nameTravels;
    int cost;
    EditText userName;
    int costOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSpinner();
        setCost();
        userName = findViewById(R.id.userName);
    }

    void createSpinner(){
        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        travels = new ArrayList();
        travels.add("Крым август");
        travels.add("Байкал сентябрь");
        travels.add("Теберда сентябрь");
        travels.add("Крым сентябрь");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, travels);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    void setCost(){
        costTravels = new HashMap();
        costTravels.put("Крым август", 7500);
        costTravels.put("Байкал сентябрь", 19500);
        costTravels.put("Теберда сентябрь", 12500);
        costTravels.put("Крым сентябрь", 9500);
    }

    public void setMinus(View view) {
        TextView text = findViewById(R.id.textView2);
        quantity = quantity-1;
        if (quantity<1) {
            quantity=1;
        }
        text.setText (""+quantity);
        TextView textView = findViewById(R.id.textView);
        costOrder = quantity*cost;
        textView.setText(costOrder+" ₽");
    }

    public void setPlus(View view) {
        TextView text = findViewById(R.id.textView2);
        quantity = quantity+1;
        text.setText (""+quantity);
        TextView textView = findViewById(R.id.textView);
        costOrder = quantity*cost;
        textView.setText(costOrder+" ₽");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nameTravels = spinner.getSelectedItem().toString();
        cost = (int)costTravels.get(nameTravels);
        TextView textView = findViewById(R.id.textView);
        costOrder = quantity*cost;
        textView.setText(costOrder+" ₽");
        ImageView pictureTravels = findViewById(R.id.pictureTravels);

        switch (nameTravels){
            case "Крым август":
                pictureTravels.setImageResource(R.drawable.krimea);
                break;
            case "Байкал сентябрь":
                pictureTravels.setImageResource(R.drawable.baikal);
                break;
            case "Теберда сентябрь":
                pictureTravels.setImageResource(R.drawable.teberda);
                break;
            case "Крым сентябрь":
                pictureTravels.setImageResource(R.drawable.krimea2);
                break;
            default:
                pictureTravels.setImageResource(R.drawable.krimea);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName=userName.getText().toString();
        order.nameTravels=nameTravels;
        order.quantity=quantity;
        order.cost=cost;
        order.costOrder=costOrder;

        Intent addToCart = new Intent(MainActivity.this, OrderActivity.class);
        addToCart.putExtra("userName", order.userName);
        addToCart.putExtra("nameTravels", order.nameTravels);
        addToCart.putExtra("quantity", order.quantity);
        addToCart.putExtra("cost", order.cost);
        addToCart.putExtra("costOrder", order.costOrder);

        startActivity(addToCart);
    }
}