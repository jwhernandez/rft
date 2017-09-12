package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarNroServicio_SIM_IMEI_AntHelper;
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
 * Description   : Valida que la SIM y IMEI actuales
 * @author FF
 * Script Name   : <b>fValidarNroServicio_SIM_IMEI</b>
 * @since  2016/11/25
 */
public class fValidarNroServicio_SIM_IMEI_Ant extends fValidarNroServicio_SIM_IMEI_AntHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[5];

		String[] valida;
		valida = new String[4];
		// Parámetros: 0) OK/NOK  1) PrecioInicio 2) PrecioNeto 3) Tramite 

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		valida[1] = dpString("Tramite");
		valida[2] = dpString("NroServicio"); 
		valida[3] = dpString("SIMValidado" + i);  
		valida[4] = dpString("IMEIValidado" + i);  
		callScript("Scripts.Comun.ValidarPrecioenPedido",valida);
		
		if  ((valida[0].toString().toLowerCase().equals("NOK"))){
			MensError[0] = "Precios incorrectos";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}