package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidaContratoHelper;
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
 * Descripcion     : Valida el estado del contrato
 * Parámetros	   :  
 * Pre-condiciones : Estar en la vista 360
 * SS Nov 2015
 */
public class fValidaContrato extends fValidaContratoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] Contrato;
		Contrato = new String[2];
		// Parámetros	   : 0) Activo / Inactivo / Otros 1) OK/NOK 

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
		 * Ir a validar contrato
		 */

		Contrato[0]=dpString("Contrato");
		callScript("Scripts.Comun.ValidaContrato",Contrato);

		if  (Contrato[1].toString().equals("NOK")){
			MensError[0] = "Contrato en estado diferente al deseado";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

