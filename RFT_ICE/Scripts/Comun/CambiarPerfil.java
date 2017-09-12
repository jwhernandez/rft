package Scripts.Comun;
import resources.Scripts.Comun.CambiarPerfilHelper;
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
 * Descripción: Cambia la cuenta de factutacion al perfil indicado en el
 * argunmento Parámetros: 0) Recibe el perfil de fact, ejemplo Postpago  1)Tramite 2)OK/NOK 
 * 3)CtaCte
 * NA o CtaCte NA baja al perfil de facturacion desde el pedido y CtaCte solo cambia perfil
 * SS Nov 2015
 * ej Postpago PortIn res CtaCte
 */
public class CambiarPerfil extends CambiarPerfilHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[2]= "NOK";
		String sTramite = "Venta"; 
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString(); // tramite
		}

		String sTipoPago = argu[0].toString();

		if (!(argu[3].toString().equals("CtaCte"))) {
			if (!sTramite.equals("PortIn")){
				LineasPedido().activateRow(0);
				LineasPedido().drillDownColumn("Billing Account", 0);

			}
			if (sTramite.equals("PortIn")){
				LineasPedidoPI().activateRow(0);
				LineasPedidoPI().drillDownColumn("Billing Account", 0);
			}
			TipoPago().select(sTipoPago);
		}
		else 
		{
			TipoPagoCtaCte().select(sTipoPago);
		}

		argu[2]= "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}	

