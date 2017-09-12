package Scripts.Comun;
import resources.Scripts.Comun.ValidarCamposTerminalHelper;
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
 * Description   : Validar los campos marca, serie, modelo y version sean editables o no 
 * Script Name   : <b>ValidarCamposTerminal</b>
 * @Param 0)IN producto  a posicionarse en o NA si se usa la linea donde esta
 * 1) tramite 2)OUT OK/NOK  
 *  3) Marca 4) Modelo 5) Serie 6) Version
 * Los parametros version, marca, modelo y serie pueden tener Editable o no Editable por separado
 * Pueden tener NA si no hay que validar nada para ese campo
 * ejemplo "Servicio de Telefonia Movil" PortIn  OK "No Editable" "No Editable" "No Editable" "No Editable"
  * "PLAN PROFESIONAL 2 12 M" PortIn OK "No Editable" "No Editable" "No Editable" "No Editable"
 * "NOKIA E7"  PortIn res  "No Editable" "No Editable" Editable Editable (OK)
 * "NOKIA E7" PortIn res  "No Editable" "NA" Editable Editable (OK)
 * "NOKIA E7" PortIn res "No Editable" "NA" "No Editable" Editable (NOK)
 * "NOKIA E7" PortIn res  
 * NA PortIn res "No Editable" "No Editable" Editable Editable (OK)
 * @since  2016/01/21
 * @author Sandra
 */
public class ValidarCamposTerminal extends ValidarCamposTerminalHelper
{

	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString(); // tramite
		}
		
		// Marca, Modelo, Version o Serie pueden tener Editable / No Editable o NA
		String sMarca = "NA";
		if (argu.length >= 4 ) { 
			sMarca = argu[3].toString();} // Indica si debe ser editable o no o no validarse

		String sModelo = "NA";
		if (argu.length >= 5 ) { 
			sModelo = argu[4].toString();} // Indica si debe ser editable o no o no validarse

		String sSerie = "NA";
		if (argu.length >= 6 ) { 
			sSerie = argu[5].toString();} // Indica si debe ser editable o no o no validarse
		
		String sVersion = "NA";
		if (argu.length >= 7 ) { 
			sVersion = argu[6].toString();} // Indica si debe ser editable o no o no validarse
		argu[2] = "NOK";
		
		if (!argu[0].toString().equals("NA")) {
			
			String[] BuscProd;
			BuscProd = new String[6];
			// 0) IN Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 3)action code 
			// 4) Si / No 5) Tramite

			BuscProd[0] = argu[0].toString();

			BuscProd[4] = "Si"; // Buscar desde el comienzo
			BuscProd[5] = sTramite;
			callScript("Scripts.Comun.BuscarProducto",BuscProd);
			int iPosicion = Integer.parseInt (BuscProd[2].toString());

			if (!sTramite.equals("PortIn")){
				LineasPedido().activateRow(iPosicion); }
		}
		
		String sVersionOK="OK"; 
		
		String sMarcaOK="OK"; 
		if (!sVersion.equals("NA")) { 
			if (!sTramite.equals("PortIn")){
				boolean bMarcaEnabled = Boolean.parseBoolean(Marca().getProperty("IsEnabled").toString());
				System.out.println("Marca is enabled? = " + bMarcaEnabled );
				if ((sMarca.equals("Editable") && !bMarcaEnabled) || 
						(sMarca.equals("No Editable") && bMarcaEnabled)) {sMarcaOK="NOK";}}
			if (sTramite.equals("PortIn")){
				boolean bMarcaEnabled = Boolean.parseBoolean(MarcaPI().getProperty("IsEnabled").toString());
				System.out.println("Marca is enabled? = " + bMarcaEnabled );
				if ((sMarca.equals("Editable") && !bMarcaEnabled) || 
						(sMarca.equals("No Editable") && bMarcaEnabled)) {sMarcaOK="NOK";}}
		} 
		
		String sModeloOK="OK"; 
		if (!sModelo.equals("NA")) { 
			if (!sTramite.equals("PortIn")){
				boolean bModeloEnabled = Boolean.parseBoolean(Modelo().getProperty("IsEnabled").toString());
				System.out.println("Modelo is enabled? = " + bModeloEnabled );
				if ((sModelo.equals("Editable") && !bModeloEnabled) || 
						(sModelo.equals("No Editable") && bModeloEnabled)) {sModeloOK="NOK";}}
			if (sTramite.equals("PortIn")){
				boolean bModeloEnabled = Boolean.parseBoolean(ModeloPI().getProperty("IsEnabled").toString());
				System.out.println("Modelo is enabled? = " + bModeloEnabled );
				if ((sModelo.equals("Editable") && !bModeloEnabled) || 
						(sModelo.equals("No Editable") && bModeloEnabled)) {sModeloOK="NOK";}}
		} 

		String sSerieOK="OK"; 
		if (!sSerie.equals("NA")) { 
			if (!sTramite.equals("PortIn")){
				boolean bSerieEnabled = Boolean.parseBoolean(Serie().getProperty("IsEnabled").toString());
				System.out.println("Serie is enabled? = " + bSerieEnabled );
				if ((sSerie.equals("Editable") && !bSerieEnabled) || 
						(sSerie.equals("No Editable") && bSerieEnabled)) {sSerieOK="NOK";}}
			if (sTramite.equals("PortIn")){
				boolean bSerieEnabled = Boolean.parseBoolean(SeriePI().getProperty("IsEnabled").toString());
				System.out.println("Serie is enabled? = " + bSerieEnabled );
				if ((sSerie.equals("Editable") && !bSerieEnabled) || 
						(sSerie.equals("No Editable") && bSerieEnabled)) {sSerieOK="NOK";}}
		} 
		
		if (!sVersion.equals("NA")) { 
			if (!sTramite.equals("PortIn")){
				boolean bVersionEnabled = Boolean.parseBoolean(Version().getProperty("IsEnabled").toString());
				System.out.println("Version is enabled? = " + bVersionEnabled );
				if ((sVersion.equals("Editable") && !bVersionEnabled) || 
						(sVersion.equals("No Editable") && bVersionEnabled)) {sVersionOK="NOK";}}
			if (sTramite.equals("PortIn")){
				boolean bVersionEnabled = Boolean.parseBoolean(VersionPI().getProperty("IsEnabled").toString());
				System.out.println("Version is enabled? = " + bVersionEnabled );
				if ((sVersion.equals("Editable") && !bVersionEnabled) || 
						(sVersion.equals("No Editable") && bVersionEnabled)) {sVersionOK="NOK";}}
		} 

		System.out.println("Validacion Marca: " + sMarca + "-" + sMarcaOK);
		System.out.println("Validacion Modelo: " + sModelo + "-" + sModeloOK);
		System.out.println("Validacion Serie: " +sSerie + "-" + sSerieOK);
		System.out.println("Validacion Version: " +sVersion + "-" + sVersionOK);
		
		if (sVersionOK.equals("OK") && sSerieOK.equals("OK") && sModeloOK.equals("OK") 
				                    && sMarcaOK.equals("OK")) 
			{
				argu[2] = "OK";
			} else {argu[2] = "NOK";}

		System.out.println("Resultado: " + argu[2]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}

