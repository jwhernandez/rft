package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarUnPerfilHelper;
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
 * Script Name   : <b>fValidarUnPerfil</b>
 * Description   : Functional Test Script
 * @since  2015/12/28
 * @author Sandra
 */
public class fValidarUnPerfil extends fValidarUnPerfilHelper
{
	public void testMain(Object[] args) 
	{
		// ValidarUnPerfil
		String[] Perfil;
		Perfil = new String[2];
		// 0) OK/NOK 1)Cliente / Fact
		
		String[] MensError;
		MensError = new String[4];
		
		Perfil [1] = args[1].toString();
		callScript("Scripts.Comun.ValidarUnPerfil",Perfil);
		if (Perfil[0].toString().equals("NOK") ) {
			MensError[0] = "No se cumple validación de solo un perfil de facturación por cuenta";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

