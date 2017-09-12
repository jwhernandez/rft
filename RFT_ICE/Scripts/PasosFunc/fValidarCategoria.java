package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarCategoriaHelper;

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
 * Description   : Valida la categoria creditica
 * Script Name   : <b>fValidarCategoria</b>
 * @author FF
 * @since  2016/12/15
 * @Param =
 * Última modificación VC 20170207
 * ss 15 03 2017 se agrega tramite para identificar el port-in
 * ej CP32_CD1_T1 1 QA NA NA
 * ss 10/7/2017 se agrega mensaje de error
 */

public class fValidarCategoria extends fValidarCategoriaHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidarCatCred;
		ValidarCatCred = new String[3];
		//0) OK/NOK 1) CatCrediticia 2) Tramite

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
		
		//Se añade condicion para validar diferentes categorías en un mismo CP sin afectar los casos anteriores VC 20170207
		
		ValidarCatCred[2]=dpString("Tramite"); // ss 15 3 2017 se agrega para identificar port-in
		
		if(!args[1].toString().equals("NA") && !args[1].toString().equals("DP")){
			int i = Integer.parseInt(args[1].toString());
			ValidarCatCred[1]=dpString("CategoriaCrediticia"+i);
		}else{	
			ValidarCatCred[1]=dpString("CategoriaCrediticia");
		}
		
		callScript("Scripts.Comun.ValidarCategoria", ValidarCatCred);
		
		if  ((ValidarCatCred[0].toString().equals("NOK"))){
			MensError[0] = "Validar categoria falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}