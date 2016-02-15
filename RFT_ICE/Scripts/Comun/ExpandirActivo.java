package Scripts.Comun;
import resources.Scripts.Comun.ExpandirActivoHelper;
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
* Descripción: Expande todas las lineas del activo para busquedas o print de pantalla
* Parámetros:  no recibe no retorna
* SS Nov 2015  
*/
public class ExpandirActivo extends ExpandirActivoHelper
{
	public void testMain(Object[] args) 
	{
		LineasActivo().waitForExistence();
		int i=0;
		int iTotal = (int) LineasActivo().getProperty("RowsCount") ;
		System.out.println(iTotal );
		
		MostrarMasMenos().performAction();
	
		i=0;
		while ( i <= iTotal-1) {	
			LineasActivo().activateRow(i);
//			System.out.println(i );
//			System.out.println(LineasActivo().isRowExpanded(i));
//			System.out.println(LineasActivo().getProperty("RowsCount") );
//			System.out.println("------------------------") ;
			if (!LineasActivo().isRowExpanded(i)) {
				LineasActivo().clickHier(); 
				iTotal = (int) LineasActivo().getProperty("RowsCount");
			}
			//LineasActivo().nextRow();
			iTotal = (int) LineasActivo().getProperty("RowsCount");
			i++;
		} 
		
 		logWarning("This is a warning", getRootTestObject().getScreenSnapshot());
 		logInfo("This is an info message", getRootTestObject().getScreenSnapshot());
	}
}
// hacer un catch de la excepcion y colocar un nextrowset
//exception_context = ActivateRow() invocado en siebel.SiebListTestObject(Nombre: LineasActivo, Correlación: SiebList).
//exception_name = com.rational.test.ft.Domain.Siebel.SiebelItemNotFoundException
//exception_message = SiebList.ActivateRow(7), Row index 7 is not valid or visible; Valid range is [0..6];
//script_name = Scripts.Comun.ExpandirActivo
//script_id = Scripts.Comun.ExpandirActivo.java
//line_number = 33
