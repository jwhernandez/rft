package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBackCMHelper;
import Scripts.Comun.BackCM;
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
 * Description   : Presiona Back en la task de nombre pasado como parametro
 * @author SS
 * Script Name   : <b>fBackCM</b>
 * @since  2017/02/10
 * Param1 = cuenta, plan, paquetes, imei, contrato, terminal,facilidades -- solo implementado IMEI
 */
public class fBackCM extends fBackCMHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Back;
		Back = new String[2];
		// 0)OK/NOK  1) Pantalla

		String[] MensError;
		MensError = new String[4];
		
		Back[1]=args[1].toString();
		callScript(new BackCM(),Back);

		if  (Back[0].toString().equals("NOK")){
			MensError[0] = "Paso Back falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

