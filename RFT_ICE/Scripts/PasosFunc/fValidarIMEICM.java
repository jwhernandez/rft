package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarIMEICMHelper;
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
 * Description   : Validar IMEI contra el del activo (que debe figurar en el DP)
 * @author SS
 * Script Name   : <b>fValidarIMEICM</b>
 * @since  2016/04/15
 */
public class fValidarIMEICM extends fValidarIMEICMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidaIMEI;
		ValidaIMEI = new String[2];
		// Parám:  0) OK/NOK 1) IMEI del activo (Vacio para indicar que esté vacio)	

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
		ValidaIMEI[1]=dpString("IMEI del activo");
		callScript("Scripts.Comun.ValidarIMEICM", ValidaIMEI);

		if  ((ValidaIMEI[0].toString().equals("NOK"))){
			//MensError[0] = "IMEI no validó correctamente";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

