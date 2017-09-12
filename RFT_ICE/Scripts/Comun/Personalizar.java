package Scripts.Comun;
import resources.Scripts.Comun.PersonalizarHelper;
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
* Descripción: Presiona personalizar desde la linea en la que se encuentra 
* @Param 0) OK/NOK            
* @since  2016/05/04 
* Script Name   : <b>Personalizar</b>
* @author SS
* ej: res
*/
public class Personalizar extends PersonalizarHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		BtonPersonalizar().performAction();
		
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

