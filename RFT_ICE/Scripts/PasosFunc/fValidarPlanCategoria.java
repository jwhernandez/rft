package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarPlanCategoriaHelper;
import Scripts.Comun.BuscarCategoriasPlan;
import Scripts.Comun.CompraPlanCategoriaCatalogoNew;

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
 * Description   : Functional Test Script
 * @author rft
 */
public class fValidarPlanCategoria extends fValidarPlanCategoriaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Plan;
		Plan = new String[12];
		//  Parámetros: 0) OK/NOK  1) plan 2) tramite 3) cant categorias 4) Tipo Categoria 1 5) Categ 1 
		// 6) Tipo categoria 2 7)Categ 2 8) Tipo categoria 3 9)Categ 3  10) Tipo categoria 4 
		// 11)Categ 4
			
		// * Parámetros: 0) OK/NOK  1) plan 2) tramite 3) cant categorias 4) Categ 1 
		// * 5) Categ 2 6) Categ 3  7) Categ 4 8) Categ 5

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
		Plan[1] = dpString("Plan"+i);
		if (dpString("CatLug"+i) =="Local") // por bug en la re/ejecucion busca variable CatLug 1_02_2017
		{
			Plan[3] = dpString("CantCats"+i);
			int CantCats = Integer.parseInt(dpString("CantCats"+i));
			for (int j = 0; j < CantCats; j++) {
				Plan[4+j] = dpString("Cat"+(j+1)+"_"+i);
			}
		} 
		else {
			// Se busca la categoria en el dp central de categorias
			Plan[2]=args[2].toString();
			callScript(new BuscarCategoriasPlan(), Plan);
		}

		Plan[2] = dpString("Tramite");
		callScript(new CompraPlanCategoriaCatalogoNew(), Plan);
		
		if  ((Plan[0].equals("NOK"))){
			//MensError[0] = "Plan no se encontró en catálogo";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}


