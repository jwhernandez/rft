package Scripts.Comun;
import resources.Scripts.Comun.ValidaPI_AdminHelper;
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
 * Script Name   : <b>ValidaPI_Admin</b>
 * Descripcion   : Recibe si debe estar editable o no y realiza la validacion
 * @Param 0) OK/NOK 1) Editable / No Editable True / False para saber que validar 2)Opcion
 * Opciones = NroServicio; TipoOrden
 * 3) si aplica el producto de la linea que se debe buscar
 * Ej: res true NroServicio "Servicio de Telefonia Movil"
 * res true TipoOrden
 * 
 * @since  2016/04/07
 * @author SS
 */
public class ValidaPI_Admin extends ValidaPI_AdminHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] ProductoAdmin;
		ProductoAdmin = new String[4];
		
		IFtVerificationPoint ValidacionVP=null;
		
		String sProducto="NA";
		if (argu.length >= 4 ) { 
			sProducto = argu[3].toString();  
		}
		String[] Mens;
		Mens = new String[2];
	
		argu[0] = "NOK"; 
		String sResultadoEsperado = argu[1].toString().toLowerCase();
		
		switch (argu[2].toString()) {
		case "NroServicio":
			ProductoAdmin[0]=sProducto; // producto a buscar
			ProductoAdmin[3] = "PortIn"; // para que use la busqueda de objetos port-in
			callScript("Scripts.Comun.BuscarProductoAdmin",ProductoAdmin);
			String sEncontrado = ProductoAdmin[1].toString();
			if (sEncontrado.equals("OK")){
				System.out.println("Tercer Argumento Recibido de producto en ingresarServicioadmin: " + ProductoAdmin[2].toString());

				int iPosicion = Integer.parseInt(ProductoAdmin[2].toString().trim());

				Mens [0] = "Servicio Posicion" + ProductoAdmin[2].toString() + "flag " + sEncontrado; 
				callScript("Scripts.Comun.Informar", Mens);

				LineasPedidoPIAdmin().activateRow(iPosicion);

				System.out.println("NroServicio editable?: " + NroServicioPIAdmin().isEnabled());
				ValidacionVP = vpManual("NumeroServicio", sResultadoEsperado, NroServicioPIAdmin().isEnabled());		
				if (ValidacionVP.performTest()) argu[0] = "OK"; 
			}  
			break;
		case "TipoOrden":
			System.out.println("TipoOrden editable?: " + TipoOrdenPIAdmin().isEnabled());
			ValidacionVP = vpManual("TipoOrden", sResultadoEsperado, TipoOrdenPIAdmin().isEnabled());
			if (ValidacionVP.performTest()) argu[0] = "OK"; 
			break;
		default:  
			argu[0] = "NOK";
			break;
		} // end del switch

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

