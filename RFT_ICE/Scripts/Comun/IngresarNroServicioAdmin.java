package Scripts.Comun;
import resources.Scripts.Comun.IngresarNroServicioAdminHelper;
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
 * Descripción: 
 * ------------
 * 
 * Detalle de acciones: 
 * --------------------
 * Toma el numero de pedido y va a administracion, ingresa en la linea del servicio 
 * el numero vuelve al pedido. No se posiciona en ninguna línea del pedido en particular.
 *  
 * Parámetros: 0) Devuelve OK o NOK 1) Recibe tipo de servicio  Prepago / Postpago  
 * 2) numero de pedido 3) numero de servicio 4) tramite
 * ej res Postpago 1-1739837742 10895968 venta
 * 
 * SS Sep 2016
 */
public class IngresarNroServicioAdmin extends IngresarNroServicioAdminHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] ProductoAdmin;
		ProductoAdmin = new String[4];
		// Parámetros de llamado a BuscarProductoAdmin
		// 0) Nombre del producto 1) OK "Encontrado"/ NOK "No Encontrado" 
		// 2) posicion en la que se encontro el objeto 3)Tramite 

		String[] Producto;
		Producto = new String[4];

		String[] Pedido;
		Pedido = new String[2];

		boolean bError = false;

		argu[0]= "NOK";

		// -----------------------------------------------------
		// Ir a buscar el pedido en administración
		// -----------------------------------------------------
		PestanasPedido().gotoScreen("Sales Order Screen");
		PestanasPedido().gotoView("ICE Order Entry - Line Items Detail View Admin");
		EstadoPedidoAdmin().waitForExistence();
		
		NewQuery().performAction();
		NroPedidoAdmin().setText(argu[2].toString()); // Nro pedido
		ExecuteQuery().performAction();

		// --------------------------------------------------------------------------
		// Buscar Servicio de Telefonia Movil e ingresar el numero de servicio deseado
		// ---------------------------------------------------------------------------
		switch (argu[1].toString().trim()) {
		case "Prepago":
			ProductoAdmin[0]=dpString("ServicioPrepago");
			Producto[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "Postpago":
			ProductoAdmin[0]=dpString("ServicioPostpago");
			Producto[0]=dpString("ServicioPostpago");
			System.out.println("Postpago-Servicio");
			break;
		default:  
			bError = true;
			break;
		} // end del switch

		if (!bError)
		{
		ProductoAdmin[3]=argu[4].toString(); // tramite
		callScript("Scripts.Comun.BuscarProductoAdmin",ProductoAdmin);
		//String sEncontrado = ProductoAdmin[1].toString();

		int iPosicion = Integer.parseInt(ProductoAdmin[2].toString().trim());

		LineasPedidoAdmin().activateRow(iPosicion);

		// -----------------------------------------------------------------------
		// Ingresar el nro de servicio en la pantalla de administración de pedidos
		// -----------------------------------------------------------------------
		NroServicioAdmin().setText(argu[3].toString()); // nro Servicio
		Siebel().processKeyboardAccelerator("Ctrl+S");

		// -----------------------------
		// ir al pedido
		// -----------------------------
		Pedido[0]= argu[2].toString(); // nro pedido
		callScript("Scripts.Comun.BuscarPedidoVenta",Pedido);
		argu[0]= "OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());	
	}
}

