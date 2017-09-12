package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidaCampoEstadoHelper;
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
 * Script Name   : <b>fValidaCampoEstado</b>
 * Descripcion   : Chequea que el campo estado permite o no modificacion
 * @Param  0) nombre del caso 1) NA  2) IN Ambiente 3) IN ErrorStop (Si / No)
 * ej CP46 true (habilitado) false (inhabilitado) PREQA true 20
 * @since  2016/05/04
 * @author SS
 */
public class fValidaCampoEstado extends fValidaCampoEstadoHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Estado;
		Estado = new String[2];
		//@Param 0)  ok/nok 1) true false
		
		String[] MensError;
		MensError = new String[4];
		
		Estado[1]=args[1].toString().toLowerCase();
		callScript("Scripts.Comun.ValidaCampoEstado", Estado);

		if  (Estado[0].toString().equals("NOK")){
			//MensError[0] = "Validación estado en la línea falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

