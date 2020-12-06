import Logica.InvalidOperationException;
import Logica.PluginFunction;

public class Potencia implements PluginFunction {

	private int x1;
	private int x2;
	
	@Override
	public String getPluginName() {
		
		return "Potencia";
		
	}

	@Override
	public void setParameter(int param1, int param2) {
		
		this.x1 = param1;
		this.x2 = param2;
		
	}

	@Override
	public float getResult() throws InvalidOperationException {
		
		return (float)Math.pow(x1, x2);
		
	}

	@Override
	public boolean hasError() {
		
		//La potencia nunca da errores, entonces siempre devuelve false.
		return false;
		
	}

}
