package Scripts.Comun;
import resources.Scripts.Comun.ObtenerMorosidadHelper;
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
 * Description   : Obtiene morosidad de un pedido de CP o de Venta o de port-in
 * Script Name   : <b>ObtenerMorosidad</b>
 * Precondiciones: Estar en la vista del pedido en cualquier applet
 * Parametros 0) OUT OK/NOK 1)tramite
 * ej res
 * @author Sandra
 * @since  2016/01/19
 */
public class ObtenerMorosidad extends ObtenerMorosidadHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sTramite = "Venta";
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString();
		}	
		argu[0]="NOK";

		if (!sTramite.equals("PortIn")){
			TabsPedidoVta().goTo("Sales Order-Browse Catalog View","L3");
			ConsultaMorosidadVta().performAction();
			TabsPedidoVta().goTo("Order Entry - Line Items Detail View (Sales)","L3");
		}
		if (sTramite.equals("PortIn")){
			TabsPedidoPortabilidad().goTo("ICE Port Order - Browse Catalog View","L3");
			ConsultaMorosidadPI().performAction();
			TabsPedidoPortabilidad().goTo("ICE Port Entry - Line Items Detail View (Sales)",  "L3");
		}

		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

