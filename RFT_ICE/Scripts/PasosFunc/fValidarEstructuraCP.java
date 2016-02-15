package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarEstructuraCPHelper;
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
 * Script Name   : <b>fValidarEstructuraCP</b>
 * Description   : Functional Test Script
 * @since  2016/01/05
 * @author Sandra
 */
public class fValidarEstructuraCP extends fValidarEstructuraCPHelper
{
	public void testMain(Object[] args) 
	{
		String[] MensError;
		MensError = new String[4];

		String[] ValidaEstruc;
		ValidaEstruc = new String[2];
		//Parámetros: 0) Nombre del producto 1) OK/NOK

		callScript("Scripts.Comun.ValidarEstructuraCP", ValidaEstruc);

		if  ((ValidaEstruc[1].toString().equals("NOK"))){
			MensError[0] = "Estructura de CP invalida";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

