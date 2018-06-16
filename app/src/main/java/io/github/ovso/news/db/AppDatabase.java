package io.github.ovso.news.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.google.gson.Gson;
import io.github.ovso.news.framework.AssetsUtils;
import io.github.ovso.news.search.model.Website;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;

@Database(entities = { WebsiteEntity.class }, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
  private final static String DATABASE_NAME = "website";
  private static AppDatabase instance;

  public static synchronized AppDatabase getInstance(Context context) {
    if (instance == null) {
      instance = buildDatabase(context);
    }
    return instance;
  }

  private static AppDatabase buildDatabase(final Context context) {
    return Room.databaseBuilder(context,
        AppDatabase.class,
        DATABASE_NAME)
        .allowMainThreadQueries()
        .build();
  }

  public static void destroy() {
    if (instance.isOpen()) {
      instance.close();
      instance = null;
    }
  }

  public abstract WebsiteDao websiteDao();

  public boolean insertFirstRunData(Context context) {
    try {
      Gson gson = new Gson();
      String jsonString = AssetsUtils.read(context, "news.json");
      JSONArray jsonArray = new JSONArray(jsonString);
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        Website website = gson.fromJson(jsonObject.toString(), Website.class);
        WebsiteEntity websiteEntity = WebsiteEntity.convertWebsiteToEntiry(website);
        websiteDao().insert(websiteEntity);
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
