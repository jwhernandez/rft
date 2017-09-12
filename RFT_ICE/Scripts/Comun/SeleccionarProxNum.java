package Scripts.Comun;
import resources.Scripts.Comun.SeleccionarProxNumHelper;
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
 * Descripci�n: Presiona el bot�n de proximo n�mero disponible
 * Par�metros: recibe 0) Prepago o Postpago 1) Devuelve NOK o OK 2)nro pedido 
 * 3)nro servicio deseado, NA (input) o DP (para que solo presione el bot�n) 4) output nro de servicio asignado
 * Ej Postpago res 1-1692877975 10202040 res 
 * Postpago res 1-1743435812 res 
 * Precondiciones: estar en la pesta�a de pedido, sim asignada
 * FF- VC 26/12/2016
 * 
 */
public class SeleccionarProxNum extends SeleccionarProxNumHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
	
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0] = "NOK";
		try{
			PrimerNumero().performAction();
			sleep(5);
			argu[0] = "OK";
		}catch(Exception e){
			System.out.print("Error al seleccionar el pr�ximo n�mero. " + e);
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

