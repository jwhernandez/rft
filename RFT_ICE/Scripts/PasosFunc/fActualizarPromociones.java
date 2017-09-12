package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fActualizarPromocionesHelper;
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
 * Descripcion     : Crear un pedido de CP desde la cuenta cliente
 * Script Name   : <b>fNuevoCambioPlan</b>
 * @param 0) numero de caso 1)digito iterador en el DP
 * Parámetros	   : 0) nro caso (CP05) 1) NA 2) Ambiente 3) true/False 4) Nro paso
 * Pre-condiciones : Estar en la vista 360 de la cuenta
 * SS Nov 2015
 */
public class fActualizarPromociones extends fActualizarPromocionesHelper
{

	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] SelInforme;
		SelInforme = new String[2];
		//Parámetros: 0) NOK / OK 1) Opción a imprimir

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		callScript("Scripts.Comun.ActualizarPromociones", SelInforme);

		if  ((SelInforme[0].toString().equals("NOK"))){
			//MensError[0] = "Asignación de Lista Especial tuvo error";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}
