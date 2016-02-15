package Scripts.Comun;
import resources.Scripts.Comun.ValidarAgenciaHelper;
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
 * Script Name   : <b>ValidarAgencia</b>
 * Descripcion   : devuelve OK si agencia diferente de blanco, NOK caso contrario
 * @since  2015/12/27
 * @author SS
 */
public class ValidarAgencia extends ValidarAgenciaHelper
{
	public void testMain(Object[] argu) 
	{
		argu[0] = "OK";
		IFtVerificationPoint agenciaVP = vpManual("Agencia", "", Agencia().getProperty("ActiveItem"));
		if (agenciaVP.performTest()) {
			argu[0] = "NOK";
		}
	}
}

