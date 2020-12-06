import Logica.InvalidOperationException;
import Logica.PluginFunction;

public class Division implements PluginFunction {

	private int x1;
	private int x2;
	private boolean error = false;
	
	@Override
	public String getPluginName() {
		
		return "División";
		
	}

	@Override
	public void setParameter(int param1, int param2) {
		
		this.x1 = param1;
		if(param2 == 0)
			this.error = true;
		else
			this.error = false;
		this.x2 = param2;	 	
		
	}

	@Override
	public float getResult() throws InvalidOperationException {
		
		if(error)
			throw new InvalidOperationException("No es posible realizar esta operación.");
		return (float)this.x1/(float)this.x2;
		
	}

	@Override
	public boolean hasError() {
		
		return this.error;
		
	}

}
