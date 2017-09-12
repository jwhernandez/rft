package Scripts.Comun;
import resources.Scripts.Comun.CrearLENewHelper;
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
 * Descripci�n: Crea una lista especial con el tipo pasado como parametro. La ingresa desde la subpestana del pedido
 * @param Par�metros: 0) NOK / OK 1) Tipo de lista N�mero de tel�fono / N�mero de SMS
 * 2)Desde pedido true/false 3) Nombre lista 4) Tramite (agregado 17/11/16)
 * Precondiciones: Si param 2 = true : Estar en el pedido o Si param 2 = false : estar en la pantalla de LE 
 * Ejemplo:  
 *  
 * Script Name   : <b>CrearLENew</b>
 * @author SS 
 * @since 2016/10/20
 */
public class CrearLENew extends CrearLENewHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0] = "OK";
		
		  String sTramite = "Venta";
		  if (argu.length >= 5 ) { 
		   sTramite = argu[4].toString();
		  }
		
		// Si esta en el pedido va a la pantalla de lista especial
		if (argu[2].toString().toLowerCase().equals("true")) {
			if (!sTramite.equals("PortIn")) 
			{
				BtonConsultaPedido().ensureObjectIsVisible();	
				SubPesta�as().goTo("SWI Special Rating Orders Profile View", "L4");
			} else
			{
				BtonConsultaPedidoPI().ensureObjectIsVisible();	
				//SubPesta�as().goTo("", "L4");
				System.out.println("Paso no implementado para portin");
			}
			sleep(3);
		}
		// En la primera fila genera una nueva lista especial y se la hace lista x defecto		
		// Crear la lista especial
		if (!sTramite.equals("PortIn")) 
		{
			EncabezadoLE().ensureObjectIsVisible();
			NumerosLE().ensureObjectIsVisible();
			sleep(2);
			NuevaLE().performAction();
			ListaEspecial().activateRow(0);
			String sTipo = argu[1].toString().toString();
//			sTipo = "N�mero de SMS";
//
//			// 
//			ListaEsp().select("N�mero de tel�fono");

			ListaEsp().select(sTipo);

			// Capturar y retornar el nombre de la lista especial
			argu[3] = NombreLE().getProperty("Text");
			System.out.println("Nombre de la lista especial" +argu[3]);
		} else
		{
			//SubPesta�as().goTo("", "L4");
			System.out.println("Paso no implementado para portin");
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

