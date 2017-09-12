package Scripts.Comun;
import resources.Scripts.Comun.ValidarCamposTerminalCM_AntHelper;
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
 * Description   : Valida si campos del terminal estan o no editables
 * @author VC
 * Script Name   : <b>ValidarCamposTerminalCM</b>
 * @since  2017/03/15
 * @Param 0)OK/NOK
 * res 
 */
public class ValidarCamposTerminalCM_Ant extends ValidarCamposTerminalCM_AntHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK" ;

		boolean Habilitado = true;
		if (argu[1].toString().toLowerCase().equals("false")){
			Habilitado = false;
		}
		
		boolean bMarcaEnabled = Boolean.parseBoolean( Marca().getProperty("IsEnabled").toString());
		boolean bModeloEnabled = Boolean.parseBoolean(Modelo().getProperty("IsEnabled").toString());
		boolean bSerieEnabled = Boolean.parseBoolean(Serie().getProperty("IsEnabled").toString());
		boolean bVersionEnabled = Boolean.parseBoolean(Version().getProperty("IsEnabled").toString());

		System.out.println("Marca is enabled? = " + bMarcaEnabled );
		System.out.println("Modelo is enabled? = " + bModeloEnabled );
		System.out.println("Serie is enabled? = " + bSerieEnabled );
		System.out.println("Version is enabled? = " + bVersionEnabled );

		IFtVerificationPoint MarcaVP = vpManual("Marca", Habilitado, Marca().getProperty("IsEnabled"));
		MarcaVP.performTest();
		IFtVerificationPoint ModeloVP = vpManual("Modelo", Habilitado, Modelo().getProperty("IsEnabled"));
		ModeloVP.performTest();
		IFtVerificationPoint SerieVP = vpManual("Serie", Habilitado, Serie().getProperty("IsEnabled"));
		SerieVP.performTest();
		IFtVerificationPoint VersionVP = vpManual("Version", Habilitado, Version().getProperty("IsEnabled"));
		VersionVP.performTest();

		
		switch (argu[1].toString().toLowerCase()) {
		case "true":
			if (bMarcaEnabled && bModeloEnabled && bSerieEnabled && bVersionEnabled) 
			{
				argu[0] = "OK";
			}
			break;
		case "false":
			if (!bMarcaEnabled && !bModeloEnabled && !bSerieEnabled && !bVersionEnabled) 
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

