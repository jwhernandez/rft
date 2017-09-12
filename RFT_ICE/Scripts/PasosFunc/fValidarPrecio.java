package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarPrecioHelper;
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
* Descripción: Chequea que el precio del producto pasado como parametro
* sea mayor que cero o igual al valor pasado como parametro
* Expande el servicio pasado como parametro y busca el producto pasado como parametro.
* Script Name   : <b>fValidarPrecio</b> 
* @since   2017/03/31
* @author SS
* @Param 0) digito
* ej:  CP26_CD1_T1 1 QA NA NA
*/
public class fValidarPrecio extends fValidarPrecioHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Precio;
		Precio = new String[4];
		//  OK / NOK 1) tramite 2) prod 3) precio (Puede ser NA 
		// en ese caso solo valida que sea mayor que cero)
		
		String[] BuscProd;
		BuscProd = new String[6];
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		// 3)action code 4) desde la linea o no (Si No) 5)Tramite 

		String[] ExpandiryBuscar;
		ExpandiryBuscar = new String[4]; // se agrega tramite
		// 0) Producto padre 1) Producto Hijo 2)Encontro / No Encontro 3)Tramite
		
		String[] MensError;
		MensError = new String[4];
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		int i = Integer.parseInt(args[1].toString());
		
		// Buscar el Producto Padre
		BuscProd[0] = dpString("Price_ProdPadre"+i);
		BuscProd[4] = "Si"; // Que busque desde el principio 	
		BuscProd[5] = dpString("Tramite"); 	
		callScript("Scripts.Comun.BuscarProducto", BuscProd);
		
		if (BuscProd[1].equals("Encontrado") )
		{
			// Expandir el prod padre y buscar el producto hijo
			ExpandiryBuscar[0]=dpString("Price_ProdPadre"+i);
			ExpandiryBuscar[1]=dpString("Price_Prod"+i);
			ExpandiryBuscar[3] = dpString("Tramite"); 	 
			callScript("Scripts.Comun.ExpandiryBuscar",ExpandiryBuscar);

			if (ExpandiryBuscar[2].equals("Encontrado") )
			{
				Precio[1] = dpString("Tramite");
				Precio[2]=dpString("Price_Prod"+i);
				Precio[3] = dpString("Price"+i);
				callScript("Scripts.Comun.ValidarPrecio", Precio);

				if  (Precio[0].toString().equals("NOK")){
					MensError[0] = "La validación de precio falló";
					//MensError[0] = "xDefecto";
					MensError[1] = args[3].toString();
					MensError[2] = args[0].toString();
					MensError[3] = getScriptName( );
					callScript("Scripts.Comun.TerminarCasoError", MensError);
				}				
			}  else System.out.println("No se encontró el producto");
		} else System.out.println("No se encontró el producto padre");
	}
}

