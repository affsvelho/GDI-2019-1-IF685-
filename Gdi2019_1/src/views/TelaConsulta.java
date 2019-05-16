package views;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.ExameController;
import models.Exame;
import models.ImagemExame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;

public class TelaConsulta {

	private JFrame FrameConsulta;
	private JTextField codExame;
	private static ExameController exameConsulta;

	public TelaConsulta(ExameController exameConsulta) {
		this.exameConsulta = exameConsulta;
		initialize();
	}

	private void initialize() {
		FrameConsulta = new JFrame();
		FrameConsulta.setTitle("Projeto GDI consulta da tabela Exame");
		FrameConsulta.setBounds(100, 100, 600, 626);
		FrameConsulta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		FrameConsulta.getContentPane().setLayout(null);
		FrameConsulta.setVisible(true);
		
		FrameConsulta.getContentPane().setBackground(Color.lightGray);
		
		final JLabel lblDigite = new JLabel("Digite a código do Exame: ");
		lblDigite.setBounds(39, 65, 150, 16);
		FrameConsulta.getContentPane().add(lblDigite);
		lblDigite.setVisible(true);
		
		codExame = new JTextField();
		codExame.setBounds(39, 82, 200, 43);
		codExame.setText("");
		FrameConsulta.getContentPane().add(codExame);
		codExame.setColumns(15);
		
		JLabel lblbuscarExame = new JLabel("Buscar Exame");
		lblbuscarExame.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblbuscarExame.setBounds(189, 11, 157, 43);
		FrameConsulta.getContentPane().add(lblbuscarExame);
		
		final JLabel lblIdExame = new JLabel("codExame");
		lblIdExame.setBounds(39, 300, 61, 16);
		FrameConsulta.getContentPane().add(lblIdExame);
		lblIdExame.setVisible(false);
		
		final JLabel lblIdExamevalue = new JLabel("codExame_value");
		lblIdExamevalue.setBounds(112, 300, 127, 16);
		FrameConsulta.getContentPane().add(lblIdExamevalue);
		lblIdExamevalue.setVisible(false);
				
		final JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(39, 350, 61, 16);
		FrameConsulta.getContentPane().add(lblStatus);
		lblStatus.setVisible(false);
		
		final JLabel lblStatusvalue = new JLabel("Status_value");
		lblStatusvalue.setBounds(112, 350, 127, 16);
		FrameConsulta.getContentPane().add(lblStatusvalue);
		lblStatusvalue.setVisible(false);
		
		final JLabel IblData = new JLabel("Data");
		IblData.setBounds(39, 400, 61, 16);
		FrameConsulta.getContentPane().add(IblData);
		IblData.setVisible(false);
		
		final JLabel IblDatavalue = new JLabel("dataExame_value");
		IblDatavalue.setBounds(112, 400, 127, 16);
		FrameConsulta.getContentPane().add(IblDatavalue);
		IblDatavalue.setVisible(false);
		
		final JLabel lblSemImagem = new JLabel("Exame sem imagem");
		lblSemImagem.setBounds(350, 350, 150, 16);
		FrameConsulta.getContentPane().add(lblSemImagem);
		lblSemImagem.setVisible(false);
		
		final JLabel lblSemId = new JLabel("sem Exame para esse códgio");
		lblSemId.setBounds(180, 350, 250, 16);
		FrameConsulta.getContentPane().add(lblSemId);
		lblSemId.setVisible(false);
		
		JButton buscar = new JButton("Buscar");
	
		
		buscar.setBounds(304, 82, 125, 43);
		buscar.setBackground(Color.white);
		buscar.setForeground(Color.black);
		FrameConsulta.getContentPane().add(buscar);
		
		final JPanel panel = new JPanel();
		panel.setBounds(251, 229, 302, 221);
		panel.setBackground(Color.lightGray);
		FrameConsulta.getContentPane().add(panel);
		
		
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				lblIdExame.setVisible(false);
				lblIdExamevalue.setVisible(false);
				lblStatus.setVisible(false);
				lblStatusvalue.setVisible(false);
				IblData.setVisible(false);
				IblDatavalue.setVisible(false);
				lblSemImagem.setVisible(false);
				lblSemId.setVisible(false);

				panel.setVisible(false);
				
				panel.removeAll();
				 
				try {
					Exame Exame = exameConsulta.getExame(codExame.getText());	
					lblIdExame.setVisible(true);
					lblIdExamevalue.setText(Exame.getCodigo()+"");
					lblIdExamevalue.setVisible(true);
				
					lblStatus.setVisible(true);
					lblStatusvalue.setText(Exame.getStatus()+"");
					lblStatusvalue.setVisible(true);
					
					IblData.setVisible(true);
					IblDatavalue.setText("R$ "+Exame.getDateExame()+"");
					IblDatavalue.setVisible(true);
					ImagemExame img = exameConsulta.getImageById(codExame.getText());
					if(img != null) {

						Blob img_blob = img.getFile();
						byte bytes[] = img_blob.getBytes(1, (int) img_blob.length());
						InputStream in = new ByteArrayInputStream(bytes);
						BufferedImage bImageFromConvert = ImageIO.read(in);
						
						panel.setVisible(true);
						JLabel label = new JLabel();
				        label.setIcon(new ImageIcon(bImageFromConvert));
				        panel.add(label);

					}else {
						lblSemImagem.setVisible(true);

					}
				        
			        FrameConsulta.setVisible(true);
					
				} catch (ClassNotFoundException | SQLException | IOException e) {
					lblSemId.setVisible(true);
					e.printStackTrace();
				}
				
			}
		});
	}
}
