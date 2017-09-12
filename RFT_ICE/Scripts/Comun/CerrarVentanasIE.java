package Scripts.Comun;
import resources.Scripts.Comun.CerrarVentanasIEHelper;
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
 * Description   : Buscar y Cerrar las ventanas de IE
 * Script Name   : <b>CerrarVentanasIE</b>
 * @since  2016/04/20
 * @author SS
 */
public class CerrarVentanasIE extends CerrarVentanasIEHelper
{
	public void testMain(Object[] args) 
	{
		RootTestObject root = RationalTestScript.getRootTestObject();

		// Find all the windows on the system
		IWindow windows[] = root.getTopWindows(); 
		String className;


		for (int i=0;i<windows.length;i++)
		{
			// Get the class name of each window
			className = windows[i].getWindowClassName();
			System.out.println(className);
			// If the window is an instance of IE, close it
			if (className.equals("IEFrame"))
			{
				System.out.println("***************"+className+windows[i].getText());
			
//				windows[i].close();
//				windows[i].inputKeys("{ENTER}");
			}
		}

		unregisterAll();
	}
}

