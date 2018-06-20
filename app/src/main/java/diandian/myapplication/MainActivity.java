package diandian.myapplication;

import android.app.Activity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import dalvik.system.DexClassLoader;
import diandian.myapplication.bean.TestLoginBean;
import diandian.myapplication.tools.PluginUtil;
import rx.Subscriber;

import static diandian.myapplication.tools.NetWorkUtil.ping;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.

    rx.Observable<TestLoginBean> observable;

    static {
        System.loadLibrary("hello");
    }

    private DexClassLoader dexClassLoader;

    private PluginUtil pluginUtil;
    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    @AspectAnnotation(MethodName = "onCreate",clazz = "MainActivity",params = "")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pluginUtil = new PluginUtil(this,"com.diandian.bitestswitch","bitestswitch");
        Log.e("suiyi", " --> onCreate");
        test();
        boolean is = ping("www.baidu.com");
       /* ImageView iv = (ImageView) findViewById(R.id.anim_view);

        // Example of a call to a native method
        final TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater layoutInflater1 = LayoutInflater.from(this);
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(tv, "TextColor", new ArgbEvaluator(), Color.RED, Color.BLUE, Color.BLACK);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //objectAnimator.start();

//        AnimationDrawable animationDrawable = AnimationUtils.loadAnimation(this,R.drawable.fram);

        ValueAnimator animScale = ValueAnimator.ofFloat(20f, 80f, 60f, 10f, 35f, 55f, 10f);
        animScale.setRepeatCount(-1);
        animScale.setRepeatMode(ValueAnimator.REVERSE);
        animScale.setDuration(5000);
        animScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv.setTextSize((float) animation.getAnimatedValue());
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator).with(animScale);
        animatorSet.setDuration(3000);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();

        AnimationDrawable animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.frame_animation);
        iv.setBackground(animationDrawable);
        animationDrawable.start();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .connectTimeout(1500, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://passport-dev-cn.ifunplus.cn:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        TestRequestApi api = retrofit.create(TestRequestApi.class);


        HashMap<String, Object> params = new HashMap<>();
        params.put("gameId", "2066");
        params.put("platUid", "12356222");
        params.put("platToken", "sdfs6d14f6sd51f6s546s1d65f14s56d4f65s");
        params.put("channelId", 1000);
        params.put("platform", "1");//1 android
        params.put("method", "login_with_channel");
        String auth = makeSignature(params);

        observable = api.loginByRxJava(auth, params);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Action1<TestLoginBean>() {
                    @Override
                    public void call(TestLoginBean testLoginBean) {
                        Log.e("suiyi", "doOnNext" + testLoginBean.getStatus());

                        if (testLoginBean.getStatus() == 1) {
                            TestLogin testLogin = testLoginBean.getLoginBean();
                            if (null != testLogin) {
                                String fpid = testLogin.getFpid();
                                String sessionKey = testLogin.getSessionKey();
                                Log.e("suiyi", fpid + ";;;;" + sessionKey);
                            }
                        }
                    }
                })
                .filter(new Func1<TestLoginBean, Boolean>() {
                    @Override
                    public Boolean call(TestLoginBean testLoginBean) {
                        if (testLoginBean != null) {
                            Log.e("suiyi", "filter-call-true");
                            return false;
                        } else{
                            Log.e("suiyi", "filter-call-false");
                            return true;
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(mSubscriber);

       /*Call<TestLoginBean> call = api.loginByCall(auth,params);
       call.enqueue(new Callback<TestLoginBean>() {
           @Override
           public void onResponse(Call<TestLoginBean> call, Response<TestLoginBean> response) {
               TestLoginBean testLoginBean = response.body();
               if (testLoginBean.getStatus()==1){
                   TestLogin testLogin = testLoginBean.getLoginBean();
                   if (null!=testLogin){
                       String fpid = testLogin.getFpid();
                       String sessionKey = testLogin.getSessionKey();
                       Log.e("suiyi",fpid+";;;;"+sessionKey);
                   }
               }
           }

           @Override
           public void onFailure(Call<TestLoginBean> call, Throwable throwable) {
               Log.e("suiyi","onFailure"+throwable.getLocalizedMessage());
           }
       });
*/

      /* Intent intent  = new Intent(this,WakeUpBroadCast.class);
       intent.setAction("wake_up_alerm");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,(int)System.currentTimeMillis(),intent,0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC,System.currentTimeMillis()+5*1000,pendingIntent);*/
