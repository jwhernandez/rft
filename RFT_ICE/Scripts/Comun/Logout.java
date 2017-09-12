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
 * @Param 0)OK/NOK 1) NA o SBL (para solo cerrar SBL y no IE)
 * ult modif ss 1 03 2017 se agrega opcion de solo cerrar SBL con el valor cambiousuario
 */
public class Logout extends LogoutHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		//String[] CerrarTM;
		//CerrarTM = new String[2];
		String[] CerrarIE;
		CerrarIE = new String[1];
		boolean bCerrarIE = true;
		
		if (argu.length >= 2) if (argu[1].toString().toLowerCase().equals("cambiousuario")) bCerrarIE = false;
		
		System.out.println("Cerrar IE: " + bCerrarIE);
		
		argu[0]="NOK";
		Menu().select(atPath("File->Logout"));
		
		if (bCerrarIE)
		{
			BrowserIE(Siebel(),DEFAULT_FLAGS).close();
			sleep(10);

			// Recorre las instancias abiertas de IE y las cierra
			callScript("Scripts.Comun.CerrarIE",CerrarIE);
			// Si está IE colgado lo cierra vía administrador de tareas
			//CerrarTM [1]= "iexplore.exe";
			//callScript("Scripts.Comun.CerrarProcesosAdminTareas",CerrarTM);
			// Se valida este parametro para que no se haga en un cambio de usuario
			/*if (argu[4].toString().toLowerCase().equals("true")) 
		{
			CerrarTM [1]= "siebelax_tes";
			callScript("Scripts.Comun.CerrarProcesosAdminTareas",CerrarTM);
		}*/

			/*if (CerrarTM[0].equals("OK")) {*/	
		}
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado: " + argu[0].toString());
	}
}

