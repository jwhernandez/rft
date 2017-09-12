package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import Scripts.Comun.CapturarPedidoCM;
import resources.Scripts.PasosFunc.fCapturarPedidoCMHelper;
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
 * Description   : Captura el nro de pedido (venta o desconexion) segun lo indicado por argumento.
 * Guarda en variable global nropedido y en dp_datosSalida
 *  Script Name   : <b>fCapturarPedidoCM</b>
 *  @since  2017/02/14
 * @author ss
 * param1 = venta o desconexion
 * CP20_CD1_T1 Venta QA NA NA
 */
public class fCapturarPedidoCM extends fCapturarPedidoCMHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String sScriptName=getScriptName().toString();  
		String[] NroPedido;
		NroPedido = new String[3];
		// Parámetros: 0)  OK/NOK 1) venta o desconexion 2) nro pedido

		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
		String[] MensError;
		MensError = new String[4];
		
		NroPedido[1]=args[1].toString();
		callScript( new CapturarPedidoCM(),NroPedido);
		
		if  ((NroPedido[0].toString().equals("NOK"))){
			MensError[0] = "Capturar pedido CM falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		/**  Guardar el nro de pedido en variable global, dp y texto salida */

		setNroPedido(NroPedido[2].toString());
		
		setUltimoTramite("1");
		//
		int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
		DatoSalida[1]= args[0].toString().substring(0, sLong-3);
		DatoSalida[2]=args[2].toString(); // nro ambiente
		DatoSalida[3]="T"+ getUltimoTramite()+"_NroPedido"; // Tx_Nombre variable // Se modifica de nrotramite a este valor de variable
		DatoSalida[4]=NroPedido[2].toString(); // Valor de la variable
		
		callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
		
		if  ((DatoSalida[0].toString().equals("NOK"))){
			MensError[0] =  "Capturar pedido CM falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}

		infoPaso(sScriptName, Tipo.DATO,"NroPedido"+args[1].toString(),getNroPedido());
	}
}
