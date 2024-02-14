package com.example.hashdash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class RazorPayActivity extends AppCompatActivity implements PaymentResultListener {

    private EditText amountEdt;
    private Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Checkout checkout = new Checkout();
        // setting api key
        checkout.setKeyID("rzp_test_Yf9o0Eps380RRc");

        amountEdt = findViewById(R.id.amount_edt);
        payBtn = findViewById(R.id.btnPay);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = amountEdt.getText().toString();

                int amountINT = Math.round(Float.parseFloat(amount) * 100);


                JSONObject object = new JSONObject();
                try {

                    object.put("name", "HashDash");
                    object.put("description", "Test payment");
                    object.put("currency", "INR");
                    object.put("amount", amountINT);
                    object.put("prefill.contact", "8527531585");
                    object.put("prefill.email", "imvikaskumar99@gmail.com");

                    // open razorpay checkout activity
                    checkout.open(RazorPayActivity.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment successful " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment failed " + s, Toast.LENGTH_SHORT).show();

    }
}