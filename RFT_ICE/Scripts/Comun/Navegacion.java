package Scripts.Comun;
import resources.Scripts.Comun.NavegacionHelper;
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
 * Description   : Functional Test Script
 * @author Sandra
 */
public class Navegacion extends NavegacionHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
//		System.out.println("1" + SiebelApp().getLastErrorMessage());
//		System.out.println("2" + SiebelApp().getSessionId());
//		System.out.println("3" + SiebelApp().getTabPage()); 
//		System.out.println("4" + Screen_SalesOrder().getViews()); // no funciona
		//SiebScreenViews().isExists("Order Entry - Line Items Detail View (Sales)", "L2");
		// CreaRED().
//		System.out.println("5" + PriceAll().isShowing()); // da true siempre que exista
		// si no se esta en el applet da error 
		// exception_name = com.rational.test.ft.Domain.Siebel.SiebelMethodExecutionFailureException
		//		exception_message = GetProperty("IsEnabled"); Error:(-1016) - Failed to create object "Siebel Power Communications.Sales Order Screen" of class "SiebScreen".
		System.out.println("6" + PriceAll().isEnabled()); // da falsa si esta inhabiilitado
		System.out.println("7" + LiberarNumero().isEnabled());

		//PriceAll().ensureObjectIsVisible();

		System.out.println("***" + Pestañas().getProperty("ActiveView"));
		Pestañas().gotoView("Account List View");
		System.out.println("***" +siebView_accountListView().activeApplet());
		System.out.println("***" +siebView_accountListView().getApplets());
		
System.out.println("***" +Applet_AccountEntryApplet().hasFocus());

applet_sisAccountListApplet().hover();
		// 
		//list_siebList().drillDownColumn("Account Number", 0);
		

		stop();

		// Pestañas().gotoScreen("Accounts Screen");
		
		
		//System.out.println("***" + Pestañas().getProperty("ActiveView"));	
		
		//LogoOracle().waitForExistence();

		//Pestañas().gotoScreen("Accounts Screen");
		// 
		//Pestañas().gotoScreen("Accounts Screen");
		

		Applet_AccountEntryApplet().waitForExistence();
		// Ingresar número de cuenta recibido como parámetro
		String NroCuenta = "101786249987";
		//NroCuenta().setText(NroCuenta);
		//Ir().click();
		//ListaCuentas().waitForExistence();
		//sleep(5);
		//
		//  iTotal = (int) LineasPedido().getProperty("RowsCount");
		// ListaCuentas().getProperty("RowsCount")
		ListaCuentas().drillDownColumn("Name", 0);
		//sleep(1);
		//NroPedido().waitForExistence();
		stop();
	}
}

