package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarActivoenCtaFact_TipoPedHelper;

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
/**fValidarActivoenCtaFact_TipoPed
 * Script Name   : <b>fValidarActivoenCtaFact_TipoPed</b>
 * Description   : Valida el tipo de pedido en el activo 
 * 
 * @since  2017/04/26
 * @author SS
 * ej CP26_CD1_T1 tipo QA NA NA 
  */
public class fValidarActivoenCtaFact_TipoPed extends fValidarActivoenCtaFact_TipoPedHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
 		String[] Validar;
 		Validar = new String[3];
		// Parametros: 0) OK / NOK  1) Tipo de pedido 2) nro pedido
 
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		Validar[1]=dpString("TipoPedido");
		Validar[2]=getNroPedido();
 		callScript("Scripts.Comun.ValidarActivoenCtaFact_TipoPed", Validar);
		if (Validar[0].toString().equals("NOK")) {
			MensError[0] = "Validacion de tipo pedido incorrecto";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

