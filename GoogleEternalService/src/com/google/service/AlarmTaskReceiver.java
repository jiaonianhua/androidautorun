package com.google.service;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;

public class AlarmTaskReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//判断当前时间是否在凌晨一点
//		int _hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//		if (_hour == 1) {
			//启动app
			startApp(context, "com.zjkj.share");
//	    	startApp(context, "com.debugger");
//		}
	}
	
	private void startApp(Context content, String packageName) {
		Intent _intent = new Intent();
		try {
			// 通过包名启动其他应用
			PackageInfo _pi = content.getPackageManager().getPackageInfo(packageName, 0);
			Intent _resolveIntent = new Intent(Intent.ACTION_MAIN, null);
			_resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			_resolveIntent.setPackage(_pi.packageName);
			List<ResolveInfo> _apps = content.getPackageManager().queryIntentActivities(_resolveIntent, 0);
			ResolveInfo _ri = _apps.iterator().next();
			if (_ri != null) {
				ComponentName _cn = new ComponentName(_ri.activityInfo.packageName, _ri.activityInfo.name);
				_intent.setAction(Intent.ACTION_MAIN);
				_intent.addCategory(Intent.CATEGORY_LAUNCHER);
				_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				_intent.setComponent(_cn);
				content.startActivity(_intent);
			}
		} catch (Exception _ex) {
			_ex.printStackTrace();
		}
	}
}
