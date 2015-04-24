package com.onedeveloperstudio.happycalculations;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MenuActivity extends Activity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.menu_activity);
    Button sumButton = (Button) findViewById(R.id.plus);
    sumButton.setOnClickListener(this);
    Button multiplyButton = (Button) findViewById(R.id.multiply);
    multiplyButton.setOnClickListener(this);
    Button devideButton = (Button) findViewById(R.id.division);
    devideButton.setOnClickListener(this);
    Button powerButton = (Button) findViewById(R.id.power);
    powerButton.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Intent intent = new Intent(this, MainActivity.class);
    if(v.getId() == R.id.plus){
      intent.putExtra("operation", Operations.PLUS);
    } else if(v.getId() == R.id.multiply){
      intent.putExtra("operation", Operations.MULTIPLY);
    } else if(v.getId() == R.id.division){
      intent.putExtra("operation", Operations.DIVISION);
    } else if(v.getId() == R.id.power){
      intent.putExtra("operation", Operations.POWER);
    }
    startActivity(intent);
  }
}
