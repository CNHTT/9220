package com.odd.sportal.inter;

import com.odd.sportal.model.EventModel;

import java.util.List;

/**
 * Created by 戴尔 on 2017/12/13.
 */

public interface OnLoadMoreEventListener {
    void getEventList (List<EventModel> list);
}
