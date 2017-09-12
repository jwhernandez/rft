package Scripts.Comun;

import resources.Scripts.Comun.CompraPlanHelper;
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
 * Descripción: Compra de un plan x configurador de producto 
 * Parámetros: 0) IN  el plan 
 * SS Nov 2015
 */
public class CompraPlan extends CompraPlanHelper 
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString()); 
		
		NuevoProducto().performAction();
		String sPlan = argu[0].toString();
		Plan().setText(sPlan);
		ImpreResultadoScript(getScriptName( ).toString(), "Sin parametro de resultado");

	}
}
