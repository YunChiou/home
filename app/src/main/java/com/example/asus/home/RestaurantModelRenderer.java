package com.example.asus.home;

import android.content.Context;
import android.view.MotionEvent;
import org.rajawali3d.Object3D;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.renderer.Renderer;

public class RestaurantModelRenderer extends Renderer {

    private DirectionalLight mDirectionalLight;
    private Context context;
    private Object3D mObject;
    private Object3D mickyObject;
    private Object3D starwarObject;
    private Object3D minionObject;
    private Object3D bowlingObject;
    private Object3D skullObject;

    public RestaurantModelRenderer(Context context) {
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
            mObject.setScale(0.15);
            mObject.rotate(Vector3.Axis.Y, 170);
            getCurrentScene().addChild(mObject);

            LoaderOBJ mickyParser = new LoaderOBJ(context.getResources(),mTextureManager, R.raw.mickey_mouse);
            mickyParser.parse();
            mickyObject = mickyParser.getParsedObject();
            mickyObject.setPosition(18.5, 1.5, -20);
            mickyObject.setScale(1.2);
            mickyObject.rotate(Vector3.Axis.Y, 70);
            getCurrentScene().addChild(mickyObject);

            LoaderOBJ starwar = new LoaderOBJ(context.getResources(),mTextureManager, R.raw.stormtrooper);
            starwar.parse();
            starwarObject = starwar.getParsedObject();
            starwarObject.setScale(1.25);
            starwarObject.setPosition(2, 1.5, 5);
            starwarObject.rotate(Vector3.Axis.Y, 270);
            getCurrentScene().addChild(starwarObject);

            LoaderOBJ minion = new LoaderOBJ(context.getResources(),mTextureManager, R.raw.minion);
            minion.parse();
            minionObject = minion.getParsedObject();
            minionObject.setScale(0.5);
            minionObject.setPosition(7.7, 8, 11.5);
            minionObject.rotate(Vector3.Axis.Y, 50);
            getCurrentScene().addChild(minionObject);

            LoaderOBJ bowling = new LoaderOBJ(context.getResources(),mTextureManager, R.raw.bowling);
            bowling.parse();
            bowlingObject = bowling.getParsedObject();
            bowlingObject.setScale(1.2);
            bowlingObject.setPosition(15.7, 8, 10);
            bowlingObject.rotate(Vector3.Axis.Y, 50);
            getCurrentScene().addChild(bowlingObject);

            LoaderOBJ skull = new LoaderOBJ(context.getResources(),mTextureManager, R.raw.skull);
            skull.parse();
            skullObject = skull.getParsedObject();
            skullObject.setScale(0.28);
            skullObject.setPosition(1, 3, -5.8);
            skullObject.rotate(Vector3.Axis.Y, 270);
            getCurrentScene().addChild(skullObject);

        } catch (ParsingException e){
        }
        getCurrentCamera().setX(12);
        getCurrentCamera().setY(30);
        getCurrentCamera().setZ(35);
        getCurrentCamera().setCameraPitch(40);
    }

    public void setMickyZ() {
        mickyObject.setPosition(19, mickyObject.getY()-0.1, -20);
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
