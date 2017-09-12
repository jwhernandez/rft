package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.SIPV_LoginHelper;
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
 * Description   : Ejecuta el login de SIPV en IE (Idem Siebel)
 * Script Name   : <b>SIPV_Login</b>
 * @since  2016/08/29
 * Parametros: 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
 * Última modificación VC 20170331
 *  110440882 110440882 
 */
public class SIPV_Login extends SIPV_LoginHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		sleep(5);
		System.out.println("Esta pantalla Menu SIPV abierta?:" + IEBrowserSIPV().exists());

		if (!Usuario().exists()){
			
			sleep(5);
			System.out.println("Esta pantalla login SIPV abierta?:" + Usuario().exists());

			if (!Usuario().exists()){
				String sApp_Ambiente = "SIPVQA"; 
				if (argu.length >= 4 ) {
					sApp_Ambiente = "SIPVQA";
				}
				startApp(sApp_Ambiente);
				sleep(5);
				Usuario().waitForExistence();
			}		
		}
		
		String sUsuario = argu[1].toString().trim();
		String sClave = argu[2].toString().trim();
		
		Usuario().setProperty("Text", sUsuario); // VC 20170331 Se cambia el método en el que se ingresa el usuario

		Clave().setProperty("Text", sClave); // VC 20170331 Se cambia el método en el que se ingresa la clave

		sleep(2);
		IniciarSesion().click();

		Menu_Salir().waitForExistence();
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

