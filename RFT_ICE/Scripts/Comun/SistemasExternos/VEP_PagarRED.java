package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.VEP_PagarREDHelper;
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
import java.awt.Rectangle;
/**
 * Description   : Pagar un RED por sistema VEP. Recibe el nro de red y el monto para validar en la búsqueda de VEP.
 * @author SS
 * Script Name   :  <b>VEP_PagarRED</b>
 * @since  2016/08/25
 * Parametros: 0) OK/NOK 1) Msj Error 2) NroRED 3) Monto  (usar un valor negativo para que el monto no se compare) 4)Resultado
 * Vector Resultado tendrá = Nro Red, Nombre, Cedula, Monto
 * eJ res res 9155993 1000 res
 * res res 9160417 -10 res (para que no valide monto)
 * ult modif 22 02 2017 se soluciona bug 
 * 11/4/2017 ss se agregan sentencias luego de abrir o cerra caja 
 * ss 18/4/2017 se corrige el tema de validacion de monto del red, se elimina parametro 4 no usado
 */
public class VEP_PagarRED extends VEP_PagarREDHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sMensaje=null;
		argu[0] = "NOK"; 
		argu[1] = "Error desconocido";
		boolean Pagar=false; 
		String path = "C:\\"; // cambiar por Resultados/CP0x/ obtener de variable global
		String filename = null;
		Rectangle area = new Rectangle();
		
		String[] Caja;
		Caja = new String[2];

		Menu_Pagos().hover();
		OpcionMenu_Cobrar().waitForExistence();
		OpcionMenu_Cobrar().click();
		
		sleep(5); // ss se agrega delay 22 02 2017

		
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
					Pagar=true;
					Menu_Pagos().hover(); // 11/4/2017 ss 
					OpcionMenu_Cobrar().waitForExistence();// 11/4/2017 ss 
					OpcionMenu_Cobrar().click();// 11/4/2017 ss 
				} 
				break;
			case "El cajero no tiene una caja abierta":
				System.out.println("El cajero no tiene una caja abierta ");
				callScript("Scripts.Comun.SistemasExternos.VEP_AbrirCaja", Caja);
				if  (Caja[0].toString().equals("OK")){		
					Pagar=true;
					Menu_Pagos().hover();// 11/4/2017 ss 
					OpcionMenu_Cobrar().waitForExistence();// 11/4/2017 ss 
					OpcionMenu_Cobrar().click();// 11/4/2017 ss 
				} 
				break;
			case "Existe una caja abierta y ya expiró, ciérrela y abra una nueva":
				System.out.println("Existe una caja abierta y ya expiró, ciérrela y abra una nueva");
				callScript("Scripts.Comun.SistemasExternos.VEP_CerrarCaja", Caja);
				if  (Caja[0].toString().equals("OK")){
					callScript("Scripts.Comun.SistemasExternos.VEP_AbrirCaja", Caja);
					if  (Caja[0].toString().equals("OK")){		
						Pagar=true;
						Menu_Pagos().hover();// 11/4/2017 ss 
						OpcionMenu_Cobrar().waitForExistence();// 11/4/2017 ss 
						OpcionMenu_Cobrar().click();// 11/4/2017 ss 
					} 
				}
				break;
			default:
				Pagar=true; 
				System.out.println("No hay mensaje reconocido");
				break;
			}
		} else 
		{
			System.out.println("No hay mensaje de problemas con caja");
			Pagar=true;
		}
		if (Pagar)
		{
			// Se paga el red
			System.out.println("Se buscará el red " + argu[2].toString());
			NroRED().waitForExistence();
			NroRED().setText(argu[2].toString());
			BtonConsultar().click();
			sleep(3);
			// se valida que no haya mensaje de error
			if (MensajeREDNoEncontrado().exists())
			{
				sMensaje = MensajeREDNoEncontrado().getProperty(".text").toString();
				System.out.println("Mensaje = " + sMensaje);	
				argu[1] =sMensaje;
				argu[0] = "NOK"; 
			}
			else {
				System.out.println("No se encontro mensaje de error");	
				//Leer el RED y sus datos para confirmar su pago
				ITestDataTable iDataTable = (ITestDataTable) TablaREDs().getTestData("contents");
				int rows = iDataTable.getRowCount();
				int cols = iDataTable.getColumnCount();
				System.out.println("Filas=" + rows + " Columnas"+ cols);
				// Solo se espera que haya un red con el numero pasado como parametro
				if (rows==1){
					// Se convierte el monto a numerico
					int iMontoDeseado = Integer.parseInt(argu[3].toString());
					String sMonto = iDataTable.getCell(0, 4).toString();
					int hasta = sMonto.indexOf("."); 
					System.out.println("hasta="+hasta );	
					String iMonto = sMonto.substring(0,hasta);
					iMonto = iMonto.replace(",","");
					System.out.println(iMonto );	

					int iMontoVEP = Integer.parseInt(iMonto);
					// Se valida que el monto coincida
					if (iMontoDeseado == iMontoVEP || iMontoDeseado < 0){
						System.out.println(iDataTable.getCell(0, 1).toString() );
						System.out.println(iDataTable.getCell(0, 2).toString() );
						System.out.println(iDataTable.getCell(0, 3).toString() );
						System.out.println(iDataTable.getCell(0, 4).toString() );
						argu[4]=iDataTable.getCell(0, 1).toString() + ";" +
								iDataTable.getCell(0, 2).toString() + ";" +
								iDataTable.getCell(0, 3).toString() + ";" +
								iDataTable.getCell(0, 4).toString()  ;
						System.out.println(argu[4].toString() );

						area =TablaREDs().getClippedScreenRectangle();
						TablaREDs().ensureObjectIsVisible();
						// Generar una variable filename
						filename = path + "RED.jpg";
						CapturarPantalla(filename, area);


						//					GuiTestObject check = (GuiTestObject) TablaREDs().getSubitem(atCell(atRow(0), atColumn(1)));
						//					check.ensureObjectIsVisible();
						//					System.out.println(check.getMethods()[1]);
						//					check.click(); // (no funciono esta forma de hacer el check)

						Check().ensureObjectIsVisible();
						Check().click();
						BtonVerSeleccionados().click();

						BtonPagar().click();

						RED().ensureObjectIsVisible();
						area =RED().getClippedScreenRectangle();
						RED().ensureObjectIsVisible();
						// Generar una variable filename
						filename = path + "RED2.jpg";
						CapturarPantalla(filename, area);

						// Se comprueba que el RED se haya pagado
						Menu_Pagos().hover();
						OpcionMenu_Cobrar().waitForExistence();
						OpcionMenu_Cobrar().click();
						NroRED().waitForExistence();
						NroRED().setText(argu[2].toString());
						BtonConsultar().click();
						sleep(3);
						if (MensajeREDNoEncontrado().exists()){
							if (MensajeREDNoEncontrado().getProperty(".text").toString().contains("La cédula indicada no tiene ningún recibo especial de dinero asociado."))
							{
								argu[0] = "OK"; 
								argu[1] = "Procesamiento exitoso";
							}
							else  argu[1] = "Red no pagado";
						} argu[1] = "Red no pagado";
					}
					else {
						argu[1] = "Monto RED no coincide";
						System.out.println(argu[1] );
					}
				} else
				{
					argu[1] = "Existe más de un RED con el mismo número o ninguno";
					System.out.println(argu[1] );	
				}
			} //fin de pagar el red
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]+argu[1]);
	}
}

