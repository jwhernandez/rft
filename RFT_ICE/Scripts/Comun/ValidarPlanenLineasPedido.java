package Scripts.Comun;
import resources.Scripts.Comun.ValidarPlanenLineasPedidoHelper;
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
 * Description   : Verificar el plan en la columna de las lineas del pedido
 * Script Name   : <b>ValidarPlanenLineasPedido</b>
 * @author SS
 * @since  2016/05/04
 * @Param 0) OK/NOK  1) plan
 * ej res "PLAN KOLBI DATOS CONECTA 12 M 4GLTE"
 */
public class ValidarPlanenLineasPedido extends ValidarPlanenLineasPedidoHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		String sPlanDeseado= argu[1].toString(); // debe ser case sensitive 
		IFtVerificationPoint PlanVP;
		PlanVP = vpManual("Plan", sPlanDeseado, Plan().getProperty("Text"));
		if (PlanVP.performTest()) {
			System.out.println("Plan: " + Plan().getProperty("Text"));
			argu[0] = "OK";
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

