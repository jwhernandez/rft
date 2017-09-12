package Scripts.Comun;
import resources.Scripts.Comun.ValidarActivoenCtaFact_AccionHelper;

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
 * Script Name   : <b>ValidarActivoenCtaFact_Accion</b>
 * Description   : Valida el tipo de pedido en el activo 
 * Parametros: 0) OK / NOK  1) accion de pedido 2) nro pedido
 * @since  2017/04/26
 * @author SS	
 * ej res  Venta 1-1753707433
  */
public class ValidarActivoenCtaFact_Accion extends ValidarActivoenCtaFact_AccionHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		String sAccion = argu[1].toString().toLowerCase();
		String spedido = argu[2].toString();
		
		NuevaConsulta().performAction();
		nroPedido().setText(spedido);
		EjecConsulta().performAction();
		sleep(2);
		
	
		String sAccionDeseada = Accion().getProperty("Text").toString().toLowerCase();
		if (sAccionDeseada.equals(sAccion)) 		argu[0]="OK";
		
 		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

