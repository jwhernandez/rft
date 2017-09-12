package Scripts.Comun;
import resources.Scripts.Comun.ComprarPlanCMHelper;
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
 * Description   : Permite seleccionar un plan en CM
 * Script Name   : <b>CompraPlanCM</b>
 * @author SS
 * @since  2016/04/13
 * @Param  Par�metros: 0) OK/NOK 1) Nombre del producto 2) Posicion 
 * ej res "PLAN COMPARTIDO KOLBI EMPRESARIAL 12 M" res
 * ult modif ss 09/2/2017 se elimina el avanzar de este script
 * �ltima modificaci�n 20170421 VC Si se colocaba listar producia un error ya que lo tomaba como el plan a buscar
 */
public class ComprarPlanCM extends ComprarPlanCMHelper
{
	public void testMain(Object[] argu) 
	{ 
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] RecordCount;
		RecordCount = new String[5];

		System.out.println(AppletPlanesCM().exists());
		System.out.println(AppletPlanesCM().getProperties());
		RecordCount[0] = AppletPlanesCM().getProperty("RecordCounter").toString();

		callScript("Scripts.Comun.RecordCount",RecordCount);

		int iDesde = Integer.parseInt(RecordCount[1]);
		int iSubtotal = Integer.parseInt(RecordCount[2]);
		int iStart1 = (int) RecordCount[3].toString().indexOf("+");
		int iTotal = 0;
		if (iStart1 >=1) {
			iTotal = 1000; // si encuentra un "+", no importa el total hay mas registros
		} else {
			iTotal = Integer.parseInt(RecordCount[3]);
		}

		int irows = 1;
		irows=(int) ListaPlanesCM().getProperty("RowsCount");

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Inicio de logica de buscar producto (1)" );
		System.out.println("Desde= " + iDesde + " Subtotal= " + iSubtotal + " Total= " + iTotal );
		System.out.println("Producto a buscar= " + argu[1]);
		System.out.println("-------------------------------------------------------------------");


		int i = 0;
		// Ir al primer RowSet
		while (iDesde > 1 ) {
			ListaPlanesCM().firstRowSet();
			RecordCount[0] = AppletPlanesCM().getProperty("RecordCounter").toString();

			callScript("Scripts.Comun.RecordCount",RecordCount);

			iDesde = Integer.parseInt(RecordCount[1]);
			iSubtotal = Integer.parseInt(RecordCount[2]);
			iTotal = Integer.parseInt(RecordCount[3]);
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Inicio de logica de buscar producto (2)" );
			System.out.println("Desde= " + iDesde + "Subtotal= " + iSubtotal + "Total= " + iTotal );
			System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
			System.out.println("-------------------------------------------------------------------");
		} // end del while

		String sProductoObjetivo = argu[1].toString();
		argu[0] = "NOK";

		int iPosicion = i;
		String sProducto = "Nada";
		sProducto = (String) ListaPlanesCM().getCellText("Name", iPosicion);

		Boolean Iterar =  true;  
		while (Iterar){
			while (i <= irows - 1 && !(sProducto.equals(sProductoObjetivo))) {
				iPosicion = i;
				ListaPlanesCM().activateRow(i);
				sProducto = (String) ListaPlanesCM().getCellText("Name", i);
				System.out.println("Producto: " + ListaPlanesCM().getCellText("Name", i) );

				i = i + 1;
			}

			if ((sProducto.equals(sProductoObjetivo))) {
				Iterar = false; 
				argu[0] = "OK";
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Producto encontrado");
				System.out.println("-------------------------------------------------------------------");


			} else {
				if (iSubtotal < iTotal) {
					ListaPlanesCM().nextRowSet();
					RecordCount[0] = AppletPlanesCM().getProperty("RecordCounter").toString();

					callScript("Scripts.Comun.RecordCount",RecordCount);

					iDesde = Integer.parseInt(RecordCount[1]);
					iSubtotal = Integer.parseInt(RecordCount[2]);
					iStart1 = (int) RecordCount[3].toString().indexOf("+");
					iTotal = 0;
					if (iStart1 >=1) {
						iTotal = 1000; // si encuentra un "+", no importa el total hay mas registros
					} else {
						iTotal = Integer.parseInt(RecordCount[3]);
					}					
					
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
			argu[0] = "OK";
			System.out.println("Producto Seleccionado: " + sProducto + " en posici�n: " + iPosicion);
			argu[2] = Integer.toString(iPosicion);
			// Siguiente().performAction(); ss 09/12/2017 
		} else if(sProductoObjetivo.toLowerCase().equals("listar")) argu[0] = "OK"; //VC 20170421 Si se colocaba listar producia un error porque no encontraba el plan "listar"
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());	
	}
}
