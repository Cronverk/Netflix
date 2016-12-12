package com.example.hisaki.netflix;

import android.app.Application;

import com.example.hisaki.netflix.enteties.DaoMaster;
import com.example.hisaki.netflix.enteties.DaoSession;
import com.example.hisaki.netflix.retrofit.NetflixApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.greenrobot.greendao.database.Database;

import static com.example.hisaki.netflix.MyApp.getDaoSession;

/**
 * Created by hisaki on 09.12.2016.
 */

public class MyApp extends Application {


    private Retrofit retrofit;
    private static NetflixApi netflixApi;
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://netflixroulette.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        netflixApi = retrofit.create(NetflixApi.class);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "movies-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static NetflixApi getNetflixAPI(){
        return netflixApi;
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
