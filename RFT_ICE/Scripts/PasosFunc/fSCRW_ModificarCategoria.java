package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSCRW_ModificarCategoriaHelper;

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

public class fSCRW_ModificarCategoria extends fSCRW_ModificarCategoriaHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] datosCategoria;
		datosCategoria = new String[4];
		// 0) Resultado = OK/NOK 1) cedula 2) catagoria 3)comentario  
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		int i = Integer.parseInt(args[1].toString());
		datosCategoria[1] = dpString("Identificacion");
		datosCategoria[2] = dpString("CategoriaCrediticia"+ i);
		datosCategoria[3] = dpString("Comentario");
	
		callScript("Scripts.Comun.SistemasExternos.SCRW_ModificarCategoria", datosCategoria);
		
		if  (datosCategoria[0].toString().equals("NOK")) {
			//MensError[0] = "Problema al ingresar a BRM";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

