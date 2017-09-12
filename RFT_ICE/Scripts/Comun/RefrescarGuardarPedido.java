package Scripts.Comun;
import resources.Scripts.Comun.RefrescarGuardarPedidoHelper;
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
 * Descripción: Graba para evitar transacciones largas sin commit
 * Autor: Ale Fabale
 * @Param res Tramite
 */
public class RefrescarGuardarPedido extends RefrescarGuardarPedidoHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		SiebApplication().processKeyboardAccelerator("Ctrl+S");
		
		argu[0]="NOK";
		String sTramite = argu[1].toString().toLowerCase();
		
		if (!sTramite.equals("portin")) Menu().select(atPath("WriteRecord")); // Se agrega porque en la VM de juli no funciona el short-cut
		if (sTramite.equals("portin"))  MenuPI().select(atPath("WriteRecord"));
		argu[0]="OK";
	}
}

