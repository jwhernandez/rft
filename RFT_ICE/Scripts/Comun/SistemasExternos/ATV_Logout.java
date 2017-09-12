package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ATV_LogoutHelper;
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
 * Description   : Ejecuta el logout de ATV en IE (Idem Siebel)
 * Script Name   : <b>ATV_Logout</b>
 * @since  2016/09/16
 * Parametros: 0) Resultado = OK/NOK 1 
 * res atvgfernandez asp128 QA
 */
public class ATV_Logout extends ATV_LogoutHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		
		if (IEATV().exists())
		{
			if(Salir().exists()) {
				Salir().click();
				Usuario().waitForExistence();
			}
				IEATV().close();
				argu[0] = "OK";
		} else argu[0]="OK"; // ya estaba cerrado
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

