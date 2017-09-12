package Scripts.Comun;
import resources.Scripts.Comun.ValidarCampoCedCM_AntHelper;
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
 * Description   : Valida si el campo identificación esta o no editable
 * @author VC
 * Script Name   : <b>ValidarCampoCedCM</b>
 * @since  2017/03/16
 * @Param 0)OK/NOK
 * res 
 */
public class ValidarCampoCedCM_Ant extends ValidarCampoCedCM_AntHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK" ;

		boolean Habilitado = true;
		if (argu[1].toString().toLowerCase().equals("false")){
			Habilitado = false;
		}
		
		boolean bIdentificacionEnabled = Boolean.parseBoolean( Identificacion().getProperty("IsEnabled").toString());

		System.out.println("Identificacion is enabled? = " + bIdentificacionEnabled );

		IFtVerificationPoint IdentificacionVP = vpManual("Identificacion", Habilitado, Identificacion().getProperty("IsEnabled"));
		IdentificacionVP.performTest();
		
		switch (argu[1].toString().toLowerCase()) {
		case "true":
			if (bIdentificacionEnabled) 
			{
				argu[0] = "OK";
			}
			break;
		case "false":
			if (!bIdentificacionEnabled) 
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

