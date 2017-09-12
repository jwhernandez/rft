package Scripts.Comun;
import resources.Scripts.Comun.LoginIfHelper;
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
 * Description   : LoginIf Chequea si SBL no tiene un timeout refrescando la pantalla de SBL con F5.
 * Si es asi devuelve OK y esto permitirá no hacer login nuevamente. 
 * Caso contrario devuelve NOK y esto obligará a fLogin a hacer el login nuevamente con el script login.
 * @author SS
 * Script Name   : <b>LoginIf</b>
 * @since  2016/09/20
 * ult modif ss 20/12/2016 agregado de mensaje de reintentar
 * 			ss 09/2/2017 se evita el refresh si es el parametro de ejecutar desde inicio = false
 *          ss 22 2 2017 se evita el refresh tambien para cm mediante la variable global tramite
 * param 0)OK/NOK 1) usuario 2) clave 3)Ambiente 4)no se usa
 * res jvalerio1 jvalerio1 QA NA
 */

public class LoginIf extends LoginIfHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		String sMensaje=null;
		setLoginIf("NOK");

		System.out.println("Existe mensaje de Reintentar?:" + Reintentar().exists());
		if(Reintentar().exists()) 
		{
			sMensaje = Reintentar().getProperty(".text").toString();
			BtonReintentar().click();
			System.out.println("---------------------------------------------------------");
			System.out.println("Se acepta el mensaje de reintentar " + sMensaje);
			System.out.println("---------------------------------------------------------");
		}
		System.out.println("Existe mensaje de error?:" + ErrorMsj().exists());
		if(ErrorMsj().exists()) 
		{
			sMensaje = ErrorMsj().getProperty(".text").toString();
			Aceptar().click();
			System.out.println("---------------------------------------------------------");
			System.out.println("fLogin hará nuevamente login por " + sMensaje);
			System.out.println("---------------------------------------------------------");
		} else
		{
			System.out.println("Existe el IE de siebel?:" + IESiebel().exists());
			if (IESiebel().exists()) 
			{
				// si existe el browser de siebel y no hay timeout o SW visible refrescar y seguir		
				System.out.println("---------------------------------------------------------");
				System.out.println("Se intenta refrescar Siebel");
				System.out.println("---------------------------------------------------------");
				// Se está dentro de Siebel
				if (LogoICE().exists())
				{
					if (getRe_EjecDesdeIni() && !getTramite().equals("CM")) // ss 09/2/2017 ss 22/02/2017 se saca tambien para CM
						IESiebel(Siebel(),DEFAULT_FLAGS).inputKeys("{F5}");
					// Aquí puede volver a aparecer un mensaje de timeout
					System.out.println("Existe mensaje de error luego del refresh?:" + ErrorMsj().exists());
					if(ErrorMsj().exists()) 
					{
						sMensaje = ErrorMsj().getProperty(".text").toString();
						Aceptar().click();
						System.out.println("---------------------------------------------------------");
						System.out.println("fLogin hará nuevamente login por " + sMensaje);
						System.out.println("---------------------------------------------------------");
					} else
					{
						System.out.println("Existe mensaje de Reintentar?:" + Reintentar().exists());
						if(Reintentar().exists()) 
						{
							sMensaje = Reintentar().getProperty(".text").toString();
							BtonReintentar().click();
							System.out.println("---------------------------------------------------------");
							System.out.println("Se acepta mensaje de reintentar " + sMensaje);
							System.out.println("---------------------------------------------------------");
						}
						try 
						{
							sleep(4);
							LogoICE().waitForExistence();	
							System.out.println("---------------------------------------------------------");
							System.out.println("Se retoma siebel sin hacer el login " + sMensaje);
							System.out.println("---------------------------------------------------------");
							setLoginIf("OK");
							argu[0]="OK";
						} 
						catch (Exception e) {
							System.out.println("---------------------------------------------------------");
							System.out.println("fLogin hará nuevamente el login por Mensaje de excepción: "+e.getMessage());
							System.out.println("---------------------------------------------------------");
						}
					}
				} else 
				{
					System.out.println("---------------------------------------------------------");
					System.out.println("fLogin hará nuevamente login por no estar el logo de ICE");
					System.out.println("---------------------------------------------------------");
				}
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

