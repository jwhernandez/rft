package Scripts.Comun;
import resources.Scripts.Comun.ValidacPostEnvio1Helper;
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
 * Description   : Chequea si el estado del pedido es completar y si el botón 
 * volver a valorar está inhabilitado
 * Script Name   : <b>ValidacPostEnvio1</b>
 * 0) OK/NOK si el pedido está en completar 1) OK/NOK si el botón está en inhabilitar
 * @author SS
 */
public class ValidacPostEnvio1 extends ValidacPostEnvio1Helper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "OK";
		argu[1] = "OK";


		//  Chequear que el pedido este en completado
		IFtVerificationPoint  pedidoVP;
		pedidoVP = vpManual("EstadoPedido","Completar",EstadoPedidoVta().getProperty("ActiveItem"));
		if  (!pedidoVP.performTest()){
			argu[0] = "NOK";
		} 
		//  Chequear que el boton valorar todo este en inhabilitado
		IFtVerificationPoint  valorarVP;
		valorarVP = vpManual("ValorarTodo",false,VolveraValorar().isEnabled());
		if  (!valorarVP.performTest()){
			argu[1] = "NOK";
		} 
		
		System.out.println("Resultado de ValidacPostEnvio1: " + argu[0] + "-" + argu[1]);
		logInfo("Resultado de ValidacPostEnvio: " + argu[0] + "-" + argu[1]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}