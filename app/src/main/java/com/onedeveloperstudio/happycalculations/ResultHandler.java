package com.onedeveloperstudio.happycalculations;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by y.zakharov on 03.08.2015.
 */
public class ResultHandler {

  public static boolean saveHighScore(String operation, Integer highScore, Activity activity) {
    SharedPreferences sharedPref = activity.getSharedPreferences(
        activity.getString(R.string.com_onedeveloperstudio_happycalculations_preference_key),
        Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putInt(operation, highScore);
    return editor.commit();
  }

  public static Map<String, Integer> loadHighScore(Activity activity) {
    Map<String, Integer> result = new HashMap<>(4);
    SharedPreferences sharedPref = activity.getSharedPreferences(
        activity.getString(R.string.com_onedeveloperstudio_happycalculations_preference_key),
        Context.MODE_PRIVATE);
    for (Operations operation : Operations.values()) {
      result.put(operation.name(), sharedPref.getInt(operation.name(), 0));
    }
    return result;
  }

  public static boolean dropHighScores(Activity activity) {
    SharedPreferences sharedPref = activity.getSharedPreferences(
        activity.getString(R.string.com_onedeveloperstudio_happycalculations_preference_key),
        Context.MODE_PRIVATE);
    sharedPref.edit().clear().commit();
    return true;
  }
}
