package com.onedeveloperstudio.happycalculations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * y.zakharov on 22.04.2015.
 */
public class MainActivity extends Activity {
  private TextView firstNumber;
  private TextView secondNumber;
  private TextView operator;
  private Integer maxValue;
  private Operations operation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    firstNumber = (TextView) findViewById(R.id.first);
    secondNumber = (TextView) findViewById(R.id.second);
    operator = (TextView) findViewById(R.id.operator);
    Intent intent = getIntent();
    Operations operation = (Operations) intent.getSerializableExtra("operation");
    this.operation = operation;
    prepareForOperation(operation);
    firstNumber.setText(maxValue);
    secondNumber.setText(maxValue);
  }

  private void prepareForOperation(Operations operation){
    switch (operation) {
      case PLUS:
        operator.setText("+");
        maxValue = 50;
        break;
      case MULTIPLY:
        operator.setText("*");
        maxValue = 10;
        break;
      case DIVISION:
        operator.setText("/");
        maxValue = 100;
        break;
      case POWER:
        operator.setText("pow");
        maxValue = 10;
        break;
    }
  }
}
