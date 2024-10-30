// src/main/java/com/example/calculator/MainActivity.java
package com.example.mipt3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtDisplay;
    private double firstOperand = 0;
    private double secondOperand = 0;
    private String operator = "";
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDisplay = findViewById(R.id.txtDisplay);

        setNumericButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumericButtonListeners() {
        View.OnClickListener listener = v -> {
            if (isNewInput) {
                txtDisplay.setText("");
                isNewInput = false;
            }
            Button button = (Button) v;
            txtDisplay.append(button.getText().toString());
        };

        // Set listeners for all numeric buttons 0-9
        findViewById(R.id.btn0).setOnClickListener(listener);
        findViewById(R.id.btn1).setOnClickListener(listener);
        findViewById(R.id.btn2).setOnClickListener(listener);
        findViewById(R.id.btn3).setOnClickListener(listener);
        findViewById(R.id.btn4).setOnClickListener(listener);
        findViewById(R.id.btn5).setOnClickListener(listener);
        findViewById(R.id.btn6).setOnClickListener(listener);
        findViewById(R.id.btn7).setOnClickListener(listener);
        findViewById(R.id.btn8).setOnClickListener(listener);
        findViewById(R.id.btn9).setOnClickListener(listener);
    }

    private void setOperatorButtonListeners() {
        findViewById(R.id.btnAdd).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.btnEquals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.btnClear).setOnClickListener(v -> clearDisplay());
        findViewById(R.id.btnSqrt).setOnClickListener(v -> calculateSquareRoot());
        findViewById(R.id.btnSign).setOnClickListener(v -> toggleSign());
        findViewById(R.id.btnDelete).setOnClickListener(v -> deleteLastCharacter());
    }

    private void setOperator(String op) {
        firstOperand = Double.parseDouble(txtDisplay.getText().toString());
        operator = op;
        isNewInput = true;
    }

    private void calculateResult() {
        secondOperand = Double.parseDouble(txtDisplay.getText().toString());
        double result = 0;
        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    txtDisplay.setText("Error");
                    return;
                }
                break;
        }
        txtDisplay.setText(String.valueOf(result));
        isNewInput = true;
    }

    private void calculateSquareRoot() {
        double value = Double.parseDouble(txtDisplay.getText().toString());
        txtDisplay.setText(String.valueOf(Math.sqrt(value)));
        isNewInput = true;
    }

    private void toggleSign() {
        double value = Double.parseDouble(txtDisplay.getText().toString());
        txtDisplay.setText(String.valueOf(-value));
    }

    private void deleteLastCharacter() {
        String text = txtDisplay.getText().toString();
        if (text.length() > 1) {
            txtDisplay.setText(text.substring(0, text.length() - 1));
        } else {
            txtDisplay.setText("0");
        }
    }

    private void clearDisplay() {
        txtDisplay.setText("0");
        firstOperand = 0;
        secondOperand = 0;
        operator = "";
        isNewInput = true;
    }
}
