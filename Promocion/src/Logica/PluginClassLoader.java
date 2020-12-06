package Logica;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Clase que se encarga de cargar los plugins.
 * @author https://javaranch.com/journal/200607/Plugins.html
 */
public class PluginClassLoader extends ClassLoader {
    //Directorio del cual van a ser cargados los plugins.
    File directory;

    /**
     * Constructor del PluginClassLoader.
     * @param dir File que se asigna al directorio.
     */
    public PluginClassLoader (File dir) {
		directory = dir;
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
	public Class loadClass (String name) throws ClassNotFoundException { 
    	return loadClass(name, true); 
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
	public Class loadClass (String classname, boolean resolve) throws ClassNotFoundException {
		try {
			//Busca la clase y la carga.
			Class c = findLoadedClass(classname);
			
			if (c == null) {
				try {
					//Se cargan las superclases.
					c = findSystemClass(classname); 
				}
				catch (Exception ex) {}
			}
			
			//Si todavía no fue encontrada la clase:
			if (c == null) {
				//Se crea el nombre de la clase.
				String filename = classname.replace('.',File.separatorChar)+".class";
				
				//S crea un nuevo File.
				File f = new File(directory, filename);
					
				int length = (int) f.length();
				byte[] classbytes = new byte[length];
				DataInputStream in = new DataInputStream(new FileInputStream(f));
				in.readFully(classbytes);
				in.close();
				
				//Convierte los bytes de la clase en una clase.
				c = defineClass(classname, classbytes, 0, length);
			}
			
			if (resolve) {
				resolveClass(c);
			}
			
			//Se retorna la clase que fue cargada.
			return c;
		}
		//Si sucede algún problema, se lanza una excepción.
		catch (Exception ex) { 
			throw new ClassNotFoundException(ex.toString()); 
		}
    }
    
}