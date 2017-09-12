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
import com.rational.test.util.regex.Regex;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

/**
 * Descripción: Permite seleccionar una línea del pedido 
 * Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion 
 * y 3)OUT action code 4) desde el comienzo (Si= desde el comienzo No = desde la linea actual  
 * Nro:string permite excluir ese numero de linea en la busqueda para buscar el siguiente en su 
 * especie 
 * 5)Tramite
 * 4)argumento opcional para indicar desde posicion actual
 * EJ "Numero Kolbi SMS Favorito" res res res No PortIn
 *  * EJ "Numero Kolbi SMS Favorito" res res res No PortIn
 *  ej "Servicio Datos" res res red Si CambioPlan
 * SS Nov 2015 -- Modificado en Dic para agregar que permita encontrar un producto fuera de la pantalla
 * ss 1-2-2017 objeto menu para port in agregado
 * "Plan Profesional*" res res res No Venta
 * ss 22/06/2017 agregado de regex
 * "Activador descuento.*SVA" res res res No Venta
 * ss 3/07/2017 agregado de opcion Nro: para excluir una linea de la busqueda y que busque el siguiente
 * "Plan Especial.*" res res res No Venta
 * "Plan Especial.*" res res res Nro:1.2 Venta
 * SMS.* res res res Nro:1.3.3 "Cambio de plan"
 */
public class BuscarProducto extends BuscarProductoHelper 
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 6 ) { 
			sTramite = argu[5].toString();
		}
	
		String sProductoObjetivo = argu[0].toString(); // se movio de lugar ss 22/07/2017
		Regex re = new Regex(sProductoObjetivo); // ss 22/06/2017 agregado de regex
		
		String[] RecordCount;
		RecordCount = new String[5];
		Boolean matches = false; // ss 3/7/2017 agregado de buscar segundo que matchea
	
		if (!sTramite.equals("PortIn")) Menu().ensureObjectIsVisible();
		if (sTramite.equals("PortIn")) MenuPI().ensureObjectIsVisible(); // 1-2-2017 ss 
		
		System.out.println(AppletLineasPedido().exists());
		System.out.println(AppletLineasPedido().getProperties());
		if (!sTramite.equals("PortIn")){ RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();}
		if (sTramite.equals("PortIn")){ RecordCount[0] = AppletLineasPedidoPI().getProperty("RecordCounter").toString();}

		callScript("Scripts.Comun.RecordCount",RecordCount);
		
		int iDesde = Integer.parseInt(RecordCount[1]);
		int iSubtotal = Integer.parseInt(RecordCount[2]);
		int iTotal = Integer.parseInt(RecordCount[3]);
		int irows = 1;
		Boolean SeleccionarSgdo=false; // ss 3/7/2017
		String NroLinea = null;  // ss 3/7/2017
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
			// ss 3 julio 2017
			if (sDesdeComienzo.contains("Nro:"))
			{
				SeleccionarSgdo=true;
				sDesdeComienzo = argu[4].toString().replace("Nro:","");
			}
		}
		
		int i = 0;
		// Ir al primer RowSet
		if (sDesdeComienzo.equals("Si") || SeleccionarSgdo ) { // 3/7/2017 se comienza desde el principio si hay que buscar un 
			// segundo patron que excluya el primer nrolinea
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


		argu[1] = "No Encontrado";

		int iPosicion = i;
		String sProducto = "Nada";
		if (!sTramite.equals("PortIn")){ sProducto = (String) LineasPedido().getCellText("Product", iPosicion);}
		if (sTramite.equals("PortIn")){ sProducto = (String) LineasPedidoPI().getCellText("Product", iPosicion);}

		Boolean Iterar =  true;  
		while (Iterar){
			while (i <= irows - 1 && !matches) { // ss 22/06/2017 agregado de regex
				if (re.matches(sProducto))
				{
					// ss 3/7/2017 si se incluye opcion de buscar sgdo cambia el concepto de macheo
					if (!SeleccionarSgdo) matches = true;
					else
						if (!NroLinea.equals(sDesdeComienzo))
							matches = true;
						else
						{
							argu[1] = "Solo el excluido"; // ss 3/7/2017
							// Coloca el valor en el param 1 pero sigue buscando por si encuentra
							System.out.println("Se encontró el excluido en la linea=" + NroLinea +" "+ sDesdeComienzo); // ss 3/7/2017
						}
				System.out.println("Matches: " + matches +" "+ NroLinea +" "+ sDesdeComienzo); // ss 3/7/2017
				} 
				if (!matches)
				{
					iPosicion = i;
					if (!sTramite.equals("PortIn")){ LineasPedido().activateRow(i);
					sProducto = (String) LineasPedido().getCellText("Product", i);
					NroLinea = (String) LineasPedido().getCellText("Outline Number", i);
					System.out.println("Producto: " + sProducto +NroLinea);} // 3/7/2017

					if (sTramite.equals("PortIn")){ LineasPedidoPI().activateRow(i);
					sProducto = (String) LineasPedidoPI().getCellText("Product", i);
					NroLinea = (String) LineasPedidoPI().getCellText("Outline Number", i);
					System.out.println("Producto: " + sProducto +NroLinea);} // 3/7/2017

					i = i + 1;
					System.out.println("expresion regular" + (re.matches(sProducto)));
				}
			}


//			if ((sProducto.equals(sProductoObjetivo))) { // ss 22/06/2017 agregado de regex
			if ((re.matches(sProducto))) {
			
				// ss 3/7/2017 si se incluye opcion de buscar sgdo cambia el concepto de macheo
				if (!SeleccionarSgdo) 
				{
					matches = true;
					Iterar = false; 
					argu[1] = "Encontrado";
					System.out.println("-------------------------------------------------------------------");
					System.out.println("Producto encontrado");
					System.out.println("-------------------------------------------------------------------");
				}
				else
				{
					if (!NroLinea.equals(sDesdeComienzo))
					{
						matches = true;
						Iterar = false; 
						argu[1] = "Encontrado";
						System.out.println("-------------------------------------------------------------------");
						System.out.println("Producto encontrado");
						System.out.println("-------------------------------------------------------------------");
					}
					else
					{
						argu[1] = "Solo el excluido"; // ss 3/7/2017
						// Coloca el valor en el param 1 pero sigue buscando por si encuentra
						System.out.println("Se encontró el excluido en la linea=" + NroLinea +" "+ sDesdeComienzo); // ss 3/7/2017
					}
				}
			System.out.println("Matches: " + matches +" "+ NroLinea +" "+ sDesdeComienzo); // ss 3/7/2017
			} 
			if (!matches) {
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

		if (re.matches(sProducto)) { // ss 6/07/2017 agregado de regex
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

