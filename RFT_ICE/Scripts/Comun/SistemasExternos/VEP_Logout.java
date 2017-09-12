package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.VEP_LogoutHelper;
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
 * Description   : Ejecuta el logout de VEP en IE (idem siebel)
 * @author SS
 * Script Name   : <b>VEP_Logout</b>
 * @since  2016/08/29
 * Parametros: 0) Resultado = OK/NOK  
 * Última modificación VC 20170419 Se cambia el if por un try-catch ya que IEBrowserVEP().exists() no funciona correctamente
 */
public class VEP_Logout extends VEP_LogoutHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		if (Menu_Salir().exists()){
			System.out.println("Saliendo del sistema VEP:");
			Menu_Salir().click();
		}
		//VC 20170419 Se cambia el if por un try-catch ya que IEBrowserVEP().exists() no funciona correctamente
		try{
			IEBrowserVEP().close();
			System.out.println("Cerrando el browser de VEP");
		}catch(Exception e){
			System.out.println("No se pudo cerrar la ventana de VEP " + e);
		}
		/*if (IEBrowserVEP().exists())
		{
			IEBrowserVEP().close();
			System.out.println("Cerrando el browser de VEP:");
		}*/
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}
