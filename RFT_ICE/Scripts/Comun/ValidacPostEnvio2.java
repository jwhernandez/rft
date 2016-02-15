package Scripts.Comun;
import resources.Scripts.Comun.ValidacPostEnvio2Helper;
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
 * Script Name   : <b>ValidacPostEnvio2</b>
 * Description   : Que SimTech esté inhabilitado, que IMSI este inahabilitado (esto solo en la línea del servicio). 
 * @ Param	0) OK / NOK
 * Precondiciones: estar en el servicio
 * @since  2015/12/27
 * @author Sandra
 */
public class ValidacPostEnvio2 extends ValidacPostEnvio2Helper
{
	public void testMain(Object[] argu) 
	{
		argu[0] = "OK";

		//  Chequear que el pedido este en completado
		IFtVerificationPoint  pedidoVP;
		pedidoVP = vpManual("EstadoPedido","Completar",EstadoPedidoVta().getProperty("ActiveItem"));
		if  (!pedidoVP.performTest()){
			argu[0] = "NOK";
		} else {
			//  Chequear que el boton valorar todo este en inhabilitado
			IFtVerificationPoint  valorarVP;
			valorarVP = vpManual("ValorarTodo",false,VolveraValorar().isEnabled());
			if  (!valorarVP.performTest()){
				argu[0] = "NOK";
			} else {
				IFtVerificationPoint  SimTechVP;
				SimTechVP = vpManual("SimTech",false,SIMTech().isEnabled() );
				if  (!SimTechVP.performTest()){
					argu[0] = "NOK";
				} else {
					// El campo IMSI y Tecnologia NO deben estar editable
					IFtVerificationPoint  IMSIVP;
					IMSIVP = vpManual("IMSI",false,IMSI().isEnabled() );
					if  (!IMSIVP.performTest()){
						argu[0] = "NOK";
					}
				}
			}
		}
	
		System.out.println("Resultado de ValidacPostEnvio: " + argu[0]);
		logInfo("Resultado de ValidacPostEnvio: " + argu[0]);
	}
}

