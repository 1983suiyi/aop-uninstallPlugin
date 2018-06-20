package diandian.myapplication.tools;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import diandian.myapplication.R;

/**
 * Created by funplus on 2018/6/20.
 */

public class PluginUtil {
    public final static String apkDir = "/storage/emulated/0/360";
    private Context context;
    private DexClassLoader dexClassLoader;
    private String apkName;
    private String apkPackageName;
    private String path = "/sdcard/resource.apk";

    public PluginUtil(Context context,String apkPackageName,String apkName){
        this.context = context;
        this.apkName = apkName;
        this.apkPackageName = apkPackageName;

        File optimizedDirectoryFile = context.getDir("dex", Context.MODE_PRIVATE);//在应用安装目录下创建一个名为app_dex文件夹目录,如果已经存在则不创建
        //参数：1、包含dex的apk文件或jar文件的路径，2、apk、jar解压缩生成dex存储的目录，3、本地library库目录，一般为null，4、父ClassLoader
        dexClassLoader = new DexClassLoader(path, optimizedDirectoryFile.getPath(), null, context.getClassLoader());

    }

    //获取apk数据
    private String[] getUninstallApkInfo(Context context, String archiveFilePath) {
        String[] info = new String[2];
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null) {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            String versionName = pkgInfo.versionName;//版本号
            Drawable icon = pm.getApplicationIcon(appInfo);//图标
            String appName = pm.getApplicationLabel(appInfo).toString();//app名称
            String pkgName = appInfo.packageName;//包名
            info[0] = appName;
            info[1] = pkgName;
        }
        return info;
    }
    private Resources getPluginResources() {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);//反射调用方法addAssetPath(String path)
            //第二个参数是apk的路径：Environment.getExternalStorageDirectory().getPath()+File.separator+"plugin"+File.separator+"apkplugin.apk"
            addAssetPath.invoke(assetManager, path);//将未安装的Apk文件的添加进AssetManager中，第二个参数为apk文件的路径带apk名
            Resources superRes = context.getResources();
            Resources mResources = new Resources(assetManager, superRes.getDisplayMetrics(),
                    superRes.getConfiguration());
            return mResources;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //drawableId = "forest"
    //资源的位置根据自己的情况修改，如果把资源放在mipmap下，就把drawable改成mipmap
    public Bitmap getApkBitmap(String drawableId){
        try {
            Class<?> clazz = dexClassLoader.loadClass(apkPackageName + ".R$drawable");//通过使用apk自己的类加载器，反射出R类中相应的内部类进而获取我们需要的资源id
            Field field = clazz.getDeclaredField(drawableId);
            int resId = field.getInt(R.id.class);//得到图片id
            Resources mResources = getPluginResources();
            return BitmapFactory.decodeResource(mResources,resId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getApkString(String stringId){
        try {
            Class<?> clazz = dexClassLoader.loadClass(apkPackageName + ".R$string");//通过使用apk自己的类加载器，反射出R类中相应的内部类进而获取我们需要的资源id
            Field field = clazz.getDeclaredField(stringId);
            int resId = field.getInt(R.id.class);//得到图片id
            Resources mResources = getPluginResources();
            return mResources.getString(resId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Drawable getApkDrawable(String drawableId){
        try {
            Class<?> clazz = dexClassLoader.loadClass(apkPackageName + ".R$drawable");//通过使用apk自己的类加载器，反射出R类中相应的内部类进而获取我们需要的资源id
            Field field = clazz.getDeclaredField(drawableId);
            int resId = field.getInt(R.id.class);//得到图片id
            Resources mResources = getPluginResources();
            assert mResources != null;
            return mResources.getDrawable(resId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getClass(String className){
        try {
            Class<?> clazz = dexClassLoader.loadClass(className);
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}