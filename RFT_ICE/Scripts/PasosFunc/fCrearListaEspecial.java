package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fCrearListaEspecialHelper;
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
 * Description   : Chequear en la lista especial que no se permita numero invalido
 * @since  2015/12/26
 * @param 0) numero de caso 1)un digito indicando el numero de variable en el dp
 * Precondiciones: Estar en el pedido, siebel debe tener visible la pestaña de perfiles en preferidos
 * debe estar en la linea 1 de los nombres de lista si es que no esta en el pedido
 * @author SS 
 */
public class fCrearListaEspecial extends fCrearListaEspecialHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] ListaEsp;
		ListaEsp = new String[9];
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
		/**
		 * Parámetros de Crear Lista: 0) NOK / OK 1) Tipo de lista Número de teléfono / Número de SMS
		 * 2)Desde pedido true/false 3) volver a pedido true false 4) NumTel o NA 5) Nombre lista 
		 * 6) Msj a validar o NA 7) Msj real 8) false o true si coincide o no
		 */

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		ListaEsp[1] = dpString("LETipo"+i);
		ListaEsp[2] = dpString("LEDesdePed"+i);
		ListaEsp[3] = dpString("LEVolverPed"+i);
		//if (!(dpString("LETel"+i) == "NA")) { 
		System.out.println("Numero de Telefono: " +  dpString("LETel"+i));
		ListaEsp[4] = dpString("LETel"+i);
		ListaEsp[6] = dpString("LEMsj"+i);

		callScript("Scripts.Comun.CrearListaEspecial", ListaEsp);

		if  ((ListaEsp[0].toString().equals("NOK")) || (ListaEsp[8].toString().equals("false"))){
			MensError[0] = "Lista Especial tuvo error";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} else {
			/**  Guardar el nro de lista en variable global, dp y texto salida */

			setNomListaEspecial(ListaEsp[5].toString()); 
	
			DatoSalida[1]=args[0].toString(); // nro caso
			DatoSalida[2]=args[2].toString(); // nro ambiente
			DatoSalida[3]="NomListaEspecial"; // Nombre variable
			DatoSalida[4]=ListaEsp[5].toString(); // Valor de la variable

			callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
			

			infoPaso(args[0].toString(), Tipo.DATO,"NomListaEspecial",getNomListaEspecial());
		}

	}
}
