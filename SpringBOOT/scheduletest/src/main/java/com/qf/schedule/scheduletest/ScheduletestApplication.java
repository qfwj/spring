package com.qf.schedule.scheduletest;

import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试为什么scheduler里面不能使用FutureTask作为定时的task
 *
 * 原因：futuretask每次执行完之后会将自身状态转换为 NORMAL；导致在调用futureTask的run方法时无法执行callable.call方法
 *
 * Possible state transitions:
 * NEW -> COMPLETING -> NORMAL
 * NEW -> COMPLETING -> EXCEPTIONAL
 * NEW -> CANCELLED
 * NEW -> INTERRUPTING -> INTERRUPTED
 * private volatile int state;
 *private static final int NEW          = 0;
 *private static final int COMPLETING   = 1;
 *private static final int NORMAL       = 2;
 *private static final int EXCEPTIONAL  = 3;
 *private static final int CANCELLED    = 4;
 *private static final int INTERRUPTING = 5;
 *private static final int INTERRUPTED  = 6;
 *
 * */
public class ScheduletestApplication {


    public static void main(String[] args) throws Exception {
        int i = 10;
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(8);
        scheduledExecutor.setRemoveOnCancelPolicy(true); //清除取消的task
        FutureTask task = new FutureTask<Integer>(
                new Runnable() {
                    public void run() {

                        System.out.println("test");
                    }
                }
                , i);
        ScheduledFuture sss = scheduledExecutor.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);
        ScheduledFuture sss2 = scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("test2");
            }
        }, 0, 2, TimeUnit.SECONDS);
        Thread.sleep(8000);
        //sss.cancel(true);

    }
}
