package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.REMO_LogoutHelper;
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
 * Description   : Ejecuta el logout de REMO en IE (Idem Siebel)
 * Script Name   : <b>REMO_Logout</b>
 * @since  2017/03/13
 * Parametros: 0) OK/NOK  
 * ej res  
 * @author SS
 */
public class REMO_Logout extends REMO_LogoutHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		if (CambiarSesión().exists()){
			System.out.println("Saliendo del sistema REMO:");
			CambiarSesión().click();
			sleep(2);
			REMOIE(REMO(),DEFAULT_FLAGS).close();
		}
		if (Usuario().exists())
		{
			REMOIE(REMO(),DEFAULT_FLAGS).close();
			System.out.println("Cerrando el browser de REMO:");
			
		}
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

