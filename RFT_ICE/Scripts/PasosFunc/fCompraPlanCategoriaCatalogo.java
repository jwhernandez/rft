package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fCompraPlanCategoriaCatalogoHelper;
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
 * @author SS
 * */
public class fCompraPlanCategoriaCatalogo extends fCompraPlanCategoriaCatalogoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Plan;
		Plan = new String[12];
		//  Parámetros: 0) OK/NOK  1) plan 2) tramite 3) cant categorias 4) Tipo Categoria 1 5) Categ 1 
		// 6) Tipo categoria 2 7)Categ 2 8) Tipo categoria 3 9)Categ 3  10) Tipo categoria 4 
		// 11)Categ 4
		
		int CantCateg = 0;
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
		Plan[2] = dpString("Tramite");
		if (!dpString("TipoCat1_"+i).equals("NA")) 
		{
			CantCateg = 1;
			Plan[4] = dpString("TipoCat1_"+i);
			Plan[5] = dpString("Cat1_"+i);
			
			if (!dpString("TipoCat2_"+i).equals("NA")) 
			{
				CantCateg = 2;
				Plan[6] = dpString("TipoCat2_"+i);
				Plan[7] = dpString("Cat2_"+i);

				if (!dpString("TipoCat3_"+i).equals("NA")) 
				{
					CantCateg = 3;
					Plan[8] = dpString("TipoCat3_"+i);
					Plan[9] = dpString("Cat3_"+i);
					
					if (!dpString("TipoCat4_"+i).equals("NA")) 
					{
						CantCateg = 4;
						Plan[8] = dpString("TipoCat4_"+i);
						Plan[9] = dpString("Cat4_"+i);
					}
				}
			} 
			Plan[3]=String.valueOf(CantCateg);
			callScript("Scripts.Comun.CompraPlanCategoriaCatalogo", Plan);
		} else Plan[0]= "NOK";

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

