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
 */
public class RefrescarGuardarPedido extends RefrescarGuardarPedidoHelper
{
	public void testMain(Object[] args) 
	{
		SiebApplication().processKeyboardAccelerator("Ctrl+S");
		SiebApplication().processKeyboardAccelerator("Alt+Enter");
		
	}
}

