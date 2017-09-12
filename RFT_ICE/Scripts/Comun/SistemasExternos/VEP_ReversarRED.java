package Scripts.Comun.SistemasExternos;
import java.awt.Rectangle;

import resources.Scripts.Comun.SistemasExternos.VEP_ReversarREDHelper;
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
 * Description   : Reversar un RED por sistema VEP. Recibe el nro de red.
 * @author SS
 * Script Name   :  <b>VEP_ReversarRED</b>
 * @since  2016/08/26
 * Parametros: 0) OK/NOK 1) Msj Error 2) NroRED 3) Resultado
 * Vector Resultado tendrá = Nro Red, Nombre, Cedula, Monto
 * eJ res res 9155993 res
 */
public class VEP_ReversarRED extends VEP_ReversarREDHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sMensaje=null;
		argu[0] = "NOK"; 
		argu[1] = "Error desconocido";
		boolean Reversar=false; 
		String path = "C:\\"; // cambiar por Resultados/CP0x/ obtener de variable global
		String filename = null;
		Rectangle area = new Rectangle();
		
		String[] Caja;
		Caja = new String[2];
		
		Menu_Reversiones().hover();
		Opcion2().waitForExistence();
		Opcion2().click();
		
		// Si aparece un mensaje de caja no abierta o expirada se procesa primero
		// caso contrario se coloca Pagar=true y se procede a pagar
		if (Mensaje().exists()){
			sMensaje = Mensaje().getProperty(".text").toString();
			System.out.println("Mensaje = " + sMensaje);
			switch (sMensaje) {
			case "No hay caja abierta válida":
				System.out.println("No hay caja abierta válida");
				callScript("Scripts.Comun.SistemasExternos.VEP_AbrirCaja", Caja);
				if  (Caja[0].toString().equals("OK")){		
					Reversar=true;
				} 
				break;
			case "El cajero no tiene una caja abierta":
				System.out.println("El cajero no tiene una caja abierta ");
				callScript("Scripts.Comun.SistemasExternos.VEP_AbrirCaja", Caja);
				if  (Caja[0].toString().equals("OK")){		
					Reversar=true;
				} 
				break;
			case "Existe una caja abierta y ya expiró, ciérrela y abra una nueva":
				System.out.println("Existe una caja abierta y ya expiró, ciérrela y abra una nueva");
				callScript("Scripts.Comun.SistemasExternos.VEP_CerrarCaja", Caja);
				if  (Caja[0].toString().equals("OK")){
					callScript("Scripts.Comun.SistemasExternos.VEP_AbrirCaja", Caja);
					if  (Caja[0].toString().equals("OK")){		
						Reversar=true;
					} 
				}
				break;
			default:
				Reversar=true;
				break;
			}
		} else Reversar=true;

		if (Reversar)
		{
			// Se paga el red
			System.out.println("Se buscará el red " + argu[2].toString());
			NroRED().waitForExistence();
			NroRED().setText(argu[2].toString());
			BtonConsultar().click();

			if (Mensaje().exists()){
				sMensaje = Mensaje().getProperty(".text").toString();
				if (sMensaje.contains("El número no ha sido cancelado")){
					argu[1] = "No existe el número de RED para reversar";
					System.out.println(argu[1] );
					Reversar=false;
				} 
			}

			if (Reversar){
			//Leer el RED y sus datos para confirmar su pago
				ITestDataTable iDataTable = (ITestDataTable) TablaREDReversados().getTestData("contents");
				int rows = iDataTable.getRowCount();
				int cols = iDataTable.getColumnCount();
				System.out.println("Filas=" + rows + " Columnas"+ cols);
				// Solo se espera que haya un red con el numero pasado como parametro
				// Son dos filas x las observaciones
				if (rows==2){
					// Se convierte el monto a numerico
					System.out.println(iDataTable.getCell(0, 0).toString() );
					System.out.println(iDataTable.getCell(0, 1).toString() );
					System.out.println(iDataTable.getCell(0, 2).toString() );
					System.out.println(iDataTable.getCell(0, 3).toString() );
					argu[3]=iDataTable.getCell(0, 0).toString() + ";" +
							iDataTable.getCell(0, 1).toString() + ";" +
							iDataTable.getCell(0, 2).toString() + ";" +
							iDataTable.getCell(0, 3).toString()  ;
					System.out.println(argu[3].toString() );

					area =TablaREDReversados().getClippedScreenRectangle();
					TablaREDReversados().ensureObjectIsVisible();
					// Generar una variable filename
					filename = path + "REDReversado.jpg";
					CapturarPantalla(filename, area);
						
						Reversar().click();

						// Se comprueba que el RED se haya reversado
						Menu_Reversiones().hover();
						Opcion2().waitForExistence();
						Opcion2().click();
						NroRED().waitForExistence();
						NroRED().setText(argu[2].toString());
						BtonConsultar().click();
						if (Mensaje().exists()){
							if (Mensaje().getProperty(".text").toString().contains("El número no ha sido cancelado"))
							{
								argu[0] = "OK"; 
								argu[1] = "Procesamiento exitoso";
							}
							else  argu[1] = "Red no reversado";
						} argu[1] = "Red no reversado";

				} else
				{
					argu[1] = "Existe más de un RED con el mismo número o ninguno";
					System.out.println(argu[1] );	
				}
			} //fin de reversar el red 2
		} //fin de reversar el red 1
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);	

	}
}

