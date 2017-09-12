package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.SCRW_LoginHelper;

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
 * Description   : Ejecuta el login de ScoreWEB en IE (Idem Siebel)
 * Script Name   : <b>SCRW_Login</b>
 * @since  2016/08/29
 * Autor FF
 * Ult modif ss 19 de julio 2017 se cambian el acople de todos los objetos usados para que sea mas fuerte y mas dinamico. Se elimina la version, se agrega el nomnbre del 
 * documento al browser, se ajustan multiples parametros.
 * Parametros: 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
 */
public class SCRW_Login extends SCRW_LoginHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		//System.out.println("Esta pantalla Menu SIPV abierta?:" + IEBrowserSIPV().exists());

		if (!Usuario().exists()){

			System.out.println("Esta pantalla login ScoreWEB abierta?:" + Usuario().exists());

			if (!Usuario().exists()){
				String sApp_Ambiente = "SCRWQA"; 
				if (argu.length >= 4 ) {
					sApp_Ambiente = "SCRW" + argu[3].toString();
				}
				startApp(sApp_Ambiente);
				Usuario().waitForExistence();
			}	
		}
		
		String sUsuario = argu[1].toString().trim();
		String sClave = argu[2].toString().trim();
		Usuario().setText(sUsuario);
		Password().setText(sClave);
		Ingresar().click();
		
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

