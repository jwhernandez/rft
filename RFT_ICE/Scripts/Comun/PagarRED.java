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
* Script Name   : <b>PagarRED</b>
* Parámetros:  0) OK / NOK
* @since  2016/02/16
* @author SSASTRE
*/
public class PagarRED extends PagarREDHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[1]="NOK";
		String sPedido = NroPedido().getProperty("Text").toString();
		String sMonto = Total().getProperty("Text").toString();

		PestañasPedido().gotoView("ICE Order Entry - Line Items Detail View Admin");
		sleep(3);
		
		NewQuery().performAction();
		NroPedidoAdmin(ANY,DISABLED).setText(sPedido);
		ExecuteQuery().performAction();

		EstadoRED().select("Pagado");
		TotalAdmin().setText(sMonto);
		NroRED().setText("123");
		EstadoPedido().select("RED Pagado");
		
		argu[1]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

