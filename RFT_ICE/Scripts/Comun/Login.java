package Scripts.Comun;
import resources.Scripts.Comun.LoginHelper;
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
import com.rational.test.ft.sys.SpyMemory; 
import com.rational.test.ft.sys.SpyMemoryStatistics; 

/**
 * Descripción: Ejecuta el login
 * Parametros: 0) usuario 1) clave 2)Ambiente
 * Pre-Condiciones de inicio: No haya otra sesion abierta
 */
public class Login extends LoginHelper
{
public void testMain(Object[] argu) throws RationalTestException
	{
		String sAmbiente = "SiebelDESA"; 
		if (argu.length >= 3 ) {
			sAmbiente = "Siebel" + argu[2].toString();
		} 
		startApp(sAmbiente);
		
		UserName().waitForExistence();

		UserName().click();
		UserName().setText((String) argu[0]);
		Password().click();
		Password().setText((String) argu[1]);
		Ingresar().click();	

     	LogoOracle().waitForExistence();
 
	}
}

