package Scripts.Comun;
import javax.swing.JTable;
import resources.Scripts.Comun.SeleccionarProductosCPHelper;
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
 * Description   : Permite seleccionar los productos a comparar en un CP
 * Script Name   : <b>SeleccionarProductoCP</b>
 * @Param 0) int iCant 1) table
 * @since  2016/01/05
 * @author Sandra
 */
public class SeleccionarProductosCP extends SeleccionarProductosCPHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] RecordCount;
		RecordCount = new String[4];

		String[] columnNames = {"Producto","Categoria", "Codigo"};
		Object[][] data = null;
		
		JTable table = new JTable(data, columnNames);
		
		int iCantRows = 0; // número de objetos guardados para posterior comparación

		callScript("Scripts.Comun.RecordCount",RecordCount);

		int iDesde = Integer.parseInt(RecordCount[1]);
		int iSubtotal = Integer.parseInt(RecordCount[2]);
		int iTotal = Integer.parseInt(RecordCount[3]);
		int irows = (int) LineasPedido().getProperty("RowsCount") ;

		LineasPedido().waitForExistence();

		// Ir al primer RowSet
		while (iDesde > 1 ) {
			LineasPedido().firstRowSet();
			RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();
			callScript("Scripts.Comun.RecordCount",RecordCount);

			iDesde = Integer.parseInt(RecordCount[1]);
			iSubtotal = Integer.parseInt(RecordCount[2]);
			iTotal = Integer.parseInt(RecordCount[3]);
		}

		int i = 0;

		Boolean Iterar =  true;  
		while (Iterar){
			i = 0;
			while (i <= irows - 1 ) {
				LineasPedido().activateRow(i);
				String sProducto = (String) LineasPedido().getCellText("Product", i);
				//String sCodAccion = (String) LineasPedido().getCellText("Action Code Calc", i);

				dpReset();
				while (!dpDone() &&  !(dpString("Producto").equals(sProducto))){
					dpNext(); 
				}
				if (dpString("Producto").equals(sProducto)) {
					//data[iCantRows][0] = sProducto;
					//data[iCantRows][1] = dpString("Categoria");
					//data[iCantRows][2] = sCodAccion;
					iCantRows++;
				}

				i = i + 1; 
			} // mientras haya filas en el primer rowSet

			if (iSubtotal < iTotal) {
				LineasPedido().nextRowSet();
				RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();
				callScript("Scripts.Comun.RecordCount",RecordCount);

				iSubtotal = Integer.parseInt(RecordCount[2]);
				iTotal = Integer.parseInt(RecordCount[3]);

			} else {
				Iterar = false; 
			}
		} // end del while iterar
		
		args[0] = iCantRows;
		args[1] = table;
		ImpreResultadoScript(getScriptName( ).toString(), "Sin resultado");

	}
}

