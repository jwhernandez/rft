package Scripts.Comun;

import resources.Scripts.Comun.BuscarProductoHelper;
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
 * Descripción: Permite seleccionar una línea del pedido 
 * Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posipageTabList_siebScreenViews().
 * SS Nov 2015 -- Modificado en Dic para agregar que permita encontrar un producto fuera de la pantalla
 */
public class BuscarProducto extends BuscarProductoHelper {

	public void testMain(Object[] argu) throws RationalTestException {
		String[] RecordCount;
		RecordCount = new String[4];
	
		System.out.println(AppletLineasPedido().exists());
		System.out.println(AppletLineasPedido().getProperties());
		RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();
		
		callScript("Scripts.Comun.RecordCount",RecordCount);
		
		int iDesde = Integer.parseInt(RecordCount[1]);
		int iSubtotal = Integer.parseInt(RecordCount[2]);
		int iTotal = Integer.parseInt(RecordCount[3]);
		int irows = (int) LineasPedido().getProperty("RowsCount") ;
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Inicio de script buscar producto (1)" );
		System.out.println("Desde= " + iDesde + " Subtotal= " + iSubtotal + " Total= " + iTotal );
		System.out.println("-------------------------------------------------------------------");
	
		LineasPedido().waitForExistence();

		// Ir al primer RowSet
		while (iDesde > 1 ) {
			LineasPedido().firstRowSet();
			RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();
			callScript("Scripts.Comun.RecordCount",RecordCount);

			iDesde = Integer.parseInt(RecordCount[1]);
			iSubtotal = Integer.parseInt(RecordCount[2]);
			iTotal = Integer.parseInt(RecordCount[3]);
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Inicio de script buscar producto (2)" );
			System.out.println("Desde= " + iDesde + "Subtotal= " + iSubtotal + "Total= " + iTotal );
			System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
			System.out.println("-------------------------------------------------------------------");
		}
		
		String sProductoObjetivo = argu[0].toString();
		argu[1] = "No Encontrado";

		int i = 0;
		int iPosicion = i;
		String sProducto = (String) LineasPedido().getCellText("Product", 0);
		
		Boolean Iterar =  true;  
		while (Iterar){
			i = 0;
			while (i <= irows - 1 && !(sProducto.equals(sProductoObjetivo))) {
				iPosicion = i;
				LineasPedido().activateRow(i);
				sProducto = (String) LineasPedido().getCellText("Product", i);
				System.out.println("Producto: " + LineasPedido().getCellText("Product", i) );
				i = i + 1;
			}

			if ((sProducto.equals(sProductoObjetivo))) {
				Iterar = false; 
				argu[1] = "Encontrado";
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Producto encontrado");
				System.out.println("-------------------------------------------------------------------");

				
			} else {
				if (iSubtotal < iTotal) {
					LineasPedido().nextRowSet();
					RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();
					callScript("Scripts.Comun.RecordCount",RecordCount);

					iSubtotal = Integer.parseInt(RecordCount[2]);
					iTotal = Integer.parseInt(RecordCount[3]);
					System.out.println("-------------------------------------------------------------------");
					System.out.println("Siguiente rowset" );
					System.out.println("Subtotal" + iSubtotal + "Total " + iTotal );
					System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
					System.out.println("-------------------------------------------------------------------");
				} else {
					Iterar = false; 
				}
			}
		} // end del while iterar

		if (sProducto.equals(sProductoObjetivo)) {
			argu[1] = "Encontrado";
			System.out.println("Producto Seleccionado: "
					+ LineasPedido().getCellText("Product", iPosicion)
					+ " en posición: " + iPosicion);
			argu[2] = Integer.toString(iPosicion);
			LineasPedido().activateRow(iPosicion);
			System.out.println("Producto Seleccionado: "
					+ LineasPedido().getCellText("Action Code Calc", iPosicion));
			argu[3] = LineasPedido().getCellText("Action Code Calc", iPosicion)
					.toString();
		} else {
			System.out.println("Resultado: " + 	argu[1]);
		}
	}
}

