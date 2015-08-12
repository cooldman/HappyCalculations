package com.onedeveloperstudio.happycalculations;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by y.zakharov on 11.08.2015.
 */
public class HighScoreActivity extends ActionBarActivity {
  private ListView listView;
  final ResultHandler handler = ResultHandler.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.highscore_activity);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setTitle(R.string.highscores);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeActionContentDescription("Меню");
    listView = (ListView) findViewById(R.id.highscorelist);
    showHighScore();
    Button dropButton = (Button) findViewById(R.id.drophighscore);
    final Activity activity = this;
    dropButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        handler.dropHighScores(activity);
        showHighScore();
        Toast.makeText(getApplicationContext(), "Рекорды сброшены", Toast.LENGTH_LONG).show();
      }
    });
  }

  private void showHighScore() {
    Map<String, Integer> map = handler.loadHighScore(this);
    List<String> list = new ArrayList<>(map.size());
    for (String key : map.keySet()) {
      list.add(key + " : " + map.get(key));
    }
    ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    listView.setAdapter(adapter);
  }
}
