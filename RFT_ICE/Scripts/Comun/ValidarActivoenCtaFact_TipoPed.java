package Scripts.Comun;
import resources.Scripts.Comun.ValidarActivoenCtaFact_TipoPedHelper;

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
 * Script Name   : <b>ValidarActivoenCtaFact_TipoPed</b>
 * Description   : Valida el tipo de pedido en el activo 
 * Parametros: 0) OK / NOK  1) Tipo de pedido 2) nro pedido
 * @since  2017/04/26
 * @author SS	
 * ej res  Port-In 1-1753707433
  */
public class ValidarActivoenCtaFact_TipoPed extends ValidarActivoenCtaFact_TipoPedHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		String sTipo = argu[1].toString().toLowerCase();
		String spedido = argu[2].toString();

		NuevaConsulta().performAction();
		nroPedido().setText(spedido);
		EjecConsulta().performAction();
		sleep(2);
		
		String sTipoDeseado = TipoPedido().getProperty("Text").toString().toLowerCase();
		if (sTipoDeseado.equals(sTipo)) 		argu[0]="OK";
		
		System.out.println(sTipoDeseado);
		System.out.println(sTipo);
 
 		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

