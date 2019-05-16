package views;

import java.awt.Color;
import java.awt.EventQueue;
import views.TelaCadastro;
import views.TelaConsulta;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.ExameController;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ExameController cad;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setTitle("Projeto GDI home");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static class AcaoBtnCadastrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new TelaCadastro(cad);
		}
	}
	
public TelaPrincipal() {
		
		this.cad = new ExameController();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.lightGray);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBuscarExame = new JButton("BUSCAR EXAME");
		btnBuscarExame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaConsulta(cad);
			}
		});
		btnBuscarExame.setBounds(377, 60, 236, 80);
		btnBuscarExame.setBackground(Color.white);
		btnBuscarExame.setForeground(Color.black);
		contentPane.add(btnBuscarExame);
		
		JButton btnCadastrarExame = new JButton("CADASTRAR EXAME");
		btnCadastrarExame.addActionListener(new AcaoBtnCadastrar());
		btnCadastrarExame.setBounds(133, 60, 236, 80);
		btnCadastrarExame.setBackground(Color.white);
		btnCadastrarExame.setForeground(Color.black);
		contentPane.add(btnCadastrarExame);
		
		final JLabel lblEquipe = new JLabel();
		lblEquipe.setBounds(625, 325, 300, 300);
		lblEquipe.setText("<html>Grupo 4: </html>");
		contentPane.add(lblEquipe);
		lblEquipe.setVisible(true);
	}
}
