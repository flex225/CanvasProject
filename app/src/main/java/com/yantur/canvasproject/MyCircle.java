package com.yantur.canvasproject;

import android.graphics.Paint;

/**
 * Created by Artur on 3/23/2016.
 */
public class MyCircle {
    private float x;
    private float y;
    private int radius;
    private Paint paint;
    private boolean touched;

    public MyCircle(float x, float y, Paint paint) {
        this.x = x;
        this.y = y;
        this.paint = paint;
        this.radius = 50;
        this.touched = false;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean isTouched) {
        this.touched = isTouched;
    }

    public boolean touchInCircle(float x, float y) {
        float deltaX = this.x - x;
        float deltaY = this.y - y;

        return radius * radius >= deltaX * deltaX + deltaY * deltaY;
    }
}
