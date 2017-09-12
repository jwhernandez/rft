package Scripts.Comun;
import resources.Scripts.Comun.Personalizar_SRV_MOV_POS_InicioHelper;
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
* Descripción: Personaliza el servicio postpago. 
* No agrega ningún producto ni sale del configurador.
* Solo ingresa al configurador.
* Script Name   : <b>Personalizar_SRV_MOV_POS_Inicio</b> 
* @since   2017/03/28
* @author SS
* @Param 0) OK / NOK 1) Tramite 2)Servicio
* ej:  res PortIn "Servicio de Telefonia Movil"

*/
public class Personalizar_SRV_MOV_POS_Inicio extends Personalizar_SRV_MOV_POS_InicioHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sTramite = argu[1].toString(); // tramite
		argu[0]="NOK";
		String[] Producto;
		Producto = new String[6];
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		// 3)action code 4) desde la linea o no (Si No) 5)Tramite 
		
		// Buscar el servicio
		Producto[0]=argu[2].toString();
		Producto[4]="Si"; // se le indica buscar desde el comienzo
		Producto[5]=argu[1].toString(); // tramite
		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();

		if (sEncontrado == "Encontrado"){


			if (!sTramite.equals("PortIn")) 
			{
				BtonPersonalizar().performAction();
				boolean bExiste = (boolean) Advertencia().exists();
				if (bExiste) ButtonOk().performAction();
			}

			if (sTramite.equals("PortIn")) BtonPersonalizarPI().performAction();
			argu[0]="OK";
		} else System.out.println("Producto no encontrado");

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

