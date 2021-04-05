package com.example.travelersleague;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String[] addresses = {"ewgeney@mail.ru"};
    String subject = "Заказ путешествия";
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Ваш заказ:");

        Intent getOrder = getIntent();
        String userName = getOrder.getStringExtra("userName");
        String nameTravels = getOrder.getStringExtra("nameTravels");
        int quantity = getOrder.getIntExtra("quantity",0);
        int cost = getOrder.getIntExtra("cost",0);
        int costOrder = getOrder.getIntExtra("costOrder",0);


        text = "Имя: " + userName + "\n"+ "Поход: "
                +nameTravels+"\n"+ "Количество человек: " +quantity+"\n"+ "Стоимость похода: " +
                "" +cost+"\n"+ "Стоимость заказа: " +costOrder;
        TextView orderView = findViewById(R.id.orderView);
        orderView.setText(text);

    }

    public void sendEmail(View view) {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
