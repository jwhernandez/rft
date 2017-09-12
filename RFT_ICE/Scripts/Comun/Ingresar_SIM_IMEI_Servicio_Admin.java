package Scripts.Comun;
import Scripts.Comun.BuscarPedidoVenta;
import resources.Scripts.Comun.Ingresar_SIM_IMEI_Servicio_AdminHelper;
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
 * Descripción: Ingresar vía Admin los datos del pedido
 * solo implementada la versión terminal cliente
 *  
 * Parámetros: 0) Devuelve OK o NOK 1) servicio (Prepago / Postpago) 2) nroPedido  3)Recibe tipo terminal
 * (Cliente / ICE) 4) servicio 5) imei 6) IMSI 7) SIM 8)tramite 
 * ej res Postpago  1-1723726007 Cliente 10101010 12486004543101 712012005129687 89506010911111296873 venta
 * Solo se implementa la opción de terminal cliente
 * 
 * Script Name   : <b>Ingresar_SIM_IMEI_Servicio_Admin</b>
 * @since  2016/12/12
 * @author SS
 */
public class Ingresar_SIM_IMEI_Servicio_Admin extends Ingresar_SIM_IMEI_Servicio_AdminHelper
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
		sleep(5);
		PestanasPedido().gotoView("ICE Order Entry - Line Items Detail View Admin");
		sleep(5);
		//NewQueryAnt().performAction(); 
		NewQuery().performAction(); //ss 13_12_2016 cambio nombre applet
		
		sleep(5);

		//NroPedidoAdmin_Ant().setText(argu[2].toString()); // Nro pedido
		NroPedidoAdmin().setText(argu[2].toString()); //ss 13_12_2016 cambio nombre applet

		//ExecuteQuery_Ant().performAction();
		Menu().select(atPath("ExecuteQuery"));  //ss 13_12_2016 cambio nombre applet

		bError=true;
		switch (argu[3].toString()) {
		case "Cliente":
			bError = false;
			break;
		default:
			System.out.println("Solo opción terminal cliente implementada");
			break;
		}
		if (!bError)
		{
			switch (argu[8].toString()) {
			case "PortIn":
				bError = true;
				System.out.println("Opcion Port-In no implementada");
				break;
			default:
				bError = false;
				break;
			}	
		}
		if (!bError)
		{
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
				System.out.println("Servicio no reconocido");
				bError = true;
				break;
			} // end del switch

			if (!bError)
			{
				System.out.println("Busca producto");
				ProductoAdmin[3]=argu[8].toString(); // tramite
				callScript("Scripts.Comun.BuscarProductoAdmin",ProductoAdmin);
				//String sEncontrado = ProductoAdmin[1].toString();

				int iPosicion = Integer.parseInt(ProductoAdmin[2].toString().trim());

				LineasPedidoAdmin().activateRow(iPosicion);

				// -----------------------------------------------------------------------
				// Ingresar el nro de servicio en la SIM().text_iceTerminalDelCliente().IMEI().IMSI().pantalla de administración de pedidos
				// -----------------------------------------------------------------------
				NroServicioAdmin().setText(argu[4].toString()); // nro Servicio
				IMEI().setText(argu[5].toString()); 
				IMSI().setText(argu[6].toString()); 
				SIM().setText(argu[7].toString()); 


				Siebel().processKeyboardAccelerator("Ctrl+S");

				// -----------------------------
				// ir al pedido
				// -----------------------------
				Pedido[0]= argu[2].toString(); // nro pedido
				callScript(new BuscarPedidoVenta(), Pedido);
				//callScript("Scripts.Comun.BuscarPedidoVenta",Pedido);
				argu[0]= "OK";
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());	
	}
}

