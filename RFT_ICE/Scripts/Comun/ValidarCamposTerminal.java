package Scripts.Comun;
import resources.Scripts.Comun.ValidarCamposTerminalHelper;
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
 * Description   : Validar los campos marca, serie, modelo y version sean editables o no 
 * Script Name   : <b>ValidarCamposTerminal</b>
 * @Param 0)IN producto 1)IN Editable? Editable o No Editable 2)OUT OK/NOK
 * ejemplo "Servicio de Telefonia Movil" "Editable" res  OK
 * ejemplo "Servicio de Telefonia Movil" "No Editable" res  NOK
 * "PLAN PROFESIONAL 2 12 M" "No Editable" res  OK
 * "PLAN PROFESIONAL 2 12 M" "Editable" res  NOK
 * @since  2016/01/21
 * @author Sandra
 */
public class ValidarCamposTerminal extends ValidarCamposTerminalHelper
{

	public void testMain(Object[] argu) 
	{

		String[] BuscProd;
		BuscProd = new String[4];
		// 0) IN Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 3)action code 
		
		argu[2] = "NOK";
		
		BuscProd[0] = argu[0].toString();
		callScript("Scripts.Comun.BuscarProducto",BuscProd);
		int iPosicion = Integer.parseInt (BuscProd[2].toString());
		
		LineasPedido().activateRow(iPosicion); 
		
		boolean bVersionEnabled = Boolean.parseBoolean(VersionSoftware().getProperty("IsEnabled").toString());
		boolean bMarcaEnabled = Boolean.parseBoolean(Marca().getProperty("IsEnabled").toString());
		boolean bModeloEnabled = Boolean.parseBoolean(Modelo().getProperty("IsEnabled").toString());
		boolean bSerieEnabled = Boolean.parseBoolean(Serie().getProperty("IsEnabled").toString());

		System.out.println("Version is enabled? = " + bVersionEnabled );
		System.out.println("Marca is enabled? = " + bMarcaEnabled );
		System.out.println("Modelo is enabled? = " + bModeloEnabled );
		System.out.println("Serie is enabled? = " + bSerieEnabled );
		
		switch (argu[1].toString()) {
		case "Editable":
			if (bVersionEnabled && bMarcaEnabled && bModeloEnabled && bSerieEnabled) 
			{
				argu[2] = "OK";
			}
			break;
		case "No Editable":
			if (!bVersionEnabled && !bMarcaEnabled && !bModeloEnabled && !bSerieEnabled) 
			{
				argu[2] = "OK";
			}
			break;
		default:  
			break;
		} // end del switch

		System.out.println("Resultado: " + argu[2]);
	}
}

