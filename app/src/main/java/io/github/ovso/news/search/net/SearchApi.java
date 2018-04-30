package io.github.ovso.news.search.net;

import io.github.ovso.news.search.model.WebsiteResult;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SearchApi {
  @GET("/v1/search/webkr") Observable<WebsiteResult> getWebsiteResult(
      @QueryMap Map<String, String> queryMap);
}
