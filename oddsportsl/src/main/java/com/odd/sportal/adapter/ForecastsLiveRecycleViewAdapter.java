package com.odd.sportal.adapter;

import android.widget.TextView;

import com.extra.adapter.BaseRecycleAdapter;
import com.odd.sportal.R;
import com.odd.sportal.model.BetForecastsLiveModel;
import com.odd.sportal.model.BetForecastsModel;

import java.util.List;

/**
 * Created by 戴尔 on 2017/12/20.
 */

public class ForecastsLiveRecycleViewAdapter extends BaseRecycleAdapter<BetForecastsLiveModel> {

    public ForecastsLiveRecycleViewAdapter(List<BetForecastsLiveModel> datas) {
        super(datas);
    }

    @Override
    protected void bindData(BaseRecycleAdapter.BaseViewHolder holder, int position) {
        BetForecastsLiveModel gameModel = datas.get(position);
        ((TextView)holder.getView(R.id.v_game_descr)).setText(gameModel.getDESCR());
        ((TextView)holder.getView(R.id.v_chart_descr)).setText(gameModel.getODDS()+"");
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_game_item;
    }
}
