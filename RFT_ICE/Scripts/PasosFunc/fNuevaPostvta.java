package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fNuevaPostvtaHelper;
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
 * Descripcion     : Crear un pedido de postventa desde la cuenta cliente
 * @param 0) numero de caso 1)digito iterador en el DP
 * Parámetros	   : 0) nro caso (CP05)  1) Argumento (indice de variables, etc. 
 * 2) ambiente 3) para ante error true / false 4) Nro paso Pasoi
 * Pre-condiciones : Estar en la vista 360 de la cuenta
 * ej CP76_CD_T1 NA QA true Paso4
 * @since  2016/10/24
 * @author SS
 */
public class fNuevaPostvta extends fNuevaPostvtaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] NroPedido;
		NroPedido = new String[6];
		// Parámetros: 0) OK/NOK 1) subtramite 2) motivo 3) Tipo Servicio 
		// 4) NroPedido (dato de salida) 5)ValidarMensaje (NA o DPMnnn) 

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

		int i = Integer.parseInt(args[1].toString());
		NroPedido[1] = dpString("Subtramite"+i);
		NroPedido[2] = dpString("Motivo"+i);
		NroPedido[3] = dpString("Servicio");
		NroPedido[5] = dpString("Msj"+i);
		callScript("Scripts.Comun.NuevaPostvta", NroPedido);

		if  ((NroPedido[0].toString().equals("NOK"))){
			MensError[0] = "Nueva venta falló";
			// MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}

		/**  Guardar el nro de pedido en variable global, dp y texto salida */

		if (NroPedido[5].equals("NA"))
		{
			setNroPedido(NroPedido[4].toString());

			infoPaso(sScriptName, Tipo.DATO,"NroPedido",getNroPedido());

			int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
			DatoSalida[1]= args[0].toString().substring(0, sLong-3);
			DatoSalida[2]=args[2].toString(); // nro ambiente
			DatoSalida[3]="T"+ getUltimoTramite()+"_NroPedido"; // Tx_Nombre variable // Se modifica de nrotramite a este valor de variable
			DatoSalida[4]=NroPedido[4].toString(); // Valor de la variable

			callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
			if  ((DatoSalida[0].toString().equals("NOK")))
			{
				MensError[0] = "Grabar NroPedido Falló";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
		}

	}
}

