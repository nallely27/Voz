package com.example.voice;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityGrabar extends ActionBarActivity 
{
	private static final String LOG_TAG ="Grabadora";
	private MediaRecorder mediaRecorder;
	private MediaPlayer mediaPlayer;
	private static String fichero=Environment.getExternalStorageDirectory().getAbsolutePath() + "/rec/";
    private int numeroCancion=0; 
    private String strNumeroCancion="";
	private Button bGrabar, bDetener, bReproducir, bDetenerReproduccion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_grabar);
		
		Bundle bundle = getIntent().getExtras();
		strNumeroCancion = bundle.getString("nombreCancion");
		numeroCancion = Integer.parseInt(strNumeroCancion);
		
		bGrabar = (Button) findViewById(R.id.bGrabar) ;
		bDetener = (Button) findViewById(R.id.bDetener) ;
		bReproducir = (Button) findViewById(R.id.bReproducir);
		bDetenerReproduccion = (Button) findViewById(R.id.bDetenerReproduccion);
		
		bDetener.setEnabled(false);
		bReproducir.setEnabled(false);
		bDetenerReproduccion.setEnabled(false);
	}
	
	public void grabar(View view) 
	{
		bGrabar.setEnabled(false);
		bDetener.setEnabled(true);
		bReproducir.setEnabled(false);
		
		numeroCancion+=1;
		
		mediaRecorder = new MediaRecorder();
	    mediaRecorder.setOutputFile(fichero+"grabacion"+numeroCancion+".3gp");
	    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); 
	    try 
	    {
	        mediaRecorder.prepare();
	    } catch (IOException e) {
	        Log.e(LOG_TAG, "Fallo en grabación");
	    }
	    mediaRecorder.start();
	}
	
	public void detenerGrabacion(View view) {
		bGrabar.setEnabled(true);
		bDetener.setEnabled(false);
		bReproducir.setEnabled(true);
		
		mediaRecorder.stop();
		mediaRecorder.release();
	}
	
	public void reproducir(View view) {
		bGrabar.setEnabled(false);
		bDetenerReproduccion.setEnabled(true);
		bReproducir.setEnabled(false);
		
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(fichero+"grabacion"+numeroCancion+".3gp");
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			Log.e (LOG_TAG,"Fal1o la reproduccion");
		}
	}
	
	public void detener(View view) {
		try {
			bDetenerReproduccion.setEnabled(false);
			bReproducir.setEnabled(true);
			bGrabar.setEnabled(true);
			
			mediaPlayer.stop();
		} catch (Exception e) {
			Log.e (LOG_TAG,"Fal1o la reproduccion");
		}
	}
}
