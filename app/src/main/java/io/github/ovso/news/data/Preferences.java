package io.github.ovso.news.data;

public enum Preferences {
  KEY_LAST_SCREEN("last_screen"), SCREEN_LIST_UP("listUp"), SCREEN_WEB("web"), KEY_WEB_POSITION(
      "web_position");
  private String value;

  Preferences(String $value) {
    value = $value;
  }

  public String get() {
    return this.value;
  }
}
