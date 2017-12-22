package com.odd.sportal.utils;

import com.extra.utils.DataUtils;
import com.odd.sportal.greendao.BetForecastsLiveModelDao;
import com.odd.sportal.greendao.BetForecastsModelDao;
import com.odd.sportal.greendao.EventLiveModelDao;
import com.odd.sportal.greendao.EventModelDao;
import com.odd.sportal.greendao.GameLiveModelDao;
import com.odd.sportal.greendao.GameModelDao;
import com.odd.sportal.inter.OnLoadMoreEventListener;
import com.odd.sportal.inter.OnLoadMoreEventLiveListener;
import com.odd.sportal.model.BetForecastsLiveModel;
import com.odd.sportal.model.BetForecastsModel;
import com.odd.sportal.model.BetForecastsOutRightModel;
import com.odd.sportal.model.EventLiveModel;
import com.odd.sportal.model.EventModel;
import com.odd.sportal.model.GameLiveModel;
import com.odd.sportal.model.GameModel;
import com.odd.sportal.model.NextLiveEventsModel;
import com.player.util.L;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.greenrobot.greendao.query.Query;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        .whereOr(EventModelDao.Properties.TEAM1_NAME.like(team),EventModelDao.Properties.TEAM2_NAME.like(team))
                        .offset(eventOffset*10).limit(10).list();

            if (list==null) listener.getEventList(new ArrayList<>());
            listener.getEventList(list);
        }catch (Exception e){
            listener.getEventList(new ArrayList<>());
        }
    }
    public static void loadMoreEventLiveList(int eventOffset,String team,OnLoadMoreEventLiveListener listener) {
        List<EventLiveModel> list = new ArrayList<>() ;
        try {
            if (DataUtils.isNullString(team))
                list =GreenDaoManager.getInstance().getSession().getEventLiveModelDao().queryBuilder()
                        .offset(eventOffset*10).limit(10).list();
            else
                list =GreenDaoManager.getInstance().getSession().getEventLiveModelDao().queryBuilder()
                        .where(EventLiveModelDao.Properties.TEAM1_NAME.like(team),EventLiveModelDao.Properties.TEAM2_NAME.like(team))
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
    public static List<GameLiveModel> loadGameLiveByEventId(long event_id) {
        try {
            Query<GameLiveModel>  query = null;
                ArrayList count=null;
                query = GreenDaoManager.getInstance().getSession().getGameLiveModelDao().queryBuilder()
                        .where(GameLiveModelDao.Properties.EVENT_ID.eq(event_id)).build();

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
                    .where(BetForecastsModelDao.Properties.GAME_ID.eq(bean1.getGAME_ID()),BetForecastsModelDao.Properties.EVENT_ID.eq(bean1.getEVENT_ID())).build();

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
    public static List<BetForecastsLiveModel> loadForecastsListTest(GameModel bean1) {
        try {
            Query<BetForecastsLiveModel>  query = null;
            ArrayList count=null;
            query = GreenDaoManager.getInstance().getSession().getBetForecastsLiveModelDao().queryBuilder()
                    .where(BetForecastsLiveModelDao.Properties.GAME_ID.eq(bean1.getGAME_ID()),BetForecastsLiveModelDao.Properties.EVENT_ID.eq(bean1.getEVENT_ID())).build();

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

    public static List<BetForecastsLiveModel> loadForecastsLiveList(GameLiveModel bean1) {
        try {
            Query<BetForecastsLiveModel>  query = null;
            ArrayList count=null;
            query = GreenDaoManager.getInstance().getSession().getBetForecastsLiveModelDao().queryBuilder()
                    .where(BetForecastsLiveModelDao.Properties.GAME_ID.eq(bean1.getGAME_ID()),BetForecastsLiveModelDao.Properties.EVENT_ID.eq(bean1.getEVENT_ID())).build();

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


    public static void insertNewBetForecastsOutRight(BetForecastsOutRightModel object) {
        try{
            GreenDaoManager.getInstance().getSession().getBetForecastsOutRightModelDao().insert(object);
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }

    public static void deleteBetForecastsOutRightAll() {
        try{
            GreenDaoManager.getInstance().getSession().getBetForecastsOutRightModelDao().deleteAll();
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }

    public static void deleteNextLiveEventsAll() {
        try{
            GreenDaoManager.getInstance().getSession().getNextLiveEventsModelDao().deleteAll();
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }

    public static void insertNextLiveEvents(NextLiveEventsModel object) {
        try{
            GreenDaoManager.getInstance().getSession().getNextLiveEventsModelDao().insert(object);
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }

    public static void insertEventLive(EventLiveModel object) {
        try{
            GreenDaoManager.getInstance().getSession().getEventLiveModelDao().insert(object);
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }
    public static void deleteEventLiveAll() {
        try{
            GreenDaoManager.getInstance().getSession().getEventLiveModelDao().deleteAll();
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }

    public static void insertNewGameLiveModel(GameLiveModel object) {
        try{
            GreenDaoManager.getInstance().getSession().getGameLiveModelDao().insert(object);
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }

    public static void deleteBetGameLiveAll() {
        try{
            GreenDaoManager.getInstance().getSession().getGameLiveModelDao().deleteAll();
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }

    public static void insertNewBetForecastsLive(BetForecastsLiveModel object) {
        try{
            GreenDaoManager.getInstance().getSession().getBetForecastsLiveModelDao().insert(object);
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }

    public static void deleteBetForecastsLiveAll() {
        try{
            GreenDaoManager.getInstance().getSession().getBetForecastsLiveModelDao().deleteAll();
        }catch (Exception e){
            L.d(e.getMessage());
        }
    }

    public static List<EventLiveModel> loadEventLiveList(int i) {
        try {
            List<EventLiveModel> list =GreenDaoManager.getInstance().getSession().getEventLiveModelDao().queryBuilder().offset(i*10).limit(10).list();
            return list;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
