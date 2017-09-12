package Scripts.Comun;
import resources.Scripts.Comun.AdministrarTemporadasHelper;
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
 * Script Name   : <b>AdministrarTemporadas</b>
 * @Param 0) IN Nombre Producto Temporada 1) IN  Accion Apagar / Encender / Consultar  2) OUT OK/NOK 
 * 3) Valor de la consulta 
 * ej: "Favorito SMS - Habilitacion" Consultar res res 
 * ej: "Favorito SMS - Habilitacion" Encender res res 
 * @since  2016/01/15
 * @author SS
 * Precond Estar dentro de Siebel en cualquier pantalla
 */
public class AdministrarTemporadas extends AdministrarTemporadasHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[2] = "NOK";
		argu[3] = "NA";
		
		try {
			Pestañas().gotoScreen("ISS Unified Administration Screen");		
		} catch (Exception e) {
			sleep(3);

			Pestañas().gotoView("ICE ISS Product Administration View");
		}
		sleep(3);

		NewQuery().performAction();

		NomProducto().setText(argu[0].toString());
		ExecuteQuery().performAction();

		System.out.println("Argumento recibido: " +argu[1] );
		switch (argu[1].toString()) {
		case "Encender":
			Estado().select("Activo");
			argu[2] = "OK"; 
			break;
		case "Apagar":
			Estado().select("Inactivo");
			argu[2] = "OK"; 
			break;
		case "Consultar":
			argu[3] = Estado().getProperty("ActiveItem");
			argu[2] = "OK"; 
			break;
		default:  
			argu[2] = "NOK";
			break;
		} // end del switch
		argu[3]=Estado().getProperty("ActiveItem");
		System.out.println("Resultado: " +argu[2] + " " + argu[3]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());	
		sleep (5);
	}
}

