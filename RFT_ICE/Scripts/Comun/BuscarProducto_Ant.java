package Scripts.Comun;

import resources.Scripts.Comun.BuscarProducto_AntHelper;
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
 * Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion 
 * y 3)OUT action code 4) desde el comienzo (Si= desde el comienzo No = desde la linea actual) 5)Tramite
 * 4)argumento opcional para indicar desde posicion actual
 * EJ "Numero Kolbi SMS Favorito" res res res No PortIn
 *  * EJ "Numero Kolbi SMS Favorito" res res res No PortIn
 *  ej "Servicio Datos" res res red Si CambioPlan
 * SS Nov 2015 -- Modificado en Dic para agregar que permita encontrar un producto fuera de la pantalla
 */
public class BuscarProducto_Ant extends BuscarProducto_AntHelper 
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 6 ) { 
			sTramite = argu[5].toString();
		}
		
		String[] RecordCount;
		RecordCount = new String[5];
	
		Menu().ensureObjectIsVisible();
		System.out.println(AppletLineasPedido().exists());
		System.out.println(AppletLineasPedido().getProperties());
		if (!sTramite.equals("PortIn")){ RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();}
		if (sTramite.equals("PortIn")){ RecordCount[0] = AppletLineasPedidoPI().getProperty("RecordCounter").toString();}

		callScript("Scripts.Comun.RecordCount",RecordCount);
		
		int iDesde = Integer.parseInt(RecordCount[1]);
		int iSubtotal = Integer.parseInt(RecordCount[2]);
		int iTotal = Integer.parseInt(RecordCount[3]);
		int irows = 1;
		if (!sTramite.equals("PortIn")){irows=(int) LineasPedido().getProperty("RowsCount");}
		if (sTramite.equals("PortIn")){irows=(int) LineasPedidoPI().getProperty("RowsCount");}

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Inicio de logica de buscar producto (1)" );
		System.out.println("Desde= " + iDesde + " Subtotal= " + iSubtotal + " Total= " + iTotal );
		System.out.println("Producto a buscar= " + argu[0]);
		System.out.println("-------------------------------------------------------------------");
	
		// Decidir si ir al primer RowSet
		String sDesdeComienzo = "Si";
		if (argu.length >= 5 ) { 
			sDesdeComienzo = argu[4].toString(); 
		}
		
		int i = 0;
		// Ir al primer RowSet
		if (sDesdeComienzo.equals("Si")) {
			while (iDesde > 1 ) {
				if (!sTramite.equals("PortIn")){LineasPedido().firstRowSet();
				RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();}
				if (sTramite.equals("PortIn")){LineasPedidoPI().firstRowSet();
				RecordCount[0] = AppletLineasPedidoPI().getProperty("RecordCounter").toString();}
				
				callScript("Scripts.Comun.RecordCount",RecordCount);

				iDesde = Integer.parseInt(RecordCount[1]);
				iSubtotal = Integer.parseInt(RecordCount[2]);
				iTotal = Integer.parseInt(RecordCount[3]);
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Inicio de logica de buscar producto (2)" );
				System.out.println("Desde= " + iDesde + "Subtotal= " + iSubtotal + "Total= " + iTotal );
				System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
				System.out.println("-------------------------------------------------------------------");
			}
		} else {
			System.out.println("Se busca desde la posición actual");
			if (!sTramite.equals("PortIn")){i = Integer.parseInt(LineasPedido().getProperty("ActiveRow").toString());}
			if (sTramite.equals("PortIn")){i = Integer.parseInt(LineasPedidoPI().getProperty("ActiveRow").toString());}

		}

		String sProductoObjetivo = argu[0].toString();
		argu[1] = "No Encontrado";

		int iPosicion = i;
		String sProducto = "Nada";
		if (!sTramite.equals("PortIn")){ sProducto = (String) LineasPedido().getCellText("Product", iPosicion);}
		if (sTramite.equals("PortIn")){ sProducto = (String) LineasPedidoPI().getCellText("Product", iPosicion);}

		Boolean Iterar =  true;  
		while (Iterar){
			while (i <= irows - 1 && !(sProducto.equals(sProductoObjetivo))) {
				iPosicion = i;
				if (!sTramite.equals("PortIn")){ LineasPedido().activateRow(i);
				sProducto = (String) LineasPedido().getCellText("Product", i);
				System.out.println("Producto: " + LineasPedido().getCellText("Product", i) );}
				
				if (sTramite.equals("PortIn")){ LineasPedidoPI().activateRow(i);
				sProducto = (String) LineasPedidoPI().getCellText("Product", i);
				System.out.println("Producto: " + LineasPedidoPI().getCellText("Product", i) );}

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
					if (!sTramite.equals("PortIn")){LineasPedido().nextRowSet();
						RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();}
					
					if (sTramite.equals("PortIn")){LineasPedidoPI().nextRowSet();
					RecordCount[0] = AppletLineasPedidoPI().getProperty("RecordCounter").toString();}

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
			if (Iterar) i = 0; // vuelvo a posicionarme en la primer fila del siguiente row_set
		} // end del while iterar

		if (sProducto.equals(sProductoObjetivo)) {
			argu[1] = "Encontrado";
			System.out.println("Producto Seleccionado: "
					+ sProducto
					+ " en posición: " + iPosicion);
			argu[2] = Integer.toString(iPosicion);
			
			if (!sTramite.equals("PortIn")){ LineasPedido().activateRow(iPosicion);
				System.out.println("Producto Seleccionado: "
					+ LineasPedido().getCellText("Action Code Calc", iPosicion));
				argu[3] = LineasPedido().getCellText("Action Code Calc", iPosicion).toString();}
			
			if (sTramite.equals("PortIn")){ LineasPedidoPI().activateRow(iPosicion);
			System.out.println("Producto Seleccionado: "
				+ LineasPedidoPI().getCellText("Action Code Calc", iPosicion));
			argu[3] = LineasPedidoPI().getCellText("Action Code Calc", iPosicion).toString();}
			
		} else {
			System.out.println("Resultado: " + 	argu[1]);
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

