package com.example.herbert.fightgo.greendao;

import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 */

public class MeiziDaoUtils {
    private static final String TAG = MeiziDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public MeiziDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param MeiZi
     * @return
     */
    public boolean insertMeizi(MeiZi MeiZi){
        boolean flag = false;
        flag = mManager.getDaoSession().getMeiZiDao().insert(MeiZi) == -1 ? false : true;
        Log.i(TAG, "insert MeiZi :" + flag + "-->" + MeiZi.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<MeiZi> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (MeiZi MeiZi : meiziList) {
                        mManager.getDaoSession().insertOrReplace(MeiZi);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param MeiZi
     * @return
     */
    public boolean updateMeizi(MeiZi MeiZi){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(MeiZi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param MeiZi
     * @return
     */
    public boolean deleteMeizi(MeiZi MeiZi){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(MeiZi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(MeiZi.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<MeiZi> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(MeiZi.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public MeiZi queryMeiziById(long key){
        return mManager.getDaoSession().load(MeiZi.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<MeiZi> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(MeiZi.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<MeiZi> queryMeiziByQueryBuilder(long id){
        QueryBuilder<MeiZi> queryBuilder = mManager.getDaoSession().queryBuilder(MeiZi.class);
        return queryBuilder.where(MeiZiDao.Properties.Id.eq(id)).list();
    }
}
