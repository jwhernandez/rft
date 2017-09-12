package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ASRM_ConsultarEstadoServicioHelper;

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
 * Description   : Consulta el estado de un servicio en ASRM
 * Script Name   : <b>ASRM_ConsultarEstadoServicio</b>
 * @since  2016/10/14
 * Parametros: 0) Resultado = OK/NOK 1) Servicio 2) estadodeseado
 * ej  res  89520124 RESERVADO
 * @author SS
 */
public class ASRM_ConsultarEstadoServicio extends ASRM_ConsultarEstadoServicioHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sEstadoLeido=null;
		String sNroLeido=null;
		
		Tabs().click(atName("Recurso Numeros"));
		
		NroServicio().ensureObjectIsVisible();
		NroServicio().setProperty(".value", argu[1].toString());
		
		if (Informacion().exists()) okbutton().click();
		else 
		{
			sNroLeido = NroConsultado().getProperty(".value").toString();
			sEstadoLeido = Estado().getProperty(".value").toString();		

			if (sNroLeido.equals(argu[1]) && sEstadoLeido.equals(argu[2]) ) argu[0] = "OK";

			IFtVerificationPoint EstadoNROVP = vpManual("EstadoNroServicio", argu[2].toString(), Estado().getProperty(".value"));
			EstadoNROVP.performTest(); 
		}			
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

