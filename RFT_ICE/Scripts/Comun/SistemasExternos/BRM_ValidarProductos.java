package Scripts.Comun.SistemasExternos;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.SistemasExternos.BRM_ValidarProductosHelper;

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
import com.rational.test.util.regex.Regex;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Valida productos en BRM  
 * Script Name   : <b>BRM_ValidarProductos</b>
 * @since  2016/11/15
 * @author SS
 * Parametros: 0) Resultado = OK/NOK 1) Producto 2)Operador 3)Estado 4)Cant Lineas Deseadas 
 * o NA para no realizar este control
 * ej res "Numero Kolbi SMS = Cancelado
 * ej res ALL <> Canceled NA
 * ej res "Descuento Basico  GPRS"  = Activo
 * Ult Modif
 * 	ss 24-11-2016 Se agrega el control de cantidad de lineas en forma opcional para los 
 * casos en de estado no establecido en los cuales se debe controlar que se agregue
 * en el trámite un producto con el mismo estado.
 * Para ello el tester podrá ingresar en el data pool la cantidad de líneas de ese producto
 * esperadas o NA para indicar que no realice este control.
 * ult modif ss 28 02 2017 se agrega el sleep porque no llega a reconocer la tab de pestanias
 * ss 22 de julio se cambia de col 3 a col2 y el estado de col4 a col3
 * ss 23/07/2017 se agrega exp reg
 */
public class BRM_ValidarProductos extends BRM_ValidarProductosHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		int iCantEncontrada =0;
		ITestDataTable iDataTable = null;
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String sEstado = null;
		String sEstadoLong = null;
		String sProd =  null;
		String sOperador =  argu[2].toString();
		String sProdDeseado = argu[1].toString();
		String sEstadoDeseado = argu[3].toString();
		String sCantLineasDeseadas = argu[4].toString();
		boolean bTodos = false;
		if (sProdDeseado.equals("ALL")) bTodos=true; // 7_12_2017 se cambia NA por ALL
		boolean bIgual = true;
		boolean bMostrar = false;
		if (sOperador.equals("<>")) bIgual = false;
		//IFtVerificationPoint ProductoVP;
		//IFtVerificationPoint EstadoVP;
		IFtVerificationPoint CantVP;
		boolean bEncontro=false;
		
		Regex re = new Regex(sProdDeseado); // ss 22/07/2017 agregado de regex

		int cant = 1;
		infoPaso(sScriptName, Tipo.DATO,"Productos filtrados por estado ", sOperador +  sEstadoDeseado );
		
		sleep(5);
		pestañas().waitForExistence();
		pestañas().click(atText("Plans"));
		sleep(10);
		
		Productos().waitForExistence(); // no funciona
				
		iDataTable = (ITestDataTable) Productos().getTestData("contents");
		int rows = iDataTable.getRowCount();
		System.out.println(iDataTable.getColumnCount());
		
		//for (int i = 0; i <rows && !bEncontro; i++) 
		for (int i = 0; i <rows; i++) // 24-11-2016 se eliminá el buscar hasta encontrar el primero
			// porque se necesita saber cuantos productos hay del nombre y estado deseado
		{
			sProd = iDataTable.getCell(i, 3).toString(); // ss 22 de julio se cambia de col 3 a col2 
 
			sEstado = iDataTable.getCell(i,6).toString(); //ss 22 de julio se cambia de col 6 a col3 
			switch (sEstado) {
			case "0":
				sEstadoLong = "Not Set";
				break;
			case "1":
				sEstadoLong = "Activo";
				break;
			case "3":
				sEstadoLong = "Canceled";
				break;
			default:
				sEstadoLong = "Desconocido";
				break;
			}
			bMostrar = false;
			if (sProdDeseado.equals("ALL"))
			{
				bTodos = true;
				// informar productos filtrando por estado deseado
				if (bIgual) 
				{
					if (sEstadoLong.equals(sEstadoDeseado)) bMostrar = true;
				} 
				else 
				{
					if (!sEstadoLong.equals(sEstadoDeseado)) bMostrar = true;
				}
				if (bMostrar) 
				{
					System.out.println("Producto:" + cant + "-"+ sProd);
					System.out.print(" Estado" +sOperador + sEstadoLong);			
					infoPaso(sScriptName, Tipo.DATO,"Producto y Estado "+cant,sProd + "-" + sEstado+ "-" + sEstadoLong );
					cant++;
				}
			} else // Validar que el producto sea el deseado con el estado deseado
			{
				System.out.println( i + "-"+ sProdDeseado+ "-"+ sProd);
				System.out.println("matches "+re.matches(sProd));
				//if (sProdDeseado.equals(sProd) && sEstadoDeseado.equals(sEstadoLong)) // ss 23/07/2017 se cambia por exp reg
				if (re.matches(sProd) && sEstadoDeseado.equals(sEstadoLong)) // ss 23/07/2017 se cambia por exp reg

				{
					bEncontro=true;
					iCantEncontrada++;
					System.out.println("encontrado" );	

				}
			}
		} // fin de la iteracion
		if (bTodos) argu[0] = "OK";
		else 
		{ // si solo se busca un producto
			if (bEncontro) // Si solo se busca un nombre de producto y lo encontro
			{
				// ProductoVP = vpManual("Producto", sProdDeseado, sProdDeseado); no valida nada en realidad - se elimina dado que vp no se usa
				// EstadoVP = vpManual("Estado", sEstadoDeseado, sEstadoDeseado); no valida nada en realidad - se elimina dado que vp no se usa
				System.out.println("Producto:" + cant + "-"+ sProd);
				System.out.print(" Estado=" + sEstadoDeseado + "-" + sEstadoDeseado);	
				String sCantEncontrada = String.valueOf(iCantEncontrada);
				infoPaso(sScriptName, Tipo.DATO,"Cantidad - Producto - Estado:",sCantEncontrada+"-" + sProd + "-" + sEstado+ "-" + sEstadoLong );
				if (!sCantLineasDeseadas.equals("NA"))
				{
					CantVP = vpManual("Cant", sCantLineasDeseadas, sCantEncontrada);
					//if (ProductoVP.performTest() && EstadoVP.performTest() && CantVP.performTest())  
					if (CantVP.performTest())  // solo se deja CantVP que es la única que valida la cantidad de lineas
						argu[0] = "OK";
				} else
				{
					//if (ProductoVP.performTest() && EstadoVP.performTest() )  
					argu[0] = "OK";
				}
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

