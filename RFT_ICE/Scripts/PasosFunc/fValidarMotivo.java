package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarMotivoHelper;
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
 * Script Name   : <b>fValidarMotivo</b>
 * Description   : Verificar el motivo, que sea por ejemplo Cambio de plan
 * @Param 0) Nombre del caso 1) Cambio de plan, otros
 * 2) Ambiente 3) Si / No para reportar error 4)nro paso
 * ej CP46 "Cambio de plan" PREQA true
 * @since  2016/05/04
 * @author SS
 */
public class fValidarMotivo extends fValidarMotivoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Motivo;
		Motivo = new String[2];
		// Parámetros: 0)OK/NOK 1) motivo (case sentisitive)

		Motivo[1] = args[1].toString(); // sin lowercase a proposito

		callScript("Scripts.Comun.ValidarMotivo",Motivo);
		
		if  ((Motivo[0].toString().equals("NOK"))){
			//MensError[0] = "Validacion de motivo falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

