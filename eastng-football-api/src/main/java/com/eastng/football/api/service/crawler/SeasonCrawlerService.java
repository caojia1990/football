package com.eastng.football.api.service.crawler;

import java.io.IOException;

public interface SeasonCrawlerService {

    void crawler(String url, String seasonNo) throws IOException;
}
