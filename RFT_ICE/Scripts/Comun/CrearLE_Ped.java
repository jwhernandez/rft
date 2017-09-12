package Scripts.Comun;
import resources.Scripts.Comun.CrearLE_PedHelper;
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
 * Descripción: Crea una lista especial con el tipo pasado como parametro
 * @param Parámetros: 0) NOK / OK 1) Tipo de lista Número de teléfono / Número de SMS
 * 2)Desde pedido true/false 3) volver a pedido true false 
 * 4) out Nombre lista 5) Tramite (tramite y volver al pedido ss agregado 17/11/16)
 * Precondiciones: Si param 2 = true : Estar en el pedido o Si param 2 = false : estar en la pantalla de LE 
 * Ejemplo: res "Número de teléfono" true true res venta (ejemplo de pedido = 1-1721952061)	 
 *  res "Número de SMS" true true res venta
 * Script Name   : <b>CrearLE_Ped</b>
 * @author SS 
 * @since 2016/11/18
 * Ult modif ss 28 03 2017 se elimina un objeto
 */
public class CrearLE_Ped extends CrearLE_PedHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0] = "NOK"; // Valor x defecto
		String sTipo = argu[1].toString();
		boolean bLEDesdePed =Boolean.parseBoolean(argu[2].toString().toLowerCase());
		boolean bLEVolverPed =Boolean.parseBoolean(argu[3].toString().toLowerCase());
		boolean bListaOK = false; 
		String sTramite = argu[5].toString();	
//		TestObject[] to;
//		GuiTestObject gto; 

		
		// Si esta en el pedido va a la pantalla de lista especial
		if (bLEDesdePed) 
		{
			if (!sTramite.equals("PortIn")) 
			{
				BtonConsultaPedido().ensureObjectIsVisible();	
				SubPestañas().goTo("SWI Special Rating Orders Profile View", "L4");
		
				//RowCount().performAction();
				//comboBox_iceChannelId().select("Agencia ICE");
				
				//Descripcion().setText(" ");


			} else
			{
				BtonConsultaPedidoPI().ensureObjectIsVisible();	
				//SubPestañas().goTo("", "L4");
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
			ListaEspecial().activateRow(0);
			NuevaLE().performAction();

//			to = form_sweForm2_0().find(atChild("ClassName", "SiebText"));
//			gto = 	(GuiTestObject)	to[0];
//			System.out.println(gto.getProperties());
			
//			sTipo = "Número de SMS";
			ListaEsp().select(sTipo);

			// Capturar y retornar el nombre de la lista especial
			argu[4] = NombreLE().getProperty("Text");
			System.out.println("Nombre de la lista especial " +argu[4]);
			bListaOK = true;
		} else
		{
			//SubPestañas().goTo("", "L4");
			System.out.println("Paso no implementado para portin");
		}
		
		// Retornar si el argumento lo indica 
		if ((bLEVolverPed) && bListaOK) 
		{
			LogoICE().ensureObjectIsVisible();
			sleep(2);
			if (!sTramite.equals("PortIn")) 
			{
				BtonConsultaPedido().ensureObjectIsVisible();	
				sleep(1);	
				SubPestañas().goTo("Order Entry - Line Items Detail View (Sales)", "L4");
				sleep(2);			
				BtonConsultaPedido().ensureObjectIsVisible();	
				argu[0] = "OK";
			} else
			{
				BtonConsultaPedidoPI().ensureObjectIsVisible();	
				sleep(1);	
				//SubPestañas().goTo("", "L4");
				System.out.println("Paso no implementado para portin");
				BtonConsultaPedidoPI().ensureObjectIsVisible();	
			}
		} if (bListaOK) argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

