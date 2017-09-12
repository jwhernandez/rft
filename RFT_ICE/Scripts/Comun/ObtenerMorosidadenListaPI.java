package Scripts.Comun;
import resources.Scripts.Comun.ObtenerMorosidadenListaPIHelper;
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
 * Description   : Obtiene morosidad de un pedido de port-in desde la lista de pedidos
 * de port-in
 * Script Name   : <b>ObtenerMorosidadenListaPI</b>
 * Precondiciones: Estar en la lista de pedidos de PI
 * Parametros 0) OUT OK/NOK 
 * @author SS
 * @since  2017/03/13
 * ej res
 */
public class ObtenerMorosidadenListaPI extends ObtenerMorosidadenListaPIHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";

		MorosidadenListaPI().performAction();
		
		sleep(1);

		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());

	}
}

