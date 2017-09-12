package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarMsjFavoritoHelper;
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
 * Descripcion     : Validar que el mensaje de favorito sea correcto
 * Par�metros	   :  
 * Pre-condiciones : Estar en la vista del pedido con el mensaje desplegado
 * SS Nov 2015
 * CP26_CD1_T1 DPM0014 QA NA NA 
 */
public class fValidarMsjFavorito extends fValidarMsjFavoritoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MsjFavorito;
		MsjFavorito = new String[4];

		String[] MensError;
		MensError = new String[4];
	
		/** 
		 * Validar favorito sms
		 */

		MsjFavorito[0]= args[1].toString();
		MsjFavorito[3] = "BrowserScript";
		callScript("Scripts.Comun.ValidarMensaje",MsjFavorito);
		
		// Se mejora para que acepte true (coincide) o Parcialmente

		// Acepta valores como Parcialmente o true
		if  (!(MsjFavorito[1].toString().equals("true") || 
			   MsjFavorito[1].toString().equals("Parcialmente"))) {
			MensError[0] = "Mensaje diferente al deseado";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

