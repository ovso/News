package io.github.ovso.news.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import io.github.ovso.news.search.model.Website;
import lombok.Data;

@Data @Entity(tableName = "WEBSITE_INFO") public class WebsiteEntity {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "TITLE")
  public String title;
  @ColumnInfo(name = "LINK")
  public String link;
  @ColumnInfo(name = "DESC")
  public String description;

  @ColumnInfo(name =  "POSITION")
  public int position;

  public static WebsiteEntity convertWebsiteToEntiry(Website item) {
    WebsiteEntity entity = new WebsiteEntity();
    entity.description = item.getDescription();
    entity.link = item.getLink();
    entity.title = item.getTitle();
    return entity;
  }
}