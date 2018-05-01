package io.github.ovso.news.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao public interface WebsiteDao {
  @Query("SELECT * FROM WEBSITE")
  List<WebsiteEntity> getAll();

  @Query("SELECT * FROM WEBSITE WHERE uid IN (:userIds)")
  List<WebsiteEntity> loadAllByIds(int[] userIds);

  @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
      + "last_name LIKE :last LIMIT 1")
  WebsiteEntity findByName(String first, String last);

  @Insert
  void insertAll(WebsiteEntity... users);

  @Delete
  void delete(WebsiteEntity user);
}
