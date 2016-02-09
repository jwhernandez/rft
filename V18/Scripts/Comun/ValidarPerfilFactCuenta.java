package Scripts.Comun;
import resources.Scripts.Comun.ValidarPerfilFactCuentaHelper;
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
 * Descripcion     : Valida el tipo de perfil de facturacion de la cuenta
 * Parámetros	   : 0) Prepago / Postpago / Otros 1) OK/NOK 
 * Pre-condiciones : Estar en la vista 360
 * SS Nov 2015
 */
public class ValidarPerfilFactCuenta extends ValidarPerfilFactCuentaHelper
{
	public void testMain(Object[] argu) 
	{
		argu[1] = "OK";
		
		System. out.println("ValordeEntrada" + argu[0].toString()); 
		System. out.println("ValordeComp" + PerfilFact().getCellText("Payment Type", 0));

		IFtVerificationPoint  perfilVP = vpManual("Perfil",argu[0].toString(),PerfilFact().getCellText("Payment Type", 0) );
		if  (!perfilVP.performTest()){
			argu[1] = "NOK";
		}
	}
}

