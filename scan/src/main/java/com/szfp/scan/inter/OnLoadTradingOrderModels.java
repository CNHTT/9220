package com.szfp.scan.inter;

import com.szfp.scan.bean.TradingOrderModel;
import com.szfp.scan.bean.TradingShopModel;

import java.util.List;

/**
 * Created by 戴尔 on 2017/12/25.
 */

public interface OnLoadTradingOrderModels {

    void loadData(List<TradingOrderModel> models);
}
