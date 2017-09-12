package Scripts.Comun;
import resources.Scripts.Comun.ValidarCampoSIMHelper;
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
 * Description   : Valida que el campo SIM este editable o no editable 
 * Script Name   : <b>ValidarCampoSIM</b>
 * @author SS
 * @since  2016/05/03
 * @Param 0) OK/NOK  1) true (editable) false (no editable)
 */
public class ValidarCampoSIM extends ValidarCampoSIMHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		Boolean bEstadoSIMDeseado= Boolean.parseBoolean(argu[1].toString().toLowerCase()); 
		IFtVerificationPoint CampoSIMVP;
		CampoSIMVP = vpManual("CampoSIM", bEstadoSIMDeseado, SIM().getProperty("IsEnabled"));
		if (CampoSIMVP.performTest()) {
			System.out.println("SIM Editable?: " + SIM().getProperty("IsEnabled"));
			argu[0] = "OK";
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

