package Scripts.Comun;
import resources.Scripts.Comun.IngresarServicioAdminHelper;
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
 * Descripción: Permite ingresar ingresar por adm de pedido el numero de servicio y liberarlo en la pantalla
 * de pedidos estandard. 
 * Toma el numero de pedido y va a administracion, ingresa en la linea del servicio el numero 
 * vuelve al pedido y selecciona liberar numero y acepta el mensaje de numero liberado sin controlar 
 * Parámetros: 0) Recibe tipo de servicio  Prepago / Postpago  1) numero de pedido
 * 2) numero de servicio 3)Devuelve OK o NOK
 * SS Nov 2015
 */
public class IngresarServicioAdmin extends IngresarServicioAdminHelper
{
	public void testMain(Object[] argu) 
	{
		String[] ProductoAdmin;
		ProductoAdmin = new String[3];

		String[] Producto;
		Producto = new String[4];

		String[] Mens;
		Mens = new String[2];

		String[] Pedido;
		Pedido = new String[1];

		String[] ValidarMsj;
		ValidarMsj = new String[4];


		argu[3]= "NOK";

		// Ir a buscar el pedido en administración
		PestanasPedido().gotoScreen("Sales Order Screen");
		PestanasPedido().gotoView("ICE Order Entry - Line Items Detail View Admin");
		EstadoPedidoAdmin().waitForExistence();
		NewQuery().performAction();
		NroPedidoAdmin().setText(argu[1].toString());
		ExecuteQuery().performAction();

		// Buscar Servicio de Telefonia Movil e ingresar el numero de servicio deseado
		System.out.println("Primer Argumento recibido en Ingresar Servicio Admin: " + argu[0].toString());
		switch (argu[0].toString().trim()) {
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
			System.out.println("Stop");
			break;
		} // end del switch

		System.out.println("Primer Argumento enviado a producto en ingresarServicioadmin: " + ProductoAdmin[0].toString());
		callScript("Scripts.Comun.BuscarProductoAdmin",ProductoAdmin);
		String sEncontrado = ProductoAdmin[1].toString();
		System.out.println("Tercer Argumento Recibido de producto en ingresarServicioadmin: " + ProductoAdmin[2].toString());

		int iPosicion = Integer.parseInt(ProductoAdmin[2].toString().trim());

		Mens [0] = "Servicio Posicion" + ProductoAdmin[2].toString() + "flag " + sEncontrado; 
		callScript("Scripts.Comun.Informar", Mens);

		LineasPedidoAdmin().activateRow(iPosicion);

		NroServicioAdmin().setText(argu[2].toString());

		Siebel().processKeyboardAccelerator("Ctrl+S");

		// ir al pedido
		Pedido[0]= argu[1].toString();
		callScript("Scripts.Comun.BuscarPedidoVenta",Pedido);

		// en la pestaña de pedido estandar ingresar seleccionar el numero de servicio y liberar
		callScript("Scripts.Comun.RefrescarGuardarPedido");

		//Ir a la linea del servicio
		callScript("Scripts.Comun.BuscarProducto",Producto);
		sEncontrado = Producto[1].toString();

		Mens [0] = "Servicio Posicion" + Producto[2].toString() + "flag " + sEncontrado; 
		callScript("Scripts.Comun.Informar", Mens);

		LiberarNumero().performAction();

		// Llamar para chequear que sea el mensaje correcto caso contrario advertir error

		ValidarMsj[0]="DPM0020";
		ValidarMsj[3] = "HTML";
		callScript("Scripts.Comun.ValidarMensaje",ValidarMsj);
		if (ValidarMsj[2].toString().equals("false")) {
			AceptarMensaje().click();
			argu[3]= "NOK";
		} else 
			argu[3]= "OK";}
}

