package diandian.myapplication.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Created by funplus on 2018/4/8.
 */

public class onlyViewGrop extends ViewGroup {
    public onlyViewGrop(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.play(ObjectAnimator.ofFloat(this,"scaleX",1.0f,2.0f)).with(ObjectAnimator.ofFloat(this,"scaleY",1.0f,2.0f));
        animationSet.setDuration(3000);
        animationSet.start();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
