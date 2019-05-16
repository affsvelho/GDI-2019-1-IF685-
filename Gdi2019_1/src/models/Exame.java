package models;

import java.sql.Date;

public class Exame {

	private String codigo;
	private String status;
	private String dateExame;
	
	
	public Exame(String codigo, String status, String dateExame) {
		this.codigo = codigo;
		this.status = status;
		this.dateExame = dateExame;
		
	}
	
	public void toStringExame() {
		System.out.println(this.codigo+""+this.status+""+this.dateExame);
	}
	
	public String getCodigo() { return this.codigo; }
	
	public void setCodigo(String codigo) { this.codigo = codigo; }
	
	public String getStatus() {return this.status; }
	
	public void setStatus(String status) {this.status = status; }
	
	public String getDateExame() {return this.dateExame;}
	
	public void setDateExame(String dateExame) {this.dateExame = dateExame; }
}
