package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fEsperarCompletarHelper;
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
 * Script Name   : <b>fEsperarCompletar</b>
 * Description   : Functional Test Script
 * @Param 0) Nombre del caso 1)digitio con segundos a esperar 2) Ambiente
 * @since  2016/01/12
 * @author Sandra
 */
public class fEsperarCompletar extends fEsperarCompletarHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
//		String[] MensError;
//		MensError = new String[4];
		
		String[] Esperar;
		Esperar = new String[1];

		Esperar[0] = args[1].toString();
		callScript("Scripts.Comun.EsperarCompletar", Esperar);
		
//		MensError[0] = "xDefecto"; Esperar Completar falló
//		MensError[1] = args[3].toString();
//		MensError[2] = args[0].toString();
//		MensError[3] = getScriptName( );
	}
}

