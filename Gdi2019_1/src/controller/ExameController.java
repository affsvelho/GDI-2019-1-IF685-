package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Exame;
import models.ImagemExame;

public class ExameController {

	private static String username = "g191if685cc_eq04";
    private static String password = "lecqpper";
    private Connection con;
    
    public ExameController () {
    	
    	this.con = connectToDatabase(username,password);
    }
    
    public static Connection connectToDatabase(String username,String password) {
    	Connection con = null;
    	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		con = DriverManager.getConnection("jdbc:oracle:thin:@oracle12c.cin.ufpe.br:1521:Instance01",username,password);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return con;
    }
    
    public void inserir(Exame exame) throws SQLException {
    	Statement statement = this.con.createStatement();
		String insercao = "INSERT INTO EXAME VALUES ('"+exame.getCodigo()+"',"+" '"+exame.getStatus()+"','"+exame.getDateExame()+"')";
		System.out.println(insercao);
		statement.executeQuery(insercao);
		statement.close();
		con.close();
    }
    
    public Exame getExame (String codigo) throws ClassNotFoundException, SQLException {		
		PreparedStatement ps = this.con.prepareStatement("SELECT * FROM EXAME WHERE Codigo = ? ");
		ps.setString(1, codigo);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		return new Exame(rs.getString("codigo"),rs.getString("status"),rs.getString("data_exame"));
	}
    
    
    
    public ImagemExame getImageById (String codImagemExame) throws SQLException, IOException, ClassNotFoundException {		
		PreparedStatement ps = this.con.prepareStatement("SELECT * FROM MIDIA WHERE ID = ?");
		ps.setString(1, codImagemExame);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			Blob b = rs.getBlob("IMAGEM");
			return new ImagemExame(b);
		}else {
			return null;
		}
	}
    
public static void salvarImagemExame(String filepath, String codExame) throws ClassNotFoundException, SQLException {		
		Connection con = connectToDatabase(username, password);
		byte[] bFile = readBytesFromFile(filepath);
		PreparedStatement ps = con.prepareStatement("INSERT INTO MIDIA (ID, IMAGEM) VALUES(?, ?)");
		ps.setString(1, codExame);
		ps.setBytes(2, bFile);
		
		ps.executeUpdate();
	}
	
	private static byte[] readBytesFromFile(String filePath) {		
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;
		try {
			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];

			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);
			
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;
	}
}
