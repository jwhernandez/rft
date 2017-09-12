package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.SCRW_ModificarCategoriaHelper;
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
 * Description   : Cambia la categoria crediticia
 * Script Name   : <b>SCRW_ModificarCategoria</b>
 * @since  2017/02/13
 * Autor FF-MB
 * ult modif ss 19 julio 2017 se arreglan todos los objetos para que no sean dependientes de la version etc sino que tengan 
 * propiedades dinamicas 
 * Parametros: 0) Resultado = OK/NOK 1) cedula 2) categoria 3)observación  
 * ej res 408950854 B observacion 
 */
public class SCRW_ModificarCategoria extends SCRW_ModificarCategoriaHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		args[0] = "NOK"; 
		
		consultarScore().click();
		cedula().setText(args[1].toString());
		consultaCedula().click();
		modificarCategoríaManual().click();
		listaCategorias().click();
		listaCategorias().click(atText(args[2].toString()));
		observacion().setText(args[3].toString());
		btnModificar().click();
		
		args[0] = "OK"; 
	}
	
	
	
}

