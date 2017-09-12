package Scripts.Comun;
import resources.Scripts.Comun.AnularREDHelper;
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
 * Script Name   : <b>AnularRED</b>
 * Descripcion   : Anula el RED
 * @Param 0) OK/NOK 1) Ambiente 2)Tramite 3)Pedido
 * Ej: res  PREQA PortIn 1-1702466921
 * @since  2016/04/07
 * @author SS
 */
public class AnularRED extends AnularREDHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		String sTramite = "Venta";
		if (argu.length >= 3 ) { 
			sTramite = argu[2].toString(); // tramite
		}
		argu[0] = "NOK";
		String sPedido = argu[3].toString(); // pedido
		
		if (!argu[1].toString().equals("QA")) {
			if (sTramite.equals("PortIn")){
				Pestañas().gotoView("ICE Port Entry - Line Items Detail View Admin");
				sleep(2);

				NewQueryAdmin().performAction();
				NroPedido_PI_Admin().setText(sPedido);
				ExecuteQueryAdmin().performAction();

				EstadoPedidoPIAdmin().setText("Pendiente");
				Pestañas().gotoView("ICE Order Entry - My Orders View (Port)");
				sleep(2);

				NewQuery().performAction();
				NroPedidoPortabilidad().setText(sPedido);
				ExecuteQuery().performAction();
				
				ListaPedidosPortInt().drillDownColumn("Order Number", 0);
				sleep(2);
				
				TabsPedidoPortabilidad().goTo("ICE Port Entry - Line Items Detail View (Sales)", "L3");
			}
			
			// if (!sTramite.equals("PortIn")){}
		}
		// Procesa para ambiente QA
		else {
			if (sTramite.equals("PortIn")){
				AnularREDPI().performAction();
			} else
			{
				AnulaRED().performAction();
			}
		}
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

