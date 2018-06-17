package io.github.ovso.news.ad;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import io.github.ovso.news.Security;

public class MyAdView {

  public static AdView getAdmobAdView(Context context) {
    AdView adView = new AdView(context);
    adView.setAdSize(AdSize.SMART_BANNER);
    adView.setAdUnitId(Security.ADMOB_UNIT_ID.get());
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);
    return adView;
  }
}