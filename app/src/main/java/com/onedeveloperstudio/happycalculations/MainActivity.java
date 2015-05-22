package com.onedeveloperstudio.happycalculations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * y.zakharov on 22.04.2015.
 */
public class MainActivity extends Activity {
  private TextView firstNumberField;
  private TextView secondNumberField;
  private TextView operatorField;
  private Integer maxValue;
  private Operations operation;
  private Button checkButton;
  private EditText resultField;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    firstNumberField = (TextView) findViewById(R.id.first);
    secondNumberField = (TextView) findViewById(R.id.second);
    operatorField = (TextView) findViewById(R.id.operator);
    resultField = (EditText) findViewById(R.id.result);
    Intent intent = getIntent();
    final Operations operation = (Operations) intent.getSerializableExtra("operation");
    this.operation = operation;
    prepareForOperation(operation);
    firstNumberField.setText(maxValue.toString());
    secondNumberField.setText(String.valueOf(maxValue / 2));
    checkButton = (Button) findViewById(R.id.checkButton);
    checkButton.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                       if (!resultField.getText().equals("")) {
                                         Integer result = getIntegerValueOfTextField(resultField);
                                         Integer firstNumber = getIntegerValueOfTextField(firstNumberField);
                                         Integer secondNumber = getIntegerValueOfTextField(secondNumberField);
                                         switch (operation) {
                                           case PLUS:
                                               Toast.makeText(getApplicationContext(), String.valueOf(result.equals(firstNumber + secondNumber)), Toast.LENGTH_LONG).show();
                                             break;
                                           case MULTIPLY:
                                             Toast.makeText(getApplicationContext(),  String.valueOf(result.equals(firstNumber * secondNumber)), Toast.LENGTH_LONG).show();
                                             break;
                                           case DIVISION:
                                             Toast.makeText(getApplicationContext(),  String.valueOf(result.equals(firstNumber / secondNumber)), Toast.LENGTH_LONG).show();
                                             break;
                                           case POWER:
                                             Toast.makeText(getApplicationContext(),  String.valueOf(result.equals((int)Math.pow(firstNumber, secondNumber))), Toast.LENGTH_LONG).show();
                                             break;
                                         }
                                       } else {
                                         Toast.makeText(getApplicationContext(), "Необходимо заполнить значение в поле Результат", Toast.LENGTH_LONG).show();
                                       }
                                     }
                                   }
    );
  }

  private Integer getIntegerValueOfTextField(TextView textView) {
    return Integer.valueOf(String.valueOf(textView.getText()));
  }

  private Integer getIntegerValueOfTextField(EditText textView) {
    return Integer.valueOf(String.valueOf(textView.getText()));
  }

  private void prepareForOperation(Operations operation) {
    switch (operation) {
      case PLUS:
        operatorField.setText("+");
        maxValue = 50;
        break;
      case MULTIPLY:
        operatorField.setText("*");
        maxValue = 10;
        break;
      case DIVISION:
        operatorField.setText("/");
        maxValue = 100;
        break;
      case POWER:
        operatorField.setText("pow");
        maxValue = 10;
        break;
    }
  }
}
