package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPersonalizarFavoritoNewHelper;
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
 * Descripcion     :Ingresar SMS Favorito. o Favorito Voz
 * Parámetros	   : 0) Nombre del caso 1) SMSFav o FavVoz 2)Ambiente 
 * Pre-condiciones : Estar en la vista del pedido. No hace falta estar en el producto.
  * SS Nov 2015
  * Param1: SMSFav o SMSVoz}
  *  ult modif 4/4/2017 agregado de port-in
  *  última modificación VC 20170614 Se agrega la opción de desactivar el favorito con los parámetros SMSFavDesac y FavVozDesac
 */
public class fPersonalizarFavoritoNew extends fPersonalizarFavoritoNewHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] PersFavSMS;
		PersFavSMS = new String[4];
		// 0) Producto: SMSFav / FavVoz 1) Agregar / Eliminar 2) Resultado 3) Tramite
		// devuelve Agregado / Eliminado / Error 

		String[] MensError;
		MensError = new String[4];
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
 	
		PersFavSMS[0] = "SMSFav";
		PersFavSMS[0] = args[1].toString();
		PersFavSMS[1] = "Agregar";
		// VC 20170614 Se agrega la opción de desactivar el favorito con los parámetros SMSFavDesac y FavVozDesac
		if(PersFavSMS[0].equals("SMSFavDesac") || PersFavSMS[0].equals("FavVozDesac")){
			PersFavSMS[1] = "Eliminar";
			if(PersFavSMS[0].equals("SMSFavDesac")) PersFavSMS[0] = "SMSFav";
			else PersFavSMS[0] = "FavVoz";
		}
		PersFavSMS[3] =  dpString("Tramite");	// ss 4/4/2017 
		callScript("Scripts.Comun.PersonalizarGrupoSVANew", PersFavSMS);

		if  (PersFavSMS[2].toString().equals("Error")){
			MensError[0] = "Personalizar Favorito falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

