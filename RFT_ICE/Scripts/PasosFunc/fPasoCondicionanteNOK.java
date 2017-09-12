package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPasoCondicionanteNOKHelper;
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
 * Description   : Para pruebas de pasos condicionantes - Paso que resulta en NOK
 * @author Ss
 * Script Name   : <b>fPasoCondicionanteNOK</b>
 * @since  2017/05/16
 */
public class fPasoCondicionanteNOK extends fPasoCondicionanteNOKHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		String[] MensError;
		MensError = new String[4];
		MensError[0] = "Paso para pruebas condicionantes que da NOK";
		//MensError[0] = "xDefecto";
		MensError[1] = args[3].toString();
		MensError[2] = args[0].toString();
		MensError[3] = getScriptName( );
		callScript("Scripts.Comun.TerminarCasoError", MensError);
	}
}

