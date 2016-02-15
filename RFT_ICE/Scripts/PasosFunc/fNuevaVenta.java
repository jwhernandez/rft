package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fNuevaVentaHelper;
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
 * Descripcion     : Crear un pedido de venta desde la cuenta cliente
 * @param 0) numero de caso 1)digito iterador en el DP
 * Parámetros	   : 0) nro caso (CP05) 1) Argumento (indice de variables, etc. 
 * Pre-condiciones : Estar en la vista 360 de la cuenta
 * SS Nov 2015
 */
public class fNuevaVenta extends fNuevaVentaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] NroPedido;
		NroPedido = new String[1];
		// Parámetros: 0) NroPedido
		
//		String[] MensError;
//		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		/** 
		 * Generar Venta
		 */

			callScript("Scripts.Comun.NuevaVenta", NroPedido);
			setNroPedido(NroPedido[0].toString());
			infoPaso(args[0].toString(), Tipo.DATO,"NroPedido",getNroPedido());

//			if  (!(NroPedido[1].toString())){
//			MensError[0] = "Cuenta inexistente";
			//MensError[0] = "xDefecto";
//			MensError[1] = args[3].toString();
//			MensError[2] = args[0].toString();
//			MensError[3] = getScriptName( );
//			callScript("Scripts.Comun.TerminarCasoError", MensError);
//		
	}
}

