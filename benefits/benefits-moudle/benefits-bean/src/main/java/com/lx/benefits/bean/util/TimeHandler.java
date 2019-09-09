package com.lx.benefits.bean.util;

import com.lx.benefits.bean.constants.grain.GrainArticleInfoConstant;

import java.util.Timer;
import java.util.TimerTask;

/**
 * User:wangmeng
 * Date:2019/5/24
 * Time:10:19
 * Verision:2.x
 * Description:TODO
 **/
public class TimeHandler {

    private Integer articleReadTime;

    private static final long timer_unit = 1000;

    private Timer timer = null;

    public TimeHandler(Integer articleReadTime){
        this .articleReadTime = articleReadTime;
    }

    public void startTimer(){
        timer = new Timer();
        TimerTask timerTask = new MyTimerTask();
        timer.schedule(timerTask, 0, timer_unit);
    }

    /**
     * count down task
     */
    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            try{
                Thread.sleep(articleReadTime *timer_unit);
            }catch (Exception e) {
                e.printStackTrace();
            }
            timerRes();
        }
    }

    public Boolean  timerRes(){
        if(GrainArticleInfoConstant.ARTICLE_READ_TIME_TIMER_END.equals(articleReadTime)){
            return true;
        }
        return false;
    }

    public void cancelTimer(){
        timer.cancel();
    }

}
