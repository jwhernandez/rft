package Scripts.Comun;
import java.util.Iterator;

import libreria.utileria.Tipo;
import resources.Scripts.Comun.CompraPlanCategoriaCatalogoNewHelper;
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
 * Description   :  Comprar plan x catalogo de categorias
 * @author SS
 * @since  2016/10/28
 * Ultima modificacion SS 2017/01/17 se leen las categorias desde un DP
 * ss 07/3/2017 se agrega la accion para poder validar que un plan no existe en una categoria
 * Para el tema de clientes morosos o de otras categorias crediticias que no veran ciertos planes
 * 
 * Script Name   : <b>CompraPlanCategoriaCatalogoNew</b>
 * Parámetros: 0) OK/NOK  1) plan 2) tramite 3) cant categorias 4) accion 5) Categ 1 
 * 6) Categ 2 7) Categ 3  8) Categ 4 9) Categ 5 
 * (Accion NA para comprar, true para chequear que existe, false para chequear que no existe)
 * Ej res "PLAN KOLBI DATOS CONECTA 24 M"  venta 2 T1 "Nueva Generación Kölbi" T2 "Planes Kolbi Datos 4G"  
 * Ej res "HUAWEI ASCEND G610 BLANCO" venta 5 T0 "ICE Catálogo" T1 Terminales T1 "Terminales Móviles" T2 Huawei T2 ASCEND
 * Ej res "PLAN PROFESIONAL 1 24 M" venta 3 T1 "Nueva Generación Kölbi" T2 Profesional T2 "24 Meses SIN 4GLTE" 
 * res "PLAN CONECTADO 1 24 M 4GLTE" venta 3 T1 Planes T2 "Planes Post Pago 4GLTE" T2 "Conctado 24 M 4GLTE" 
 * res "PLAN CONECTADO 1 24 M 4GLTE" venta 4 T0 "ICE Catálogo" T1 Planes T2 "Planes Post Pago 4GLTE" T2 "Conctado 24 M 4GLTE" 
 * res "PLAN ALO 1 12 M EMPRESARIAL" venta 4 T0 "Catalogo Kolbi 5.0" T0 "Plan Alo" T1 "Oferta Empresarial" T1 "12 Meses" 
 * res "PLAN COMO QUIERO PREMIUM 2 12 M" venta 4 T0 "Catalogo Kolbi 5.0" T0 "Plan Como Quiero" T1 "Oferta Masiva" T1 "12 Meses" 
 * 1-1740805382
 * 1-1739437422
 * 1-1723516849 en desa -- script tecnico probado con este pedido
 * res "PLAN COMO QUIERO PREMIUM 2 12 M" venta 4 "Catalogo Kolbi 5.0" "Plan Como Quiero" "Oferta Masiva"  "12 Meses" 
 * Ej res "IPHONE 6 PLUS 64GB GRIS PLATA" venta 5 "ICE Catálogo" Terminales "Terminales Móviles" Huawei ASCEND
 * ej res "CHIP MEJORADO 2000" venta 4 "ICE Catálogo" Kölbi Planes "Planes Prepagos"
 * res "PLAN COMO QUIERO CHART 1 12 M" venta 4 "Oferta kölbi 5.0" "Oferta Kolbi Masiva" "Planes Como Quiero Chart" "12 Meses Grupo I Chart"
 * res "PLAN COMO QUIERO CHART 1 12 M" venta 4 "Catalogo ICE" "Nueva Generación Kölbi" Conversón "12 Meses"
 * res "Chip Extremo Verano" PortIn 5 "Catalogo ICE" Kölbi Planes "Planes Prepagos" "PLANES KOLBI PREPAGO" 
 * res "Chip Extremo Verano" PortIn NA 5 "Catalogo ICE" Kölbi Planes "Planes Prepagos" "PLANES KOLBI PREPAGO" 
 * res "Chip Extremo Verano" PortIn FALSE 5 "Catalogo ICE" Kölbi Planes "Planes Prepagos" "PLANES KOLBI PREPAGO" 



 */
public class CompraPlanCategoriaCatalogoNew extends CompraPlanCategoriaCatalogoNewHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";
		ITestDataTable iDataTable = null;
		TestObject[] to;
		GuiTestObject gto; 
		boolean bEncontrado = false;
		int CantCat = Integer.valueOf(argu[4].toString()); // ss 07/03/2017 se desplaza en 1
		String sPlan = argu[1].toString();
		String sAccion = argu[3].toString().toLowerCase(); // ss 07/03/2017 se agrega el param accion
		int i = 0;
		int rows = 0;
		int cols = 0;
		//int col=0;
		//String sTipoCat;
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
			sOpcionCat = argu[5+i].toString(); // 4 5 6 7 8 	// ss 07/03/2017 se desplaza en 1 	
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
					sOpcionCat = argu[5+i].toString(); //  4 5 6 7 8 	// ss 07/03/2017 se desplaza en 1		
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
 					to = Categorias_T2().find(atChild(".class", "Html.A", ".text", sOpcionCat.trim())); //
// 					System.out.println(to.length);
//					to = Categorias_T2().find(atChild(".class", "Html.A")); //, ".text", sOpcionCat.trim())
//					System.out.println(to.length);
//					for (int j = 0; j < to.length; j++) {
//						gto = 	(GuiTestObject)	to[j];	
//						System.out.println(gto.getProperties());
//						if (gto.getProperty(".text").equals("Oferta Kolbi Masiva"))
//							System.out.println("coincidio");
//						//System.out.println(gto.getProperty("*"+".text"+"*"));
//					}
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
		// Selección de productos
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
								//System.out.println(gto.getProperties());
								if (sAccion.equals("na")) gto.click(); // ss 07/01/2017 agrega la condicionalidad para comprar el plan
								bEncontrado = true;
								// argu[0]="OK"; // ss 07/01/2017 se considera la opcion de validar
							}
						}
					}
					if (i==0 || i==2|| i==4 || i == 6 || i==8 || i==10 ||
							i==12|| i==14 || i == 16 || i==18 || i==20) index++;
				}  


				if (!bEncontrado)
				{
					// Iterar sobre las posibles listas de productos
					try {
						System.out.println("Avanza al siguiente conjunto de productos");
						//CantProductos().click(atCell(atRow(atIndex(0)), atColumn(atIndex(5))));

						Property[] props = new Property[2];
						props[0] = new Property(".class", "Html.A");
						props[1] = new Property("RN", "GotoNextSet");
						to= CantProductos().find(atChild(props));
						gto = 	(GuiTestObject)	to[0];
						gto.click();
					} catch (Exception e) {
						bBuscarMasProds=false;
						System.out.println("No hay más productos");

					}
				} else bBuscarMasProds=false;
			}
		}	
		// ss 07/03/2017 se agrega el control de resultado para la opcion accion
		if ((sAccion.equals("na") && bEncontrado) ||
		   (sAccion.equals("false") && !bEncontrado) ||
		   (sAccion.equals("true") && bEncontrado))
				argu[0]="OK";
		
		//		} // si existe catalogo 12_12_2016 se modifica para agrear el release 5
		sleep(5);		
		if (sTramite.equals("Venta"))
			TabsPedidoVta().goTo("Order Entry - Line Items Detail View (Sales)", "L3");
		if (sTramite.equals("PortIn"))
			TabsPedidoPortabilidad().goTo("ICE Port Entry - Line Items Detail View (Sales)","L3");

		unregisterAll();
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

