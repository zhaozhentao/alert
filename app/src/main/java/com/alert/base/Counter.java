package com.alert.base;

import android.os.CountDownTimer;

/**
 * Created by zhaotao on 2018/1/13.
 */

public class Counter {

    public static long Minute = 60 * 1000;
    public static long Second = 1000;

    private CountDownTimer countDownTimer;

    public Counter(long total, long countDownInterval, Tick tick, Finish finish) {
        countDownTimer = new CountDownTimer(total, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                tick.onTick(millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                finish.onFinish();
            }
        };
    }

    public void start() {
        countDownTimer.start();
    }

    public void cancel() {
        countDownTimer.cancel();
    }

    public interface Tick {
        void onTick(long secondLeft);
    }

    public interface Finish {
        void onFinish();
    }
}
