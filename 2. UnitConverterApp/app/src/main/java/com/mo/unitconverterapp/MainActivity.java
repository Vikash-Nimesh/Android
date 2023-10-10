package com.mo.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.button);
        textView = findViewById(R.id.textView);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting the user input (Kilos)
                String inputText = editText.getText().toString();


                // Converting a string into double
                double kilos = Double.parseDouble(inputText);

                // Converting kilos into pounds
                double pounds = makeConversion(kilos);

                // Displaying the conversion result
                textView.setText(String.format("%s", pounds));
            }
        });
    }

    public double makeConversion(double kilos){
        // 1 kilo = 2.20462 pounds

        return kilos * 2.20462;

    }
}