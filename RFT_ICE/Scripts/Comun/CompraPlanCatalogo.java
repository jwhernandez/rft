package Scripts.Comun;
import resources.Scripts.Comun.CompraPlanCatalogoHelper;
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
 * Description   : Compra un plan desde el catalogo
 * Script Name   : <b>ComprarPlanCatalogo</b>
 * @Param 0) OK/NOK 1) IN Producto a comprar 2) tramite 
 * Ej: res "PLAN CONECTADO 4 12 M" PortIn 
 * @author SS
 * 1-1740805382
 */
public class CompraPlanCatalogo extends CompraPlanCatalogoHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		String sTramite = "Venta";
		if (argu.length >= 3 ) { 
			sTramite = argu[2].toString();
		}
		
		if (!sTramite.equals("PortIn")){
			sleep(2);
			TabsPedidoVta().goTo("Sales Order-Browse Catalog View", "L3");
			sleep(2);
			TabsPedidoVta().goTo("Sales Order-Search Catalog View", "L4");}
		if (sTramite.equals("PortIn")){
			sleep(2);
			TabsPedidoPortabilidad().goTo("ICE Port Order - Browse Catalog View", "L3");
			sleep(2);
			TabsPedidoPortabilidad().goTo("ICE Port Order - Search Catalog View", "L4");}
		
		Nombre().click();
		String sPlan = "'" + argu[1].toString() + "'";
		Nombre().setText(sPlan);
	
		Busqueda().click();
		
		if (sTramite.equals("Venta")){
			AgregaraLista().performAction();
			sleep(2);
			TabsPedidoVta().goTo("Order Entry - Line Items Detail View (Sales)", "L3");}
		if (sTramite.equals("PortIn")){
			AgregaraListaPI().performAction();
			sleep(2);
			TabsPedidoPortabilidad().goTo("ICE Port Entry - Line Items Detail View (Sales)","L3");}
		
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());	
	}
}

