package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPagoFactTodoHelper;
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
 * Description   : Elimina el tilde de pago factura en todos los ítems de un nodo o en el especificado
 * @author SS
 * Script Name   : <b>fCheckPagoFactTodo</b>
 * @Param  0) nombre del caso 1) true / false  2) IN Ambiente 3) IN ErrorStop (Si / No)
 * ej CP46 true (habilitado) false (inhabilitado) PREQA true 20
 * @since  2016/05/13
 */
public class fPagoFactTodo extends fPagoFactTodoHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] aFactura;
		aFactura = new String[4];
		//0) OK/NOK 1) true o false 2) NA o TODO 3) Servicio 
		//Todo implica sacar todos los tildes  
		//Se dimensiona de 4 porque se usan los dos ultimos parametros
		
		String[] MensError;
		MensError = new String[4];
		
		aFactura[1]=args[1].toString().toLowerCase();
		aFactura[2]="TODO";
		aFactura[3]=dpString("Servicio");
		callScript("Scripts.Comun.CheckPagoFact", aFactura);

		if  (aFactura[0].toString().equals("NOK")){
			//MensError[0] = "Chequeo de tilde a factura falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

