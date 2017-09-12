package Scripts.Comun;
import resources.Scripts.Comun.ValidacPostREDPagadoHelper;
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
 * Script Name   : <b>ValidacPostREDPagado</b>
 * Description   : Valida que el RED se encuentre en estado "RED Pagado"
 * que botón Valorar Todo se encuentre des-habilitado
 * que Enviar se encuentre habilitado. 
 * @Param 0) OK / NOK 1)tramite
 * @since  2015/12/27
 * @author SS
 * Ult modif 12/4/2017 ss se agrega opcion para port/in
 */
public class ValidacPostREDPagado extends ValidacPostREDPagadoHelper
{
	public void testMain(Object[] argu) 	
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="OK";
		String sTramite = argu[1].toString();
		String sEstado= null;
		boolean bValorar=false;
		boolean bEnviar=false;		
		
		// Capturar info
		
		if (!sTramite.equals("PortIn"))
		{
			sEstado= Estado().getProperty("ActiveItem").toString();
			bValorar=Valorar_Todo().isEnabled();
			bEnviar=enviarPedido().isEnabled();	
		}
		if (sTramite.equals("PortIn"))
		{
			sEstado= Estado_PI().getProperty("ActiveItem").toString();
			bValorar=Valorar_Todo_PI().isEnabled();
			bEnviar=enviarPedido_PI().isEnabled();		
		}
		
		//Verificar boton enviar inhabilitado
		IFtVerificationPoint  EstadoVP;
		EstadoVP = vpManual("Estado","RED Pagado",sEstado);
		if  (!EstadoVP.performTest()){
			argu[0]="NOK";
		} else {
 			IFtVerificationPoint  enviarVP;
			enviarVP = vpManual("BotonEnvío",true,bEnviar );
			if  (!enviarVP.performTest()){
				argu[0]="NOK";
			} else {
				//Verificar boton valorar todo inhabilitado
				IFtVerificationPoint  valorarVP;
				valorarVP = vpManual("BotonValorarTodo",false,bValorar );
				if  (!valorarVP.performTest()){
					argu[0]="NOK";
				}
			}
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

