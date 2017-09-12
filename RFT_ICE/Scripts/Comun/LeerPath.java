package Scripts.Comun;
import resources.Scripts.Comun.LeerPathHelper;
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
 * Description   : Lee el path donde se encuentran los datos de prueba
 * @author SS
 * Script Name   : <b>LeerPath</b>
 * @since  2016/05/13
 * @Param 0)OK/NOK 1) path
 */
public class LeerPath extends LeerPathHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK"; 	
		String sPath = dpString ("Path");
		argu[1]=sPath;
		System.out.println("El path de parametria es=" + sPath);
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

