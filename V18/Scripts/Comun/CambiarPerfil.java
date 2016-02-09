package Scripts.Comun;

import resources.Scripts.Comun.CambiarPerfilHelper;
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
 * Descripción: Cambia la cuenta de factutacion al perfil indicado en el
 * argunmento Parámetros: 0) Recibe el perfil de fact, ejemplo Postpago 
 * SS Nov 2015
 */
public class CambiarPerfil extends CambiarPerfilHelper {

	public void testMain(Object[] argu) throws RationalTestException {
		LineasPedido().activateRow(0);
		LineasPedido().drillDownColumn("Billing Account", 2);
		String sTipoPago = argu[0].toString();
		TipoPago().select(sTipoPago);
	}
}
