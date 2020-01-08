package com.google.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class GoogleEternalService extends Service {
	private AlarmManager am;
	private PendingIntent sender;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		//启动定时器，12小时启动一次  12*60*60*1000
		// 重启成功会起启动服务
		Intent intent = new Intent(this, AlarmTaskReceiver.class);
		intent.setAction("com.google.eternal.service.alarm_task_receiver");
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
		long firstime = SystemClock.elapsedRealtime();
		am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		// 59min一个周期，不停的发送广播  
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 59 * 60 * 1000, sender);
//		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 10 * 1000, sender);

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (am != null || sender != null) {
			am.cancel(sender);
		}
	}

}
