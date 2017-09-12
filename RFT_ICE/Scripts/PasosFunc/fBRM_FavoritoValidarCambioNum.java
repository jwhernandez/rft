package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBRM_FavoritoValidarCambioNumHelper;

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
 * Description   : Valida Cambio de numero en BRM  
 * Script Name   : <b>fBRM_FavoritoValidarCambioNum</b>
 * @since  016/11/14
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)NA 2) ambiente 3) para ante error
 * Última modificación VC 201706016 Se añade opción de usar el parámetro i en el dp de pasos para poder hacer varias validaciones
 */
public class fBRM_FavoritoValidarCambioNum extends fBRM_FavoritoValidarCambioNumHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Favorito;
		Favorito = new String[4];
		// 0) Resultado = OK/NOK 1) Producto 2)Numero
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		
		
		// VC 201706016 Se añade opción de usar el parámetro i en el dp de pasos para poder hacer varias validaciones 
		if(args[1].toString().equals("NA")){
			Favorito[1] = dpString("BRM_FavProd");
			Favorito[2] = dpString("BRM_FavNum");
			Favorito[3] = dpString("BRM_Servicio");
		}else{
			int i = Integer.parseInt(args[1].toString());
			Favorito[1] = dpString("BRM_FavProd"+i);
			Favorito[2] = dpString("BRM_FavNum"+i);
			Favorito[3] = dpString("BRM_Servicio"+i);
		}
		callScript("Scripts.Comun.SistemasExternos.BRM_FavoritoValidarCambioNum", Favorito);

		if  (Favorito[0].toString().equals("NOK")) {
			MensError[0] = "Problema al buscar numero favorito en BRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

