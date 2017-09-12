package Scripts.Comun;
import resources.Scripts.Comun.CrearLEHelper;

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
 * Descripción: Crea una lista especial con el tipo pasado como parametro. La ingresa desde la pestana de perfiles
 * @param Parámetros: 0) NOK / OK 1) Tipo de lista Número de teléfono / Número de SMS
 * 2)Desde pedido true/false 3) Nombre lista 4) tramite
 * Precondiciones: Si param 2 = true : Estar en el pedido o Si param 2 = false : estar en la pantalla de LE 
 * Ejemplo: res "Número de SMS" true  res   
 * Script Name   : <b>CrearLE</b>
 * @author SS 
 * @since 2016/10/20
 * ult modif ss 12/4/2017 se agrega opcion de port/in
 */
public class CrearLE extends CrearLEHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0] = "OK";
		String sTramite = argu[4].toString(); 
		
		// Si esta en el pedido va a la pantalla de lista especial
		if (argu[2].toString().toLowerCase().equals("true")) {
			if (!sTramite.equals("PortIn")) 
			{
				LineasPedido().activateRow(0);
				LineasPedido().drillDownColumn("Billing Account", 0);
			}
			if (sTramite.equals("PortIn")) 
			{
				LineasPedido_PI().activateRow(2);
				LineasPedido_PI().drillDownColumn("Billing Account", 2);
			}
			sleep(4);	
			Pestañas().goTo("Account Customer Profile View", "L3");
			sleep(3);
			Pestañas().goTo("SWI Special Rating Profile View", "L4");
		}

		// En la primera fila genera una nueva lista especial y se la hace lista x defecto		
		// Crear la lista especial
		BtonNuevo().ensureObjectIsVisible();
		Listas().ensureObjectIsVisible();
		NumerosMenu().ensureObjectIsVisible();
		sleep(2);
		NuevaLista().performAction();
		ListaEspecial().activateRow(0);
		ListaEsp().select(argu[1].toString());
		Primaria().setOn();

		// Capturar y retornar el nombre de la lista especial
		System.out.println("Nombre de la lista especial" + NombreLE().getProperty("Text"));
		argu[3] = NombreLE().getProperty("Text");

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

