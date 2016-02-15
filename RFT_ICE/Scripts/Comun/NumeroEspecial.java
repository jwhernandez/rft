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
/**
 * Descripción: Ingresar un numero especial
 * Parámetros: 0) tipo (Postpago / Prepago) 1) nro pedido  2) nro especial 
 * 3) true si acepta 1er num como segunda opcion 4) true si tiene que ir a admin
 * 5)OK / NOK 6) nro de servicio asignado
 * Pre-Condicion estar en la linea del servicio y con las condiciones para que se abra el numero especial
 * SS Nov 2015
 */
public class NumeroEspecial extends NumeroEspecialHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		argu[6] = "NA";
		if (argu[4].toString().equals("true")) {
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
		int i= 0;
		int iTotal = (int) NumerosEspeciales().getProperty("RowsCount") ;
		for  ( i = 0 ; (i<=iTotal -1) ; i++) {
			if (!(NumerosEspeciales().getCellText("DisplayValue", i).equals(sNumeroEspecial))){
				NumerosEspeciales().activateRow(i);
				NumerosEspeciales().getCellText("DisplayValue", i);  
				System. out.println("NumeroEspecial " + i +" :" + NumerosEspeciales().getCellText("DisplayValue", i)); 
			}
		}
		if  (i>iTotal -1){i=i-1;}
		if (NumerosEspeciales().getCellText("DisplayValue", i).equals(sNumeroEspecial)) 
		{ 
			argu[5] = "OK";
			System. out.println("NumeroEspecial existe en posicion " + i); 
			NumerosEspeciales().doubleClick(i, "DisplayValue");
			System. out.println("NumeroEspecial asignado " + NroServicio().getProperty("Text")); 
			argu[6] = NroServicio().getProperty("Text");
			// Luego de este punto sale del applet y el applet no se vuelve a ver
		} 
		else 
		{ 
			if (argu[3].toString().equals("true")) {
				argu[5] = "OK";
				NumerosEspeciales().doubleClick(0, "DisplayValue");	
				System. out.println("NumeroEspecial asignado " + NroServicio().getProperty("Text")); 
				argu[6] = NroServicio().getProperty("Text");
			} else {
				argu[5] = "NOK"; 
			}
		} 
	}
}

