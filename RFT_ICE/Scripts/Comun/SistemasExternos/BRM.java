package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.BRMHelper;
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
 * Description   : Prueba de BRM
 * @author SS
 *  password
 */
public class BRM extends BRMHelper
{

	public void testMain(Object[] args) 
	{
		
		// Frame: Login Customer Center
		startApp("BRMQA");
		jPasswordField().click(atPoint(24,15));
		loginCustomerCenter().inputChars("password");
		ok().click();
		
		// Frame: Customer Center
		customerCenter().move(atPoint(265,211));
		customerCenter().resize(872, 718);
		actions().click();
		actions().click();
		pMenuBar().click(atPath("File"));
		pMenuBar().click(atPath("File"));
		newAccount().click();
		newAccount().click();
		pMenuBar().click(atPath("File"));
		pMenuBar().click(atPath("File->Search"));
		
		// 
		accountNumber().click(atPoint(25,8));
		search().inputChars("101735937075");
		search2().click();
		pAccountsResults().click(atCell(atRow("Account Number", 
                                        "101735937075", "Last Name", 
                                        "ESPINOZA SALAZAR", 
                                        "First Name", "VICTOR"), 
                                  atColumn("Account Number")), 
                           atPoint(64,2));
		open().click();
		
		// Frame: Customer Center
		customerCenter().move(atPoint(81,202));
		pMenuBar().click(atPath("File"));
		pMenuBar(ANY,MAY_EXIT).click(atPath("File->Exit"));
		// PENDIENTE Insertar código aquí
	}
}

