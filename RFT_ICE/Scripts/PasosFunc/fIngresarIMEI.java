package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fIngresarIMEIHelper;
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
 * Descripcion     :Ingresar un IMEI
 * @param 0) numero de caso 1)digito iterador en el DP
 * Pre-condiciones : Estar en la vista del pedido. No hace falta estar en el producto.
 * Toma del DP el servicio a buscar de parametria
 * SS Nov 2015
 */
public class fIngresarIMEI extends fIngresarIMEIHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] MensError;
		MensError = new String[4];

		String[] argsIMEI;
		argsIMEI = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		argsIMEI[0] = dpString("TipoPerfilCorrecto");
		argsIMEI[1] = dpString("IMEI_Lugar" +i ); // "Servicio"
		argsIMEI[2] = dpString("IMEI" + i); // correcto o incorrecto
		callScript("Scripts.Comun.IngresarIMEI",argsIMEI);
		
		if  ((argsIMEI[3].toString().equals("No Encontrado"))){
			MensError[0] = "IMEI Ingresado incorrectamente";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

