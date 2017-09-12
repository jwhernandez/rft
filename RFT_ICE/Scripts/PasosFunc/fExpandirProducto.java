package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fExpandirProductoHelper;
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
 * Script Name   : <b>fExpandirProducto</b>
 * Description   : Expande todo el arbol de las líneas del pedido
 * @since  2015/12/27
 * @author SS
 */
public class fExpandirProducto extends fExpandirProductoHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		//String[] ExpandirProducto;
		//ExpandirProducto = new String[1];

//		String[] MensError;
//		MensError = new String[4];
		/** 
		 * Expandir Producto
		 */
			callScript("Scripts.Comun.ExpandirProducto");
			//callScript("Scripts.Comun.ExpandirProducto", ExpandirProducto);

//		if  (ExpandirProducto[0].toString().equals("NOK")) {
//			//MensError[0] = "Problema al expandir producto";
//			MensError[0] = "xDefecto";
//			MensError[1] = args[3].toString();
//			MensError[2] = args[0].toString();
//			MensError[3] = getScriptName( );
//			callScript("Scripts.Comun.TerminarCasoError", MensError);
//		}
	}
}

