package Scripts.Comun;
import resources.Scripts.Comun.LeeRegistryHelper;
import com.rational.test.ft.sys.OperatingSystem;
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
public class LeeRegistry extends LeeRegistryHelper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>05/07/2016 11:46:42</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/07/05
	 * @author SS
	 */
	public void testMain(Object[] args) 
	{

			String registryEnebleAsyncSubmit = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Rational Software\\Rational Test\\8\\Custom\\EnableSiebelSubmitAsync";
			String asyncEnabled = null;
			
			try
			{
				asyncEnabled = OperatingSystem.getRegistryValue(registryEnebleAsyncSubmit);
			}
			catch(NoSuchRegistryKeyException e)
			{
				asyncEnabled = null;
			}
			
			System.out.println("Enabled? "+ asyncEnabled); //This should print 1 if set correctly

	}
}

