package com.onedeveloperstudio.happycalculations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.TimerTask;

/**
 * y.zakharov on 22.04.2015.
 */
public class MainActivity extends ActionBarActivity {
  private TextView timechanger;
  private TextView firstNumberField;
  private TextView secondNumberField;
  private TextView operatorField;
  private Integer maxValue;
  private Operations operation;
  private Button checkButton;
  private Button startButton;
  private EditText resultField;
  private Random random = new Random();
  private int rightAnswers = 0;
  private boolean timerStarted = false;


  private long startTime = 0L;
  private Handler customHandler = new Handler();
  long timeInMilliseconds = 0L;
  long timeSwapBuff = 0L;
  long updatedTime = 0L;

  private Runnable updateTimerThread = new Runnable() {

    public void run() {
      timerStarted = true;
      timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
      updatedTime = timeSwapBuff + timeInMilliseconds;
      int secs = (int) (updatedTime / 1000);
      secs = secs % 60;
      timechanger.setText("" + String.format("%02d", secs) + "s");
      customHandler.postDelayed(this, 1000);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeActionContentDescription("Меню");
    timechanger = (TextView) findViewById(R.id.timechanger);
    firstNumberField = (TextView) findViewById(R.id.first);
    secondNumberField = (TextView) findViewById(R.id.second);
    operatorField = (TextView) findViewById(R.id.operator);
    resultField = (EditText) findViewById(R.id.result);
    startButton = (Button) findViewById(R.id.startbutton);

    startButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        startTime = SystemClock.uptimeMillis();
        rightAnswers = 0;
        startButton.setEnabled(false);
        customHandler.postDelayed(updateTimerThread, 1000);
      }
    });

    Intent intent = getIntent();
    final Operations operation = (Operations) intent.getSerializableExtra("operation");
    this.operation = operation;
    prepareForOperation(operation);
    refreshValues();
    checkButton = (Button) findViewById(R.id.checkButton);
    checkButton.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                       if (!resultField.getText().equals("")) {
                                         Integer result = getIntegerValueOfTextField(resultField);
                                         Integer firstNumber = getIntegerValueOfTextField(firstNumberField);
                                         Integer secondNumber = getIntegerValueOfTextField(secondNumberField);
                                         boolean isTrue = false;
                                         switch (operation) {
                                           case PLUS:
                                             // Молодец, красавчик, грузим следующий пример
                                             isTrue = result.equals(firstNumber + secondNumber);
                                             break;
                                           case MULTIPLY:
                                             isTrue = result.equals(firstNumber * secondNumber);
                                             break;
                                           case DIVISION:
                                             isTrue = result.equals(firstNumber / secondNumber);
                                             break;
                                           case POWER:
                                             isTrue = result.equals((int) Math.pow(firstNumber, secondNumber));
                                             break;
                                         }
                                         if (isTrue) {
                                           Toast.makeText(getApplicationContext(), "Красавчик", Toast.LENGTH_LONG).show();
                                           if(timerStarted) {
                                             rightAnswers++;
                                             startButton.setText(String.valueOf(rightAnswers));
                                           }
                                           refreshValues();
                                         } else {
                                           Toast.makeText(getApplicationContext(), "Лошара", Toast.LENGTH_LONG).show();
                                         }
                                       } else {
                                         Toast.makeText(getApplicationContext(), "Необходимо заполнить значение в поле Результат", Toast.LENGTH_LONG).show();
                                       }
                                     }
                                   }
    );
  }

  private void refreshValues() {
    firstNumberField.setText(getRandomNumber().toString());
    secondNumberField.setText(getRandomNumber().toString());
    resultField.setText("");
  }

  private Integer getIntegerValueOfTextField(TextView textView) {
    return Integer.valueOf(String.valueOf(textView.getText()));
  }

  private Integer getIntegerValueOfTextField(EditText textView) {
    String value = String.valueOf(textView.getText());
    value = "".equals(value) ? "0" : value;
    return Integer.valueOf(value);
  }

  private Integer getRandomNumber(){
    return random.nextInt(maxValue);
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
