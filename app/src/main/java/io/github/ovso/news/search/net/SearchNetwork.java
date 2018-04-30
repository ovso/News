package io.github.ovso.news.search.net;

import android.content.Context;
import io.github.ovso.news.Security;
import io.github.ovso.news.framework.network.BaseNetwork;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;

public class SearchNetwork extends BaseNetwork<SearchApi> {
  public SearchNetwork(Context context, String baseUrl) {
    super(context, baseUrl);
  }

  @Override protected Class<SearchApi> getApiClass() {
    return SearchApi.class;
  }

  @Override protected Headers createHeaders() {
    Map<String, String> headers = new HashMap<>();
    headers.put(Security.NAVER_CLIENT_ID_NAME.getValue(), Security.NAVER_CLIENT_ID.getValue());
    headers.put(Security.NAVER_CLIENT_SECRET_NAME.getValue(),
        Security.NAVER_CLIENT_SECRET.getValue());
    return Headers.of(headers);
  }
}
