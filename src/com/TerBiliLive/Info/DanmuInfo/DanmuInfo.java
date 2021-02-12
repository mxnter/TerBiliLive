package com.TerBiliLive.Info.DanmuInfo;

import com.TerBiliLive.Info.Host_list;

import java.util.List;

/**
 * Auto-generated: 2021-02-04 14:42:27
 * @author Mxnter Ye
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */

public class DanmuInfo {

    private String group;
    private int business_id;
    private double refresh_row_factor;
    private int refresh_rate;
    private int max_delay;
    private String token;
    private List<Host_list> host_list;

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setRefresh_row_factor(double refresh_row_factor) {
        this.refresh_row_factor = refresh_row_factor;
    }

    public double getRefresh_row_factor() {
        return refresh_row_factor;
    }

    public void setRefresh_rate(int refresh_rate) {
        this.refresh_rate = refresh_rate;
    }

    public int getRefresh_rate() {
        return refresh_rate;
    }

    public void setMax_delay(int max_delay) {
        this.max_delay = max_delay;
    }

    public int getMax_delay() {
        return max_delay;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setHost_list(List<Host_list> host_list) {
        this.host_list = host_list;
    }

    public List<Host_list> getHost_list() {
        return host_list;
    }

}
