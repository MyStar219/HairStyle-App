package com.applapse.hairstylesalon;

import android.content.Context;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

/**
 * Created by Administrator on 11/01/2016.
 */
public class rate {
    public static void showRate(final Context context) {


        AppRate.with(context).setDebug(false).setInstallDays(2).setLaunchTimes(1).setRemindInterval(2).setOnClickButtonListener(new OnClickButtonListener() {
            @Override
            public void onClickButton(int which) {

            }
        }).monitor();

        AppRate.showRateDialogIfMeetsConditions((android.app.Activity) context);
    }
}
