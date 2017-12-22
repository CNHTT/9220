package com.odd.sportal.adapter;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.extra.adapter.BaseRecycleAdapter;
import com.odd.sportal.R;
import com.odd.sportal.inter.OnGameAdapter;
import com.odd.sportal.model.GameModel;

import java.util.List;

/**
 * Created by 戴尔 on 2017/12/13.
 */

public class GameRecycleViewAdapter extends BaseRecycleAdapter<GameModel> {

    OnGameAdapter listner;
    public GameRecycleViewAdapter(List<GameModel> datas,OnGameAdapter listner) {
        super(datas);
        this.listner = listner;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position) {
        GameModel gameModel = datas.get(position);
        ((TextView)holder.getView(R.id.v_game_descr)).setText(gameModel.getGAME_NAME());
        ((TextView)holder.getView(R.id.v_chart_descr)).setText(gameModel.getFC_TYPE());
        ((LinearLayout)holder.getView(R.id.ll_game_view)).setOnClickListener(v ->{
            listner.getBetGameBean(gameModel);
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_game_item;
    }
}
