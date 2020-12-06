package Logica;

/**
 * Interface del plugin.
 */
public interface PluginFunction {

	/**
	 * Retorna el nombre del plugin.
	 * @return String nombre del plugin.
	 */
	public String getPluginName();
	
	/**
	 * Setea los parámetros del plugin.
	 * @param param1 int Primer parámetro del plugin.
	 * @param param2 int Segundo parámetro del plugin.
	 */
	public void setParameter(int param1, int param2);
	
	/**
	 * Retorna el resultado del plugin.
	 * @return float Resultado.
	 * @throws InvalidOperationException excepción lanzada en caso de que haya error.
	 */
	public float getResult() throws InvalidOperationException;
	
	/**
	 * Devuelve si ocurrió un error o no durante la ejecución del plugin.
	 * @return true si ocurrió un error, false en caso contrario.
	 */
	public boolean hasError();
	
}