//        printDex(this);
//
//        AssetManager am =getAssets();
        ImageView iv = (ImageView) findViewById(R.id.anim_view);
       // int id = pluginRes.getIdentifier("icon_resource","drawable",getPackageName());
       // Log.e("suiyi","id:"+id);
        iv.setImageBitmap(pluginUtil.getApkBitmap("icon_resource"));

        TextView tv =(TextView)findViewById(R.id.sample_text);
        tv.setText(pluginUtil.getApkString("app_name"));
    }

    private Subscriber<TestLoginBean> mSubscriber = new Subscriber<TestLoginBean>() {
        @Override
        public void onCompleted() {
            Log.e("suiyi", "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.e("suiyi", "onError");
        }

        @Override
        public void onNext(TestLoginBean testLoginBean) {
            Log.e("suiyi", "onNext");
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    public native String stringFromJNI();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriber.unsubscribe();
    }

    @PointCutAnnotation(MethodName = "test",clazz = "MainActivity",params = "")
    public void test(){
        Log.e("suiyi","test");
    }



    /**
     * Locates a given field anywhere in the class inheritance hierarchy.
     *
     * @param instance an object to search the field into.
     * @param name field name
     * @return a field object
     * @throws NoSuchFieldException if the field cannot be located
     */
    private static Field findField(Object instance, String name) throws NoSuchFieldException {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(name);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                // ignore and search next
            }
        }
        throw new NoSuchFieldException("Field " + name + " not found in " + instance.getClass());
    }


    private static void printDex(Context context){
        try {
            Field pathListField = findField(context.getClassLoader(), "pathList");
            if (pathListField!=null){
                Log.e("suiyi","pathListField !=null");
            }else
                return;
            Object dexPathList = pathListField.get(context.getClassLoader());
            File dexDir = new File(context.getFilesDir(), "secondary-dexes");
            Object[] elements = makeDexElements(dexPathList,new ArrayList<File>(),dexDir,new ArrayList<IOException>());
            for (Object element:elements){
                Log.e("suiyi",element.getClass().getSimpleName());
            }
            ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
//            expandFieldArray(dexPathList, "dexElements", makeDexElements(dexPathList,
//                    new ArrayList<File>(additionalClassPathEntries), optimizedDirectory,
//                    suppressedExceptions));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void expandFieldArray(Object instance, String fieldName,
                                         Object[] extraElements) throws NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field jlrField = findField(instance, fieldName);
        Object[] original = (Object[]) jlrField.get(instance);
        Object[] combined = (Object[]) Array.newInstance(
                original.getClass().getComponentType(), original.length + extraElements.length);
        System.arraycopy(original, 0, combined, 0, original.length);
        System.arraycopy(extraElements, 0, combined, original.length, extraElements.length);
        jlrField.set(instance, combined);
    }

    private static Object[] makeDexElements(
            Object dexPathList, ArrayList<File> files, File optimizedDirectory,
            ArrayList<IOException> suppressedExceptions)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        Method makeDexElements =
                findMethod(dexPathList, "makeDexElements", ArrayList.class, File.class,
                        ArrayList.class);
        return (Object[]) makeDexElements.invoke(dexPathList, files, optimizedDirectory,
                suppressedExceptions);
    }

    private static Method findMethod(Object instance, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Method method = clazz.getDeclaredMethod(name, parameterTypes);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                return method;
            } catch (NoSuchMethodException e) {
                // ignore and search next
            }
        }
        throw new NoSuchMethodException("Method " + name + " with parameters " +
                Arrays.asList(parameterTypes) + " not found in " + instance.getClass());
    }

}
