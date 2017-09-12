package Scripts.PasosFunc;
import Scripts.Comun.CompraPlanCategoriaCatalogoNew;
import Scripts.Comun.BuscarCategoriasPlan;
import resources.Scripts.PasosFunc.fCompraPlanCategoriaCatalogoNewHelper;
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
 * Description   : Comprar un plan desde el catálogo de categorias
 * Parámetros	   : 0) numero de caso 1)un digito indicando el numero de variable en el dp
 * 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * Ejemplo: CP76_CD1_T1 1 QA true (Peiddo = 1-1740533812)
 * Pre-condiciones : Estar en la vista del pedido
 * @since  2016/11/09
 * Ultima modificacion SS 2017/01/17 se leen las categorias desde un DP
 * 1_2_2017 cambio de variable para decidir si usar DP central o local
 * ss 07/3/2017 se agrega la accion para poder validar que un plan no existe en una categoria
 * Valores de CatAccion = NA le coloca cuando no lo encuentra en el DP, FALSE se utiliza para 
 * indicar que se espera no encontrar el plan y TRUE para indicar que se espera encontrar el plan
 * Para el tema de clientes morosos o de otras categorias crediticias que no veran ciertos planes
 * Pruebas realizadas QA: Pedido = 1-1744747922 PREQA: Pedido = 1-1731173622
 * Script Name   : <b>fCompraPlanCategoriaCatalogoNew</b>
 * 
 * ej CP26_CD1_T1 1 QA NA NA
 * @author SS
 * */
public class fCompraPlanCategoriaCatalogoNew extends fCompraPlanCategoriaCatalogoNewHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Plan;
		Plan = new String[12];
		//  Parámetros: 0) OK/NOK  1) plan 2) tramite 3) cant categorias 4) Tipo Categoria 1 5) Categ 1 
		// 6) Tipo categoria 2 7)Categ 2 8) Tipo categoria 3 9)Categ 3  10) Tipo categoria 4 
		// 11)Categ 4
			
		// * Parámetros: 0) OK/NOK  1) plan 2) tramite 3) Accion  4) cant categorias 5) Categ 1 
		// * 6) Categ 2 7) Categ 3  8) Categ 4 9) Categ 5

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
			Plan[4] = dpString("CantCats"+i); // ss 07/03/2017 se desplaza en 1
			int CantCats = Integer.parseInt(dpString("CantCats"+i));
			for (int j = 0; j < CantCats; j++) {
				Plan[5+j] = dpString("Cat"+(j+1)+"_"+i); // ss 07/03/2017 se desplaza en 1
			}
		} 
		else {
			// Se busca la categoria en el dp central de categorias
			Plan[2]=args[2].toString();
			callScript(new BuscarCategoriasPlan(), Plan);
		}

		Plan[2] = dpString("Tramite");
		try {
			if (dpString("CatAccion"+i)!=null) Plan[3] = dpString("CatAccion"+i);	
			else Plan[3] = "NA";
		} catch (Exception e) {
			Plan[3] = "NA"; // ss 07/03/2017 se agrega parametria para indicar la accion en true o false
		}
	
		callScript(new CompraPlanCategoriaCatalogoNew(), Plan);
		
		if  ((Plan[0].equals("NOK"))){
			MensError[0] = "Plan no se encontró en catálogo";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

