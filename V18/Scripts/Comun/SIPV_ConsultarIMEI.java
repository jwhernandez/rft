package Scripts.Comun;
import resources.Scripts.Comun.SIPV_ConsultarIMEIHelper;
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
 * Script Name   : <b>SIPV_Login</b>
 * Description   : Functional Test Script
 * @since  2015/12/31
 * @author Sandra
 */
public class SIPV_ConsultarIMEI extends SIPV_ConsultarIMEIHelper
{
	public void testMain(Object[] argu) 
	{
		startApp("SIPV");
		
		Usuario().click();
		Usuario().setText(dpString("SIPVUsuario"));
		//".contentText"
		Clave().click();
		Clave().setText(dpString("SIPVClave"));
		IniciarSesion().click();
		
		Menu_RebajosSalidas().waitForExistence();
		Menu_RebajosSalidas().hover();
		Menu_ConsultaIMEI().click();
		
		NroIMEI().waitForExistence();
		NroIMEI().setText(argu[0].toString());
		BotónBuscarIMEI().click();
		
		
		Menu_Salir().hover();
		Menu_CerrarSesion().click();
		IniciarSesion().waitForExistence();
		
		IEBrowser(SistemaDeInventarios(),DEFAULT_FLAGS).close();
		
			
	}
}

