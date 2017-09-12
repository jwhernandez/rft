package Scripts.Comun;
import resources.Scripts.Comun.LeerHTMLHelper;
import java.net.URLConnection;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import java.net.URL;
import java.util.Scanner;
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
 * Description   : Leer un HTML
 * @author SS
 * Script Name   : <b>LeerHTML</b>
 * @since  2016/11/22
 * @Param res url
 */
public class LeerHTML extends LeerHTMLHelper
{
	public void testMain(Object[] argu) 
	{
		String content = null;
		String sURL = argu[1].toString();
		sURL = "http://10.129.20.137/ecommunications_esn/start.swe?SWECmd=Login&SWEPL=1&SWETS=1479836748578";
		URLConnection connection = null;
		try {
		  connection =  new URL(sURL).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		System.out.println(content);
	}
}

