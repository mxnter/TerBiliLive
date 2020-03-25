package com.TerBiliLive.Entity;

import java.util.List;
import java.util.Map;

public class HttpClientEntity {
    Map<String, List<String>> header;
    String result;

    public HttpClientEntity() {
    }

    public Map<String, List<String>> getHeader() {
        return header;
    }

    public void setHeader(Map<String, List<String>> header) {
        this.header = header;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
