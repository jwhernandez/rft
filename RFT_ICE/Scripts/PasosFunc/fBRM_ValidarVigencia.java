package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBRM_FavoritoValidarCambioNumHelper;
import resources.Scripts.PasosFunc.fBRM_ValidarVigenciaHelper;
import Scripts.Comun.SistemasExternos.BRM_ValidarVigencia;
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
 * Description   : Validar en BRM la vigencia  del producto SMS Favorito
 * Script Name   : <b>fBRM_ValidarVigencia</b>
 * @since  29/11/2016
 * Autor VC - FF
 * Modif 30/11/2016 ss se le aplica la mejora al metodo callscript sugerida por Argentum - Jairis
 */
public class fBRM_ValidarVigencia extends fBRM_ValidarVigenciaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Vigencia;
		Vigencia = new String[5];
		
		// 0) Resultado = OK/NOK 1) Producto 2)Numero
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		int i = Integer.parseInt(args[1].toString());
		Vigencia[1] = dpString("BRM_FavProd");
		Vigencia[2] = dpString("BRM_Estado" +i);
		Vigencia[3] = dpString("BRM_Vigencia");
		Vigencia[4] = dpString("BRM_FechaIni"+ i);
		callScript(new BRM_ValidarVigencia(), Vigencia);
		//callScript("Scripts.Comun.SistemasExternos.BRM_ValidarVigencia", Vigencia);


		if  (Vigencia[0].toString().equals("NOK")) {
			MensError[0] = "Problema al buscar numero favorito en BRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

