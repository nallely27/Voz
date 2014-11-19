package com.example.voice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

public class MainActivity extends ActionBarActivity 
{
	private static String directorio=Environment.getExternalStorageDirectory().getAbsolutePath() + "/rec/";
	private ListView lvGrabaciones;
	private List <String>  listaGrabaciones;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lvGrabaciones = (ListView) findViewById(R.id.lvGrabaciones);
		listaGrabaciones= new ArrayList<String>();
		
		consultarLista();
	}
	
	public void clicIrAGrabar(View v)
	{
		int numeroCancion = listaGrabaciones.size();
		Intent activityGrabar = new Intent(this, ActivityGrabar.class);
		activityGrabar.putExtra("nombreCancion", numeroCancion+"");
	    startActivity(activityGrabar);
	}
	
	private void consultarLista() 
	{
		File archivo = new File(directorio);
		if(archivo.exists())
		{
			Filtro filtro = new Filtro(".3gp");
			
			if(archivo.listFiles(filtro).length > 0)
			{
				for(File cancion:archivo.listFiles(filtro))
				{
					listaGrabaciones.add(cancion.getName());
				}
				
				ArrayAdapter <String> listavideos = new ArrayAdapter<String>(this, R.layout.text_list_view, listaGrabaciones);
				lvGrabaciones.setAdapter(listavideos);
			}
		}
	}
}