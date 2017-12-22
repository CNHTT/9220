package com.odd.sportal.webservice;


import com.extra.retrofit.RoleInfoRequestEnvelope;
import com.extra.retrofit.RoleInfoResponseEnvelope;
import com.odd.sportal.webservice.request.RequestEnvelope;
import com.odd.sportal.webservice.response.ResponseEnvelope;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 接口请求
 * Created by SmileXie on 16/7/15.
 */
public interface IBetAPI {

    @Headers({"Content-Type: text/xml;charset=UTF-8", "SOAPAction: http://WebXml.com.cn/getWeatherbyCityName"})//请求的Action，类似于方法名
    @POST("WeatherWebService.asmx")
    Call<ResponseEnvelope> getWeatherbyCityName(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=UTF-8"})//请求的Action，类似于方法名
    @POST("http://136.243.147.149:9026/soap/IBetAPI")//请求的地址
    Call<ResponseBody> getBetEvents(@Body String string);
}
