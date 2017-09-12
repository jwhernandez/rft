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
 * Parámetros	   : 0) nro caso (CP05)  1) Argumento (indice de variables, etc. 
 * 2) ambiente 3) para ante error true / false 4) Nro paso Pasoi
 * Pre-condiciones : Estar en la vista 360 de la cuenta
 * ej CP76_CD_T1 NA QA true Paso4
 * SS Nov 2015
 */
public class fNuevaVenta extends fNuevaVentaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] NroPedido;
		NroPedido = new String[2];
		// Parámetros: 0) NroPedido 1) OK/NOK

		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
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
		
		/**  Generar Venta  */
		
		callScript("Scripts.Comun.NuevaVenta", NroPedido);
		
		if  ((NroPedido[1].toString().equals("NOK"))){
			//MensError[0] = "Nueva venta falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		/**  Guardar el nro de pedido en variable global, dp y texto salida */

		setNroPedido(NroPedido[0].toString());
		
		//
		int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
		DatoSalida[1]= args[0].toString().substring(0, sLong-3);
		DatoSalida[2]=args[2].toString(); // nro ambiente
		DatoSalida[3]="T"+ getUltimoTramite()+"_NroPedido"; // Tx_Nombre variable // Se modifica de nrotramite a este valor de variable
		DatoSalida[4]=NroPedido[0].toString(); // Valor de la variable
		
		callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
		
		if  ((DatoSalida[0].toString().equals("NOK"))){
			//MensError[0] = "Nueva venta falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}

		infoPaso(sScriptName, Tipo.DATO,"NroPedido",getNroPedido());
		
	}
}

