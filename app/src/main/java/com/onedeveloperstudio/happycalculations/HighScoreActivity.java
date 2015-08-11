package com.onedeveloperstudio.happycalculations;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by y.zakharov on 11.08.2015.
 */
public class HighScoreActivity extends ActionBarActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.highscore_activity);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setTitle(R.string.highscores);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeActionContentDescription("Меню");
    ListView listView = (ListView) findViewById(R.id.highscorelist);
    ResultHandler handler = ResultHandler.getInstance();
    Map<String, Integer> map = handler.loadHighScore(this);
    List<String> list = new ArrayList<>(map.size());
    for (String key : map.keySet()) {
      list.add(key + " : " + map.get(key));
    }
    ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    listView.setAdapter(adapter);
  }
}
