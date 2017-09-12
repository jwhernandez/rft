package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidaRedReversadoHelper;
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
 * Description   : Validación post reversar un RED 
 * Script Name   : <b>ValidaRedReversado</b>
 * @since  2016/05/25
 * @author SS
 * @Param 0) OK/NOK 1) NroRED 2)Tramite	
 * ej res nrored
 */
public class ValidaRedReversado extends ValidaRedReversadoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="OK";
		String sTramite = argu[2].toString();
		String sEstadoSBL=null;
		String sNROSBL=null;
		
		if (!sTramite.equals("PortIn")) 
		{
			sEstadoSBL = EstadoRED().getProperty("ActiveItem").toString();
			sNROSBL = NroRED().getProperty("Text").toString();
		}
		if (sTramite.equals("PortIn")) 
		{
			sEstadoSBL = EstadoRED_PI().getProperty("ActiveItem").toString();
			sNROSBL = NroRED_PI().getProperty("Text").toString();
		}
			
		IFtVerificationPoint EstadoRED = vpManual("EstadoRED", "Por pagar", sEstadoSBL);
		if (!EstadoRED.performTest()) argu[0] = "NOK"; 
		
		IFtVerificationPoint NroRED = vpManual("NroRED", argu[1].toString().trim(), sNROSBL);
		if (!NroRED.performTest()) argu[0] = "NOK"; 
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

