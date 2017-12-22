package com.odd.sportal.service;

import android.annotation.SuppressLint;
import android.app.IntentService;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.extra.retrofit.HttpBuilder;
import com.extra.utils.SPUtils;
import com.extra.utils.ToastUtils;
import com.odd.sportal.ContextValue;
import com.odd.sportal.R;
import com.odd.sportal.handler.BetForecastsLiveHandler;
import com.odd.sportal.handler.BetForecastsOutRightHandler;
import com.odd.sportal.handler.EventHandler;
import com.odd.sportal.handler.EventLiveHandler;
import com.odd.sportal.handler.ForecastsHandler;
import com.odd.sportal.handler.GameHandler;
import com.odd.sportal.handler.GameLiveHandler;
import com.odd.sportal.handler.NewFileHandler;
import com.odd.sportal.handler.NextLiveEventsHandler;
import com.odd.sportal.inter.CallBackSuccess;
import com.odd.sportal.utils.BaseHandler;
import com.odd.sportal.utils.DbHelper;
import com.odd.sportal.webservice.response.GetBetEventsLiveResponse;
import com.odd.sportal.webservice.response.GetBetEventsResponse;
import com.odd.sportal.webservice.response.GetBetForecastsLiveResponse;
import com.odd.sportal.webservice.response.GetBetForecastsOutRightResponse;
import com.odd.sportal.webservice.response.GetBetForecastsResponse;
import com.odd.sportal.webservice.response.GetBetGamesLiveResponse;
import com.odd.sportal.webservice.response.GetBetGamesResponse;
import com.odd.sportal.webservice.response.GetCashBackResponse;
import com.odd.sportal.webservice.response.GetNextLiveEventsResponse;
import com.odd.sportal.webservice.response.GetOddsOfTheDayResponse;
import com.player.util.L;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 戴尔 on 2017/12/13.
 */

@SuppressLint("Registered")
public class ParseXmlService extends IntentService implements CallBackSuccess {


    private String TAG = "ParseXmlService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * name Used to name the worker thread, important only for debugging.
     */
    public ParseXmlService() {
        super("ParseXmlService");
    }

    private boolean b1=false;
    private boolean b2=false;
    private boolean b3=false;

    private NotificationCompat.Builder builder;
    private NotificationManager manager;
    private int _notificationID = 1024;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        L.d("onHandleIntent");
        try {
            initNotification();
            manager.notify(_notificationID,builder.build());
            SPUtils.putBoolean(this, ContextValue.ISLOADING,true);

            //1.获取Event
            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapEnvGetBetEvents)
                    .path(ContextValue.soapEnvGetBetEventsFileName)
                    .success( s ->{
                        L.d("successs");
//                        BaseHandler handler = new NewFileHandler();
//                        handler.setPath(ContextValue.GetBetEventsFileName);
//                        handler.parse(ContextValue.soapEnvGetBetEventsFileName);


                        newXMLFile1(ContextValue.soapEnvGetBetEventsFileName,ContextValue.GetBetEventsFileName);
                        DbHelper.deleteEventAll();
                        Observable.create((ObservableOnSubscribe<Integer>) e -> {
                            e.onNext(1);
                            e.onComplete();
                        }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                            BaseHandler handler2 = new EventHandler(this);
                            handler2.parse(ContextValue.GetBetEventsFileName);
                        });

                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d(e.toString());
                    }).postIBetApi();
