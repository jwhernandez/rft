package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fCapturarPedidoPortInHelper;
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
 * Description   :Captura el Nro de Pedido Port-in
 * @author SS
 * Ultima modificacion feb 2017 ss para framework multitramite
 * 
 * Script Name   : <b>fCapturarPedidoPortIn</b>
 */
public class fCapturarPedidoPortIn extends fCapturarPedidoPortInHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] MensError;
		MensError = new String[4];
		
		String sScriptName=getScriptName().toString(); // 22/11/2016
		
		String[] CapturaPedidoPortabilidad;
		CapturaPedidoPortabilidad = new String[3];
		// Parámetros: 0)OK/NOK 1)NroPedido 2)Ambiente
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
		CapturaPedidoPortabilidad[2]=args[2].toString();
		callScript("Scripts.Comun.CapturarPedidoPortIn", CapturaPedidoPortabilidad);

		if  (CapturaPedidoPortabilidad[0].toString().equals("NOK")){
			// MensError[0] = "No se pudo capturar pedido de portabilidad";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		setNroPedido(CapturaPedidoPortabilidad[1].toString());
		infoPaso(sScriptName, Tipo.DATO,"NroPedido",getNroPedido());

		int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj  / CPnn_CDi_Tj  ss modif feb 2017 para adaptarlo al nuevo framework mulitramite 
		DatoSalida[1]= args[0].toString().substring(0, sLong-3);
		DatoSalida[2]=args[2].toString(); // nro ambiente
		DatoSalida[3]="T"+ getUltimoTramite()+"_NroPedido"; // Nombre variable ss modif feb 2017 para adaptarlo al nuevo framework mulitramite 
		DatoSalida[4]=getNroPedido(); // Valor de la variable

		callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
		
		if  ((DatoSalida[0].toString().equals("NOK"))){
			//MensError[0] = "No se pudo capturar pedido de portabilidad";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

