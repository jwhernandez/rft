package Scripts.Comun;
import resources.Scripts.Comun.CompilarScriptsHelper;
import java.io.PrintWriter;
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
import java.io.File;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
/**
 * Description   : Functional Test Script
 * @author Sandra
 */
public class CompilarScripts extends CompilarScriptsHelper
{
	/**
	 * Script Name   : <b>CompilarScripts</b>
	 * Generated     : <b>10/11/2016 13:04:51</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/11/10
	 * @author Sandra
	 */
	public void testMain(Object[] args) 
	{
		PrintWriter printWriter = null;

		// Provide Project location
		String Projectlocation = "C:\\Users\\IBM_ADMIN\\IBM\\rationalsdp\\workspace\\Project1";
		File directory = new File(Projectlocation);
		File[] fList = directory.listFiles();

		try {

			// Provide batch file location
			printWriter = new PrintWriter("C:/Users/IBM_ADMIN/Desktop/compiler.bat","ASCII");

			//get the java files from provided project location 
			for (File file : fList){
				if (file.isFile() && file.getName().endsWith(".java"))
				{
					String ScriptJavaName = file.getName();
					String[] ScriptNameSep = ScriptJavaName.split("\\.(?=[^\\.]+$)");
					String ScriptName = ScriptNameSep[0];

					printWriter.println("\"%IBM_RATIONAL_RFT_ECLIPSE_DIR%\\jdk\\jre\\bin\\java\" -classpath \"%IBM_RATIONAL_RFT_INSTALL_DIR%\\rational_ft.jar\" com.rational.test.ft.rational_ft -datastore \""+ Projectlocation +"\" -compile " + ScriptName + " ");
				}
			}
			printWriter.println("pause");
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		finally { printWriter.close();
		}
		unregisterAll();
	}  
}


