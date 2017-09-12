package Scripts.Comun;
import java.util.Iterator;

import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarPlanCategoriaHelper;
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
 * Description   :  Validar que el plan no se pueda vender, segun la categoria crediticia
 * @author FF-MB
 * @since  2017/02/21
 * Script Name   : <b>CompraPlanCategoriaCatalogo</b>
 * Parámetros: 0) OK/NOK  1) plan 2) tramite 3) cant categorias 4) Categ 1 
 * 5) Categ 2 6) Categ 3  7) Categ 4 8) Categ 5
 * 
 * Ej res "PLAN KOLBI DATOS CONECTA 24 M"  venta 2 T1 "Nueva Generación Kölbi" T2 "Planes Kolbi Datos 4G"  
 */

public class ValidarPlanCategoria extends ValidarPlanCategoriaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";
		ITestDataTable iDataTable = null;
		TestObject[] to;
		GuiTestObject gto; 
		boolean bEncontrado = false;
		int CantCat = Integer.valueOf(argu[3].toString());
		String sPlan = argu[1].toString();
		int i = 0;
		int rows = 0;
		int cols = 0;
		String sOpcionCat;
	
		String sTramite = "Venta";
		if (argu.length >= 3 ) { 
			sTramite = argu[2].toString();
		}

		if (!sTramite.equals("PortIn")){
			sleep(2);
			TabsPedidoVta().goTo("Sales Order-Browse Catalog View", "L3");
			sleep(2);
			TabsPedidoVta().goTo("Sales Order-Browse Catalog View", "L4");
		}
		if (sTramite.equals("PortIn")){
			sleep(2);
			TabsPedidoPortabilidad().goTo("ICE Port Order - Browse Catalog View", "L3");
			sleep(2);
			TabsPedidoPortabilidad().goTo("ICE Port Order - Browse Catalog View", "L4");}

		for (i=0; i<CantCat; i++) // 0 1 2 3 4 que equivale a 4 5 6 7 8  
		{
			sOpcionCat = argu[4+i].toString(); // 4 5 6 7 8 		
			String sTipoCatSel = "Invalida";
			if (Categorias_T0().exists()) sTipoCatSel="T0";
			else if (Categorias_T1().exists()) sTipoCatSel="T1";
			else if (Categorias_T2().exists())sTipoCatSel="T2";
			System.out.println("Se procesa: Tipo= " + sTipoCatSel + " Categoria= " + sOpcionCat);
			
			switch (sTipoCatSel) 
			{
			case "T0": // primer nivel de categorias
				Categorias_T0().waitForExistence();
				System.out.println("Opción tipo categoria T0 " + Categorias_T0().exists());	
				if (Categorias_T0().exists())
				{
					sOpcionCat = argu[4+i].toString(); //  4 5 6 7 8 			
					iDataTable = (ITestDataTable) Categorias_T0().getTestData("contents");
					rows = iDataTable.getRowCount();
					System.out.println("Se procesa: Tipo= " + sTipoCatSel + " Categoria= " + sOpcionCat);
					//System.out.println(iDataTable.getCell(0, 1)); 2 - 4 - 6 
					to = Categorias_T0().find(atChild(".class", "Html.A", ".text", sOpcionCat));
					gto = 	(GuiTestObject)	to[0];
					gto.click();
					sleep(20);
				}
				break;
			case "T1":
				Categorias_T1().waitForExistence();
				System.out.println("Opción tipo categoria T1 " + Categorias_T1().exists());	
				if (Categorias_T1().exists()) 
				{
					System.out.println("Opción tipo categoria T1 existe");	
					iDataTable = (ITestDataTable) Categorias_T1().getTestData("contents");
					rows = iDataTable.getRowCount();
					System.out.println("ROWS " + rows);
					//System.out.println(iDataTable.getCell(0, 1)); 2 - 4 - 6 
					to = Categorias_T1().find(atChild(".class", "Html.A", ".text", sOpcionCat));
					gto = 	(GuiTestObject)	to[0];
					gto.click();
					sleep(20);
				}
				break;
			case "T2":
				Categorias_T2().waitForExistence();
				System.out.println("Opción tipo categoria T2 " + Categorias_T2().exists() );		 
				if (Categorias_T2().exists() ) 
				{
					System.out.println("Opción tipo categoria T2 existe");	
					iDataTable = (ITestDataTable) Categorias_T2().getTestData("contents");
					cols = iDataTable.getColumnCount();
					System.out.println("Cols " + cols);
					System.out.println("*"+sOpcionCat.trim()+"*");
 					to = Categorias_T2().find(atChild(".class", "Html.A", ".text", sOpcionCat.trim())); 
					gto = 	(GuiTestObject)	to[0];
					gto.click();
					sleep(20);
				}
				break;
			default:
				System.out.println("Opción tipo categoria no valida");
				break;
			} // end del switch
		} // For de categorias

		//--------------------------------------------------------------------
		// Selección de productos, al no encontrarlo el script es correcto porque el plan no debe estar disponible para un cliente catagoria C o D
		//--------------------------------------------------------------------
		if (ListaProductos().exists())
		{
			boolean bBuscarMasProds=true;
			while (bBuscarMasProds)
			{
				int index = 0;
				iDataTable = (ITestDataTable) ListaProductos().getTestData("contents");
				for (i = 0; i <  iDataTable.getRowCount() && !bEncontrado && bBuscarMasProds; i++) 
				{
					if (iDataTable.getCell(i, 1)!= null)
					{
						System.out.println(i +"1-"+ index+ "-" +iDataTable.getCell(i, 1));
						if (iDataTable.getCell(i, 1).toString().contains(sPlan)) // esto puede dar lugar a error 
						{
							if (iDataTable.getCell(i, 2)!= null)
							{
								System.out.println(i+"2-"+ "-" + iDataTable.getCell(i, 2));
								Property[] props = new Property[3];
								props[0] = new Property(".class", "Html.A");
								props[1] = new Property(".text", "Agregar ítem");
								props[2] = new Property("RN", "ButtonAddToCart." + index);
								to= ListaProductos().find(atChild(props));

								gto = 	(GuiTestObject)	to[0];
								gto.click();
								bEncontrado = true;
								argu[0]="NOK";
							}
						}
					}
					if (i==0 || i==2|| i==4 || i == 6 || i==8 || i==10 ||
							i==12|| i==14 || i == 16 || i==18 || i==20) index++;
				}  


				if (!bEncontrado)
				{
					bBuscarMasProds=false;
					System.out.println("No hay más productos para el cliente");
					argu[0]="OK";
				}					
				else bBuscarMasProds=false;
				}
			}	
		
		sleep(5);		
		if (sTramite.equals("Venta"))
			TabsPedidoVta().goTo("Order Entry - Line Items Detail View (Sales)", "L3");
		if (sTramite.equals("PortIn"))
			TabsPedidoPortabilidad().goTo("ICE Port Entry - Line Items Detail View (Sales)","L3");

		unregisterAll();
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

