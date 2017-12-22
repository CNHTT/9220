package com.odd.sportal.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 戴尔 on 2017/12/19.
 */
@Entity
public class NextLiveEventsModel  implements Serializable {
    static  final  long serialVersionUID=42L;
    @Id(autoincrement = true)
    private Long id;
    private long EVENT_ID;
    @Generated(hash = 1712555625)
    public NextLiveEventsModel(Long id, long EVENT_ID) {
        this.id = id;
        this.EVENT_ID = EVENT_ID;
    }
    @Generated(hash = 2040730641)
    public NextLiveEventsModel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getEVENT_ID() {
        return this.EVENT_ID;
    }
    public void setEVENT_ID(long EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }
}
