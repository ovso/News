package io.github.ovso.news.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao public interface WebsiteDao {

  @Query("SELECT * FROM WEBSITE_INFO")
  List<WebsiteEntity> getAll();

  @Query("SELECT * FROM WEBSITE_INFO WHERE ID IN (:ids)")
  List<WebsiteEntity> loadAllByIds(int[] ids);

  /*
  @Query("SELECT * FROM WEBSITE_INFO WHERE TITLE")
  WebsiteEntity findByName(String title);
  */

  @Insert
  void insertAll(WebsiteEntity... websites);

  @Delete
  void delete(WebsiteEntity website);
}
