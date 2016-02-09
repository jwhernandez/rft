package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarPerfilFactCuentaHelper;
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
 * Descripcion     : Valida el perfil de la cuenta cliente
 * Parámetros	   :  
 * Pre-condiciones : Estar en la vista 360 de la cuenta
 * SS Nov 2015
 */
public class fValidarPerfilFactCuenta extends fValidarPerfilFactCuentaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] PerfilCtaCliente;
		PerfilCtaCliente = new String[2];

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

		PerfilCtaCliente[0]=dpString("TipoPerfilCtaCliente");
		callScript("Scripts.Comun.ValidarPerfilFactCuenta",PerfilCtaCliente);

		if  (PerfilCtaCliente[1].toString().equals("NOK")){
			MensError[0] = "Perfil Fact Cta Cliente no tiene tipo deseado";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

