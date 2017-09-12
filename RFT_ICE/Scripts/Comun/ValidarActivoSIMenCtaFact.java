package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarActivoSIMenCtaFactHelper;
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
*/
public class ValidarActivoSIMenCtaFact extends ValidarActivoSIMenCtaFactHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK"; 

		String sScriptName=getScriptName().toString(); // 22/11/2016
		String sSIM = null;

		for (int i=0;i<=3; i++)
		{
			try
			{	
				int iActiveRow = Integer.parseInt(LineasActivoCtaFact().getProperty("ActiveRow").toString());
				sSIM = LineasActivoCtaFact().getCellText("ICE SIM",iActiveRow);
				//SIM = SIM().getProperty("Text").toString();
				i= 100;
			}
			catch (Exception e){
				logInfo("Mensaje de excepción: "+e.getMessage());
				System.out.println("Mensaje de excepción: "+e.getMessage());
				// Se refresca Siebel si no logro capturar el SIM
				IESiebel(Siebel(),DEFAULT_FLAGS).inputKeys("{F5}");
				sleep (10);
			}
		} // end del while de reintentar
		System.out.println( "SIM " + sSIM);
		IFtVerificationPoint EstadoActivoVP;
		EstadoActivoVP = vpManual("EstadoActivo",argu[1].toString(), sSIM);
		System.out.println("Estado deseado y en SBL = " + argu[1].toString()+ "-" +sSIM);
		infoPaso(sScriptName, Tipo.DATO,"SIM", sSIM);
		if (EstadoActivoVP.performTest()) argu[0]="OK";	

		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

