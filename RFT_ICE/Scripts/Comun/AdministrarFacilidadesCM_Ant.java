package Scripts.Comun;
import resources.Scripts.Comun.AdministrarFacilidadesCM_AntHelper;
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
 * Description   : Valida que todas las facilides de CM sean no editables
 * @author SS
 * Script Name   : <b>AdministrarFacilidadesCM</b>
 * @Param 0) OK/NOK 1) Facilidad a incluir o excluir 2) Accion (Futuros usos) 3)Out Posicion
 * ej res LISTAR res res
 * ej res "Deposito de Garantia" res res  
 * Si parametro 1 = LISTAR no selecciona sino muestra todas las facilidades y su valor
 * @since  2016/04/13
 */
public class AdministrarFacilidadesCM_Ant extends AdministrarFacilidadesCM_AntHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		String[] RecordCount;
		RecordCount = new String[5];

		System.out.println(AppletFacilidadesCM().exists());
		System.out.println(AppletFacilidadesCM().getProperty("RecordCounter"));
		RecordCount[0] = AppletFacilidadesCM().getProperty("RecordCounter").toString();

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
		irows=(int) ListaFacilidadesCM().getProperty("RowsCount");

		System.out.println("-------------------------------------------------------------------");
		System.out.println("Inicio de logica de buscar facilidad (1)" );
		System.out.println("Desde= " + iDesde + " Subtotal= " + iSubtotal + " Total= " + iTotal );
		System.out.println("Facilidad a buscar= " + argu[1]);
		System.out.println("-------------------------------------------------------------------");


		int i = 0;
		
		// Ir al primer RowSet
		while (iDesde > 1 ) {
			ListaFacilidadesCM().firstRowSet();
			RecordCount[0] = AppletFacilidadesCM().getProperty("RecordCounter").toString();

			callScript("Scripts.Comun.RecordCount",RecordCount);

			iDesde = Integer.parseInt(RecordCount[1]);
			iSubtotal = Integer.parseInt(RecordCount[2]);
			iTotal = Integer.parseInt(RecordCount[3]);
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Inicio de logica de buscar facilidad (2)" );
			System.out.println("Desde= " + iDesde + "Subtotal= " + iSubtotal + "Total= " + iTotal );
			System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
			System.out.println("-------------------------------------------------------------------");
		} // end del while

		String sProductoObjetivo = argu[1].toString();
		if (argu[1].toString().toLowerCase().equals("listar")) argu[0] = "OK"; else argu[0] = "NOK"; 

		int iPosicion = i;
		String sProducto = "Nada";
		sProducto = (String) ListaFacilidadesCM().getCellText("Name", iPosicion);

		Boolean Iterar =  true;  
		while (Iterar){
			while (i <= irows - 1 && !(sProducto.equals(sProductoObjetivo))) {
				iPosicion = i;
				ListaFacilidadesCM().activateRow(i);
				sProducto = (String) ListaFacilidadesCM().getCellText("Name", i);
				if (NombreFacilidad().isEnabled() && argu[1].toString().toLowerCase().equals("listar")) 
					argu[0]="NOK";
				System.out.println("Facilidad: " + ListaFacilidadesCM().getCellText("Name", i) );
				if ((boolean) NombreFacilidad().getProperty("IsEnabled"))  argu[0]="NOK";
				System.out.println("Editable: " + NombreFacilidad().getProperty("IsEnabled") + "-" + argu[0]);
				System.out.println("Flag: " + ListaFacilidadesCM().getCellText("ICE Facilidades Flg", i) );
				i = i + 1;
			}

			if ((sProducto.equals(sProductoObjetivo))) {
				ListaFacilidadesCM().activateRow(i-1);
				Iterar = false; 
				if (argu[2].toString().toLowerCase().equals("seleccionar")) Seleccionado().setOn();
				else Seleccionado().setOff();
				argu[0] = "OK";
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Facilidad encontrada");
				System.out.println("-------------------------------------------------------------------");
			} 
			else // si el producto es diferentes
			{
				if (iSubtotal < iTotal) {
					ListaFacilidadesCM().nextRowSet();
					RecordCount[0] = AppletFacilidadesCM().getProperty("RecordCounter").toString();

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
			} // subtotal < itotal
			if (Iterar) i = 0; // vuelvo a posicionarme en la primer fila del siguiente row_set
		} // end del while iterar 

		if (sProducto.equals(sProductoObjetivo)) {
			argu[0] = "OK";
			System.out.println("Facilidad Seleccionada: " + sProducto + " en posición: " + iPosicion);
			argu[2] = Integer.toString(iPosicion);
		} 
		//Siguiente().performAction(); 
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());	
	}
}