////            //2.2获取BetGames
            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapGetBetGames)
                    .path(ContextValue.soapGetBetGamesFileName)
                    .success( s ->{
                        L.d("successs");
                        newXMLFile2(ContextValue.soapGetBetGamesFileName,ContextValue.GetBetGamesFileName);
                        DbHelper.deleteGameAll();

            Observable.create((ObservableOnSubscribe<Integer>) e -> {
                e.onNext(1);
                e.onComplete();
            }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                BaseHandler handler1 = new GameHandler(this);
                handler1.parse(ContextValue.GetBetGamesFileName);
            });
                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d(e.toString());
                    }).postIBetApi();

            //3.
            builder.setContentText("Downloading BetForecasts");
            manager.notify(_notificationID+3,builder.build());
            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapGetBetForecasts)
                    .path(ContextValue.soapGetBetForecastsFileName)
                    .success( s ->{
                        builder.setContentText("BetForecasts Download successful");
                        manager.notify(_notificationID+3,builder.build());


                        try {
//                            newXMLFile3(ContextValue.soapGetBetForecastsFileName,ContextValue.GetBetForecastsFileName);
//
//                            StringBuffer result = new StringBuffer();
//                            BufferedReader bt = new BufferedReader(new InputStreamReader(new FileInputStream(ContextValue.soapGetBetForecastsFileName)));

//
//                            File file = new File(ContextValue.soapGetBetForecastsFileName);
//                            RandomAccessFile raf;
//                            StringBuffer sb = new StringBuffer();
//                            File dirFirstFolder = new File(ContextValue.GetBetForecastsFileName);
//                            if (dirFirstFolder.exists()){
//                                dirFirstFolder.delete();
//                            }
//
//                            FileWriter out;
//
//                            try {
//                                out = new FileWriter(new File(ContextValue.GetBetForecastsFileName), true);
//
//                                int i=0;
//                                raf = new RandomAccessFile(file, "rw");
//                                FileChannel cha = raf.getChannel();
//                                ByteBuffer buf = ByteBuffer.allocate(1024);
//                                int size;
//                                while ((size = cha.read(buf)) != -1) {
//                                    buf.flip();
//                                    byte[] buff = new byte[size];
//                                    buf.get(buff);
//                                    i++;
//                                    StringBuffer str= new StringBuffer(new String(buff, 0, size));
//                                    String line=str.toString();
//                                    if (!line.equals("")){
//                                    if (
//                                            line.substring(line.length()-1,line.length()).equals(";")
//                                                    ||line.substring(line.length()-1,line.length()).equals("t")
//                                                    ||line.substring(line.length()-1,line.length()).equals("g")
//                                                    ||line.substring(line.length()-1,line.length()).equals("l")
//                                                    ||line.substring(line.length()-1,line.length()).equals("&")
//
//                                            )
//                                    {
//
//                                        sb.append(line);
//
//
//                                    }else
//                                        {
//                                            sb.append(line);
//                                            out.write(sb.toString().replace("<?xml version=\"1.0\"?>","")
//                                                    .replace("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"><SOAP-ENV:Body SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:NS1=\"urn:APIInterf-IBetAPI\"><NS1:GetBetForecastsResponse><return xsi:type=\"xsd:string\">","")
//                                                    .replace("</NS1:GetBetForecastsResponse>","")
//                                                    .replace("</SOAP-ENV:Body>","")
//                                                    .replace("</SOAP-ENV:Envelope>","")
//                                                    .replace("</return>","")
//                                                    .replace("&gt;",">")
//                                                    .replace("&lt;","<")
//                                                    .replace("&amp;","&")
//                                                    .replace("  "," ")
//                                                    .replace("\t"," ")
//                                                    .replace("\n"," ")
//                                                    .replace("\r"," ")
//
//                                            );
//                                            sb = new StringBuffer();
//                                        }}else out.write(line);
//
//
//                                }
//                                out.flush();
//                                //关闭
//                                out.close();
////                                Serializer serializer = new Persister();
////                                GetNextLiveEventsResponse requset = serializer.read(GetNextLiveEventsResponse.class,new String(sb.toString()));
////                                PrintWriter p = new PrintWriter(new FileOutputStream(new File(ContextValue.GetBetForecastsFileName)));
////                                p.write(requset.body.reture.string);
////                                p.close();
//
//                            } catch (IOException e) {
//                                L.d(e.getMessage());
//                                e.printStackTrace();
//                            }
//
//
//                            DbHelper.deleteForecastsAll();
//
//                            Observable.create((ObservableOnSubscribe<Integer>) e -> {
//                                e.onNext(1);
//                                e.onComplete();
//                            }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
//                                BaseHandler handler = new NewFileHandler();
//                                handler.setPath(ContextValue.GetBetForecastsFileName);
//                                handler.parse(ContextValue.soapGetBetForecastsFileName);
//                                BaseHandler handler3 = new ForecastsHandler(this);
//                                handler3.parse(ContextValue.GetBetForecastsFileName);
//                            });
                        }catch (Exception e){
                            L.d(e.getMessage());
                        }

                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d(e.toString()+"0000");
                    }).postIBetApi();
////
//            //4.
            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapGetBetForecastsOutRight)
                    .path(ContextValue.soapGetBetForecastsOutRightFileName)
                    .success( s ->{
                        L.d("successs");


                        newXMLFile4(ContextValue.soapGetBetForecastsOutRightFileName,ContextValue.GetBetForecastsOutRightFileName);


                        Observable.create((ObservableOnSubscribe<Integer>) e -> {
                            e.onNext(1);
                            e.onComplete();
                        }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                            DbHelper.deleteBetForecastsOutRightAll();
                            BaseHandler handler4 = new BetForecastsOutRightHandler(this);
                            handler4.parse(ContextValue.GetBetForecastsOutRightFileName);
                        });
                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d(e.toString());
                    }).postIBetApi();
