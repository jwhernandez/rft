package Scripts.Comun;

import resources.Scripts.Comun.CambiarPerfilOLDHelper;
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
 * argunmento Parámetros: 0) Recibe el perfil de fact, ejemplo Postpago  1)Tramite
 * SS Nov 2015
 */
public class CambiarPerfilOLD extends CambiarPerfilOLDHelper 
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sTramite = "Venta";
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString(); // tramite
		}

		String sTipoPago = argu[0].toString();
		
		if (sTramite.equals("Venta")){
			LineasPedido().activateRow(0);
			LineasPedido().drillDownColumn("Billing Account", 2);
			TipoPago().select(sTipoPago);
		}
		if (sTramite.equals("PortIn")){
			
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Sin parametro de resultado");
	}
}
