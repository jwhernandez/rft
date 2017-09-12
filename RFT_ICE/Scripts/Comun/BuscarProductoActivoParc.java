package Scripts.Comun;
import resources.Scripts.Comun.BuscarProductoActivoParcHelper;

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
 * Descripción: Permite seleccionar una línea del activo cta fact usando parte del nombre
 * Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion 
 * y 3)OUT action code 4) desde el comienzo (Si= desde el comienzo No = desde la linea actual) 5)Tramite
 * 4)argumento opcional para indicar desde posicion actual (si indica desde el comienzo)
 * EJ "Numero Kolbi SMS Favorito" res res res No PortIn
 *  * EJ "Numero Kolbi SMS Favorito" res res res No PortIn
 *  "PLAN DATOS KOLBI MEDIO SIN TERMINAL" res res res Si PostVenta
 *  "Servicio Datos" res res res Si PostVenta
 * VC 2017/05/03
 * Script Name   : <b>BuscarProductoActivoParc</b>
 */
public class BuscarProductoActivoParc extends BuscarProductoActivoParcHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] RecordCount;
		RecordCount = new String[5];
		
		System.out.println(AppletLineasActivo().exists());
		System.out.println(AppletLineasActivo().getProperties());
		RecordCount[0] = AppletLineasActivo().getProperty("RecordCounter").toString();

		callScript("Scripts.Comun.RecordCount",RecordCount);
		
		int iDesde = Integer.parseInt(RecordCount[1]);
		int iSubtotal = Integer.parseInt(RecordCount[2]);
		int iTotal = Integer.parseInt(RecordCount[3]);
		int irows = 1;
		irows=(int) LineasActivoCtaFact().getProperty("RowsCount");

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Inicio de logica de buscar producto Parc(1)" );
		System.out.println("Desde= " + iDesde + " Subtotal= " + iSubtotal + " Total= " + iTotal );
		System.out.println("Producto a buscar= " + argu[0]);
		System.out.println("-------------------------------------------------------------------");
	
		// Decidir si ir al primer RowSet
		String sDesdeComienzo = "si";
		if (argu.length >= 5 ) { 
			sDesdeComienzo = argu[4].toString().toLowerCase(); 
		}
		
		int i = 0;
		// Ir al primer RowSet
		if (sDesdeComienzo.equals("si")) {
			while (iDesde > 1 ) {
				LineasActivoCtaFact().firstRowSet();
				RecordCount[0] = AppletLineasActivo().getProperty("RecordCounter").toString();
				
				callScript("Scripts.Comun.RecordCount",RecordCount);

				iDesde = Integer.parseInt(RecordCount[1]);
				iSubtotal = Integer.parseInt(RecordCount[2]);
				iTotal = Integer.parseInt(RecordCount[3]);
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Inicio de logica de buscar producto Parc (2)" );
				System.out.println("Desde= " + iDesde + "Subtotal= " + iSubtotal + "Total= " + iTotal );
				System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
				System.out.println("-------------------------------------------------------------------");
			}
		} else {
			System.out.println("Se busca desde la posición actual");
			i = Integer.parseInt(LineasActivoCtaFact().getProperty("ActiveRow").toString());
		}

		String sProductoObjetivo = argu[0].toString();
		sProductoObjetivo = sProductoObjetivo.toUpperCase();
		argu[1] = "No Encontrado";

		int iPosicion = i;
		String sProducto = "Nada";
		sProducto = (String) LineasActivoCtaFact().getCellText("Product Name", iPosicion);
		
		sProducto = sProducto.toUpperCase();
		int encontrado = sProducto.indexOf(sProductoObjetivo);

		Boolean Iterar =  true;  
		while (Iterar){
			while (i <= irows - 1 && !(encontrado > -1)) {
				iPosicion = i;
				
				LineasActivoCtaFact().activateRow(i);
				sProducto = (String) LineasActivoCtaFact().getCellText("Product Name", i);
				System.out.println("Producto: " + LineasActivoCtaFact().getCellText("Product Name", i) );
				
				sProducto = sProducto.toUpperCase();
				encontrado = sProducto.indexOf(sProductoObjetivo);
				
				i = i + 1;
			}

			sProducto = sProducto.toUpperCase();
			encontrado = sProducto.indexOf(sProductoObjetivo);
			System.out.println("Producto: " + sProducto + " Producto Objetivo: " + sProductoObjetivo + " indexOf: " + encontrado);

			if ((encontrado > -1)) {
				Iterar = false; 
				argu[1] = "Encontrado";
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Producto encontrado");
				System.out.println("-------------------------------------------------------------------");

				
			} else {
				if (iSubtotal < iTotal) {
					LineasActivoCtaFact().nextRowSet();
					RecordCount[0] = AppletLineasActivo().getProperty("RecordCounter").toString();

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

		if (encontrado > -1) {
			argu[1] = "Encontrado";
			System.out.println("Producto Seleccionado: " + sProducto + " en posición: " + iPosicion);
			argu[2] = Integer.toString(iPosicion);
			LineasActivoCtaFact().activateRow(iPosicion);
			} else {
			System.out.println("Resultado: " + 	argu[1]);
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

