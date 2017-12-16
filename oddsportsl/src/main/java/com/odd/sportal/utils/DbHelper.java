package com.odd.sportal.utils;

import com.extra.utils.DataUtils;
import com.odd.sportal.greendao.BetForecastsModelDao;
import com.odd.sportal.greendao.EventModelDao;
import com.odd.sportal.greendao.GameModelDao;
import com.odd.sportal.inter.OnLoadMoreEventListener;
import com.odd.sportal.model.BetForecastsModel;
import com.odd.sportal.model.EventModel;
import com.odd.sportal.model.GameModel;
import com.player.util.L;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 戴尔 on 2017/12/12.
 */

public class DbHelper {
    public static void insertNewGameModel(GameModel object) {
        try {
            GreenDaoManager.getInstance().getSession().getGameModelDao().insert(object);
        }catch (Exception e){
            L.d(object.getID()  + "+++"+ e.toString());
        }
    }

    public static void deleteEventAll() {
        try {
            GreenDaoManager.getInstance().getSession().getEventModelDao().deleteAll();
        }catch (Exception e){
            L.d( e.toString());
        }
    }
    public static void deleteGameAll() {
        try {
            GreenDaoManager.getInstance().getSession().getGameModelDao().deleteAll();
        }catch (Exception e){
            L.d( e.toString());
        }
    }
    public static void deleteForecastsAll() {
        try {
            GreenDaoManager.getInstance().getSession().getBetForecastsModelDao().deleteAll();
        }catch (Exception e){
            L.d( e.toString());
        }
    }

    public static void insertNewForecasts(BetForecastsModel object) {
        try {
            GreenDaoManager.getInstance().getSession().getBetForecastsModelDao().insert(object);
        }catch (Exception e){
            L.d( e.toString());
        }
    }

    public static void insertNewEvent(EventModel object) {
        try {
        GreenDaoManager.getInstance().getSession().getEventModelDao().insert(object);
    }catch (Exception e){
        L.d( e.toString());
    }
    }

    public static List<GameModel> loadGameList(int gameOffset) {
        try {
            List<GameModel> list =GreenDaoManager.getInstance().getSession().getGameModelDao().queryBuilder().offset(gameOffset*10).limit(10).list();

            return list;
        }catch (Exception e){
            return null;
        }
    }

    public static List<EventModel> loadEventList(int eventOffset) {
        try {
            List<EventModel> list =GreenDaoManager.getInstance().getSession().getEventModelDao().queryBuilder().offset(eventOffset*10).limit(10).list();

            return list;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
    public static void loadMoreEventList(int eventOffset,OnLoadMoreEventListener listener) {
        try {
            List<EventModel> list =GreenDaoManager.getInstance().getSession().getEventModelDao().queryBuilder().offset(eventOffset*10).limit(10).list();
            if (list==null) listener.getEventList(new ArrayList<>());
            listener.getEventList(list);
        }catch (Exception e){
            listener.getEventList(new ArrayList<>());
        }
    }
    public static void loadMoreEventList(int eventOffset,String team,OnLoadMoreEventListener listener) {
        List<EventModel> list = new ArrayList<>() ;
        try {
            if (DataUtils.isNullString(team))
                list =GreenDaoManager.getInstance().getSession().getEventModelDao().queryBuilder()
                        .offset(eventOffset*10).limit(10).list();
            else
                list =GreenDaoManager.getInstance().getSession().getEventModelDao().queryBuilder()
                        .where(EventModelDao.Properties.TEAM1_NAME.like(team),EventModelDao.Properties.TEAM2_NAME.like(team))
                        .offset(eventOffset*10).limit(10).list();

            if (list==null) listener.getEventList(new ArrayList<>());
            listener.getEventList(list);
        }catch (Exception e){
            listener.getEventList(new ArrayList<>());
        }
    }

    public static List<GameModel> loadGameByEventId(long event_id) {
        try {
            Query<GameModel>  query = null;
                ArrayList count=null;
                query = GreenDaoManager.getInstance().getSession().getGameModelDao().queryBuilder()
                        .where(GameModelDao.Properties.EVENT_ID.eq(event_id)).build();

                if (query == null){
                    return new ArrayList<>();
                }else {
                    count = (ArrayList) query.list();
                    return count;
                }
        }catch (Exception e){
            return new ArrayList<>();
        }

    }

    public static void loadEventById(long l,OnLoadMoreEventListener listener) {
        try {
            Query<EventModel>  query = null;
            ArrayList count=null;
            query = GreenDaoManager.getInstance().getSession().getEventModelDao().queryBuilder()
                    .where(EventModelDao.Properties.EVENT_ID.eq(l)).build();

            if (query == null){
                listener.getEventList( new ArrayList<>());
            }else {
                count = (ArrayList) query.list();
                listener.getEventList( count);
            }
        }catch (Exception e){
            listener.getEventList(new ArrayList<>());
        }
    }

    public static List<BetForecastsModel> loadForecastsList(GameModel bean1) {
        try {
            Query<BetForecastsModel>  query = null;
            ArrayList count=null;
            query = GreenDaoManager.getInstance().getSession().getBetForecastsModelDao().queryBuilder()
                    .where(BetForecastsModelDao.Properties.GAME_ID.eq(bean1.getGAME_ID())).build();

            if (query == null){
                return new ArrayList<>();
            }else {
                count = (ArrayList) query.list();
                return count;
            }
        }catch (Exception e){
            return new ArrayList<>();
        }

    }
}
