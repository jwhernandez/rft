package Scripts.Comun;
import resources.Scripts.Comun.ValidaCambioPlanFactFULLHelper;
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
 * Description   : Valida que no se pueda realizar un cambio de plan en la cuenta de facturación
 * @author SS
 * Script Name   : <b>ValidaCambioPlanFact</b>
 * @since  2016/05/02
 */
public class ValidaCambioPlanFactFULL extends ValidaCambioPlanFactFULLHelper
{
	public void testMain(Object[] args) 
	{
		
		
		System.out.println(menu_siebMenu().isEnabled(atPath("CmdMgr10")));
		
		for (int i = 0; i<=25;i++) {
			System.out.println(menu_siebMenu().getRepositoryNameByIndex(i));
			//System.out.println(menu_siebMenu().getRepositoryNameByIndex(i).getClass());	
			String Opcion = menu_siebMenu().getRepositoryNameByIndex(i);
			System.out.println(menu_siebMenu().getUIName(Opcion));
			System.out.println(menu_siebMenu().isEnabled(atPath(Opcion)));
		}
		
		
//		System.out.println(menu_siebMenu().getProperty("Count"));
//		System.out.println(menu_siebMenu().getRepositoryNameByIndex(20));
//		System.out.println(menu_siebMenu().getRepositoryNameByIndex(13));
//		System.out.println(menu_siebMenu().getRepositoryNameByIndex(1));
//		String sOpcion1=menu_siebMenu().getRepositoryNameByIndex(1);
//			
////		
////		// 
////		menu_siebMenu().select(atPath("CmdMgr110"));
////		
////		// HTML Browser
////		browser_browserIE(document_ordenDeClasificación(),DEFAULT_FLAGS).close();
////		
////		// 
////		menu_siebMenu().select(atPath("ColumnsDisplayed"));
////		
////		// HTML Browser
////		browser_browserIE(document_columnasMostradas(),DEFAULT_FLAGS).close();
////		
////		// 
////		menu_siebMenu().select(atPath("CmdMgr810"));
////		
////		// HTML Browser
////		browser_browserIE(document_imprimirPersonalizado(),DEFAULT_FLAGS).close();
////		
////		// 
////		menu_siebMenu().select(atPath("CmdMgr800")); // presentacion preliminar
////		
////		// HTML Browser
////		browser_browserIE(document_resumenDeLaCuenta(),DEFAULT_FLAGS).click(atPoint(852,5));
////
////		// 
//////		menu_siebMenu().select(atPath("CmdMgr800"));
//////
//////		System.out.println(isEnabled(menu_siebMenu().select(atPath(sOpcion1))));	
//////		isEnabled(Subitem menuItem)
////
////		//menu_siebMenu().select(atPath("PukRequest"));

		
	}
}

