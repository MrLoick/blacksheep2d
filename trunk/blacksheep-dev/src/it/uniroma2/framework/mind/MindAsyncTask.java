package it.uniroma2.framework.mind;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

public class MindAsyncTask extends AsyncTask<Void, Void, Void>  {
	
	private Runnable mindRunnable;
	
	@SuppressLint("NewApi")
	public MindAsyncTask(){
		mindRunnable=MindManager.getIstance();
		
	}


	@Override
	public Void doInBackground(Void... params) {
		mindRunnable.run();
		return null;
	}
	

	

}
