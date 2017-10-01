package com.example.asus.home;

import android.content.Context;
import android.view.MotionEvent;

import org.rajawali3d.Object3D;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.renderer.Renderer;

/**
 * Created by User on 2017/10/1.
 */

public class ChooserRenderer extends Renderer {

    private DirectionalLight mDirectionalLight;
    private Context context;
    private Object3D modelObject;
    int obj;

    public ChooserRenderer(Context context, int obj) {
        super(context);
        this.context = context;
        setFrameRate(60);
        this.obj = obj;
    }

    @Override
    public void initScene() {
        mDirectionalLight = new DirectionalLight(0f, 1f, 0f);
        mDirectionalLight.setColor(1.0f, 1.0f, 1.0f);
        mDirectionalLight.setPower(8);
        getCurrentScene().addLight(mDirectionalLight);
        try{
            LoaderOBJ objParser = new LoaderOBJ(context.getResources(),mTextureManager, this.obj);
            objParser.parse();
            modelObject = objParser.getParsedObject();
            modelObject.setPosition(0, 34, 30);
            modelObject.setScale(3.3);
            getCurrentScene().addChild(modelObject);
        } catch (ParsingException e){
        }
        getCurrentCamera().setX(0);
        getCurrentCamera().setY(40);
        getCurrentCamera().setZ(50);
    }

    @Override
    public void onRender(final long elapsedTime, final double deltaTime) {
        super.onRender(elapsedTime, deltaTime);
    }

    @Override
    public void onTouchEvent(MotionEvent event){
    }

    @Override
    public void onOffsetsChanged(float x, float y, float z, float w, int i, int j){
    }
}
