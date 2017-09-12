package Scripts.Comun;
import resources.Scripts.Comun.AsociarListaEspecialHelper;

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
import com.rational.test.ft.sys.SpyMemory; 
import com.rational.test.ft.sys.SpyMemoryStatistics; 

/**
 * Script Name   : <b>AsociarListaEspecial</b>
 * Descripcion   : Asocia una lista especial a un numero especial SMS o Voz
 * @author SS
 * @param Parámetros: 0) NOK / OK 1) Producto al cual asociar 2) Nombre de la lista 3)Tramite
 * Precondiciones: Estar en el pedido
 * @since Nov 2015
 * ult modif 12/4/2017 se agrega opcion portin
 * res "Numero Kolbi SMS Favorito" 1-1765891437 Venta
 */
public class AsociarListaEspecial extends AsociarListaEspecialHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] ProductoObjetivo;
		ProductoObjetivo = new String[6];
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		// 3)action code 4) desde la linea o no (Si No) 5)Tramite 

		argu[0] = "OK";
		String sTramite = argu[3].toString();
	
		//Menu().ensureObjectIsVisible();
		ProductoObjetivo[0]=argu[1].toString();
		ProductoObjetivo[4]="Si";
		ProductoObjetivo[5]=sTramite;
		callScript("Scripts.Comun.BuscarProducto", ProductoObjetivo);
		if ((ProductoObjetivo[1].toString().equals("Encontrado"))) {
			if (!sTramite.equals("PortIn")) 
				ListaEspecial().setText(argu[2].toString());
			if (sTramite.equals("PortIn")) 
				ListaEspecial_PI().setText(argu[2].toString());
			
		
//			ListaEspecial().openPopup();
//			NewQuery().performAction();
//			ListaEspecialBuscada().setText(argu[2].toString());
//			ListaEspecialBuscada().processKey("EnterKey");
//			System.out.println(LineasdeListaEspecial().getProperty("RowsCount"));
//			if (Integer.parseInt(LineasdeListaEspecial().getProperty("RowsCount").toString()) >=1) {
//				Aceptar().performAction();
//			} else { argu[0] = "NOK";}
//			//Cerrar().performAction();
		} else {
			argu[0] = "NOK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

