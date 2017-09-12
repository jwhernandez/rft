package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarObservacionHelper;
import Scripts.Comun.SistemasExternos.DB_Validar;

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
 * Description   : Valida las observaciones de un pedido
 * Script Name   : <b>fValidarObservacion</b>
 * @author VC
 * Parametros 0) Nombre Caso 1) Indicador de conjunto en el dp 2) AMbiente 3)Si No si el error para el paso
 * Ej: CP12 1 PREQA false
 * Requeire estar en la vista de detalles del pedido
 * @since  2017/03/27
 */
public class fValidarObservacion extends fValidarObservacionHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] Observaciones;
		Observaciones = new String[2];
		
		// 0) Resultado = OK/NOK 1) Observacion
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		Observaciones[1] = dpString("ObservacionesPedido");
		//System.out.println("Observaciones pedido: " + Observaciones[1] );
		
		//logInfo("Observaciones pedido: " + Observaciones[1] );
		
		callScript("Scripts.Comun.ValidarObservacion", Observaciones);
		
	
		if  (Observaciones[0].toString().equals("NOK")) {
			//MensError[0] = "Problema al validar las observaciones";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

