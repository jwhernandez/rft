package Scripts.Comun;
import resources.Scripts.Comun.CrearREDHelper;
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
* Descripción: Permite crear RED
* Parámetros: 1 || 0) Creado / No Creado
* SS Nov 2015
*/
public class CrearRED extends CrearREDHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		argu[0] = "No Creado";
		if (CrearRed().isEnabled()) {
			argu[0] = "Creado";
			CrearRed().performAction();
		}
	}
}

