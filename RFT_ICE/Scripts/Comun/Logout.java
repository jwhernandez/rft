package Scripts.Comun;
import resources.Scripts.Comun.LogoutHelper;
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
 * Script Name   : <b>Logout</b>
 * Description   : Desconecta Siebel y el IE
 * @since  2015/12/13
 * @author Sandra
 */
public class Logout extends LogoutHelper
{
	public void testMain(Object[] args) 
	{
		Menu().select(atPath("File->Logout"));
		BrowserIE(Siebel(),DEFAULT_FLAGS).close();
		sleep(10);
		// faltaria complementar con abrir el administrador de tareas y bajar toda instancia de IE
	}
}

