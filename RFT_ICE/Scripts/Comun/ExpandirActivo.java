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
* Imprime la pantalla en el Log
* Parámetros:  no recibe no retorna
* SS Nov 2015  
*/
public class ExpandirActivo extends ExpandirActivoHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		LineasActivo().waitForExistence();
		int i=0;
		int iTotal = (int) LineasActivo().getProperty("RowsCount") ;
		System.out.println(iTotal );
		
		MostrarMasMenos().performAction();
	
		i=0;
		while ( i <= iTotal-1) {	
			LineasActivo().activateRow(i);
			if (!LineasActivo().isRowExpanded(i)) {
				LineasActivo().clickHier(); 
				iTotal = (int) LineasActivo().getProperty("RowsCount");
			}
			iTotal = (int) LineasActivo().getProperty("RowsCount");
			i++;
		} 
 		logWarning("This is a warning", getRootTestObject().getScreenSnapshot());
 		logInfo("This is an info message", getRootTestObject().getScreenSnapshot());
		ImpreResultadoScript(getScriptName( ).toString(), "Sin parametro de resultado");

	}
}

