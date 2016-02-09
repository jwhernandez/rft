package Scripts.Comun;
import resources.Scripts.Comun.BuscarProductoAdminHelper;
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
 * Descripción: Permite seleccionar una línea del pedido desde el applet de administracion
 * Parámetros: 0) Nombre del producto 1) OK "Encontrado"/ NOK "No Encontrado"  
 * 2) posicion en la que se encontro el objeto
 * SS Nov 2015
 */
public class BuscarProductoAdmin extends BuscarProductoAdminHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		String sProductoObjetivo = argu[0].toString();
		argu[1] = "NOK";

		String sProducto = (String) LineasPedidoAdmin().getCellText("Product", 0);
		int i = 0;
		int iTotal = (int) LineasPedidoAdmin().getProperty("RowsCount");
		while (i < iTotal - 1 && !(sProducto.equals(sProductoObjetivo))) {
			i = i + 1;
			sProducto = (String) LineasPedidoAdmin().getCellText("Product", i);
		}
		if (sProducto.equals(sProductoObjetivo)) {
			argu[1] = "OK";
			System.out.println("Producto Seleccionado: "
					+ LineasPedidoAdmin().getCellText("Product", i)
					+ " en posición: " + i);
			argu[2] = Integer.toString(i);
		}
	}
}

