package com.example.asus.home;

import android.content.Context;
import android.view.MotionEvent;
import org.rajawali3d.Object3D;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.renderer.Renderer;


public class BasicRenderer extends Renderer {

    private DirectionalLight mDirectionalLight;
    private Context context;
    private Object3D mObject;
    private Object3D mickyObject;;

    public BasicRenderer(Context context) {
        super(context);
        this.context = context;
        setFrameRate(60);
    }

    @Override
    public void initScene() {
        mDirectionalLight = new DirectionalLight(0f, 1f, 0f);
        mDirectionalLight.setColor(1.0f, 1.0f, 1.0f);
        mDirectionalLight.setPower(8);
        getCurrentScene().addLight(mDirectionalLight);
        try{
            LoaderOBJ objParser = new LoaderOBJ(context.getResources(),mTextureManager, R.raw.fastfood);
            objParser.parse();
            mObject = objParser.getParsedObject();
            mObject.setScale(0.17);
            mObject.rotate(Vector3.Axis.Y, 90);
            //getCurrentScene().addChild(mObject);

            LoaderOBJ mickyParser = new LoaderOBJ(context.getResources(),mTextureManager, R.raw.mickey_mouse);
            mickyParser.parse();
            mickyObject = mickyParser.getParsedObject();
            mickyObject.setPosition(-10, 32, 30);
            mickyObject.setScale(3);
            //mickyObject.rotate(Vector3.Axis.Y, 90);
            getCurrentScene().addChild(mickyObject);
        } catch (ParsingException e){
        }
        getCurrentCamera().setX(0);
        getCurrentCamera().setY(40);
        getCurrentCamera().setZ(50);
        getCurrentCamera().setCameraPitch(10);
        //getCurrentCamera().setLookAt(50, 30, -200);
        //getCurrentCamera().rotate(Vector3.Axis.Y, -20);
    }

    @Override
    public void onRender(final long elapsedTime, final double deltaTime) {
        super.onRender(elapsedTime, deltaTime);
        //mObject.rotate(Vector3.Axis.Y, 1.0);
        //mickyObject.rotate(Vector3.Axis.Y, 1.0);
        //mObject.setLookAt(0,0,20);
    }

    @Override
    public void onTouchEvent(MotionEvent event){
    }

    @Override
    public void onOffsetsChanged(float x, float y, float z, float w, int i, int j){
    }
}