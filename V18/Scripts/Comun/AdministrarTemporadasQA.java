package Scripts.Comun;
import resources.Scripts.Comun.AdministrarTemporadasQAHelper;
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
 * Description   : Administracion de temporadas 
 * Script Name   : <b>AdministrarTemporadasQA</b>
 * @Param 0) IN Nombre Producto Temporada 1) IN  Accion Apagar / Encender / Consultar  2) OUT OK/NOK 
 * 3) Valor de la consulta 
 * ej: "Favorito SMS - Habilitacion" Consultar res res 
 * @since  2016/01/15
 * @author Sandra
 * Precond Estar dentro de Siebel en cualquier pantalla
 * commmment pbas
 */
public class AdministrarTemporadasQA extends AdministrarTemporadasQAHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		argu[2] = "NOK";
		argu[3] = "NA";
		
		Pestañas().gotoScreen("Products Screen");
		sleep(3);
		NewQuery().performAction();

		NomProducto().setText(argu[0].toString());
		ExecuteQuery().performAction();

		switch (argu[1].toString()) {
		case "Encender":
			argu[2] = "NOK"; 
			break;
		case "Apagar":
			argu[2] = "NOK"; 
			break;
		case "Consultar":
			argu[3] = Estado().getProperty("ActiveItem");
			argu[2] = "OK"; 
			break;
		default:  
			argu[2] = "NOK";
			break;
		} // end del switch
		System.out.println("Resultado: " +argu[2] + " " + argu[3]);
	}
}

