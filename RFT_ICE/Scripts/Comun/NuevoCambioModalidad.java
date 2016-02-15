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
 * Description   : Functional Test Script
 * @Param sin argumentos
 * @author Sandra
 * @since  2016/01/19
 */
public class NuevoCambioModalidad extends NuevoCambioModalidadHelper
{
	public void testMain(Object[] argu)  throws RationalTestException
	{
		Pestañas().gotoScreen("ICE Control Orden Task Screen");
		Tareas().click(); // "isMap no funciona"
		IndependenciaNumericaTaskUI().performAction();

	}
}

