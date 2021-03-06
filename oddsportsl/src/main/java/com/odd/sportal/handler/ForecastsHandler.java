package com.odd.sportal.handler;

import com.extra.utils.DataUtils;
import com.extra.utils.TimeUtils;
import com.odd.sportal.inter.CallBackSuccess;
import com.odd.sportal.model.BetForecastsModel;
import com.odd.sportal.utils.BaseHandler;
import com.odd.sportal.utils.DbHelper;
import com.player.util.L;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.sql.Time;

/**
 * Created by 戴尔 on 2017/12/12.
 */

public class ForecastsHandler extends BaseHandler {

    CallBackSuccess callBackSuccess;
    public ForecastsHandler(CallBackSuccess callBackSuccess) {
        this.callBackSuccess = callBackSuccess;
    }

    @Override
    public boolean parse(String xmlString) {
        try {
            super.parserXml(this, xmlString);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {
        L.d("ForecastsHandler endDocument");
        callBackSuccess.forecastsEnd();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    @Override
    public void startDocument() throws SAXException {
        L.d("ForecastsHandler startDocument");
    }

    BetForecastsModel object;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        try {
            if(qName.equals("ROW")){
                object = new BetForecastsModel();
                object.setID(DataUtils.getNumberLong("ID",attributes));
                object.setEVENT_ID(DataUtils.getNumberLong("EVENT_ID",attributes));
                object.setGAME_ID(DataUtils.getNumberLong("GAME_ID",attributes));
                object.setFORECAST_CD(DataUtils.getNumberLong("FORECAST_CD",attributes));

                object.setDESCR(DataUtils.getNumberString("DESCR",attributes));
                object.setODDS(DataUtils.getNumberDouble("ODDS",attributes));
                object.setFC_STATUS(DataUtils.getNumberLong("FC_STATUS",attributes));
                object.setFC_WIN(DataUtils.getNumberLong("FC_WIN",attributes));

                object.setREV_ID(DataUtils.getNumberLong("REV_ID",attributes));
                object.setODDS_CHANGED(DataUtils.getNumberLong("ODDS_CHANGED",attributes));
                object.setISCURRENT(DataUtils.getNumberLong("ISCURRENT",attributes));
                object.setFC_FROM(TimeUtils.getBetTime(DataUtils.getNumberString("FC_FROM",attributes)));

                object.setFC_TO(TimeUtils.getBetTime(DataUtils.getNumberString("FC_TO",attributes)));
                object.setTEAM_ID(DataUtils.getNumberLong("TEAM_ID",attributes));
                object.setFC_PLAYER_ID(DataUtils.getNumberLong("FC_PLAYER_ID",attributes));
                object.setDESCR_EN(DataUtils.getNumberString("DESCR_EN",attributes));

                object.setFC_NAME(DataUtils.getNumberString("FC_NAME",attributes));
                object.setDESCR_LOCAL(DataUtils.getNumberString("DESCR_LOCAL",attributes));
                object.setFORDER(DataUtils.getNumberLong("FORDER",attributes));
                object.setFC_UID(DataUtils.getNumberLong("FC_UID",attributes));

                object.setODDS_NORMAL(DataUtils.getNumberDouble("ODDS_NORMAL",attributes));
                object.setFC_PARAM(DataUtils.getNumberDouble("FC_PARAM",attributes));
                DbHelper.insertNewForecasts(object);
                object=null;
            }
        }catch (Exception e){
            L.d(e.toString());
        }
    }
}
