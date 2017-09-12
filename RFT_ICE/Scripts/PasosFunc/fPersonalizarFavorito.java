package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPersonalizarFavoritoHelper;
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
 */
public class fPersonalizarFavorito extends fPersonalizarFavoritoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] PersFavSMS;
		PersFavSMS = new String[3];

		String[] MensError;
		MensError = new String[4];
 	
		PersFavSMS[0] = "SMSFav";
		PersFavSMS[0] = args[1].toString();
		PersFavSMS[1] = "Agregar";
		callScript("Scripts.Comun.PersonalizarGrupoSVANew", PersFavSMS);

		if  (!PersFavSMS[2].toString().equals("Agregado")){
			MensError[0] = "Agregar SMS Favorito fatext_specialRatingListName().lló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

