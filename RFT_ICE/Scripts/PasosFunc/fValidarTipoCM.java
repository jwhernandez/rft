package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarTipoCMHelper;
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
 * Description   : Valida el tipo de CM
 * @author SS
 * Script Name   : <b>fValidarTipoCM</b>
 * @since  2016/04/11
 */
public class fValidarTipoCM extends fValidarTipoCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidaTipo;
		ValidaTipo = new String[2];
		//0) OK/NOK 1)Tipo deseado (Prepago a Postpago o Postago a Prepago)
		String[] MensError;
		MensError = new String[4];

		ValidaTipo[1]=args[1].toString();
		callScript("Scripts.Comun.ValidarTipoCM",ValidaTipo);
		
		if  (ValidaTipo[0].toString().equals("NOK")){
			//MensError[0] = "Validar Tipo no coincidió";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

