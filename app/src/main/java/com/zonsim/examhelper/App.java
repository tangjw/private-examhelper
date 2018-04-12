package com.zonsim.examhelper;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.zonsim.examhelper.entity.DaoMaster;
import com.zonsim.examhelper.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * desc
 * <p>
 * Created by tangjunwei on 2018/4/12.
 * <a href="mailto:tjwabc@gmail.com">Contact me</a>
 * <a href="https://github.com/tangjw">Follow me</a>
 */
public class App extends Application {
    
    
    private static DaoSession mDaoSession;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "question1.db", null);
        
        
        SQLiteDatabase db = helper.getWritableDatabase();
//        Database db = helper.getEncryptedWritableDb(sKey);
        
        DaoMaster daoMaster = new DaoMaster(db);
        
        mDaoSession = daoMaster.newSession();
        
    }
    
    public static DaoSession getDaoInstance() {
        return mDaoSession;
    }
    
}
