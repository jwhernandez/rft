package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarCapturarAccionProdHelper;

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
 * Description   : Functional Test Script
 * Parametros: 0) OK/NOK 1) tramite 2) Atrib_Padre 3)Patron_Atrib_Actual
 * 5) Patron_atrib_Ant 
 * Script Name   : <b>fValidarCapturarAccionProd</b>
 * @since  2017/07/03
 * @author FF
 * ej res "Cambio de plan" "Servicio de Telefonia Movil" "Plan Especial.*" "Plan Especial.*" 
 */

public class fValidarCapturarAccionProd extends fValidarCapturarAccionProdHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] MensError;
		MensError = new String[4];

		String[] ValidaCAP;
		ValidaCAP = new String[5];
		// Parámetros: 0) NOK / OK 1) Nombre lista 2) volver a pedido true false 3) NumTel 
		// 4) Msj a validar o NA 5) Msj real 6) false o true si coincide
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		ValidaCAP[1] = dpString("Tramite"); 
		ValidaCAP[2] = dpString("Atrib_Padre"+i); //ubicacion del buscar producto
		ValidaCAP[3] = dpString("Atrib_Patron_Act"+i);
		ValidaCAP[4] = dpString("Atrib_Patron_Ant"+i);
		
		callScript("Scripts.Comun.ValidarCapturarAccionCantProd", ValidaCAP);

		if  ((ValidaCAP[0].toString().equals("NOK"))){
			MensError[0] = "La validacion no es la correcta";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} 
	}
}
