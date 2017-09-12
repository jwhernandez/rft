package Scripts.Comun;

import resources.Scripts.Comun.NumeroEspecialHelper;
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
import com.ibm.xtq.ast.nodes.ValueOf;
/**
 * Descripción: Ingresar un numero especial
 * Parámetros: 0) tipo (Postpago / Prepago) 1) nro pedido  2) nro especial 
 * 3) true si acepta 1er num como segunda opcion 4) true si tiene que ir a admin
 * 5)OK / NOK 6) nro de servicio asignado
 * EJ Postpago 1 89781333 false false res res 
 * Postpago 1 89845005 false false res res 
 * Pre-Condicion estar en la linea del servicio y con las condiciones para que se abra el numero especial
 * SS Nov 2015
 * Ultima Modificacion SS 23-11-2016
 */
public class NumeroEspecial extends NumeroEspecialHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[6] = "NA";
		argu[5] = "NOK"; // agregado el 21/11/16
		boolean bPrimerNumero = Boolean.valueOf(argu[3].toString().toLowerCase());
		boolean bViaAdmin = Boolean.valueOf(argu[4].toString().toLowerCase());
		if (bViaAdmin) {
			String[] IngresarServicioAdm;
			IngresarServicioAdm = new String[4];

			IngresarServicioAdm[0] = argu[0].toString();
			IngresarServicioAdm[1] = argu[1].toString();
			IngresarServicioAdm[2] = argu[2].toString();
			System.out.println("Numero especial recibe:" + argu[0].toString() + 
					argu[1].toString()+ argu[2].toString());

			callScript("Scripts.Comun.IngresarServicioAdmin", IngresarServicioAdm);
			// ver que se debe hacer si el resultado es NOK que significa que no se pudo liberar
			// el numero especial
		}

		// Abrir el pop up de numero de servicio
		NroServicio().openPopup();

		System. out.println("NumeroEspecial-Cant Nros: " + NumerosEspeciales().getProperty("RowsCount")); 

		// Chequear que el applet este abierto ICE Number Pick Applet
		String sNumeroEspecial = argu[2].toString();
		System. out.println("Numero Especial Deseado: " + sNumeroEspecial); 

		// Maximiza la pantalla de numero especial para que siempre se pueda seleccionar el mismo 23-11-2016
		IESiebel(ListaDeNúmerosDisponibles(),DEFAULT_FLAGS).maximize();
		
		int i= 0;
		int iTotal = (int) NumerosEspeciales().getProperty("RowsCount") ;
		if (iTotal> 0){ // se agrega 23-11-2016 el control
			while ((i<=iTotal -1) && (!(NumerosEspeciales().getCellText("DisplayValue", i).equals(sNumeroEspecial))))
			{
				NumerosEspeciales().activateRow(i);
				NumerosEspeciales().getCellText("DisplayValue", i);  
				System. out.println("NumeroEspecial " + i +" :" + NumerosEspeciales().getCellText("DisplayValue", i)); 
				i=i+1;
			} 
			if  (i>iTotal -1){ // si del loop se salio con i=10 se coloca i=9
				System. out.println("Decremento en 1 i " + i ); 
				i=i-1;
			}
			if (NumerosEspeciales().getCellText("DisplayValue", i).equals(sNumeroEspecial)) 
			{ 
				System. out.println("NumeroEspecial existe en posicion " + i); 
				NumerosEspeciales().doubleClick(i, "DisplayValue");
				System. out.println("NumeroEspecial asignado " + NroServicio().getProperty("Text")); 
				argu[6] = NroServicio().getProperty("Text");

				// Luego de este punto sale del applet y el applet no se vuelve a ver
				// Se controla que el número sea igual al deseado 23-11-2016
				if (!bPrimerNumero)
				{
					if (argu[6].equals(argu[2].toString())) argu[5] = "OK";
					else argu[5] = "NOK"; 
				} else argu[5] = "OK";
			} 
			else 
			{ 
				if (bPrimerNumero) {
					System. out.println("Se activo la opcion de usar el primer número"); 
					argu[5] = "OK";
					NumerosEspeciales().doubleClick(0, "DisplayValue");	
					System. out.println("NumeroEspecial asignado " + NroServicio().getProperty("Text")); 
					argu[6] = NroServicio().getProperty("Text");
				} else {
					System. out.println("Se cierra el pop up"); 
					Cerrar().performAction();
					argu[5] = "NOK"; 

				}
			} 
		} else Cerrar().performAction();
			
		System. out.println("Resultado " + argu[5] ); 
		System. out.println("NroServicio Asignado " + argu[6] ); 
		ImpreResultadoScript(getScriptName( ).toString(), argu[5].toString() + " - NroServicio Asignado " + argu[6].toString() );
	}
}

