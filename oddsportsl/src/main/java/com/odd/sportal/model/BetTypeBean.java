package com.odd.sportal.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 戴尔 on 2017/12/4.
 */

public class BetTypeBean  implements Serializable{
    private String type;
    private List<ForecastsBean> list;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ForecastsBean> getList() {
        return list;
    }

    public void setList(List<ForecastsBean> list) {
        this.list = list;
    }
}
