package Logica;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase lógica de la calculadora simple.
 * @author https://javaranch.com/journal/200607/Plugins.html
 * @author Trinidad Crozes.
 */
public class CalculadoraSimple {
		
	//Dirección donde debo buscar los plugins
	private String pluginsDir;
	
	//Lista de plugins.
	private List<PluginFunction> plugins;

	/**
	 * Crea una lista con los plugins encontrados.
	 * @return List<PluginFunction> lista de plugins.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<PluginFunction> getPlugins() {
		this.pluginsDir = "../bin/plugins";
		
		//Inicializo la lista de plugins.
		this.plugins = new ArrayList<PluginFunction>();
		
		//Creo el directorio.
		File dir = new File(pluginsDir);
			
		//Creo el class Loader para cargar las clases en el directorio.
		ClassLoader cl = new PluginClassLoader(dir);
	
		if (dir.exists() && dir.isDirectory()) {
			//Creo una lista con los archivos del directorio.
			String [] files = dir.list();
			
			for (int i = 0; i < files.length; i++) {
				try {
					//Solo considero los que terminen en .class
					if (files[i].endsWith(".class")) {

						Class c = cl.loadClass(files[i].substring(0,files[i].indexOf(".")));
						Class[] intf = c.getInterfaces();
					
						for (int j = 0; j < intf.length; j++) {
							if (intf[j].getName().equals("Logica.PluginFunction")) {
								PluginFunction pf = (PluginFunction) c.getDeclaredConstructor().newInstance();
								this.plugins.add(pf);
								continue;
							}
						}
						
					}
					
				} 
				catch (Exception ex) {
					System.err.println("File " + files[i] + " no contiene una PluginFunction class válida.");
				}
			}
		}
		
		return this.plugins;
	}

}
