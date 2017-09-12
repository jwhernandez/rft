package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSimulacionHelper;
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
 * Description   : Script para simular la ejecuciónd de CasosPrueba
 * Script Name   : <b>fSimulacion</b>
 * @author SS
 * @since  2016/09/13
 */
public class fSimulacion extends fSimulacionHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString()); 
		//	0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		if (!dpDone()) System.out.println("Tramite=" + dpString("Tramite"));
		else System.out.println("No encontró el DP");
	}
}

