package com.odd.sportal.inter;

import com.odd.sportal.model.EventLiveModel;
import com.odd.sportal.model.EventModel;

import java.util.List;

/**
 * Created by 戴尔 on 2017/12/20.
 */

public interface OnLoadMoreEventLiveListener {
    void getEventList (List<EventLiveModel> list);
}
