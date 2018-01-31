package com.qf.schedule.scheduletest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
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
					, i );
			ScheduledFuture sss = scheduledExecutor.scheduleAtFixedRate(task, 5, 1, TimeUnit.SECONDS);
			Thread.sleep(8000);
			//sss.cancel(true);

		SpringApplication.run(ScheduletestApplication.class, args);
	}
}
