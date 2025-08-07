package com.example.bmi_calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private EditText ageInput, heightInput, weightInput;
    private Spinner genderSpinner;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ageInput = findViewById(R.id.ageInput);
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        genderSpinner = findViewById(R.id.genderSpinner);
        calculateButton = findViewById(R.id.calculateButton);

        // Set gender spinner options
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                new String[]{"Select Gender", "Male", "Female"});
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        calculateButton.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String ageStr = ageInput.getText().toString();
        String heightStr = heightInput.getText().toString();
        String weightStr = weightInput.getText().toString();
        String gender = genderSpinner.getSelectedItem().toString();

        if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty() || gender.equals("Select Gender")) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        float height = Float.parseFloat(heightStr) / 100; // Convert cm to meters
        float weight = Float.parseFloat(weightStr);
        float bmi = weight / (height * height);

        String result;
        if (bmi < 18.5)
            result = "Underweight";
        else if (bmi < 25)
            result = "Normal";
        else if (bmi < 30)
            result = "Overweight";
        else
            result = "Obese";

        String message = "Gender: " + gender + "\nAge: " + ageStr + "\nBMI: " + String.format("%.2f", bmi) + " (" + result + ")";

        new AlertDialog.Builder(this)
                .setTitle("BMI Result")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
