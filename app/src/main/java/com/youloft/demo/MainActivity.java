package com.youloft.demo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.youloft.advert.YouLoftAdManager;
import com.youloft.core.YouLoftSdk;
import com.youloft.core.utils.LogUtils;
import com.youloft.notification.firebase.FireBaseMessage;
import com.youloft.notification.local.NotificationUtil;
import com.youloft.notification.local.util.LocalNotificationHelper;
import com.youloft.statistics.StatisticsManager;

import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YouLoftSdk.initUnityAdSdk(this, "{\n" +
                        "  \"bannerIds\": [\n" +
                        "    \"0ac59b0996d947309c33f59d6676399f\"\n" +
                        "  ],\n" +
                        "  \"interstIds\": [\n" +
                        "    \"4f117153f5c24fa6a3a92b818a5eb630\",\n" +
                        "    \"b23d7be60ff047dab5f5d1d03245029f\"\n" +
                        "  ],\n" +
                        "  \"videoIds\": [\n" +
                        "    \"920b6145fb1546cf8b5cf2ac34638bb7\",\n" +
//                        "    \"5a1a63a40fca4ead861be44621729ec7\",\n" +
//                        "    \"afe438f9ed784bfe88b7acec441c62e4\",\n" +
//                        "    \"8a4e5738f11c4ab9a44f593f111b2f9c\",\n" +
                        "    \"2f1740cb09e0470c9a823f35d3f8058a\"\n" +
                        "  ]\n" +
                        "}",
                "1069167810093206", "aVg6HRucDUQU9zqCQ48TTZ", "5d7207c53fc195ac5c000f20");


        Log.e("获取友盟ID", StatisticsManager.getTestDeviceInfo(this)[0]);
        Log.e("获取友盟Mac", StatisticsManager.getTestDeviceInfo(this)[1]);

        findViewById(R.id.tv_show_load_video1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YouLoftAdManager.showRewardedVideoAd();
            }
        });

        findViewById(R.id.tv_test_statistics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticsManager.startLevel("5");
//                StatisticsManager.bonus("测试奖励", 10, 50, 1);
//                StatisticsManager.bonus(2345, 3);
//                StatisticsManager.finishLevel("5");
//                StatisticsManager.buy("购买10个道具，每个道具50人民币", 10, 50);
//                StatisticsManager.pay(100, 100, 2);
//                StatisticsManager.pay(10, "本次充值10元，价值10个虚拟币，购买2个道具,微信支付", 2, 10, 2);
//                StatisticsManager.customEvent("diy_test", "{\"key\":\"自定义测试\"}");
//                StatisticsManager.use("FireBase测试物品消耗", 100, 100.0);

                Toast.makeText(getApplicationContext(), "测试完毕", Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.tv_show_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YouLoftAdManager.loadAndShowBannerAd(1);
            }
        });

        findViewById(R.id.tv_hide_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YouLoftAdManager.hideBannerAd();
            }
        });

        findViewById(R.id.tv_show_interstitial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                YouLoftAdManager.getInstance().showGoogleAdsInterstitial(MainActivity.this);
                YouLoftAdManager.showInterstitialAd();
                StatisticsManager.bonus("FireBase测试物品消耗", 100, 100.0, 1);

            }
        });


        findViewById(R.id.tv_send_notification1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationUtil.SetLocalNotification(1, "标题1", "内容1", 0);

            }
        });

        findViewById(R.id.tv_load_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YouLoftAdManager.loadRewardedVideoAd("920b6145fb1546cf8b5cf2ac34638bb7");

            }
        });

        findViewById(R.id.tv_send_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationUtil.SetLocalNotification(2, "标题2", "内容2", 10);
            }
        });

        findViewById(R.id.tv_cancel_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationUtil.CancelLocalNotification(2);
            }
        });
        //配置大图标
        LocalNotificationHelper.setDefaultLargeIcon(R.drawable.googleg_standard_color_18);
        //配置小图标
        LocalNotificationHelper.setDefaultSmallIcon(R.drawable.googleg_disabled_color_18);
        //初始化google远程消息
//        FireBaseMessage.init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        YouLoftAdManager.onMopubResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        YouLoftAdManager.onMopubPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        YouLoftAdManager.onMopubDestroy();

    }
}
