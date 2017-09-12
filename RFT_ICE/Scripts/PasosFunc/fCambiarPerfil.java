package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fCambiarPerfilHelper;
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
 * Script Name   : <b>fCambiarPerfil</b>
 * Description   : Cambia el perfil de la cuenta de facturacion
 * @Param 0) IN nombre del caso 1) NA o CtaCte (CtaCte asume estar en la pantalla del cliente
 * NA asume estar en el pedido e ir a la pantalla del cliente)
 *  3) IN ErrorStop (true / false) 
 * @since  2015/12/27
 * @author Sandra
 */
public class fCambiarPerfil extends fCambiarPerfilHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String[] TipoPerf;
		TipoPerf = new String[4];
		// 0) Tipo perfil 1)Tramite 2) OK/NOK 
		
		String[] Pedido;
		Pedido = new String[4];
		// 0)NroPedido 1) OK/NOK 2)Ambiente 3) Usuario (NA o frontal)
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		TipoPerf[0] = dpString("TipoPerfilCorrecto");
		TipoPerf[1] = dpString("Tramite");
		TipoPerf[3] = args[1].toString();
	
		System.out.println("TipoPerf "+ dpString("TipoPerfilCorrecto") );
		callScript("Scripts.Comun.CambiarPerfil", TipoPerf);
		
		if (TipoPerf[2].toString().equals("NOK")) {
			// MensError[0] = "Falló cambio de perfil";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		// Ir a pedido de venta nuevamente
		Pedido[0] = getNroPedido();
		Pedido[2] = args[2].toString();
		if (TipoPerf[1].equals("PortIn")) Pedido[3] = "Frontal"; // ss enero 2017 para que use perfil frontal 
		if (TipoPerf[1].equals("Venta")) Pedido[3] = "NA";
		System.out.println("Pedido "+ Pedido[0] );
		if (TipoPerf[1].equals("Venta")) callScript("Scripts.Comun.BuscarPedidoVenta",Pedido);
		if (TipoPerf[1].equals("PortIn")) callScript("Scripts.Comun.BuscarPedidoVentaPI",Pedido);
		
		if (Pedido[1].toString().equals("NOK")) {
			// MensError[0] = "Falló cambio de perfil ";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

