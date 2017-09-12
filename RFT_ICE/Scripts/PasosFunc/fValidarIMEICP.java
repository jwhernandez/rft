package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarIMEICPHelper;
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
 * Description   : Functional Test Script
 * Script Name   : <b>fValidarIMEICP</b>
 * @author Sandra
 * @since  2016/02/01
 * @Param 0)nro caso 1) NA 2) ambiente 3) true / false 4) nropaso
 */
public class fValidarIMEICP extends fValidarIMEICPHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValIMEICP;
		ValIMEICP = new String[4];
		//0) IN Terminal Cliente activo true / false 1) IMEI del activo / NA 
		// 2)OK/NOK 3) Postpago / Prepago
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

		ValIMEICP[0]=dpString("TerminalClienteActivo");
		ValIMEICP[1]=dpString("IMEI del activo");
		ValIMEICP[3]=dpString("TipoPerfilCorrecto");
		callScript("Scripts.Comun.ValidarIMEICP",ValIMEICP);
		
		if  (ValIMEICP[2].toString().equals("NOK")){
			MensError[0] = "Validar transferencia IMEI no concordó";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

