package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fCompraPlanHelper;
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
 * Descripcion     : Comprar un plan desde la pestaña del pedido
 * Parámetros	   : 0) Número de caso 
 * Pre-condiciones : Estar en la vista del pedido
 * SS Nov 2015
 */
public class fCompraPlan extends fCompraPlanHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Plan;
		Plan = new String[2];
		
//		String[] MensError;
//		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		/** 
		 * Generar Venta
		 */
		Plan[0] = dpString("Plan");
		callScript("Scripts.Comun.CompraPlan", Plan);

//			if  (!()){
//			//MensError[0] = "Cuenta inexistente";
//		MensError[0] = "xDefecto";
//		MensError[1] = args[3].toString();
//		MensError[2] = args[0].toString();
//		MensError[3] = getScriptName( );
//			callScript("Scripts.Comun.TerminarCasoError", MensError);
//		}
	}
}

