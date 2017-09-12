package Scripts.Comun;
import resources.Scripts.Comun.ValidarMorosidadenListaHelper;
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
 * Description   : Validar en la vista de pedidos de portabilidad 
 * en el form applet del pedido que el cliente este moroso
 * @author SS
 * Script Name   : <b>ValidarMorosidadenLista</b>
 * @since  2017/03/07
 * Param 0) OK/NOK 1)Estado deseado (true o false)
 * res TRUE 
 */
public class ValidarMorosidadenLista extends ValidarMorosidadenListaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		boolean sEstadoDeseado = Boolean.parseBoolean(argu[1].toString().toLowerCase());
		
		argu[0]="NOK";
		boolean sMoroso = Boolean.parseBoolean(MorosoFlagAllOrders().getProperty("IsChecked").toString());

		System.out.println("Cliente moroso?:" + sMoroso);

		if ((sMoroso && sEstadoDeseado) || !sMoroso && !sEstadoDeseado)
				argu[0]="OK";
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

