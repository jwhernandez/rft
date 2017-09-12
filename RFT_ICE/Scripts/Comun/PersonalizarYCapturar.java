package Scripts.Comun;
import resources.Scripts.Comun.PersonalizarYCapturarHelper;
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
* Descripción: Presiona personalizar desde la linea en la que se encuentra y captura pantalla
* @Param 0) OK/NOK            
* @since  2016/05/04 
* Script Name   : <b>PersonalizarYCapturar</b>
* @author SS
* ej: res
*/
public class PersonalizarYCapturar extends PersonalizarYCapturarHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
	
		argu[0]="NOK";
		BtonPersonalizar().performAction();
		
		String[] CapPant;
		CapPant = new String[2];
		//0)OK/NOK 1)Futuros usos
		
		callScript("Scripts.Comun.CapturaPantalla", CapPant);
		
		Terminado().click();
				
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

