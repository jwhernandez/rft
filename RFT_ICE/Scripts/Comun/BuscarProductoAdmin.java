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
 * 2) posicion en la que se encontro el objeto 3)Tramite 
 * SS Nov 2015
 */
public class  BuscarProductoAdmin extends BuscarProductoAdminHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		String sProductoObjetivo = argu[0].toString();
		argu[1] = "NOK";
		String sTramite = "Venta";
		if (argu.length >= 4 ) { 
			sTramite = argu[3].toString(); // tramite
		}
		
		String sProducto="NA"; 
		int i = 0;
		int iTotal=0;
		
		if (!sTramite.equals("PortIn")){
			sProducto = (String) LineasPedidoAdmin().getCellText("Product", 0);
			iTotal = (int) LineasPedidoAdmin().getProperty("RowsCount");
		}
		if (sTramite.equals("PortIn")){
			sProducto = (String) LineasPedidoPIAdmin().getCellText("Product", 0);
			iTotal = (int) LineasPedidoPIAdmin().getProperty("RowsCount");
		}


		while (i < iTotal - 1 && !(sProducto.equals(sProductoObjetivo))) 
		{
			i = i + 1;
			if (!sTramite.equals("PortIn")){
			sProducto = (String) LineasPedidoAdmin().getCellText("Product", i);
			}
			if (sTramite.equals("PortIn")){
			sProducto = (String) LineasPedidoPIAdmin().getCellText("Product", i);
			}
		}
		
		if (sProducto.equals(sProductoObjetivo)) {
			argu[1] = "OK";
			System.out.println("Producto Seleccionado: "
					+ sProducto
					+ " en posición: " + i);
			argu[2] = Integer.toString(i);
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

