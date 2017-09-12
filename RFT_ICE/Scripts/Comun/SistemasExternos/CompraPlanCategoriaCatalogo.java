package Scripts.Comun.SistemasExternos;
import java.util.Iterator;

import resources.Scripts.Comun.SistemasExternos.CompraPlanCategoriaCatalogoHelper;
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
 * Script Name   : <b>CompraPlanCategoriaCatalogo</b>
 * Parámetros: 0) OK/NOK  1) plan 2) tramite 3) cant categorias 4) Tipo Categoria 1 5) Categ 1 
 * 6) Tipo categoria 2 7)Categ 2 8) Tipo categoria 3 9)Categ 3  10) Tipo categoria 4 
 * 11)Categ 4
 * 
 * Ej res "PLAN KOLBI DATOS CONECTA 24 M"  2 T1 "Nueva Generación Kölbi" T2 "Planes Kolbi Datos 4G"  
 * Ej res "HUAWEI ASCEND G610 BLANCO" 4 T1 Terminales T1 "Terminales Moviles" T2 HUAWEI T2 ASCEND


 * 1-1739437422
 */
public class CompraPlanCategoriaCatalogo extends CompraPlanCategoriaCatalogoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		ITestDataTable iDataTable = null;
		TestObject[] to;
		GuiTestObject gto; 
		boolean bEncontrado = false;
		int CantCat = Integer.valueOf(argu[3].toString());
		String sPlan = argu[1].toString();
		int i = 0;
		int rows = 0;
		int cols = 0;
		int col=0;
		String sTipoCat;
		String sOpcionCat;

		if (ICE_Catalogo().exists()) 
		{
			ICE_Catalogo().click();
			for (i=0; i<CantCat; i++) // 4  3y4 5y6 7y8 9y10
			{
				col=i*2; // 0 2 4 6
				sTipoCat = argu[4+col].toString(); // 3, 5 , 7, 9
				sOpcionCat = argu[4+col+1].toString(); // 4,6,8,10			
				System.out.println("Se procesa: Tipo= " + sTipoCat + " Categoria= " + sOpcionCat);

				switch (sTipoCat) 
				{
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
						System.out.println(gto.getProperties());
						gto.click();
						sleep(10);
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
						to = Categorias_T2().find(atChild(".class", "Html.A", ".text", sOpcionCat));
						gto = 	(GuiTestObject)	to[0];
						gto.click();
						sleep(10);
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
				iDataTable = (ITestDataTable) ListaProductos().getTestData("contents");
				for (i = 1; i <  iDataTable.getRowCount() && !bEncontrado; i++) 
				{
					if (iDataTable.getCell(i, 1)!= null)
					{
						if (iDataTable.getCell(i, 1).toString().contains(sPlan)) // esto puede dar lugar a error 
						{
							System.out.println(i +"-"+iDataTable.getCell(i, 1));
							if (iDataTable.getCell(i, 2)!= null)
							{
								System.out.println(i+"-"+iDataTable.getCell(i, 2));
								Property[] props = new Property[3];
								props[0] = new Property(".class", "Html.A");
								props[1] = new Property(".text", "Agregar ítem");
								int index = i-1;
								props[2] = new Property("RN", "ButtonAddToCart." + index);
								to= ListaProductos().find(atChild(props));

								gto = 	(GuiTestObject)	to[0];
								gto.click();
								bEncontrado = true;
								argu[0]="OK";
							}
						}
					}
				}  
			}	
		} // si existe catalogo
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

