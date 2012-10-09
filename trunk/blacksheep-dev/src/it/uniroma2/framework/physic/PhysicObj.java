package it.uniroma2.framework.physic;

import it.uniroma2.framework.Game;
import it.uniroma2.framework.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import android.util.Log;

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

public class PhysicObj implements IPhysicObj {
	
	//private BodyDef physicsBodyDef;
	public static final int BOX=0;
	public static final int CIRCLE =1;
	public static final int POLYGON=2;
	public static final int EDGE=3;
	
	public static final String DINAMIC ="DINAMIC";
	public static final String STATIC ="STATIC";
	public static final String KINEMATIC ="KINEMATIC";
	

	private Body body;
	
	private boolean dynamic=false;
	private int shapeType= BOX;
	
	//polygon def
	private float friction;
	private float density;
	private float angle;
	private Vec2 center=new Vec2(0,0);
	private String bodyType;
	private boolean sleepingAllowed=false;
	
	//massData
	private float mass=0;
	private List<Vec2> vertexList=null;
	
	//private Vec2 point;
	
	private GameEntity gameEntity;
	
	
	public PhysicObj(GameEntity gameEntity){
		this.gameEntity=gameEntity;
	}
	
	public int getLengthX() {
		return  (int) (gameEntity.getLengthX());
	}


	public int getLengthY() {
		return (int) (gameEntity.getLengthY());
	}


	public int getPointX() {
		return (int) (gameEntity.getPointX());
	}


	public int getPointY() {
		return (int) (gameEntity.getPointY());
	}


	public void sync(){
		gameEntity.setPointX((int) (((body.getPosition().x/Game.getScaleWidth())*CollisionBox2D.RATIO)-((gameEntity.getLengthX()/Game.getScaleWidth())/2)));
		gameEntity.setPointY((int) (((body.getPosition().y/Game.getScaleHeight())*CollisionBox2D.RATIO)-((gameEntity.getLengthY()/Game.getScaleHeight())/2)));
		angle=(float) Math.toDegrees(body.getAngle());
	}
	
	
	public void addVertex(int x, int y){
		if(vertexList==null){
			vertexList=new ArrayList<Vec2>();			
		}
	vertexList.add(new Vec2(x,y));
	}
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public Vec2 getCenter() {
		return center;
	}

	public void setCenter(Vec2 center) {
		this.center = center;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public boolean isDynamic() {
		return dynamic;
	}

	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}
	

	public void setShapeType(int shapeType) {
		this.shapeType = shapeType;
	}

	public int getShapeType() {
		return shapeType;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	
	public void setSleepingAllowed(boolean sleepingAllowed) {
		this.sleepingAllowed=sleepingAllowed;
	}
	
	public boolean getSleepingAllowed() {
		return this.sleepingAllowed;
	}
	
	public GameEntity getGameEntity(){
		return gameEntity;
	}
	

	
	private Vec2 p=new Vec2();
	
	public void movePhysicsObj(int px,int py){
		
		//Log.i("blacksheep","body position x"+body.getPosition().x+" y "+body.getPosition().y);
		//Log.i("blacksheep","valori ingresso funzione x "+px+" y "+py);
		
		float pointX=px/CollisionBox2D.RATIO;
		float pointY=py/CollisionBox2D.RATIO;
		float lengthX=gameEntity.getLengthX()/CollisionBox2D.RATIO;
		float lengthY=gameEntity.getLengthY()/CollisionBox2D.RATIO;
		
		
		 p.x=pointX+lengthX/2;
		 p.y=pointY+lengthY/2;
		
		
		body.setTransform(p, body.getAngle());
		//CollisionBox2D.getIstance().moveBody(body, p);
		
		Log.i("blacksheep","valori uscita x "+p.x+" y "+p.y);
		
	}
	
	
		

}
