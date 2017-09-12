package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBorrarSIMCMHelper;
import Scripts.Comun.BorrarSIMCM;
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
 * Description   : Borra la SIM en CM
 * @author ss
 * Script Name   : <b>fBorrarSIMCM</b>
 * @since  2017/02/10
 * Param 1 = NA
 */
public class fBorrarSIMCM extends fBorrarSIMCMHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] BorrarSIM;
		BorrarSIM = new String[1];
		// 0)OK/NOK  

		String[] MensError;
		MensError = new String[4];

		callScript(new BorrarSIMCM(),BorrarSIM);

		if  (BorrarSIM[0].toString().equals("NOK")){
			MensError[0] = "Paso BorrarSIM falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

