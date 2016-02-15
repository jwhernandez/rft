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
 * Script Name   : <b>ValidacPostEnvio</b>
 * Description   : Valida que el RED se encuentre en estado "RED Pagado"
 * que botón Valorar Todo se encuentre des-habilitado
 * que Enviar se encuentre habilitado. 
 * @Param 0) OK / NOK
 * @since  2015/12/27
 * @author Sandra
 */
public class ValidacPostREDPagado extends ValidacPostREDPagadoHelper
{
	public void testMain(Object[] argu) 
	{
		argu[0]="OK";

		//Verificar boton enviar inhabilitado
		IFtVerificationPoint  EstadoVP;
		EstadoVP = vpManual("Estado","RED Pagado",Estado().getProperty("ActiveItem"));
		if  (!EstadoVP.performTest()){
			argu[0]="NOK";
		} else {
			Estado().getProperty("ActiveItem");
			IFtVerificationPoint  enviarVP;
			enviarVP = vpManual("BotonEnvío",true,enviarPedido().isEnabled() );
			if  (!enviarVP.performTest()){
				argu[0]="NOK";
			} else {
				//Verificar boton valorar todo inhabilitado
				IFtVerificationPoint  valorarVP;
				valorarVP = vpManual("BotonValorarTodo",false,Valorar_Todo().isEnabled() );
				if  (!valorarVP.performTest()){
					argu[0]="NOK";
				}
			}
		}
		System.out.println("Resultado de ValidacPostREDPagado: " + argu[0]);
		logInfo("Resultado de ValidacPostREDPagado: " + argu[0]);
	}
}

