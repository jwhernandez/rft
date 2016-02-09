package Scripts.Comun;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import javax.swing.RowSorter;
import javax.swing.JTable;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import resources.Scripts.Comun.ValidarEstructuraCPHelper;
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
 * Description   : Valida la estructura de productos dados de baja y de alta en un CP
 * Script Name   : <b>ValidarEstructuraCP</b>
 * @since  2016/01/05
 * @author Sandra
 */
public class ValidarEstructuraCP extends ValidarEstructuraCPHelper
{
	public void testMain(Object[] args) 
	{

		String[] columnNames = {"Producto","Categoria", "Codigo"};
		Object[][] data = null;
		JTable table = new JTable(data, columnNames);
		
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
		
		JTable tablaOrdenada = new JTable(tableModel);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaOrdenada.getModel());

		String[] ValidaEstruct;
		ValidaEstruct = new String[2];
		
		table = (JTable) args[1];
		
		args[0] = "OK";

		callScript("Scripts.Comun.SeleccionarProductosCP",ValidaEstruct);
		if (ValidaEstruct[1].toString().equals("OK")) {
			tablaOrdenada.setRowSorter(sorter);
			
			List <RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
            sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            
           	String sCodAccion1 = data[0][2].toString(); // codigo
        	String sCat1 = data[0][1].toString(); // categoria

            int iCantRows = 1;
            String iterar ="true";
            
            // recorrer y trabajar por categoría de a pares o de a uno
            while (iterar=="true") {
            	String sCodAccion2 = data[iCantRows][2].toString(); // codigo
            	String sCat2 = data[iCantRows][1].toString(); // categoria
            	if ((sCat1.equals(sCat2))) {
            		if (!((sCodAccion1 == "Nuevo" && sCodAccion2=="Desconexión") ||
            				(sCodAccion2 == "Nuevo" && sCodAccion1=="Desconexión"))) {
            			iterar = "false";
            			args[1] = "NOK";
            		} else {
            			if (!(sCodAccion1=="-")) { 
            				iterar = "false";
            				args[1] = "NOK";
            			}
            		}
            	} 
            	sCodAccion1 = sCodAccion2 ;
            	sCat1 = sCat2 ;
           	iCantRows++;
            } // while
		} else {
			args[0] = "NOK";
		}

		
	}
}

