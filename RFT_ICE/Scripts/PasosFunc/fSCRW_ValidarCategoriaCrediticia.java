package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSCRW_ValidarCategoriaCrediticiaHelper;

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
 * Description   : Cambia la categoria crediticia
 * Script Name   : <b>fSCRW_ModificarCategoria</b>
 * @since  2017/02/13
 * Autor FF-MB
 * Parametros: 0) Resultado = OK/NOK 1) cedula 2) categoria 3)observación 
 * Modificacion:  
 */

public class fSCRW_ValidarCategoriaCrediticia extends fSCRW_ValidarCategoriaCrediticiaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());	
		String[] ValidaCedula;
		ValidaCedula = new String[3];
		// 0) OK/NO 1) tramite

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

		ValidaCedula[1]=dpString("Cedula");
		ValidaCedula[2]=dpString("TipoCedula");
		
		callScript("Scripts.Comun.SistemasExternos.SCRW_ValidarCategoriaCrediticia", ValidaCedula);

		if  (ValidaCedula[0].toString().equals("NOK")){
			MensError[0] = "Obtener Morosidad Falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}