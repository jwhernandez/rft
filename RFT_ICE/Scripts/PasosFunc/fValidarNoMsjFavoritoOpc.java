package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarNoMsjFavoritoOpcHelper;
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
 * Descripcion     : Validar que el mensaje de favorito no se encuentre entre el 
 * mensaje desplegado o que no haya mensaje alguno.
 * Parámetros :  
 * Pre-condiciones : Estar en la vista del pedido con el mensaje desplegado
 * Script Name   : <b>fValidarNoMsjFavoritoOpc</b>
 * @since  2017/04/04
 * @author SS
 */
public class fValidarNoMsjFavoritoOpc extends fValidarNoMsjFavoritoOpcHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MsjFavorito;
		MsjFavorito = new String[4];

		String[] MensError;
		MensError = new String[4];

		MsjFavorito[0]= args[1].toString();
		MsjFavorito[3] = "BrowserScript";
		callScript("Scripts.Comun.ValidarMensaje",MsjFavorito);
		
		// Acepta valores como false o No Existe
		if  (!(MsjFavorito[1].toString().equals("false") || 
				   MsjFavorito[1].toString().equals("No Existe"))) {
			MensError[0] = "Mensaje existe y no debería";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