////            //5.
            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapGetNextLiveEvents)
                    .path(ContextValue.soapGetNextLiveEventsFileName)
                    .success( s ->{
                        L.d("successs");
                        newXMLFile5(ContextValue.soapGetNextLiveEventsFileName,ContextValue.GetNextLiveEventsFileName);

                        //Analysis
                        Observable.create((ObservableOnSubscribe<Integer>) e -> {
                            e.onNext(1);
                            e.onComplete();
                        }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                            DbHelper.deleteNextLiveEventsAll();
                            BaseHandler handler5 = new NextLiveEventsHandler(this);
                            handler5.parse(ContextValue.GetNextLiveEventsFileName);
                        });
                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d(e.toString());
                    }).postIBetApi();

            //6.
            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapGetBetEventsLive)
                    .path(ContextValue.soapGetBetEventsLiveFileName)
                    .success( s ->{
                        L.d("successs");

                        newXMLFile6(ContextValue.soapGetBetEventsLiveFileName,ContextValue.GetBetEventsLiveFileName);

                        //Analysis
                        Observable.create((ObservableOnSubscribe<Integer>) e -> {
                            e.onNext(1);
                            e.onComplete();
                        }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                            DbHelper.deleteEventLiveAll();
                            BaseHandler handler5 = new EventLiveHandler(this);
                            handler5.parse(ContextValue.GetBetEventsLiveFileName);
                        });
                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d( e.toString());
                    }).postIBetApi();
//
//            //7.
            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapGetBetGamesLive)
                    .path(ContextValue.soapGetBetGamesLiveFileName)
                    .success( s ->{
                        L.d("successs");
                        newXMLFile7(ContextValue.soapGetBetGamesLiveFileName,ContextValue.GetBetGamesLiveFileName);
                        Observable.create((ObservableOnSubscribe<Integer>) e -> {
                            e.onNext(1);
                            e.onComplete();
                        }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                            DbHelper.deleteBetGameLiveAll();
                            BaseHandler handler4 = new GameLiveHandler(this);
                            handler4.parse(ContextValue.GetBetGamesLiveFileName);
                        });

                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d(e.toString());
                    }).postIBetApi();
