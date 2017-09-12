package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fNuevoPedidoPortInSimpleHelper;
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
 * Description   : Permite crear un nuevo pedido de port-in. No ingresa el nro de servicio ni nigun dato.
 * Si captura el nro de pedido generado
 * Se asume que el usuario es el frontal para la vista
 * @author SS
 * Script Name   : <b>fNuevoPedidoPortInSimple</b>
 * @since  2017/03/07
 */
public class fNuevoPedidoPortInSimple extends fNuevoPedidoPortInSimpleHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		String[] NuevoPedidoPortabilidad;
		NuevoPedidoPortabilidad = new String[2];
		// Parámetros: 0)OK/NOK 1)NroPedido

		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable

		String[] MensError;
		MensError = new String[4];

		callScript("Scripts.Comun.NuevoPedidoPortInSimple", NuevoPedidoPortabilidad);

		if  (NuevoPedidoPortabilidad[0].toString().equals("NOK")){
			MensError[0] = "No se pudo generar pedido de portabilidad";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}

		setNroPedido(NuevoPedidoPortabilidad[1].toString());
		infoPaso(getScriptName().toString() , Tipo.DATO,"NroPedido",getNroPedido());

		int sLong = args[0].toString().length(); 
		DatoSalida[1]= args[0].toString().substring(0, sLong-3);
		DatoSalida[2]=args[2].toString(); // nro ambiente
		DatoSalida[3]="T"+ getUltimoTramite()+"_NroPedido"; 
		DatoSalida[4]=NuevoPedidoPortabilidad[1].toString(); // Valor de la variable

		callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);

		if  ((DatoSalida[0].toString().equals("NOK"))){
			//MensError[0] = "No se pudo generar pedido de portabilidad";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

