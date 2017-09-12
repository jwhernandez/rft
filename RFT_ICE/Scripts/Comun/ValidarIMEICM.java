package Scripts.Comun;
import resources.Scripts.Comun.ValidarIMEICMHelper;
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
 * Description   : Validar IMEI contra el del activo (que debe figurar en el DP)
 * @author SS
 * @since  2016/04/15
 * Script Name   : <b>ValidarIMEICM</b>
 * @Param 0) OK/NOK 1) IMEI del activo (Vacio para indicar que esté vacio)
 */
public class ValidarIMEICM extends ValidarIMEICMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sValor = null;
		sValor = argu[1].toString().toLowerCase();
		System.out.println(IMEI().getProperty("Text"));
		
		IFtVerificationPoint IMEIVP;
		if (sValor.equals("vacio")) {
			IMEIVP = vpManual("IMEI", "", IMEI().getProperty("Text"));
		} else  {
			IMEIVP = vpManual("IMEI", sValor, IMEI().getProperty("Text"));
		}
		if (IMEIVP.performTest()) {
			System.out.println("IMEI valido");
			argu[0] = "OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

