package Scripts.Comun;
import resources.Scripts.Comun.CerrarSBLHelper;
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
 * Script Name   : <b>CerrarSBL</b>
 * Description   : Desconecta Siebel 
 * @since  2016/10/31
 * @author SS
 * @Param 0)OK/NOK
 */
public class CerrarSBL extends CerrarSBLHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		Menu().select(atPath("File->Logout"));

		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado: " + argu[0].toString());
	}
}

