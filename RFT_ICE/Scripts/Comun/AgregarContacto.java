package Scripts.Comun;
import resources.Scripts.Comun.AgregarContactoHelper;
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
 * Script Name   : b>AgregarContacto</b>
 * @param  0)Identificación 1)OK/NOK
 * ej:405123336 res
 * @since  2016/01/26
 * @author Sandra
 */
public class AgregarContacto extends AgregarContactoHelper
{

	public void testMain(Object[] argu) 
	{
		argu[1]="NOK";
		Contacto().openPopup();
		sleep(1);
		NewQuery().performAction();
		sleep(1);
		Identificacion().setText(argu[0].toString());
		Identificacion().processKey("EnterKey");
		sleep(1);
		PickRecord().performAction();
		argu[1]="OK";
	}
}

