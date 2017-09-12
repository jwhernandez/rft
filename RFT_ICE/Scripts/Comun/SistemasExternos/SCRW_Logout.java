package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.SCRW_LogoutHelper;

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
 * Description   : Ejecuta el logout de ScoreWEB en IE (idem siebel)
 * @author FF
 * Script Name   :  <b>SCRW_Logut</b>
 * @since  2016/12/26
 * Parametros: 0) Resultado = OK/NOK  
 * Ult modif ss 20 junio 2017 se arreglan todos los objetos para que no sean dependientes de la version etc sino que tengan 
 * propiedades dinamicas 
 * Se modifica el método click por hover
 * Se agrega el waitforexistance
 * ej res 
 */
public class SCRW_Logout extends SCRW_LogoutHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		if (OpcionesDelSistema().exists()){
			System.out.println("Saliendo del sistema ScoreWeb:");
			OpcionesDelSistema().hover();	// ss 19 junio 2017
			SalirDelSistema().waitForExistence(); // ss 19 junio 2017
			SalirDelSistema().click();
			//IEBrowserSCRW().close();
			//argu[0] = "OK";
		}
		if (IEBrowserSCRW().exists())
		{
			IEBrowserSCRW().close();
			System.out.println("Cerrando el browser de ScoreWEB:");
			
		}
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

