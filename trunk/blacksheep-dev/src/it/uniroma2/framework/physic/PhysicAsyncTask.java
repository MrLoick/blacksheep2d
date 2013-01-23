package it.uniroma2.framework.physic;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

@SuppressLint("NewApi")
public class PhysicAsyncTask extends AsyncTask<Void, Void, Void>{
	
	private Runnable physicRunnable;
	
	public PhysicAsyncTask(){
		physicRunnable=AdaptJBox2D.getIstance();
	}

	@Override
	public Void doInBackground(Void... arg0) {
		physicRunnable.run();
		return null;
	}

}
