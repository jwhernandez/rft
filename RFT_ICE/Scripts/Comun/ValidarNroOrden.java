package Scripts.Comun;
import resources.Scripts.Comun.ValidarNroOrdenHelper;
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
 * Description   : Validar en el pedido el campo nro de orden
 * Con la opción Valor el contenido y con las opciones true o false si está habilitado o inhabilitado
 * 
 * @author SS
 * Script Name   : <b>ValidarNroOrden</b>
 * @since  2017/03/23
 * Param 0) OK/NOK 1) Accion = Valor o False o True 2) Valor o NA 3) Tramite
 * Ej res False NA PortIn
 */
public class ValidarNroOrden extends ValidarNroOrdenHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		IFtVerificationPoint NropedidoVP = null;
		String sTramite;
		
		String sAccion = argu[1].toString().toLowerCase();
		String sNroDeseado = argu[2].toString();
		sTramite = argu[3].toString();
		boolean bHabilitado = true;
		if (sAccion.equals("true")) bHabilitado = true;
		if (sAccion.equals("false")) bHabilitado = false;
		
		
		switch (sAccion) {
		case "valor":
			// Se chequeará el valor del campo
			if (sTramite.equals("PortIn"))
				NropedidoVP = vpManual("NroPedidoPI", sNroDeseado, NroPedido_PI().getProperty("ActiveItem"));

			if (!sTramite.equals("PortIn"))
				System.out.println("Opcion no implementada");
				//NropedidoVP = vpManual("NroPedido", sTipoDeseado, NroPedido().getProperty("ActiveItem"));
			
			if (NropedidoVP.performTest()) {
				argu[0] = "OK";
			}
			break;
		case "true":
		case "false":
			// Se chequeará que el campo esté habilitado o inhabilitado
			
			if (sTramite.equals("PortIn"))
				NropedidoVP = vpManual("NroPedidoPI", bHabilitado, NroPedido_PI().getProperty("IsEnabled"));

			if (!sTramite.equals("PortIn"))
				System.out.println("Opcion no implementada");
				//pedidoVP = vpManual("NroPedido", bHabilitado, NroPedido().getProperty("IsEnabled"));
			
			if (NropedidoVP.performTest()) {
				argu[0] = "OK";
			}

			break;
		default:
			System.out.println("Opcion no implementada");
			break;
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

