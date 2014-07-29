package com.example.testopengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;



public class MyView extends GLSurfaceView {
    private MyRenderer renderer;
 
    public MyView(Context context) {
        super(context);
        renderer = new MyRenderer();
        setRenderer(renderer);
    }
    
    public boolean onTouchEvent(final MotionEvent event) {
        queueEvent(new Runnable() {
            public void run() {
                renderer.setColor(
                    event.getX()/getWidth(), event.getY()/getHeight(), 1.0f
                );
            }
        });
       
        return true;
    }
    
}