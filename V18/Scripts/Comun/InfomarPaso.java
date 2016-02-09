package Scripts.Comun;
import resources.Scripts.Comun.InfomarPasoHelper;
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
* Descripción: Informa sobre la ejecución del paso
* Parámetros:  Recibe nombre de paso
* SS Nov 2015
*/
public class InfomarPaso extends InfomarPasoHelper
{
	public void testMain(Object[] argu) 
	{
		System.out.println(" ");
		System.out.println("-------------------------------------------------");
		System.out.println("Inicio de Paso: " + argu[0].toString());
		System.out.println("-------------------------------------------------");
	    logInfo("Inicio de " + argu[0].toString() );  
	}
}

