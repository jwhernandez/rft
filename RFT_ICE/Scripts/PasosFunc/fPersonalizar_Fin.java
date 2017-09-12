package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPersonalizar_FinHelper;
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
* Descripción: Sale de la sesión de catálogo luego de presionar valorar
* Script Name   : <b>fPersonalizar_Fin</b> 
* @since   2017/03/30
* @author SS
* @Param1 NA
* ej:  CD26_CD1_T1 NA QA NA NA 

*/
public class fPersonalizar_Fin extends fPersonalizar_FinHelper
{

	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Personalizacion;
		Personalizacion = new String[1];
		// 0) OK/NOK  

		String[] MensError;
		MensError = new String[4];
		
		callScript("Scripts.Comun.Personalizar_Fin", Personalizacion);

		if  (Personalizacion[0].toString().equals("NOK")){
			MensError[0] = "Salir de catálogo falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

