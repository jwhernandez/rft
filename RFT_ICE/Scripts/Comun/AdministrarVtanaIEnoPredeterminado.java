package Scripts.Comun;
import resources.Scripts.Comun.AdministrarVtanaIEnoPredeterminadoHelper;
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
 * Script Name   : <b>AdministrarVtanaIEnoPredeterminado</b>
 * Description   : Maneja la venta de IE no predeterminado, la cierra
 * @since  2017/03/14
 * @author SS
 */
public class AdministrarVtanaIEnoPredeterminado extends AdministrarVtanaIEnoPredeterminadoHelper
{

	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		System.out.println("Mensaje DialogoExplorer existe?:" + DialogoExplorer().exists() );
 		if (DialogoExplorer().exists())
 		{
 			System.out.println("Mensaje DialogoExplorer existe");
 			sleep (2);	
			try
				{DialogoExplorer().close();}
			catch (Exception e) 
				{System.out.println("Mensaje de excepción en IE predeterminado: "+e.getMessage());}
 		}
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

