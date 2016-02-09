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
 * Descripción: Ir a la vista 360 Parámetros: Recibe el número de cuenta cliente
 * Script Name   : <b>Vista360</b>
 * Parametros 0) Nro de cuenta 1)OK/NOK
 * ej: 101784954044 res
 * Precondiciones: Estar en siebel en cualquier pantalla 
 * @since  2016/01/12
 * @author Sandra
 */
public class Vista360 extends Vista360Helper {
	public void testMain(Object[] argu) throws RationalTestException{
		
		argu[1] = "NOK";
		LogoOracle().waitForExistence();

		Pestañas().gotoScreen("Accounts Screen");
 
		NroCuenta().waitForExistence();
		// Ingresar número de cuenta recibido como parámetro
		String NroCuenta = argu[0].toString();
		NroCuenta().setText(NroCuenta);
		Ir().click();
		NuevaConsuta().waitForExistence(); // no se hace en la lista pues no funciona
		
		sleep(5);
 
		SiebScreenViews().goTo("All Account List View", "L2");
		sleep(2);
		ListaCuentas().drillDownColumn("Name", 0); // es la vista de todas las cuentas
		
		sleep(1);
		NroPedido().waitForExistence();
		argu[1] = "OK";
	}
}