////
            //8.
            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapGetBetForecastsLive)
                    .path(ContextValue.soapGetBetForecastsLiveFileName)
                    .success( s ->{
                        L.d("successs");
                        newXMLFile8(ContextValue.soapGetBetForecastsLiveFileName,ContextValue.GetBetForecastsLiveFileName);
                        Observable.create((ObservableOnSubscribe<Integer>) e -> {
                            e.onNext(1);
                            e.onComplete();
                        }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                            DbHelper.deleteBetForecastsLiveAll();
                            BaseHandler handler4 = new BetForecastsLiveHandler(this);
                            handler4.parse(ContextValue.GetBetForecastsLiveFileName);
                        });

                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d(e.toString());
                    }).postIBetApi();

            //9.
            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapGetOddsOfTheDay)
                    .path(ContextValue.soapGetOddsOfTheDayFileName)
                    .success( s ->{
                        L.d("successs");
                        newXMLFile9(ContextValue.soapGetOddsOfTheDayFileName,ContextValue.GetOddsOfTheDayFileName);
                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d(e.toString());
                    }).postIBetApi();

            //10

            new HttpBuilder("http://136.243.147.149:9026/soap/IBetAPI")
                    .postBet(ContextValue.soapGetCashBack)
                    .path(ContextValue.soapGetCashBackFileName)
                    .success( s ->{
                        L.d("successs");
                        newXMLFile10(ContextValue.soapGetCashBackFileName,ContextValue.GetCashBackFileName);
                    })
                    .progress(p -> {
                    })
                    .error( e ->{
                        L.d(e.toString());
                    }).postIBetApi();




        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void newXMLFile7(String soapFileName, String fileName) {
        try {
            ReadFile readFile = new ReadFile(soapFileName).invoke();
            Serializer serializer = readFile.getSerializer();
            String result = readFile.getResult();

            GetBetGamesLiveResponse requset = serializer.read(GetBetGamesLiveResponse.class,new String(result));
            PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
            p.write(requset.body.reture.string);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void newXMLFile8(String soapFileName, String fileName) {
        try {
            ReadFile readFile = new ReadFile(soapFileName).invoke();
            Serializer serializer = readFile.getSerializer();
            String result = readFile.getResult();

            GetBetForecastsLiveResponse requset = serializer.read(GetBetForecastsLiveResponse.class,new String(result));
            PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
            p.write(requset.body.reture.string);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void newXMLFile9(String soapFileName, String fileName) {
        try {
            ReadFile readFile = new ReadFile(soapFileName).invoke();
            Serializer serializer = readFile.getSerializer();
            String result = readFile.getResult();

            GetOddsOfTheDayResponse requset = serializer.read(GetOddsOfTheDayResponse.class,new String(result));
            PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
            p.write(requset.body.reture.string);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void newXMLFile10(String soapFileName, String fileName) {
        try {
            ReadFile readFile = new ReadFile(soapFileName).invoke();
            Serializer serializer = readFile.getSerializer();
            String result = readFile.getResult();

            GetCashBackResponse requset = serializer.read(GetCashBackResponse.class,new String(result));
            PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
            p.write(requset.body.reture.string);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void newXMLFile6(String soapFileName, String fileName) {
        try {
        ReadFile readFile = new ReadFile(soapFileName).invoke();
        Serializer serializer = readFile.getSerializer();
        String result = readFile.getResult();

        GetBetEventsLiveResponse requset = serializer.read(GetBetEventsLiveResponse.class,new String(result));
        PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
        p.write(requset.body.reture.string);
        p.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    private void newXMLFile5(String soapFileName, String fileName) {
        try {
            ReadFile readFile = new ReadFile(soapFileName).invoke();
            Serializer serializer = readFile.getSerializer();
            String result = readFile.getResult();

            GetNextLiveEventsResponse requset = serializer.read(GetNextLiveEventsResponse.class,new String(result));
            PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
            p.write(requset.body.reture.string);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void newXMLFile4(String soapFileName, String fileName) {

        try {
            ReadFile readFile = new ReadFile(soapFileName).invoke();
            Serializer serializer = readFile.getSerializer();
            String result = readFile.getResult();

            GetBetForecastsOutRightResponse requset = serializer.read(GetBetForecastsOutRightResponse.class,new String(result));
            PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
            p.write(requset.body.reture.string);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void newXMLFile3(String soapFileName, String fileName) {
        try {
            ReadFile readFile = new ReadFile(soapFileName).invoke();
            Serializer serializer = readFile.getSerializer();
            String result = readFile.getResult();

            GetBetForecastsResponse requset = serializer.read(GetBetForecastsResponse.class,new String(result));
            PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
            p.write(requset.body.reture.string);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void newXMLFile2(String soapFileName, String fileName) {
        try {
            ReadFile readFile = new ReadFile(soapFileName).invoke();
            Serializer serializer = readFile.getSerializer();
            String result = readFile.getResult();

            GetBetGamesResponse requset = serializer.read(GetBetGamesResponse.class,new String(result));
            PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
            p.write(requset.body.reture.string);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void newXMLFile1(String soapFileName, String fileName) {
        try {
            ReadFile readFile = new ReadFile(soapFileName).invoke();
            Serializer serializer = readFile.getSerializer();
            String result = readFile.getResult();

            GetBetEventsResponse requset = serializer.read(GetBetEventsResponse.class,new String(result));
            PrintWriter p = new PrintWriter(new FileOutputStream(new File(fileName)));
            p.write(requset.body.reture.string);
            p.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initNotification() {

        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Data loading, please do not kill this app").setContentText("Loading……");//图标、标题、内容这三个设置是必须要有的。
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void eventEnd() {
        builder.setContentText("Event Loading completed");
        manager.notify(_notificationID++,builder.build());
        b1=true;
        showLoadSuccess();
    }

    @Override
    public void gameEnd() {
        builder.setContentText("Games Loading completed");
        manager.notify(_notificationID++,builder.build());
        b2=true;
        showLoadSuccess();
    }

    @Override
    public void forecastsEnd() {
        builder.setContentText("Forecasts Loading completed");
        manager.notify(_notificationID++,builder.build());

        b3=true;
        showLoadSuccess();
    }

    private void showLoadSuccess() {
        if (b3&&b1&&b2){
            L.d(TAG+ "showLoadSuccess");
            manager.cancel(_notificationID);
            SPUtils.putBoolean(this, ContextValue.ISLOADING,false);
            SPUtils.putBoolean(this, ContextValue.ISLOADDATA,true);
            ToastUtils.showToast("Data loading completed");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d(TAG+ "onDestroy");
    }

    private class ReadFile {
        private String soapFileName;
        private String result;
        private Serializer serializer;

        public ReadFile(String soapFileName) {
            this.soapFileName = soapFileName;
        }

        public String getResult() {
            return result;
        }

        public Serializer getSerializer() {
            return serializer;
        }

        public ReadFile invoke() throws IOException {
            result = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(soapFileName)));
            String tmpStr = "";
            serializer = new Persister();

            while ((tmpStr = br.readLine())!=null){
                result = result + tmpStr.trim();
            }
            return this;
        }
    }
}
