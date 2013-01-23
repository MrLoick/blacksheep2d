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
//import org.jbox2d.dynamics.joints.JointDef;

import android.os.Handler;
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

public class AdaptJBox2D implements Runnable {

	private static AdaptJBox2D collisionBox2D;
	private World world;
	private boolean run;

	public static float RATIO = 32.0f;

	private ArrayList<PhysicObjJBox2d> listPhysicsObj;
	private Handler handler;

	private Vec2 gravity;

	private AdaptJBox2D() {

		handler = new Handler();
		boolean doSleep = true;
		listPhysicsObj = new ArrayList<PhysicObjJBox2d>();
		Properties properties = GameProperties.getIstance().getPropeties();
		// properties.getProperty(name, 0);
		Float gx = new Float(properties.getProperty("GRAVITYX", "0.0f"));
		Float gy = new Float(properties.getProperty("GRAVITYY", "0.0f"));

		RATIO = new Float(properties.getProperty("RATIO", "32.0f"));
		gravity = new Vec2(gx, gy);
		world = new World(gravity, doSleep);
		setRun(false);
		Log.i("box2d", "start world of physics");

	}

	public static AdaptJBox2D getIstance() {
		if (collisionBox2D == null)
			collisionBox2D = new AdaptJBox2D();
		return collisionBox2D;

	}

	public void destroy(IPhysicObj physicsObj) {
		synchronized (collisionBox2D) {
			listPhysicsObj.remove(physicsObj);
			world.destroyBody(physicsObj.getBody());

		}
	}

	public void add(PhysicObjJBox2d physicsObj) {
		synchronized (collisionBox2D) {

			float pointX = physicsObj.getPointX() / RATIO;
			float pointY = physicsObj.getPointY() / RATIO;
			float lengthX = physicsObj.getLengthX() / RATIO;
			float lengthY = physicsObj.getLengthY() / RATIO;

			BodyDef bodyDef = new BodyDef();

			// Log.i("box2d","lengthY "+physicsObj.getLengthY()+" pointY "+physicsObj.getPointY());
			// Log.i("box2d",
			// "position x: "+(pointX-lengthX/2)*RATIO+" y: "+(pointY+lengthY/2)*RATIO);
			// Log.i("",""+(int)
			// (body.getPosition().x*CollisionBox2D.RATIO)-(gameEntity.getLengthX()/2));

			if (physicsObj.isDynamic()) {
				bodyDef.type = BodyType.DYNAMIC;
			} else
				bodyDef.type = BodyType.STATIC;

			bodyDef.angle = (float) Math.toRadians(physicsObj.getAngle());
			bodyDef.position = new Vec2(pointX + lengthX / 2, pointY + lengthY
					/ 2);
			bodyDef.active = true;

			// FixtureDef
			FixtureDef fixtureDef = new FixtureDef();

			fixtureDef.density = physicsObj.getDensity();
			fixtureDef.friction = physicsObj.getFriction();

			fixtureDef.userData = physicsObj.getGameEntity();

			Body body = world.createBody(bodyDef);

			switch (physicsObj.getShapeType()) {

			case (PhysicObjJBox2d.CIRCLE): {
				CircleShape circleShape = new CircleShape();
				circleShape.m_radius = lengthX / 2;
				fixtureDef.shape = circleShape;
				body.createFixture(fixtureDef);

				break;
			}
			case (PhysicObjJBox2d.POLYGON): {
				break;
			}

			case (PhysicObjJBox2d.BOX): {
				PolygonShape polygonShape = new PolygonShape();
				polygonShape.setAsBox(lengthX / 2, lengthY / 2);
				fixtureDef.shape = polygonShape;
				body.createFixture(fixtureDef);

				break;
			}
			case (PhysicObjJBox2d.EDGE): {

				break;
			}
			default: {
				break;
			}

			}

			body.setSleepingAllowed(physicsObj.getSleepingAllowed());

			physicsObj.setBody(body);

			listPhysicsObj.add(physicsObj);

		}
	}

	public void run() {
		synchronized (collisionBox2D) {
			// while (run) {
			float timeStep = 1.0f / 50.0f;
			int iterations = 10;

			world.step(timeStep, iterations, iterations);
			for (int i = 0; i < listPhysicsObj.size(); i++)
				listPhysicsObj.get(i).sync();

		}
		Contact contact = world.getContactList();
		while (contact != null) {
			GameEntity a = (GameEntity) contact.m_fixtureA.getUserData();
			GameEntity b = (GameEntity) contact.m_fixtureB.getUserData();
			a.receiveCollisionEvent(contact);
			b.receiveCollisionEvent(contact);
			contact = contact.getNext();
		}
		// }
		handler.postDelayed(this, 5);

	}

	public void setGravity(float x, float y) {
		gravity.set(-1 * x, y);
		world.setGravity(gravity);
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	/*
	 * public void moveBody(Body body,Vec2 p){
	 * 
	 * //body = callback.fixture.getBody();
	 * 
	 * mouseJointDef.bodyA = groundBody; mouseJointDef.bodyB = body;
	 * mouseJointDef.target.set(p); mouseJointDef.maxForce = 1000f *
	 * body.getMass(); world.createJoint(mouseJointDef); //body.setAwake(true);
	 * 
	 * 
	 * 
	 * }
	 */
	
	/*public void joint(PhysicObjJBox2d physicObjA, PhysicObjJBox2d physicObjB){
		
	}*/

}
