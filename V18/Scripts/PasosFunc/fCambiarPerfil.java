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
 * @Param 
 * @since  2015/12/27
 * @author Sandra
 */
public class fCambiarPerfil extends fCambiarPerfilHelper
{
	public void testMain(Object[] args) 
	{

//		String[] MensError;
//		MensError = new String[4];
		
		String[] TipoPerf;
		TipoPerf = new String[1];
		
		String[] Pedido;
		Pedido = new String[1];
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		TipoPerf[0] = dpString("TipoPerfilCorrecto");
		System.out.println("TipoPerf "+ dpString("TipoPerfilCorrecto") );
		callScript("Scripts.Comun.CambiarPerfil", TipoPerf);
		
		// Ir a pedido de venta nuevamente
		Pedido[0] = getNroPedido();
		System.out.println("Pedido "+ Pedido[0] );
		callScript("Scripts.Comun.BuscarPedidoVenta",Pedido);
		
//	
//		MensError[0] = "xDefecto";
//		MensError[1] = args[3].toString();
//		MensError[2] = args[0].toString();
//		MensError[3] = getScriptName( );
//		callScript("Scripts.Comun.TerminarCasoError", MensError);
	}
}

