package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarActivoSIMenCtaFact_v1Helper;
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
 * Description   : Functional Test Script
 * 89040122
 * Script Name   : <b>ValidarActivoSIMenCtaFact</b>
 * @since  2016/11/04
 * Parametros 0) Resultado = OK/NOK 1) SIM Deseada 
 * @author VC
 * Última modificación 07/11/2016
*/
public class ValidarActivoSIMenCtaFact_v1 extends ValidarActivoSIMenCtaFact_v1Helper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK"; 

		String sNroCaso = getNroCaso();
		System.out.println("NroCaso"+ sNroCaso );

		System.out.println( "SIM " + SIM().getProperty("Text"));
		IFtVerificationPoint EstadoActivoVP;
		EstadoActivoVP = vpManual("EstadoActivo",argu[1].toString(), SIM().getProperty("Text"));
		System.out.println("Estado deseado y en SBL = " + argu[1].toString()+ "-" + SIM().getProperty("Text"));
		infoPaso(sNroCaso, Tipo.DATO,"SIM", SIM().getProperty("Text").toString());
		if (EstadoActivoVP.performTest()) argu[0]="NOK";	

		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

