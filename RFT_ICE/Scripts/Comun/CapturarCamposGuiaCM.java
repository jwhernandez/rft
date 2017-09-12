package Scripts.Comun;
import resources.Scripts.Comun.CapturarCamposGuiaCMHelper;
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
 * Script Name   : <b>CapturarCamposGuiaCM</b>
 * Description   : Agrega los campos Nombre Guia, Num Tel y Direccion en Guia
 * @Param 0) OK/NOK 1) out Nombre 2) out Tel 3) out Direccion   	 
 * @since  2016/03/08
 * @author SS
 */
public class CapturarCamposGuiaCM extends CapturarCamposGuiaCMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "OK";

		Privado().setOff();
		InformacionPromoci().setOn();
		InformacionPromoci().setOff();
		// para actualizar la informacion
		
		argu[1]= NombreenGuia().getProperty("Text");
		argu[2]= Telefono().getProperty("Text");   
		argu[3]= Direccion().getProperty("Text");   

		IFtVerificationPoint NombreVP = vpManual("NombreGuia","",NombreenGuia().getProperty("Text"));
		IFtVerificationPoint TelGuiaVP = vpManual("TelGuia","",Telefono().getProperty("Text"));
		IFtVerificationPoint DireGuiaVP = vpManual("DireGuia","",Direccion().getProperty("Text"));

		if  (NombreVP.performTest()) 			argu[0] = "NOK";
		if  (TelGuiaVP.performTest()) 			argu[0] = "NOK";
		if  (DireGuiaVP.performTest()) 			argu[0] = "NOK";

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

