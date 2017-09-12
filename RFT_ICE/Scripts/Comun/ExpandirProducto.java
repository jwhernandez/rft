package Scripts.Comun;
import resources.Scripts.Comun.ExpandirProductoHelper;
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
import com.ibm.xtq.ast.nodes.ValueOf;
/**
* Descripción: Expande todas las lineas del pedido para busquedas o print de pantalla
* Parámetros:  no recibe no retorna
* SS Nov 2015  
*/
public class ExpandirProducto extends ExpandirProductoHelper
{
	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] RecordCount;
		RecordCount = new String[4];
		
		RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();
		
		callScript("Scripts.Comun.RecordCount",RecordCount);
		
		int iSubtotal = Integer.parseInt(RecordCount[2]);
		int iTotal = Integer.parseInt(RecordCount[3]);
		System.out.println("Subtotal" + iSubtotal + "Total " + iTotal );
		System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
	
		LineasPedido().waitForExistence();
		int i=0;
		int irows = (int) LineasPedido().getProperty("RowsCount") ;

 		//String sOutlineMax =LineasPedido().getCellText("Outline Number", irows-1) ; 
 		//String sOutlineMaxAnt = LineasPedido().getCellText("Outline Number", irows-1);
		if (iSubtotal > 10) {
			LineasPedido().firstRowSet();
		}
		System.out.println("Linea #:" + irows);
		System.out.println("Outline number" + LineasPedido().getCellText("Outline Number", i) );
 		//if (!LineasPedido().getCellText("Outline Number", i).equals("1")) {
 		//	System.out.println("Outline number no es 1" );
 		//}
		Boolean Iterar =  true;  
		while(Iterar) 
		{	
 			//sOutlineMaxAnt = LineasPedido().getCellText("Outline Number", irows-1);
			for(i = 0 ; i<irows;i++ ) {
				LineasPedido().activateRow(i);
				System.out.println("------------------------") ;
				System.out.println("i="+ i );
				System.out.println(LineasPedido().isRowExpanded(i) );
				System.out.println(LineasPedido().getProperty("RowsCount") );
				System.out.println("RecordCounter: " +AppletLineasPedido().getProperty("RecordCounter") );
				System.out.println("OutlineNumber: " +LineasPedido().getCellText("Outline Number", i) );
				if (!LineasPedido().isRowExpanded(i)) {
					LineasPedido().clickHier(); 
					irows = (int) LineasPedido().getProperty("RowsCount");
					
					RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();
					callScript("Scripts.Comun.RecordCount",RecordCount);
			
					iSubtotal = Integer.parseInt(RecordCount[2]);
					iTotal = Integer.parseInt(RecordCount[3]);
					
					System.out.println("New: " +LineasPedido().getCellText("Outline Number", i) );
					System.out.println("------------------------") ;
				}
			} //cierro el for
			System.out.println("Subtotal = " + iSubtotal + " Total = " + iTotal );
			System.out.println("Subtotal < Total" +  (iSubtotal < iTotal) );
			if (iSubtotal < iTotal) {
				logWarning("This is a warning", getRootTestObject().getScreenSnapshot()); 
				logInfo("This is an info message", getRootTestObject().getScreenSnapshot());
				
				LineasPedido().nextRowSet();
				RecordCount[0] = AppletLineasPedido().getProperty("RecordCounter").toString();
				callScript("Scripts.Comun.RecordCount",RecordCount);
		
				iSubtotal = Integer.parseInt(RecordCount[2]);
				iTotal = Integer.parseInt(RecordCount[3]);
			} else {
				Iterar = false; 
			}
			i=0;
			sleep(2);
			irows = (int) LineasPedido().getProperty("RowsCount");
 			//sOutlineMax = LineasPedido().getCellText("Outline Number", irows-1);
 			//System.out.println(sOutlineMaxAnt + "-" +sOutlineMax );
			sleep(2);
		} ;

		logWarning("This is a warning", getRootTestObject().getScreenSnapshot()); 
		// Ver como asegurarse estar en la pantalla correcta
		logInfo("This is an info message", getRootTestObject().getScreenSnapshot());
		ImpreResultadoScript(getScriptName( ).toString(), "Sin parametro de retorno");
		}
}

