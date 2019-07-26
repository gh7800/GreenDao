package cn.shineiot.libgreendao.helper;

import android.content.Context;

import cn.shineiot.libgreendao.App;
import cn.shineiot.libgreendao.entity.DaoMaster;
import cn.shineiot.libgreendao.entity.DaoSession;

/**
 * @author wangs
 */
public class GreenDaoHelper {

	private static DaoMaster.DevOpenHelper devOpenHelper;
	private static DaoMaster daoMaster;

	public static void initDataBase(Context context, String name) {
		devOpenHelper = new DaoMaster.DevOpenHelper(context, name, null);
	}

	public static DaoMaster getDaoMaster(){
		if(null == devOpenHelper){
			initDataBase(App.context(),App.DateBaseName);
		}
		return new DaoMaster(devOpenHelper.getWritableDb());
	}

	public static DaoSession getDaoSession(){
		if(null == daoMaster){
			daoMaster = getDaoMaster();
		}
		return daoMaster.newSession();
	}

}
