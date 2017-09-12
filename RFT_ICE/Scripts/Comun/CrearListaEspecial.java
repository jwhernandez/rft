package Scripts.Comun;
import resources.Scripts.Comun.CrearListaEspecialHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.sys.SpyMemory;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Descripción: Crea una lista especial con el nombre pasado como parametro
 * @param Parámetros: 0) NOK / OK 1) Tipo de lista Número de teléfono / Número de SMS
 * 2)Desde pedido true/false 3) volver a pedido true false 4) NumTel o NA 5) Nombre lista 
 * 6) Msj a validar o NA 7) Msj real 8) false o true si coincide o no
 * Precondiciones: Estar en el pedido
 * Ejemplo: res "Número de SMS" true false 24314578 res NA res res 
 * Ejemplo: res "Número de SMS" false false 85542085 res DPM019 res res 
 * 
 * @author SS 
 * @since Nov 2015
 */
public class CrearListaEspecial extends CrearListaEspecialHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validaciones;
		Validaciones = new String[4];
		/**
		 * Invoca a Validaciones con los siguientes parámetros:
		 * Parámetros: 0) Recibe código de mensaje a validar, 
		 * 1) devuelve mensaje y 2) un boolean true / false (indicando true si el mensaje coincide)
		 * 3) recibe un tipo de mensaje si no es HTML el mensaje a validar (opcional) HTML, BrowserScript
		 */

		argu[0] = "OK";
		argu[8] = "true";

		// Si esta en el pedido va a la pantalla de lista especial
		if (argu[2].toString().toLowerCase().equals("true")) {
			LineasPedido().activateRow(0);
			LineasPedido().drillDownColumn("Billing Account", 1);
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
		argu[5] = NombreLE().getProperty("Text");

		// Si se le paso un num de tel se lo ingresa en la lista
		System.out.println("Numero de Telefono: " + argu[4].toString());
		if (!(argu[4].toString().equals("NA"))) { 
			ListaEspecial().activateRow(0);	// siempre genera la lista arriba de todo, se para arriba de todo y agrega el numero en el applet de abajo
			NuevoNumero().performAction();
			// workaround para evitar que no permita ingresar el numero especial por no estar visible
			SiebelBar().ensureObjectIsVisible();
			NumerosMenu().ensureObjectIsVisible();
			sleep(2);
			NumTel().setText(argu[4].toString());

			//Si se incuyó Msja se llama a script de validar Msj
			System.out.println("Mensaje a validar: " + argu[6].toString());
			if (!(argu[6].toString().equals("NA"))) { 
				Validaciones[0]=argu[6].toString();  
				Validaciones[3]="HTML";  
				callScript("Scripts.Comun.ValidarMensaje", Validaciones);
				argu[7] = Validaciones[1];
				argu[8] = Validaciones[2];

				Siebel().processKeyboardAccelerator("Esc");
				//Siebel().processKeyboardAccelerator("Ctrl+U");
		
				//Menu().select(atPath("UndoRecord")); 
				
				if (Validaciones[2].toLowerCase().toString().equals("true")) {
					argu[0] = "OK"; 
				} 
				if ((!(argu[6].toString().equals("NA")) && Validaciones[2].toString().equals("false")) 
						|| (argu[6].toString().equals("NA"))) { 

					NumTel().getProperty("Text");
					if (!(NumTel().getProperty("Text").toString().equals(argu[4].toString()))) {
						Siebel().processKeyboardAccelerator("Ctrl+U");
						argu[0] = "NOK";
					} else { 
						argu[0] = "OK";
					}
				} else {
					argu[0] = "OK"; 
					Siebel().processKeyboardAccelerator("Ctrl+S");
				}
			} else
			{
				argu[0] = "OK";		
			}
			//					}
			//				}
		} // fin de si no hay numero

		// Retornasimage_conTecnologíaSiebel().el argumento lo indica y si no dio problemas validar el mensaje
		if ((argu[3].toString().toLowerCase().equals("true")) 
				&& ((argu[6].toString().equals("NA"))||(Validaciones[2].toString().equals("true")))) {
			LogoICE().ensureObjectIsVisible();
			sleep(2);
			try
			{
				Threadbar().goTo("Order Entry - Line Items Detail View (Sales)1");
				sleep(1);
			}
			catch (Exception e){
				Threadbar().goTo("Order Entry - Line Items Detail View (Sales)0");
				System.out.println("Mensaje de excepción: "+e.getMessage());
			}
			
		} else {
			ListaEspecial().activateRow(0);	
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

