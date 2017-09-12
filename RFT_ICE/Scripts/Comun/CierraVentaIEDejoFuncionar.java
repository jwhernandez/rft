package Scripts.Comun;
import resources.Scripts.Comun.CierraVentaIEDejoFuncionarHelper;
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
 * Description   : Cierra las ventanas de IE dejo de funcionar luego de matar los procesos
 * via admin de tareas
 * Script Name   : <b>CierraVentaIEDejoFuncionar</b>
 * @since  2016/09/26
 * @author SS
 */
public class CierraVentaIEDejoFuncionar extends CierraVentaIEDejoFuncionarHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";
		System.out.println("Existe una ventana de IE dejo de funcionar? "+ DejoFuncionar().exists());
		System.out.println("Existe una ventana de IE enviar info problema? "+ EnviarInfo().exists());
		
		if (EnviarInfo().exists()) CancelarEnviarInfo().click();
		if (DejoFuncionar().exists()) CancelarDejoFuncionar().click();
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);	
	}
}

