package Scripts.Comun;
import resources.Scripts.Comun.TerminalClienteHelper;
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
 * Descripción: Elimina Terminales promocionales y coloca terminal cliente en Y
 * Parámetros: 0)Prepago/Postpago 1) Encontrado / No Encontrado
 * SS Nov 2015
 */
public class TerminalCliente extends TerminalClienteHelper 
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		String[] Producto;
		Producto = new String[4];
		String sEncontrado = "No Encontrado";

		switch (argu[0].toString()) {
		case "Prepago":
			Producto[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "Postpago":
			Producto[0]=dpString("ServicioPostpago");
			System.out.println("Postpago-Servicio");
			break;
		default:  
			System.out.println("Stop");
			break;
		} // end del switch

		// busca el servicio y coloca terminal cliente en Y

		callScript("Scripts.Comun.BuscarProducto",Producto);
		sEncontrado = Producto[1].toString();
		if (sEncontrado == "Encontrado"){
			int iPosicion = Integer.parseInt (Producto[2]);
			LineasPedido().activateRow(iPosicion);
			System.out.println("Indica si es Cliente " + EsCliente().getProperties());
			System.out.println("Indica si es Cliente " + EsCliente().getProperty("IsChecked"));
			EsCliente().setOn();

			// contrae las lineas del pedido pues caso contrario puede no verse terminales promocionales
			// y al no verse no se puede eliminar.

			int iTotal1 = (int) LineasPedido().getProperty("RowsCount") ;
			if (LineasPedido().isRowExpanded(iPosicion)) {
				LineasPedido().clickHier(); 
				int iTotal2 = (int) LineasPedido().getProperty("RowsCount") ;
				if (iTotal1 < iTotal2) {
					LineasPedido().clickHier(); 
				}
			}

			Producto[0]=dpString("Terminales");
			System.out.println("Producto a buscar: " + Producto[0] );

			callScript("Scripts.Comun.BuscarProducto",Producto);
			sEncontrado = Producto[1].toString();
			System.out.println("Resultado de la busqueda: " + Producto[1] + Producto[2]);
			if (sEncontrado == "Encontrado"){
				iPosicion = Integer.parseInt (Producto[2]); // estaba con comentario
				LineasPedido().activateRow(iPosicion); // estaba con comentario
				// elimina la linea de terminales promocionales

				Eliminar().performAction();
				Aceptar().click();
				sleep(1); 		
			}  
		}
		argu[1] = sEncontrado;
	}
}


