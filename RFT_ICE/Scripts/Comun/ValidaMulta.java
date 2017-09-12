package Scripts.Comun;
import resources.Scripts.Comun.ValidaMultaHelper;
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
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataTable;
import com.rational.test.ft.vp.ITestDataText;
import com.rational.test.ft.object.interfaces.TestObject;
import java.util.Enumeration;
/**
 * Description   : Valida el contenido de la multa de un CP a traves del retorno de la integración con ATV
 * Lee la info de las pantallas de ATV
 * @Sistema : Siebel + ATV
 * @author SS
 * Script Name   : <b>ValidaMulta</b>
 * @since  2016/07/04
 * @Param 0) OK/NOK 1) Plan desde 2) valores de retorno ... ya veremos cuantos y cuales
 */
public class ValidaMulta extends ValidaMultaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";

		String sPlanBuscado = argu[1].toString();
		String testDataType;


		//BtonVerMulta().performAction();


		BrowserIE(ConsultaMultasxCP(),DEFAULT_FLAGS).maximize();

		System.out.println(ChkTodosPlanes().getProperty(".checked"));

		if (ChkTodosPlanes().getProperty(".checked").equals("false")) 	
			ChkTodosPlanes().click();

		TestObject table = MultasxPlan();

		System.out.println("Plan Buscado= " +sPlanBuscado );

		ITestDataTable iDataTable = (ITestDataTable) table.getTestData("contents");
		int rows = iDataTable.getRowCount();
		int cols = iDataTable.getColumnCount();
		System.out.println("Cols="+cols+"Rows="+rows); // 13 cols; 0 y 1 invisibles
		
		boolean bContinuar = true;
		int row=3;
		while (row<=rows && bContinuar) {
			String sPlanCell ="";
			// Obtiene la celda de la tabla
			sPlanCell = iDataTable.getCell(row, 2).toString();
			System.out.println("Plan en fila=" + row + " ="+ sPlanCell);
			if (!sPlanCell.isEmpty()) {
				if (sPlanCell.equals((sPlanBuscado))) {
					System.out.println("---El plan destino ha sido encontrado---" + "en fila=" + row);
					argu[0]="OK";
					bContinuar=false;
					System.out.println("Tipo = "+ iDataTable.getCell(row,3));						
					System.out.println("Sin terminal nueva, Paga Multa = "+ 
										iDataTable.getCell(row,4) + ";" +
										"Multa CRC = "+ iDataTable.getCell(row,5));	
					System.out.println("Con terminal nueva, Paga Multa = "+ 
										iDataTable.getCell(row,7) + ";" +
										"Multa CRC = "+ iDataTable.getCell(row,8));	
					System.out.println("Permite Act= "+ iDataTable.getCell(row,10));	
				}				
			} // si la celda no es vacia
			row=row+1;
		} // while por fila

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

