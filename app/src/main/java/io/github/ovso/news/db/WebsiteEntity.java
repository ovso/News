package io.github.ovso.news.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity public class WebsiteEntity {
  @PrimaryKey
  public int id;

  public String title;
  public String link;
  public String description;
}
