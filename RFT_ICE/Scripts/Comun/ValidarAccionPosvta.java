package Scripts.Comun;
import resources.Scripts.Comun.ValidarAccionPosvtaHelper;
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
 * Description   : Se valida que en la pantalla 360 que el botón sea consultar y ver multas
 * @author SS
 * @since  2016/10/25
 * Script Name   : <b>ValidarAccionPosvta</b>
 * Argumentos  0) OK/NOK 
 * Ej res
 */
public class ValidarAccionPosvta extends ValidarAccionPosvtaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		System.out.println("Existe botón Consulta?" + BtonNewQuery().exists());
		System.out.println("Existe botón Multas?" + BtonMultas().exists());
		
		if (BtonNewQuery().exists() && BtonMultas().exists()) argu[0]="OK";
				
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

