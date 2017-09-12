package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarPlanenLineasPedidoHelper;
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
 * Script Name   : <b>fValidarPlanenLineasPedido</b>
 * Description   : Verificar el plan en la columna de las lineas del pedido
 * @Param 0) Nombre del caso 1) NA o PLAN KOLBI DATOS CONECTA 12 M 4GLTE, otros
 * 2) Ambiente 3) Si / No para reportar error 4)nro paso
 * ej CP46 "PLAN KOLBI DATOS CONECTA 12 M 4GLTE" PREQA true
 * ej CP46 NA PREQA true // en este caso toma el valor del DP
 * @since  2016/05/04
 * @author SS
 */
public class fValidarPlanenLineasPedido extends fValidarPlanenLineasPedidoHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Plan;
		Plan = new String[2];
		// Parámetros: 0)OK/NOK 1) Plan (case sentisitive) // PLAN KOLBI DATOS CONECTA 12 M 4GLTE

		if (args[1].toString().equals("NA")){
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		}
		
		Plan[1] = dpString("Plan"); // sin lowercase a proposito
		} else 		Plan[1] = args[1].toString();

		callScript("Scripts.Comun.ValidarPlanenLineasPedido",Plan);
		
		if  ((Plan[0].toString().equals("NOK"))){
			//MensError[0] = "Validacion de plan falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

