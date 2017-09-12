package Scripts.Comun;
import resources.Scripts.Comun.ValidarAgenciaHelper;
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
 * Script Name   : <b>ValidarAgencia</b>
 * Descripcion   : devuelve OK si agencia diferente de blanco, NOK caso contrario
 * @since  2015/12/27
 * @author SS
 * ultima modif
 * 	ss 23-3-2017 - se agrega port-in
 * Param 0) OK/NOK 1) tramite
 * ej res PortIn
 */
public class ValidarAgencia extends ValidarAgenciaHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "OK";
		IFtVerificationPoint agenciaVP = null;
		String sTramite;
		
		try {
			sTramite = argu[1].toString();
		} catch (Exception e) {
			sTramite = "Otro";
		}
		
		if (sTramite.equals("PortIn"))
			agenciaVP = vpManual("AgenciaPI", "", Agencia_PI().getProperty("ActiveItem"));

		if (!sTramite.equals("PortIn"))
			agenciaVP = vpManual("Agencia", "", Agencia().getProperty("ActiveItem"));
		
		if (agenciaVP.performTest()) {
			argu[0] = "NOK";
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

