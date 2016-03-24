package com.yantur.canvasproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyView extends View {

    private List<Integer> colorRes;
    private MyCircle currentCircle = null;
    private List<MyCircle> circles;
    private float touchX = 0;
    private float touchY = 0;


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        circles = new ArrayList<>();
        colorRes = new ArrayList<>();
        colorRes.add(Color.BLACK);
        colorRes.add(Color.YELLOW);
        colorRes.add(Color.GRAY);
        colorRes.add(Color.GREEN);
        colorRes.add(Color.RED);
        colorRes.add(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        for (int i = circles.size() - 1; i >= 0; i--) {
            canvas.drawCircle(circles.get(i).getX(), circles.get(i).getY(), circles.get(i).getRadius(), circles.get(i).getPaint());
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int index = event.getActionIndex();
        int id = event.getPointerId(index);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                for (int i = circles.size() - 1; i >= 0; i--) {
                    if (circles.get(i).touchInCircle(event.getX(id), event.getY(id))) {
                        circles.remove(i);
                        invalidate();
                        return false;
                    }
                }

            case MotionEvent.ACTION_POINTER_DOWN:
                addCircle(event);

                break;
            case MotionEvent.ACTION_MOVE:
                movingCircles(event);

                break;

        }
        invalidate();
        return true;
    }

    private void movingCircles(MotionEvent event) {
        int pointerCount = event.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            circles.get(i).setX(event.getX(i));
            circles.get(i).setY(event.getY(i));
        }
    }

    private void addCircle(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        Random random = new Random();
        int circleColorIndex = random.nextInt(colorRes.size());
        Paint paint = new Paint();
        paint.setColor(colorRes.get(circleColorIndex));
        MyCircle newCircle = new MyCircle(event.getX(index), event.getY(index), paint);
        circles.add(id, newCircle);
    }

}