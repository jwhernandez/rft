package Scripts.Comun;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import resources.Scripts.Comun.LeerDPParametriaHelper;
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
 * Description   : Se lee el DP de parametria
 * Script Name   : <b>LeerDPParametria</b>
 * @author SS
 * @since  2016/11/03
 * args 0) OK/NOK  
 * ej res  
 */
public class LeerDPParametria extends LeerDPParametriaHelper
{
	public void testMain(Object[] argu) throws RationalTestException, FileNotFoundException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

			dpReset();
			
			setConsolaaFile(dpBoolean("ConsolaaFile"));
			setActDPPasos(dpBoolean("ActDPPasos"));
			setRe_EjecDesdeIni(dpBoolean("Re_EjecDesdeIni"));
	
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

