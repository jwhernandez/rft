package Scripts.Comun;

import resources.Scripts.Comun.Vista360Helper;
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
import com.rational.test.ft.sys.SpyMemory; 
import com.rational.test.ft.sys.SpyMemoryStatistics; 

/**
 * Descripción: Ir a la vista 360 
 * Parámetros: Recibe el número de cuenta cliente
 * Script Name   : <b>Vista360</b>
 * Parametros 0) OK/NOK 1) Identificacion 2) Clase cuenta: Cliente / Facturación
 * ej: res 405890652 Cliente
 * Precondiciones: Estar en siebel en cualquier pantalla 
 * @since  2016/01/12 modificado a usar identificacion 19/10
 * @author SS
 */
public class Vista360 extends Vista360Helper {
	public void testMain(Object[] argu) throws RationalTestException{
		
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
 
		argu[0] = "NOK";
		LogoOracle().waitForExistence();

		Pestañas().gotoScreen("Accounts Screen");
		
		NroCuenta().waitForExistence();
		// Ingresar número de cuenta recibido como parámetro
		String Identificacion = argu[1].toString();
		Identificación().setText(Identificacion);
		ClaseCuenta().select(argu[2].toString());
		System.out.println("Clase de cuenta ingresada :" +ClaseCuenta().getProperty(".value") );
		if (!ClaseCuenta().getProperty(".value").equals(argu[2].toString()))
			ClaseCuenta().select(argu[2].toString());
		Ir().click();
 
		Nuevo().waitForExistence(); // dado que la anterior sentencia no funciona se agrega esta
		Nuevo().ensureObjectIsVisible(); // se agrega esta sentencia para que no pierda el menu de la visibilidad y de error
		
		sleep(1);
		
		SiebScreenViews().goTo("All Account List View", "L2");
		sleep(2);
		ListaCuentas().drillDownColumn("Name", 0); // es la vista de todas las cuentas
		
		sleep(1);
		NroPedido().waitForExistence();
		argu[0] = "OK";
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}
