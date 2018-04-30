package io.github.ovso.news.search.model;

import java.util.List;
import lombok.Getter;

@Getter public class WebsiteResult {
  private String lastBuildDate;
  private String total;
  private String start;
  private String display;
  private List<Website> items;
}

/*
{
"lastBuildDate": "Mon, 30 Apr 2018 16:20:51 +0900",
"total": 1701239,
"start": 1,
"display": 10,
"items": [
{
"title": "<b>경향신문</b>",
"link": "http://www.khan.co.kr/",
"description": "정치, 경제, 사회, 문화 등 섹션별 뉴스, 인물인터뷰, 포토스토리, 만평 제공."

}]
}
 */