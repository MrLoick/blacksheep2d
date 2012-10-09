package it.uniroma2.framework.physic;

public interface IPhysic{

	public void destroy(IPhysicObj physicsObj);

	public void add(IPhysicObj physicsObj);

	public void run();

	//public boolean isRun();

	public void setGravity(float x, float y);

	//public void setRun(boolean run);

}