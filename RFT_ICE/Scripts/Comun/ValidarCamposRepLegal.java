package Scripts.Comun;
import resources.Scripts.Comun.ValidarCamposRepLegalHelper;
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
 * Description   : Validar Campos Rep Legal
 * @author SS
 * Script Name   : <b>ValidarCamposRepLegal</b>
 * @since  2016/04/15
 * @Param 0) OK/ NOK 1) true (Habilitado) / false (Inhabilitado)
 */
public class ValidarCamposRepLegal extends ValidarCamposRepLegalHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		boolean Habilitado = true;
		if (argu[1].toString().toLowerCase().equals("false")){
			Habilitado = false;
		}

		boolean bCedulaEnabled = Boolean.parseBoolean( CedulaRepresentante().getProperty("IsEnabled").toString());
		boolean bNombreEnabled = Boolean.parseBoolean(NombreRepresentante().getProperty("IsEnabled").toString());

		System.out.println("Cedula is enabled? = " + bCedulaEnabled );
		System.out.println("Nombre is enabled? = " + bNombreEnabled );

		IFtVerificationPoint CedulaVP = vpManual("CedulaRepLegal", Habilitado, CedulaRepresentante().getProperty("IsEnabled"));
		CedulaVP.performTest();

		IFtVerificationPoint NombreVP = vpManual("NombreRepLegal", Habilitado, NombreRepresentante().getProperty("IsEnabled"));
		NombreVP.performTest();

		
		switch (argu[1].toString().toLowerCase()) {
		case "true":
			if (bCedulaEnabled && bNombreEnabled) 
			{
				argu[0] = "OK";
			}
			break;
		case "false":
			if (!bCedulaEnabled && !bNombreEnabled ) 
			{
				argu[0] = "OK";
			}
			break;
		default:  
			break;
		} // end del switch

		System.out.println("Resultado: " + argu[0]);
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

