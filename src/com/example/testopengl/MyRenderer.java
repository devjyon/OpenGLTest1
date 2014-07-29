package com.example.testopengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRenderer implements Renderer {
    private float red = 0.9f;
    private float green = 0.2f;
    private float blue = 0.2f;
 
    private Square square;
    
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(red, green, blue, 1.0f);
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -10);
        square.draw(gl);
    }
 
    
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
       
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
       
        GLU.gluPerspective(gl, 45.0f, (float)width/(float)height, 0.1f, 100.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

	@Override
	public void onSurfaceCreated(GL10 gl,
			javax.microedition.khronos.egl.EGLConfig config) {
		square = new Square();
	}

	public void setColor(float r, float g, float b) {
	    red = r;
	    green = g;
	    blue = b;
	    
	    square.setColor(g, b, r);
	    
	}
	
}


class Square {
    private float vertices[] = {
        -1.0f,  1.0f, 0.0f,
        -1.0f, -1.0f, 0.0f,
        1.0f, -1.0f, 0.0f,
        1.0f,  1.0f, 0.0f,
    };
  
    private short[] indices = { 0, 1, 2, 0, 2, 3 };
    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;
    
    private float red = 0.9f;
    private float green = 0.2f;
    private float blue = 0.2f;
    
   public Square() {
       ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
       vbb.order(ByteOrder.nativeOrder());
       vertexBuffer = vbb.asFloatBuffer();
       vertexBuffer.put(vertices);
       vertexBuffer.position(0);
    
       ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
       ibb.order(ByteOrder.nativeOrder());
       indexBuffer = ibb.asShortBuffer();
       indexBuffer.put(indices);
       indexBuffer.position(0);
   }
   
   public void setColor(float pred, float pgreen, float pblue){
	   red = pred;
	   green = pgreen;
	   blue = pblue;
   }
   
   public void draw(GL10 gl) {
	   
	   gl.glColor4f(red, green, blue, 1);
	    
	    gl.glFrontFace(GL10.GL_CCW);
	    gl.glEnable(GL10.GL_CULL_FACE);
	    gl.glCullFace(GL10.GL_BACK);
	   
	    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
	    gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, 
	        GL10.GL_UNSIGNED_SHORT, indexBuffer);
	    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glDisable(GL10.GL_CULL_FACE);
	 }
}