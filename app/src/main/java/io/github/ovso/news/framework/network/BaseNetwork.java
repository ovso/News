package io.github.ovso.news.framework.network;

import android.content.Context;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseNetwork<T> {

  protected Context context;
  private String baseUrl;

  public BaseNetwork(Context context, String baseUrl) {
    this.context = context;
    this.baseUrl = baseUrl;
  }

  public T getApi() {
    return createRetrofit().create(getApiClass());
  }

  protected abstract Class<T> getApiClass();

  protected abstract Headers createHeaders();

  private Retrofit createRetrofit() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createClient())
        .build();

    return retrofit;
  }

  /*
  // Requst.Builder
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
            .header("Content-Type", "plain/text")
            .addHeader("Authorization", Security.AUTHORIZATION.getValue());
        Request.Builder requestBuilder1 = createRequestBuilder();
        Request request = requestBuilder.build();
        return chain.proceed(request);
   */
  private OkHttpClient createClient() {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor(chain -> {
      Request original = chain.request();
      Request.Builder requestBuilder =
          original.newBuilder().header("Content-Type", "plain/text").headers(createHeaders());
      Request request = requestBuilder.build();
      return chain.proceed(request);
    });
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    httpClient.addInterceptor(interceptor);
    OkHttpClient client = httpClient.build();
    return client;
  }
}
