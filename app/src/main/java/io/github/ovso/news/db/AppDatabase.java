package io.github.ovso.news.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import io.github.ovso.news.listup.adapter.ListUpAdapter;
import io.github.ovso.news.search.model.Website;
import java.util.ArrayList;
import java.util.List;

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

  public void insertFirstRunData() {
    List<WebsiteEntity> entities = new ArrayList<>();
    // title, link, position

  }
}
