package com.szfp.scan.inter;

import com.szfp.scan.bean.ShopModel;

import java.util.List;

/**
 * Created by 戴尔 on 2017/12/25.
 */

public interface OnLoadShopModelListener {
    void loadShopModels(List<ShopModel> models);
}
