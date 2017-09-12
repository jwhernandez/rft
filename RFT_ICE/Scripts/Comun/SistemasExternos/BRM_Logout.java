package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.BRM_LogoutHelper;

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
 * Description   : Ejecuta el logout de BRM
 * @author SS
 * Script Name   :  <b>BRM_Logout</b>
 * @since  2016/11/11
 * Ult modif ss 09/02/2017 para que el logout no de error cuando no se hizo el login
 * Parametros: 0) Resultado = OK/NOK  
 * ult modif ss 01 03 2017 se incrementa el timing antes de preguntar por si cerro el ccenter
 */
public class BRM_Logout extends BRM_LogoutHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		try {
			if (CustomerCenter().exists())
			{
				if (MenuFile().exists()){
					System.out.println("Saliendo del sistema BRM:");
			
					// Frame: Customer Center
					MenuBar().click(atPath("File"));
					MenuBar().click(atPath("File->Exit"));
				}
				sleep(10); // Se incrementa el timing 
			}
			if (!CustomerCenter().exists()) argu[0] = "OK";
		} catch (Exception e) {
			argu[0] = "OK";
		}

		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

