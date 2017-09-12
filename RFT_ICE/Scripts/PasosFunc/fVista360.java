package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fVista360Helper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Descripcion     : Va a la vista de la cuenta
 * Parámetros	   : 0) Nombre del caso 1)Parametros para el paso 2) ambiente 3)Error para ejecucion true o falsa
 * Pre-condiciones : Estar logoneado 
 * ej: CP46 NA PREQA true 20
 * SS Nov 2015
 */
public class fVista360 extends fVista360Helper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Cuenta;
		Cuenta = new String[3];
		// 0) OK/NOK 1) Identificacion 2) Clase cuenta:

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		/** 
		 * Ir a vista 360 
		 */
		Cuenta[1]=dpString("Identificacion");
		Cuenta[2]=dpString("ClaseCuenta");
		callScript("Scripts.Comun.Vista360", Cuenta);

		if  (Cuenta[0].toString().equals("NOK")){
			// MensError[0] = "Cuenta inexistente o no se logró llegar a la vista";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

