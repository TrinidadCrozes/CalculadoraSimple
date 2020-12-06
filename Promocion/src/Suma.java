import Logica.InvalidOperationException;
import Logica.PluginFunction;

public class Suma implements PluginFunction {
	
	private int x1;
	private int x2;

	@Override
	public String getPluginName() {
		
		return "Suma";
		
	}

	@Override
	public void setParameter(int param1, int param2) {
		
		this.x1 = param1;
		this.x2 = param2;
		
	}

	@Override
	public float getResult() throws InvalidOperationException {
	
		return this.x1 + this.x2;
		
	}

	@Override
	public boolean hasError() {
		
		//La suma nunca da errores, entonces siempre devuelve false.
		return false;
		
	}

}
