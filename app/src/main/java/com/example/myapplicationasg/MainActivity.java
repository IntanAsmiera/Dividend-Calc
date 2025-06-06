package com.example.myapplicationasg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etInvestment, etRate, etMonths;
    Button btnCalculate;
    TextView tvMonthlyDividend, tvTotalDividend;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Connect UI elements to Java code
        etInvestment = findViewById(R.id.etInvestment);
        etRate = findViewById(R.id.etRate);
        etMonths = findViewById(R.id.etMonths);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvMonthlyDividend = findViewById(R.id.tvMonthlyDividend);
        tvTotalDividend = findViewById(R.id.tvTotalDividend);


        //  Button Click Listener for btnCalculate
        btnCalculate.setOnClickListener(v -> {
            try {
                double fund = Double.parseDouble(etInvestment.getText().toString());
                double rate = Double.parseDouble(etRate.getText().toString());
                int months = Integer.parseInt(etMonths.getText().toString());

                if (months > 12) {
                    months = 12; // Limit to 12 months
                    Toast.makeText(this, "Months limited to 12", Toast.LENGTH_SHORT).show();
                }

                double monthlyDividend = (rate / 100 / 12) * fund;
                double totalDividend = monthlyDividend * months;

                // Display formatted results
                tvMonthlyDividend.setText(String.format("Monthly Dividend: RM %.2f", monthlyDividend));
                tvTotalDividend.setText(String.format("Total Dividend: RM %.2f", totalDividend));

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); // Make sure your menu file is res/menu/menu.xml
        return true;
    }

    // Handle menu item click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuAbout) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
