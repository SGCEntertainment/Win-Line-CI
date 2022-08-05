package com.wlines.game;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

//import com.yandex.appmetrica.push.firebase.FirebasePushServiceControllerProvider;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;
import com.yandex.metrica.push.YandexMetricaPush;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Creating an extended library configuration.                      //c6b8bca1-50e3-4755-80b7-77c1f3b4db5a
        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder("c6b8bca1-50e3-4755-80b7-77c1f3b4db5a").build();
        // Initializing the AppMetrica SDK.
        YandexMetrica.activate(getApplicationContext(), config);
        // Automatic tracking of user activity.
        YandexMetrica.enableActivityAutoTracking(this);

        YandexMetricaPush.init(getApplicationContext());
//        YandexMetricaPush.init(
//                getApplicationContext(),
//                new FirebasePushServiceControllerProvider(this)
//                //new HmsPushServiceControllerProvider(this)
//        );




//        OkHttpClient client = new OkHttpClient();
//        //hqmw3hchhs9q92gcrjswbdbg1knw3tpn
//        String url_api = "https://pomidorkin.space/click_api/v3?token=hqmw3hchhs9q92gcrjswbdbg1knw3tpn&log=1&info=1";
//
//        //ответ
////      Response response = new Response.Builder()
////      .build();
//
//        //запрос
//
//        Request request = new Request.Builder()
//                .url(url_api)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String myResponse = response.body().string();
//
//                    String offer_id = null;
//                    try {
//
//                        JSONObject jsonObj = new JSONObject(myResponse);
//                        Log.d("response", "11111111111111111111111111111111111111111111111111111111111111111111");
//
//                        String ansOll = jsonObj.getString("body");
//                        //Log.d("response",ansOll);
//
//                        String ans0 = jsonObj.getString("info");
//                        //Log.d("response",ans0);
//
//                        Gson g = new Gson();
//                        String str = g.toJson(ans0);
//                        Log.d("response", str);
////                      JSONObject jsonid = new JSONObject(str);
////                      String ans1 = jsonid.getString("offer_id");
////                      Log.d("response",ans1);
//
//                        //JSONObject json_info = new JSONObject(ans0);
//                        //String ans1 = json_info.getString("offer_id");
//                        //Log.d("response",ans1);
//                        //val temp = obj.getJSONObject("")
//
//                        //Log.d("log","Error: ${obj.getString("countryCode")}")
//
//                        Log.d("response", "11111111111111111111111111111111111111111111111111111111111111111111");
//                        // Считываем json
//
//                        Log.d("response", "1222222222222222222222222222222222222222222");
//
//                        //"info":{"type":null,"url":null,"campaign_id":95,"stream_id":291,"landing_id":"",
//                        //"sub_id":"335jmp2r9h","is_bot":false,
//                        // "offer_id":48,"token":"uuid_335jmp2r9h_335jmp2r9h629882c6b0b1a1.60179320",
//                        //"uniqueness":{"campaign":false,"stream":false,"global":false}}
//
//                        //String offer_id = jsonlink.getString("offer_id");
//                        //Log.d("response",offer_id);
//                        //offer_id и token
//
//                        //в логе есть параметры гео и тп.
//                        String ans = jsonObj.getString("log");
//                        //Log.d("response",ans);
//                        //вытягиваем инфу из лога 17 элемент - User info
//                        JSONArray jsonlog = new JSONArray(ans);
//                        //Log.d("response","88888888888888888888888888888888888888888888888888888888888888888888888888");
//                        String log = jsonlog.getString(17);
//                        //Log.d("response",log);
//                        //удаляем "User info: " из строки, чтобы потом её преобразовать
//                        String a = log.substring(11);
//                        //Log.d("response",a);
//
//                        JSONObject jsoninf = new JSONObject(a);
//                        Log.d("response", "110101010010101010010100010101010100101010101010101001010010001001010");
//                        //вытягивает Country, таким образом можно вытягивать другие параметры
//
//                        String country = jsoninf.getString("Country");
//                        //Log.d("response","Country: "+country);
//
//                        String bot = jsoninf.getString("Is bot");
//                        //Log.d("response","Is bot: "+bot);
//
//                        editor.remove("country_info").apply(); //clear().apply();//
//                        editor.putString("country_info", country).apply();
//                        //editor.putString("info",str).apply();
//                        editor.putString("api_info", bot).apply();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    //Log.d("response",myResponse);
//                }
//            }
//        });


    }

}


