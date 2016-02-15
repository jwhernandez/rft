package Scripts.Comun;
import resources.Scripts.Comun.PagarREDHelper;
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
* Descripción: Genera un pago de red manual desde Siebel
* Parámetros:  
* SS Nov 2015
*/
public class PagarRED extends PagarREDHelper
{
	public void testMain(Object[] args) 
	{
		
		// 
		String sPedido = text_orderNumber().getProperty("Text").toString();
		String sMonto = siebCurrency_total().getProperty("Text").toString();

		PestanasPedido().gotoView("ICE Order Entry - Line Items Detail View Admin");
		EstadoPedidoAdmin().waitForExistence();
		
		NewQuery().performAction();
		NroPedidoAdmin(ANY,DISABLED).setText(sPedido);
		ExecuteQuery().performAction();
		sleep(2);
		
		NroREDAdmin().setText("123");
		MontoREDAdmin().setText(sMonto);
		EstadoREDAdmin().select("RED Pagado");
		EstadoPedidoAdmin().select("Pagado");
	}
}

