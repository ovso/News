package io.github.ovso.news.search.net;

import io.github.ovso.news.search.model.WebsiteResult;
import io.reactivex.Single;
import java.util.HashMap;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface SearchApi {
  @GET("/v1/search/webkr") Single<WebsiteResult> getNews(@QueryMap HashMap<String, String> queryMap);

}
