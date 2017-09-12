package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSalteaBolsasHelper;
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
 * Description   :Este paso saltea el applet de bolsas si es que el mismo aparece
 * @author SS
 * @since  2016/04/15
 * Script Name   : <b>fSalteaBolsas</b>
 */
public class fSalteaBolsas extends fSalteaBolsasHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] SalteaBolsas;
		SalteaBolsas = new String[1];
		// Parám: 0) NOK o OK 		

		String[] MensError;
		MensError = new String[4];

		callScript("Scripts.Comun.SalteaBolsas", SalteaBolsas);

		if  ((SalteaBolsas[0].toString().equals("NOK"))){
			//MensError[0] = "Problema en saltear la selección de bolsas";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

