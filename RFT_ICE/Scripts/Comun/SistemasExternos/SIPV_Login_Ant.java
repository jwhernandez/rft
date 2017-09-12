package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.SIPV_Login_AntHelper;
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
 */
public class SIPV_Login_Ant extends SIPV_Login_AntHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		System.out.println("Esta pantalla Menu SIPV abierta?:" + IEBrowserSIPV().exists());

		if (!Usuario().exists()){

			System.out.println("Esta pantalla login SIPV abierta?:" + Usuario().exists());

			if (!Usuario().exists()){
				String sApp_Ambiente = "SIPVQA"; 
				if (argu.length >= 4 ) {
					sApp_Ambiente = "SIPVQA";
				}
				startApp(sApp_Ambiente);
				Usuario().waitForExistence();
			}		
		}
		
		//String sUsuario = argu[1].toString().trim();
		//String sClave = argu[2].toString().trim();
		
		Usuario().setText("12345");
		Clave().setText("12354");
		IniciarSesion().click();

		Menu_Salir().waitForExistence();
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

