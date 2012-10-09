package it.uniroma2.framework.physic;

import it.uniroma2.framework.GameProperties;
import it.uniroma2.framework.entity.GameEntity;

import java.util.ArrayList;
import java.util.Properties;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

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

public class CollisionBox2D implements Runnable{
	
	private static CollisionBox2D collisionBox2D;
	private  World world;
	private boolean run;
	
	public static float RATIO =32.0f;
	
	private ArrayList<PhysicObj> listPhysicsObj;
	
	
	private Vec2 gravity;
	
	

	private CollisionBox2D(){
		
	        
	        boolean doSleep = true;
	        listPhysicsObj=new ArrayList<PhysicObj>();
	        Properties properties= GameProperties.getIstance().getPropeties();
	        //properties.getProperty(name, 0);
	        Float gx=new Float(properties.getProperty("GRAVITYX", "0.0f"));
	        Float gy=new Float(properties.getProperty("GRAVITYY", "0.0f"));
	        
	        RATIO=new Float(properties.getProperty("RATIO", "32.0f"));
			gravity = new Vec2( gx, gy);
			world = new World(gravity, doSleep);
			run =false;
			Log.i("box2d", "start world of physics");
		
	}
	
	
	public static CollisionBox2D getIstance(){	
		if(collisionBox2D==null)
			collisionBox2D=new CollisionBox2D();
		return collisionBox2D;
		
	}
	
	
	
	public void destroy(IPhysicObj physicsObj){
		Log.i("box2d","destroy body");
		synchronized(collisionBox2D){
			listPhysicsObj.remove(physicsObj);
			world.destroyBody(physicsObj.getBody());
			
		}
		Log.i("box2d","after destroy bodycount"+world.getBodyCount());
	}
	
	public void add(PhysicObj physicsObj) {
		synchronized (collisionBox2D){
		
		
		float pointX=physicsObj.getPointX()/RATIO;
		float pointY=physicsObj.getPointY()/RATIO;
		float lengthX=physicsObj.getLengthX()/RATIO;
		float lengthY=physicsObj.getLengthY()/RATIO;

			
		BodyDef bodyDef = new BodyDef();
		
		//Log.i("box2d","lengthY "+physicsObj.getLengthY()+" pointY "+physicsObj.getPointY());
		//Log.i("box2d", "position x: "+(pointX-lengthX/2)*RATIO+" y: "+(pointY+lengthY/2)*RATIO);
		//Log.i("",""+(int) (body.getPosition().x*CollisionBox2D.RATIO)-(gameEntity.getLengthX()/2));
		
		if(physicsObj.isDynamic()){
			bodyDef.type=BodyType.DYNAMIC;
		}else
			bodyDef.type=BodyType.STATIC;
		
		bodyDef.angle=(float)Math.toRadians(physicsObj.getAngle());		
		bodyDef.position=new Vec2(pointX+lengthX/2, pointY+lengthY/2);
		bodyDef.active=true;
		
		// FixtureDef
		FixtureDef fixtureDef=new FixtureDef();
			
		fixtureDef.density=physicsObj.getDensity();
		fixtureDef.friction=physicsObj.getFriction();
		
		fixtureDef.userData=physicsObj.getGameEntity();
		
		Body body = world.createBody(bodyDef);
		
	
		switch (physicsObj.getShapeType()) {

			case (PhysicObj.CIRCLE): {
				CircleShape circleShape = new CircleShape();
				circleShape.m_radius = lengthX / 2;
				fixtureDef.shape = circleShape;
				body.createFixture(fixtureDef);
				
				break;
			}
			case (PhysicObj.POLYGON): {
				break;
			}

			case (PhysicObj.BOX): {
				Log.i("box2d", "BOX ");
				
				PolygonShape polygonShape = new PolygonShape();
				polygonShape.setAsBox(lengthX/2, lengthY/2);
				fixtureDef.shape = polygonShape;
				body.createFixture(fixtureDef);

				break;
			}case (PhysicObj.EDGE): {
				
				break;
			}
			default: {
				break;
			}

			}
		
		
			
		body.setSleepingAllowed(physicsObj.getSleepingAllowed()) ;
		
		Log.i("box2d"," body rad" + body.getAngle()+ " body angle "+Math.toDegrees( body.getAngle()));
		physicsObj.setBody(body);
		
	
		
		listPhysicsObj.add(physicsObj);
		//Log.i("box2d", "numero ogetti inseriti in box2d "+listPhysicsObj.size());
		}
	}
		
	
	
	public void run(){
		synchronized(collisionBox2D){
		float timeStep = 1.0f / 50.0f;
		int iterations = 10;		
		
		world.step(timeStep, iterations,iterations);
		for(int i=0;i<listPhysicsObj.size();i++)
			listPhysicsObj.get(i).sync();
			
		
		}
		Contact contact=world.getContactList();
		while(contact!=null){
			GameEntity a=(GameEntity) contact.m_fixtureA.getUserData();
			GameEntity b=(GameEntity) contact.m_fixtureB.getUserData();
			a.receiveCollisionEvent(contact);
			b.receiveCollisionEvent(contact);
			contact=contact.getNext();
		}
	
	}
	


	/*public boolean isRun() {
		return run;
	}*/
	
	public void setGravity(float x, float y){
		gravity.set(-1*x, y);
		world.setGravity(gravity);		
	}


	/*public void setRun(boolean run) {
		this.run = run;
	}
	*/
	
	
	/*public void moveBody(Body body,Vec2 p){
		
        //body = callback.fixture.getBody();
		
		mouseJointDef.bodyA = groundBody;
		mouseJointDef.bodyB = body;
		mouseJointDef.target.set(p);
		mouseJointDef.maxForce = 1000f * body.getMass();
		 world.createJoint(mouseJointDef);
		//body.setAwake(true);
		
		
        
	}*/
	

}
