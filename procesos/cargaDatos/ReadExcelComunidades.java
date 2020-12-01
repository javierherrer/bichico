import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcelComunidades
{
    public static void main(String args[]) throws IOException
    {
    	
//obtaining input bytes from a file  
        FileInputStream fis=new FileInputStream(new File("datosComunidades.xls"));
//creating workbook instance that refers to .xls file  
        HSSFWorkbook wb=new HSSFWorkbook(fis);
//creating a Sheet object to retrieve the object  
        HSSFSheet sheet=wb.getSheetAt(0);
//evaluating cell type

        FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();
        String consulta = "INSERT INTO bichico.comunidad(nombre,lat,long)\n" +
                "VALUES('";
        for(Row row: sheet)     //iteration over row using for each loop
        {
            //System.out.println(row.toString());
            for(Cell cell: row)    //iteration over cell using for each loop
            {
                //System.out.print(" columna "+ cell.getColumnIndex() + " row "+ cell.getRowIndex());
                switch(formulaEvaluator.evaluateInCell(cell).getCellType())
                {

                    case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type
//getting the value of the cell as a number  
                       // System.out.print(cell.getNumericCellValue()+ "\t\t");
                        consulta = consulta + cell.getNumericCellValue()+",";
                        break;
                    case Cell.CELL_TYPE_STRING:    //field that represents string cell type
//getting the value of the cell as a string  
                       // System.out.print(cell.getStringCellValue()+ "\t\t");
                        consulta = consulta + cell.getStringCellValue()+ "',";
                        break;
                }

            }
            consulta = consulta.substring(0,consulta.length()-1);
            consulta = consulta +");";
            System.out.println(consulta);
            
            Connection conn = null;
     
			// Abrimos la conexión e inicializamos los parámetros 
			try {
				conn = ConnectionManager.getConnection();
//	private static String insertarMensaje = "INSERT INTO bichico.mensaje (emisor,email,contenido) VALUES (? , ? , ? )";
			
			PreparedStatement ps = conn.prepareStatement(consulta);
			int tablasAfectadas = ps.executeUpdate();
			System.out.println(tablasAfectadas);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            consulta = "INSERT INTO bichico.comunidad(nombre,lat,long)\n" +
                    "VALUES('";
            System.out.println();
        }
    }
}  