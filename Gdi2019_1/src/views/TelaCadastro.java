package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Exame;
import controller.ExameController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JSpinner;
import javax.swing.JFileChooser;

public class TelaCadastro {

	private JFrame FrameCadastro;

	private static ExameController cad;
	private JTextField color;

	public TelaCadastro(ExameController cad) {
		this.cad = cad;
		initialize();
	}
	
	public void setVisible(boolean v){
		this.FrameCadastro.setVisible(v);
	}

	private void initialize() {
		FrameCadastro = new JFrame();
		FrameCadastro.setTitle("Cadastrar Exames na tabela Exame");
		FrameCadastro.setBounds(100, 100, 857, 621);
		FrameCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		FrameCadastro.getContentPane().setLayout(null);
		FrameCadastro.setVisible(true);
		
		FrameCadastro.getContentPane().setBackground(Color.lightGray);
		
		JLabel lblBuscarExame = new JLabel("Cadastrar Exame");
		lblBuscarExame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarExame.setBounds(10, 11, 227, 14);
		FrameCadastro.getContentPane().add(lblBuscarExame);		
				
		
		//----------------------------MUDAR TIPO DE LABEL--------------------------------
		
		JLabel lblID = new JLabel("Código");
		lblID.setBounds(10, 189, 178, 14);
		FrameCadastro.getContentPane().add(lblID);
		
		final JSpinner ExameID = new JSpinner();
		ExameID.setBounds(10, 214, 178, 20);
		FrameCadastro.getContentPane().add(ExameID);
		
		
		JLabel lblstatus = new JLabel("Status");
		lblstatus.setBounds(10, 246, 77, 14);
		FrameCadastro.getContentPane().add(lblstatus);
		
		final JSpinner QTD = new JSpinner();
		QTD.setBounds(10, 271, 178, 20);
		FrameCadastro.getContentPane().add(QTD);
		
		//EXAME TA TIPO DINHEIRO NO ANTIGO
		JLabel lbldata_Exame = new JLabel("Data Exame");
		lbldata_Exame.setBounds(10, 132, 178, 14);
		FrameCadastro.getContentPane().add(lbldata_Exame);
		
		SpinnerNumberModel modelo = new SpinnerNumberModel(0.0,0.0,1000000.0,0.5);
		final JSpinner Examedata_Exame = new JSpinner(modelo);
		Examedata_Exame.setBounds(10, 157, 178, 20);
		FrameCadastro.getContentPane().add(Examedata_Exame);
		
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setBounds(249, 11, 550, 400);
		FrameCadastro.getContentPane().add(fileChooser);
		
		JButton btnCadastarExame = new JButton("Cadastar Exame");
		btnCadastarExame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//---------------------------------MUDAR TIPO DE LABEL-----------------------------------------------
				Object p = QTD.getValue();
				Number m = (Number) p;
				String status = "A"; //MUDAR AQ
				
				Object o = ExameID.getValue();
				Number n = (Number) o;
				String idExame ="B";//AQ
				
				Object u = Examedata_Exame.getValue();
				Number i = (Number) u;
				String data_Exame = "C";//AQ
				
				Exame novoExame = new Exame(idExame, status, data_Exame);
				
				String filepath = fileChooser.getSelectedFile().getAbsolutePath();
				System.out.println(filepath);
				
				try {
					cad.inserir(novoExame);
					ExameController.salvarImagemExame(filepath, ExameID.getValue()+"");
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
				FrameCadastro.setVisible(false);
				FrameCadastro.dispose();
			}
		});
		btnCadastarExame.setBounds(177, 521, 178, 23);
		btnCadastarExame.setBackground(Color.white);
		btnCadastarExame.setForeground(Color.black);
		FrameCadastro.getContentPane().add(btnCadastarExame);
		
	}
}
