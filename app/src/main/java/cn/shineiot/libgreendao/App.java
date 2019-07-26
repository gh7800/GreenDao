package cn.shineiot.libgreendao;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

import cn.shineiot.libgreendao.helper.GreenDaoHelper;

/**
 * @author wangs
 */
@SuppressLint("Registered")
public class App extends Application {
	public static String DateBaseName = "libgreendao.bd";
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();

		//创建数据库
		GreenDaoHelper.initDataBase(context, DateBaseName);
		QueryBuilder.LOG_SQL = true;
		QueryBuilder.LOG_VALUES = true;

	}

	public static Context context(){
		return context;
	}
}
