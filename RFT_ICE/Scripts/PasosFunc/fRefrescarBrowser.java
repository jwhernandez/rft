package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fRefrescarBrowserHelper;
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
 * Description   : Presiona la tecla F5 en IE
 * @author SS
 * Script Name   : <b>fRefrescarBrowser</b>
 * @since  2016/07/11
 */
public class fRefrescarBrowser extends fRefrescarBrowserHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		//String[] MensError;
		//MensError = new String[4];

		String[] Refrescar;
		Refrescar = new String[1];
		//No tiene parametros
		callScript("Scripts.Comun.RefrescarBrowser", Refrescar);		
	}
}

