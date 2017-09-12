package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fCompraPlanCatalogoHelper;
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
 * Description   : Comprar un plan desde la pestaña de catálogo
 * Parámetros	   : 0) Número de caso 
 * Pre-condiciones : Estar en la vista del pedido
 * SS Nov 2015 */
public class fCompraPlanCatalogo extends fCompraPlanCatalogoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Plan;
		Plan = new String[3];
		//  0) OK/NOK 1) IN Producto a comprar 2) tramite
		
		String[] MensError;
		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		/** 
		 * Comprar un plan
		 */
		Plan[1] = dpString("Plan");
		Plan[2] = dpString("Tramite");
		callScript("Scripts.Comun.CompraPlanCatalogo", Plan);

		if  ((Plan[0].equals("NOK"))){
			//MensError[0] = "Plan no se encontró en catálogo";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

