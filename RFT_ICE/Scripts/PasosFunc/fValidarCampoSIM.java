package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarCampoSIMHelper;
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
 * Script Name   : <b>fValidarCampoSIM</b>
 * Description   : Valida que el campo SIM este editable o no editable
 * @Param 0) Nombre del caso 1) true (editable) false (no editable)
 * 2) Ambiente 3) Si / No para reportar error
 * ej CP46 true PREQA true
 * @since  2016/05/03
 * @author SS
 */
public class fValidarCampoSIM extends fValidarCampoSIMHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] SIM;
		SIM = new String[2];
		// Parámetros: 0)OK/NOK 1)true (editable) / false (no editable)

		SIM[1] = args[1].toString().toLowerCase();

		callScript("Scripts.Comun.ValidarCampoSIM",SIM);
		
		if  ((SIM[0].toString().equals("NOK"))){
			//MensError[0] = "Validacion de SIM falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

