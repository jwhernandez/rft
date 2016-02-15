package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidacPostREDPendPagoHelper;
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
 * Script Name   : <b>fValidacPostREDPagado</b>
 * Description   : Valida que botón Generar Cta Facturación, Liberar Número,
 * valorar todo y prime número disponible este habilitado
 * @Param 0) Numero de caso 1) argumentos que na y 2) Ambiente
 * @since  2015/12/27
 * @author Sandra
 */
public class fValidacPostREDPendPago extends fValidacPostREDPendPagoHelper
{

	public void testMain(Object[] args) 
	{
		String[] Validac;
		Validac = new String[2];
		// Parámetros	   : 0) Postpago / Prepago 1) OK/NOK 

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
		 * Validaciones post red pendiente de pago
		 */

		Validac[0]=dpString("TipoPerfilCorrecto");
		callScript("Scripts.Comun.ValidacPostREDPendPago",Validac);

		if  (Validac[1].toString().equals("NOK")){
			MensError[0] = "Validaciones Post RED pendiente de pago con error";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

