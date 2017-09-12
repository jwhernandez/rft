package Scripts.Comun;
import resources.Scripts.Comun.PersonalizarGrupoSVANewHelper;
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
* Parámetros:  0) Producto: SMSFav / FavVoz 1) Agregar / Eliminar 2) Resultado 3) Tramite
*              devuelve Agregado / Eliminado / Error 
* Pre-Condiciones: Estar en la linea del servicio    
* ej SMSFav Agregar res res     
* FavVoz Eliminar res res       
* SS Nov 2015
* ult modif 4/4/2017 agregado de port-in
*/
public class PersonalizarGrupoSVANew extends PersonalizarGrupoSVANewHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Producto;
		Producto = new String[6];
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		// 3)action code 4) desde la linea o no (Si No) 5)Tramite 
		
		String sTramite=argu[3].toString();
		
		Producto[0] = "Servicio de Telefonia Movil";
		Producto[4] = "Si"; // Que busque desde el principio  modif 4/4/2017 ss
		Producto[5] = sTramite ; 	// modif 4/4/2017 ss
		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		int iPosicion = Integer.parseInt (Producto[2]);
		if (sEncontrado =="Encontrado" ){
			if (!sTramite.equals("PortIn"))
				{
				LineasPedido().activateRow(iPosicion);
				sleep(1); 
				Personalizar().performAction();
				}
			if (sTramite.equals("PortIn")) {
				LineasPedido_PI().activateRow(iPosicion);
				sleep(1); 
				Personalizar_PI().performAction();
			}

			// PestañasDelConfigurador().click(atCell(atRow(atIndex(0)), atColumn(atIndex(1)))); Funciona al igual que servicios adicionales
			ServiciosAdicionales().waitForExistence();
			ServiciosAdicionales().click();
			sleep(1);
			GrupoSVA().click();

			switch (argu[0].toString()) {
			case "SMSFav":
				TabSMSFavorito().click();
				sleep(2);
				if (argu[1].toString().equals("Agregar")){
					//FavSMSON().click();
					argu[2] = "Agregado";
					SMSFavorito().select();
				} else {
					//FavSMSOFF().click();
					argu[2] = "Eliminado";
					SMSFavorito().deselect();
				}
				break;
			case "FavVoz":
				TabFavoritoVoz().click();
				if (argu[1].toString().equals("Agregar")){
					//FavVozON().click();
					FavoritoVoz().select();
					argu[2] = "Agregado";
				} else {
					//FavVozOFF().click();
					FavoritoVoz().deselect();
					argu[2] = "Eliminado";
				}
				break;
			default:  
				argu[2] = "Error";
				break;
			} // end del switch
			Terminado().click();
			if (!sTramite.equals("PortIn"))  
				BtonNuevo().waitForExistence();
			if (!sTramite.equals("PortIn"))  
				BtonConsultaPedido().waitForExistence();
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}

