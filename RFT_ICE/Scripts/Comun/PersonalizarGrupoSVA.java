package Scripts.Comun;
import resources.Scripts.Comun.PersonalizarGrupoSVAHelper;
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
* Descripción: Agrega el producto ingresado como parametro del Grupo SVA
* Parámetros:  0) Producto: SMSFav / FavVoz 1) Agregar / Eliminar 2) Resultado
*              devuelve Agregado / Eliminado / Error
* Pre-Condiciones: Estar en la linea del servicio             
* SS Nov 2015
*/
public class PersonalizarGrupoSVA extends PersonalizarGrupoSVAHelper
{
	public void testMain(Object[] argu) 
	{
		String[] Producto;
		Producto = new String[4];

		Producto[0] = "Servicio de Telefonia Movil";
		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		int iPosicion = Integer.parseInt (Producto[2]);
		if (sEncontrado =="Encontrado" ){
			LineasPedido().activateRow(iPosicion);
			sleep(1); 

			Personalizar().performAction();
			ServiciosAdicionales().click();
			GrupoSVA().click();

			switch (argu[0].toString()) {
			case "SMSFav":
				if (argu[1].toString().equals("Agregar")){
					FavSMSON().click();
					argu[2] = "Agregado";
				} else {
					FavSMSOFF().click();
					argu[2] = "Eliminado";
				}
				break;
			case "FavVoz":
				if (argu[1].toString().equals("Agregar")){
					FavVozON().click();
					argu[2] = "Agregado";
				} else {
					FavVozOFF().click();
					argu[2] = "Eliminado";
				}
				break;
			default:  
				argu[2] = "Error";
				break;
			} // end del switch
			Terminado().click();
		}
	}
}

