package it.uniroma2.framework.physic;

import it.uniroma2.framework.entity.GameEntity;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public interface IPhysicObj {

	public int getLengthX();

	public int getLengthY();

	public int getPointX();

	public int getPointY();

	public void sync();

	public void addVertex(int x, int y);

	public Body getBody();

	public void setBody(Body body);

	public float getFriction();

	public void setFriction(float friction);

	public float getDensity();

	public void setDensity(float density);

	public float getAngle();

	public void setAngle(float angle);

	public Vec2 getCenter();

	public void setCenter(Vec2 center);

	public float getMass();

	public void setMass(float mass);

	public boolean isDynamic();

	public void setDynamic(boolean dynamic);

	public void setShapeType(int shapeType);

	public int getShapeType();

	public String getBodyType();

	public void setBodyType(String bodyType);

	public void setSleepingAllowed(boolean sleepingAllowed);

	public boolean getSleepingAllowed();

	public GameEntity getGameEntity();

	public void movePhysicsObj(int px, int py);

}