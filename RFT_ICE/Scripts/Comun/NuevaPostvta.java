package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.NuevaPostvtaHelper;
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
 * Description   : genera un pedido de postventa con el tipo de postventa y motivo pasados como parametro
 * Script Name   :  <b>NuevaPostvta</b>
 * @since  2016/10/21
 * Parametros: 0) Resultado = OK/NOK 1) subtramite 2) motivo 3) Tipo Servicio 
 * 4) NroPedido (dato de salida) 5) ValidarMensaje (NA o DPMnnn) 
 * @author SS
 * res Modificación "Modificar Facilidades" "Servicio de Telefonia Movil" res  
 * res Reconexión "Solicitud del cliente" "Servicio de Telefonia Movil" res DPM0042
 * Ultima Modif SS 22-11-2016
 * 28 03 2017 se le saca un objeto no usado
 */
public class NuevaPostvta extends NuevaPostvtaHelper 
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		String[] Validaciones;
		Validaciones = new String[4];
		//  Invoca a Validaciones con los siguientes parámetros:
		// Parámetros: 0) Recibe código de mensaje a validar, 
		// 1) devuelve mensaje y 2) un boolean true / false (indicando true si el mensaje coincide)
		// 3) recibe un tipo de mensaje si no es HTML el mensaje a validar (opcional) HTML, BrowserScript
		
		String[] BuscProdAct;
		BuscProdAct = new String[6];
		
		// Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion 
		// 3)OUT action code 4) desde el comienzo (Si= desde el comienzo No = desde la linea actual) 
		// 5)Tramite

		BuscProdAct[0]= argu[3].toString();
		BuscProdAct[4]= "Si";
		callScript("Scripts.Comun.BuscarProductoActivo",BuscProdAct);
		if (BuscProdAct[1].equals("Encontrado"))
		{

			Subtramite().select(argu[1].toString());
			Motivo().select(argu[2].toString());
			Ejecutar().performAction();

			//Si se incuyó Msj se llama a script de validar Msj
			System.out.println("Mensaje a validar: " + argu[5].toString());			
			if (!argu[5].toString().equals("NA"))
			{
				Validaciones[0]=argu[5].toString();  
				Validaciones[3]="HTML";  
				callScript("Scripts.Comun.ValidarMensaje", Validaciones);

				if (Validaciones[1].toLowerCase().toString().equals("true"))  
					argu[0] = "OK";
			}
			else 
			{
				if (argu[1].toString().equals("Modificación"))
				{
					boolean bExiste = (boolean) Advertencia().exists();
					if (bExiste && Aceptar().exists()) Aceptar().click(); 
					sleep (5);
					if (AceptarResolucionConflicto().exists()) 
					{
						try {
							AceptarResolucionConflicto().click();
						} catch (Exception e) {
							//S e espera CRFCP0050E: No screen point found for the object
							System.out.println("Mensaje de excepción: "+e.getMessage());
						}
					} else System.out.println("No existe pantalla de resolución de conflictos");
					BtonTerminadoPvta().waitForExistence();
					BtonTerminadoPvta().click();
				}
				BtonConsultaPedido().waitForExistence();

				String sPedido = NroPedido().getProperty("Text").toString();
				System.out.println("NroPedido:" + sPedido);
				argu[4] = sPedido;
				setNroPedido(sPedido);

				argu[0] = "OK";		
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

