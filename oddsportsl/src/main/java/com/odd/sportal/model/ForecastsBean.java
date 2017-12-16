package com.odd.sportal.model;

import java.io.Serializable;

/**
 * Created by 戴尔 on 2017/12/4.
 */
public class ForecastsBean implements Serializable{
    private String forecasts;
    private String odds;


    public String getForecasts() {
        return forecasts;
    }

    public void setForecasts(String forecasts) {
        this.forecasts = forecasts;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }
}
