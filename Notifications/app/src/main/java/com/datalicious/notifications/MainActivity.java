package com.datalicious.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    Button btnHit, btnHit2, btnHit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    //    notification_receive  message_id
//    notification_open
    private void initViews() {
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setUserProperty("iAmFrom", "Datalicious");

        btnHit = (Button) findViewById(R.id.btnHit);
        btnHit2 = (Button) findViewById(R.id.btnHit2);
        btnHit3 = (Button) findViewById(R.id.btnHit3);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle params = new Bundle();
                params.putString("eventNum", "test1");
                mFirebaseAnalytics.logEvent("customEvent1", params);

            }
        });

        btnHit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle params = new Bundle();
                params.putString("eventNum", "test2");
                mFirebaseAnalytics.logEvent("customEvent2", params);

            }
        });

        btnHit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle product1 = new Bundle();
                product1.putString("item_id", "sku1234"); // ITEM_ID or ITEM_NAME is required
                product1.putString("item_name", "Donut Friday Scented T-Shirt");
                product1.putString("item_category", "Apparel/Men/Shirts");
                product1.putString("item_variant", "Blue");
                product1.putString("item_brand", "Google");
                product1.putDouble("price", 29.99);
                product1.putString("currency", "USD"); // Item-level currency unused today
                product1.putString("custom_param", "datalicious"); //

                // Prepare ecommerce bundle

                Bundle ecommerceBundle = new Bundle();
                ecommerceBundle.putBundle("items", product1);

                // Log view_item event with ecommerce bundle

                mFirebaseAnalytics.logEvent("add_to_cart", ecommerceBundle);

            }
        });
    }
}
