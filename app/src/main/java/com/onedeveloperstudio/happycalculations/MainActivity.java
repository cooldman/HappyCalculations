package com.onedeveloperstudio.happycalculations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
  private EditText resultField;
  private Random random = new Random();

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
                                         if(isTrue){
                                           Toast.makeText(getApplicationContext(), "Красавчик", Toast.LENGTH_LONG).show();
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
    return Integer.valueOf(String.valueOf(textView.getText()));
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

/*  protected static void startTimer() {
    isTimerRunning = true;
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        elapsedTime += 1; //increase every sec
        mHandler.obtainMessage(1).sendToTarget();

      }
    }, 0, 1000);
  };

  public Handler mHandler = new Handler() {
    public void handleMessage(Message msg) {
      StopWatch.time.setText(formatIntoHHMMSS(elapsedTime)); //this is the textview
    }
  }*/
}
