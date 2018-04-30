package io.github.ovso.news.search.net;

import io.github.ovso.news.Security;
import io.github.ovso.news.framework.network.BaseNetwork;
import io.github.ovso.news.search.model.WebsiteResult;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.Headers;

public class SearchNetwork extends BaseNetwork<SearchApi> {
  @Inject public SearchNetwork() {

  }

  @Override protected Class<SearchApi> getApiClass() {
    return SearchApi.class;
  }

  public Observable<WebsiteResult> getWebsiteResult() {
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put("display", String.valueOf(5));
    queryMap.put("start", String.valueOf(1));
    queryMap.put("query", "경향신문");
    return getApi().getWebsiteResult(queryMap);
  }

  @Override protected Headers createHeaders() {
    Map<String, String> headers = new HashMap<>();
    headers.put(Security.NAVER_CLIENT_ID_NAME.getValue(), Security.NAVER_CLIENT_ID.getValue());
    headers.put(Security.NAVER_CLIENT_SECRET_NAME.getValue(),
        Security.NAVER_CLIENT_SECRET.getValue());
    return Headers.of(headers);
  }

  @Override protected String getBaseUrl() {
    return Security.BASE_URL.getValue();
  }
}
