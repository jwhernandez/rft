package Scripts.Comun;
import resources.Scripts.Comun.ValidarIdServicioHelper;
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
 * Description   : Validar en el id de servicio en el pedido
 * Con la opción Valor el contenido y con las opciones true o false si está habilitado o inhabilitado
 * 
 * @author SS
 * Script Name   : <b>ValidarIdServicio</b>
 * @since  2017/03/23
 */
public class ValidarIdServicio extends ValidarIdServicioHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		IFtVerificationPoint ServVP = null;
		String sTramite;
		
		String sAccion = argu[1].toString().toLowerCase();
		//String sIdDeseado = argu[2].toString();
		sTramite = argu[3].toString();
		boolean bHabilitado = true;
		if (sAccion.equals("true")) bHabilitado = true;
		if (sAccion.equals("false")) bHabilitado = false;
		
		
		switch (sAccion) {
		case "valor":
			System.out.println("Opcion no implementada");
			// Se chequeará el valor del campo
//			if (sTramite.equals("PortIn"))
//				ServVP = vpManual("ServPI", sIdDeseado, IdServicio_PI().getProperty("Text"));
//
//			if (!sTramite.equals("PortIn"))
//				System.out.println("Opcion no implementada");
//				//ServVP = vpManual("Serv", sIdDeseado, IdServicio().getProperty("Text"));
//			
//			if (pedidoVP.performTest()) {
//				argu[0] = "OK";
//			}
			break;
		case "true":
		case "false":
			// Se chequeará que el campo esté habilitado o inhabilitado
			
			if (sTramite.equals("PortIn"))
				ServVP = vpManual("ServPI", bHabilitado, IdServicio_PI().getProperty("IsEnabled"));

			if (!sTramite.equals("PortIn"))
				System.out.println("Opcion no implementada");
				//ServVP = vpManual("Serv", bHabilitado, IdServicio().getProperty("IsEnabled"));
			
			if (ServVP.performTest()) {
				argu[0] = "OK";
			}

			break;
		default:
			System.out.println("Opcion no implementada");
			break;
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

