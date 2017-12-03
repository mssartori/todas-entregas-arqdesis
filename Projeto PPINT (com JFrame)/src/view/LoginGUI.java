package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import security.LerTxtDecifrado;

@SuppressWarnings("serial")
public class LoginGUI extends JFrame implements ActionListener 
{
	idiomas.Internacionalizacao i = new idiomas.Internacionalizacao();
	int Indice = i.getIndice();
	@SuppressWarnings("unused")
	private String Nome, Senha;
	private Container c;
	private JPanel Painel, botoes, Cabecalho;
	@SuppressWarnings("rawtypes")
	private JComboBox jcTipo;
	private JTextField tnome;
	private JPasswordField tsenha;
	@SuppressWarnings("unused")
	private JLabel lnome, ltitulo, lsenha;
	@SuppressWarnings("unused")
	private JButton bEntrar, bVoltar;
	private ResourceBundle bn = null; 
	@SuppressWarnings("unused")
	private JMenuItem Potugues, Espanhol, Ingles;
	private String empregado[] = {"Síndico", "Atendente", "Funcionário"}, item, tipo = "Sindico";;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LoginGUI(int indice) 
	{
		setIndice(indice);

		bn = new idiomas.Internacionalizacao().troca(indice);

		setTitle(bn.getString("login"));

		JMenu Idiomas = new JMenu(bn.getString("Idiomas")); 
		Idiomas.setMnemonic(bn.getString("Idiomas.mnemonico").charAt(0));

		JMenuItem Portugues = new JMenuItem(bn.getString("portugues")); 
		Portugues.setMnemonic(bn.getString("portugues.mnemonico").charAt(0));

		JMenuItem Espanhol = new JMenuItem(bn.getString("espanhol")); 
		Espanhol.setMnemonic(bn.getString("espanhol.mnemonico").charAt(0)); 

		JMenuItem Ingles = new JMenuItem(bn.getString("ingles")); 
		Ingles.setMnemonic(bn.getString("ingles.mnemonico").charAt(0)); 

		Idiomas.add(Portugues);
		Idiomas.add(Espanhol);
		Idiomas.add(Ingles);
		JMenuBar bar = new JMenuBar(); 
		setJMenuBar( bar );
		bar.add(Idiomas);

		c = getContentPane();
		c.setLayout(new BorderLayout());


		Painel = new JPanel(new FlowLayout());
		botoes = new JPanel(new GridLayout(1, 2));
		Cabecalho = new JPanel(new FlowLayout());

		c.add(Painel, BorderLayout.CENTER);
		c.add(botoes, BorderLayout.SOUTH);

		jcTipo = new JComboBox(empregado);

		jcTipo.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged( ItemEvent event )
					{
						if ( event.getStateChange() == ItemEvent.SELECTED )
						{
							item = "" + jcTipo.getSelectedItem();


							if(item.equalsIgnoreCase("Síndico"))
							{
								tipo = "Sindico";
							}
							else if(item.equalsIgnoreCase("Atendente"))
							{
								tipo = "Atendente";
							}
							else
							{
								tipo = "Funcionario";
							}
						}
					}
				}
				);

		lnome= new JLabel(bn.getString("usuario"));

		tnome = new JTextField(21);

		lsenha = new JLabel(bn.getString("senha"));

		tsenha = new JPasswordField(21);

		bEntrar = new JButton(bn.getString("Entrar"));
		bEntrar.addActionListener(this);

		Cabecalho.add(jcTipo);
		Painel.add(lnome);
		Painel.add(tnome);
		Painel.add(lsenha);
		Painel.add(tsenha);

		c.add(Cabecalho, BorderLayout.NORTH);

		botoes.add(bEntrar);

		Portugues.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evento) {
						dispose();
						new LoginGUI(0); 
					}
				}
				); 

		Espanhol.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evento) {
						dispose();
						new LoginGUI(1);
					}
				}
				);

		Ingles.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evento) {
						dispose();
						new LoginGUI(2);                    
					}
				}
				);

		setSize(270, 250);
		setLocationRelativeTo(Painel);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
	}

	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == bEntrar) 
		{  

			String nome = tnome.getText();
			@SuppressWarnings("deprecation")
			String senha = tsenha.getText();

			model.Usuario u = new model.Usuario(nome, senha, tipo);
			@SuppressWarnings("unused")
			security.LerTxtDecifrado l = new security.LerTxtDecifrado();

			if(LerTxtDecifrado.LerLoginGUI(u))
			{
				dispose();
				new BotaoEntrarGUI(getIndice(),tipo,nome);
			}
			else
			{
				new ErroLoginGUI(getIndice());
			}

			tnome.setText("");
			tsenha.setText("");
		}

	}

	public void setIndice(int indice) 
	{
		Indice = indice;
	}

	public int getIndice() 
	{
		return Indice;
	}

	public static void main(String [] args)
	{
		new LoginGUI(3);
	}
}