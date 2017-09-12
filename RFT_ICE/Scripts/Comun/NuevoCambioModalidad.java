package Scripts.Comun;
import resources.Scripts.Comun.NuevoCambioModalidadHelper;
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
 * Script Name   : <b>NuevoCambioModalidad</b>
 * Description   : Inicia un pedido de CM
 * @Param 0) OK/NOK 1) NroServicio
 * @author SS
 * @since  2016/04/11
 * Última modificación: VC 20170509 Se cierra la ventana de tareas para evitar que se presente un error de PMR más adelante
 * Última modificación: VC 20170510 Se quita la modificación del 0905 ya que se presentan errores durante el cm si se cierra la ventana
 */
public class NuevoCambioModalidad extends NuevoCambioModalidadHelper
{
	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		
		// 
		Pestañas().gotoScreen("ICE Control Orden Task Screen");

		Tareas().click(); // "isMap no funciona" por eso se maneja por excepción
		try
		{
			sleep(2);
			IndependenciaNumericaTaskUI().performAction();
		} // end del try
		catch (Exception e){
			logInfo("Mensaje de excepción: "+e.getMessage());
			System.out.println("Mensaje de excepción: "+e.getMessage());
			// Click(); Error:(-106) - m_pParent->GetChild failed for class "SiebTaskUIPane" ErrorCode: -106
			Tareas().click(); // "isMap no funciona"
			sleep(4);
			IndependenciaNumericaTaskUI().performAction();
		}
		System.out.println("Ingreso de NroServicio: "+ argu[1]);
		ServicioCM().setText(argu[1].toString());
		//if(IndependenciaNumericaTaskUI().exists()) Tareas().click(); // VC 20170509 Cierra la ventana de tareas
		ButtonNext().performAction();
		sleep(1);

		argu[0]="NOK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado: " + argu[1] );
	}
}

