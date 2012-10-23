package it.uniroma2.framework.entity;

import java.util.HashMap;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.Contact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import it.uniroma2.framework.Game;
import it.uniroma2.framework.audio.Audio;
import it.uniroma2.framework.event.Event;
import it.uniroma2.framework.event.IPerceptor;
import it.uniroma2.framework.event.Message;
import it.uniroma2.framework.event.RTSimulationKernel;
import it.uniroma2.framework.input.ITouchable;
import it.uniroma2.framework.input.TouchManager;
import it.uniroma2.framework.mind.IMind;
import it.uniroma2.framework.mind.MindManager;
import it.uniroma2.framework.physic.AdaptJBox2D;
import it.uniroma2.framework.physic.PhysicObjJBox2d;
import it.uniroma2.framework.render.ImageMap;
import it.uniroma2.framework.render.Render;


/*******************************************************************************
 * 
 * @author Valentino Colatosti
 * 
 * Copyright (C) 2012 valentino.colatosti@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/


@SuppressLint("NewApi")
public abstract class GameEntity implements IPerceptor, IDrawable, ITouchable, IMind,SensorEventListener {
	
	private int pointX;
	private int pointY;
	private int lengthX;
	private int lengthY;
	
	private PhysicObjJBox2d physicsObj;
	private boolean physics=false;

	private static SensorManager sensorManager=(SensorManager) Game.getContext().getSystemService(Context.SENSOR_SERVICE);
	private static Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	
	private static final int displayHeight=Game.getContext().getResources().getDisplayMetrics().heightPixels;
	private static final int displayWidth=Game.getContext().getResources().getDisplayMetrics().widthPixels;
	
	private Audio audio=Audio.getIstance();
	
	public final Bitmap getBitmap(int resId){
		//return BitmapFactory.decodeResource(Game.getContext().getResources(), resId );
		return ImageMap.getIstance().getBitmap(resId);
	}
	
	public final Bitmap getFullScreenBitmap(int resId){
		Bitmap bitmap=BitmapFactory.decodeResource(Game.getContext().getResources(), resId );
		int height=Game.getContext().getResources().getDisplayMetrics().heightPixels;
		int width=Game.getContext().getResources().getDisplayMetrics().widthPixels;
		return Bitmap.createScaledBitmap(bitmap, width, height, true);
	}
	
	public final Bitmap resizeBitmap(Bitmap bitmap,  int width, int height){
		return Bitmap.createScaledBitmap(bitmap, width, height  , true);
	}
	
	public final Bitmap sizeBitmap(Bitmap bitmap){
		return Bitmap.createScaledBitmap(bitmap, getLengthX(), getLengthY()  , true);
	}
	
	public GameEntity(){
		
		physicsObj=new PhysicObjJBox2d(this);	
	}
		
	public final void register(){
		//physicsObj.autoset();
		  AdaptJBox2D.getIstance().add(physicsObj);
		  TouchManager.getIstance().add(this);
	      //KeyManager.getIstance().add(this);
	      //RenderReference.getIstance().getRender().add(this);
		  Render.getIstance().add(this);
	      MindManager.getIstance().add(this);
	      
	      //sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME, handler)//
	      sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
	      //AccelerometerManager.getIstance().registerListner(this);
	}
	



	public final void unregister() {

		AdaptJBox2D.getIstance().destroy(physicsObj);
		TouchManager.getIstance().remove(this);
		//KeyManager.getIstance().remove(this);
		//RenderReference.getIstance().getRender().remove(this);
		Render.getIstance().remove(this);
		MindManager.getIstance().remove(this);
		sensorManager.unregisterListener(this);
	}
	
	
	public final Audio getSoundClip(){
		return Audio.getIstance();
	}
	
	public void mind(){
		MindManager.getIstance().remove(this);
	}

	public void receiveEvent(Event event){
		
	}


	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}


	public boolean onTouchEvent(MotionEvent motionEvent) {
		return false;
	}
	


	public boolean draw(Canvas canvas) {
		return false;
	}
	

	public boolean receiveCollisionEvent(Contact contact) {
		return false;
	}

	public final int getLengthX() {
		return lengthX;
	}

	public final void setLengthX(int lengthX) {
		this.lengthX = (int) (lengthX*Game.getScaleWidth());
	}

	public final int getLengthY() {
		return lengthY;
	}

	public final void setLengthY(int lengthY) {
		this.lengthY = (int) (lengthY*Game.getScaleHeight());
	}

	public final int getPointX() {
		return pointX;
	}

	public final void setPointX(int pointX) {
		this.pointX = (int) (pointX*Game.getScaleWidth());
	}

	public final int getPointY() {
		return pointY;
	}

	public final void setPointY(int pointY) {
		this.pointY = (int) (pointY*Game.getScaleHeight());
	}
	
	
	public final void sendMessage(String message){
		RTSimulationKernel kernel=RTSimulationKernel.getIstance();
		Event event=kernel.requestEventIstance();
		event.set(Message.get(message));
		kernel.SendEvent(event);
	}
	
	public final void sendMessage(String message, HashMap<String,Object> messageInfo){
		RTSimulationKernel kernel=RTSimulationKernel.getIstance();
		Event event=kernel.requestEventIstance();
		event.set(Message.get(message));
		event.setMessageInfo(messageInfo);
		kernel.SendEvent(event);
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		
	}


	public void onSensorChanged(SensorEvent event) {
		sensorManager.unregisterListener(this);
	
	}
	
	public void play(int resId){
		audio.play(resId);		
	}


	public void setPhysics(boolean physics) {
		this.physics = physics;
	}

	public boolean isPhysics() {
		return physics;
	}
	
	public static int getDisplayHeight() {
		return displayHeight;
	}

	public static int getDisplayWidth() {
		return displayWidth;
	}
	
	//******************************
	//integrazione oggetto fisico
	//******************************
	
	public float getFriction() {
		return physicsObj.getFriction();
	}

	public void setFriction(float friction) {
		physicsObj.setFriction(friction);
	}

	public float getDensity() {
		return physicsObj.getDensity();
	}

	public void setDensity(float density) {
		physicsObj.setDensity(density);
	}

	public float getAngle() {
		return physicsObj.getAngle();
	}

	public void setAngle(float angle) {
		physicsObj.setAngle(angle);
	}

	public Vec2 getCenter() {
		return physicsObj.getCenter();
	}

	public void setCenter(Vec2 center) {
		physicsObj.setCenter(center);
	}

	public float getMass() {
		return physicsObj.getMass();
	}

	public void setMass(float mass) {
		physicsObj.setMass(mass);
	}

	public boolean isDynamic() {
		return physicsObj.isDynamic();
	}

	public void setDynamic(boolean dynamic) {
		physicsObj.setDynamic(dynamic);
	}
	
	public void setShapeType(int shapeType) {
		physicsObj.setShapeType(shapeType);
	}

	public int getShapeType() {
		return physicsObj.getShapeType();
	}

	public void moveEntity(int px, int py){
		physicsObj.movePhysicsObj(px, py);
	}
	
	public void setSleepingAllowed(boolean sleepingAllowed) {
		physicsObj.setSleepingAllowed(sleepingAllowed);
	}

	

}
