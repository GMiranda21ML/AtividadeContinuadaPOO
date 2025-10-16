package br.edu.cs.poo.ac.ordem.telas;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import br.edu.cs.poo.ac.ordem.entidades.Cliente;
import br.edu.cs.poo.ac.ordem.entidades.Contato;
import br.edu.cs.poo.ac.ordem.mediators.ClienteMediator;
import br.edu.cs.poo.ac.ordem.mediators.ResultadoMediator;

public class TelaCliente {

	protected Shell shell;
	private Text txtCPFCNPJ;
	private Text txtNome;
	private Text txtEmail;
	private Text txtCelular;
	private Button chkEhZap;
	private Text txtDataCadastro;
	
	private Button btnNovo;
	private Button btnBuscar;
	private Button btnIncluirAlterar;
	private Button btnExcluir;
	private Button btnCancelar;

	private ClienteMediator mediator;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args) {
		try {
			TelaCliente window = new TelaCliente();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private String removerNaoDigitos(String input) {
		if (input == null) {
			return "";
		}
		return input.replaceAll("\\D", "");
	}
	
	protected void createContents() {
		mediator = ClienteMediator.getInstancia();
		
		shell = new Shell();
		shell.setSize(875, 485);
		shell.setText("Tela Cliente");
		
		Label lblCpfcnpj = new Label(shell, SWT.NONE);
		lblCpfcnpj.setBounds(10, 10, 75, 21);
		lblCpfcnpj.setText("CPF/CNPJ:");

		txtCPFCNPJ = new Text(shell, SWT.BORDER);
		txtCPFCNPJ.setBounds(100, 10, 225, 26);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 60, 75, 21);
		lblNewLabel.setText("Nome:");
		
		txtNome = new Text(shell, SWT.BORDER);
		txtNome.setBounds(100, 60, 225, 26);
		
		Label lblContato = new Label(shell, SWT.NONE);
		lblContato.setBounds(152, 108, 75, 21);
		lblContato.setText("Contato");
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setBounds(10, 150, 75, 21);
		lblEmail.setText("E-mail:");
		
		txtEmail = new Text(shell, SWT.BORDER);
		txtEmail.setBounds(100, 150, 225, 26);
		
		Label lblCelular = new Label(shell, SWT.NONE);
		lblCelular.setBounds(10, 200, 75, 21);
		lblCelular.setText("Celular:");
		
		txtCelular = new Text(shell, SWT.BORDER);
		txtCelular.setBounds(100, 200, 225, 26);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(10, 250, 75, 21);
		lblNewLabel_1.setText("É ZAP:");
		
		chkEhZap = new Button(shell, SWT.CHECK);
		chkEhZap.setBounds(100, 250, 255, 26);
		chkEhZap.setText("");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(10, 300, 75, 21);
		lblNewLabel_2.setText("Data:");
		
		txtDataCadastro = new Text(shell, SWT.BORDER);
		txtDataCadastro.setBounds(100, 300, 225, 26);
		
		
		btnNovo = new Button(shell, SWT.NONE);
		btnNovo.setBounds(400, 10, 90, 30);
		btnNovo.setText("Novo");
		
		btnBuscar = new Button(shell, SWT.NONE);
		btnBuscar.setBounds(525, 10, 90, 30);
		btnBuscar.setText("Buscar");
		
		btnIncluirAlterar = new Button(shell, SWT.NONE);
		btnIncluirAlterar.setBounds(400, 382, 90, 30);
		btnIncluirAlterar.setText("Incluir");
		
		btnExcluir = new Button(shell, SWT.NONE);
		btnExcluir.setBounds(525, 382, 90, 30);
		btnExcluir.setText("Excluir");
		
		btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setBounds(650, 382, 90, 30);
		btnCancelar.setText("Cancelar");
		
		
		adicionarListenersAosBotoes();
		adicionarMascarasAosCampos();

		controlarEstadoTela("INICIAL");
	}

	private void adicionarListenersAosBotoes() {

		btnNovo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String cpfCnpj = removerNaoDigitos(txtCPFCNPJ.getText());
				if (cpfCnpj.isEmpty()) {
					JOptionPane.showMessageDialog(null, "CPF/CNPJ deve ser preenchido!");
					return;
				}
				
				Cliente cliente = mediator.buscar(cpfCnpj);
				if (cliente != null) {
					JOptionPane.showMessageDialog(null, "Cliente já existente!");
				} else {
					controlarEstadoTela("EDICAO");
					btnIncluirAlterar.setText("Incluir");
				}
			}
		});

		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String cpfCnpj = removerNaoDigitos(txtCPFCNPJ.getText());
				if (cpfCnpj.isEmpty()) {
					JOptionPane.showMessageDialog(null, "CPF/CNPJ deve ser preenchido!");
					return;
				}

				Cliente cliente = mediator.buscar(cpfCnpj);
				if (cliente == null) {
					JOptionPane.showMessageDialog(null, "Cliente não existente!");
				} else {
					popularTela(cliente);
					controlarEstadoTela("EDICAO");
					btnIncluirAlterar.setText("Alterar");
					btnExcluir.setEnabled(true);
				}
			}
		});

		btnIncluirAlterar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Cliente cliente = montarClienteDaTela();
					ResultadoMediator resultado;
					String msgSucesso;
					
					if (btnIncluirAlterar.getText().equals("Incluir")) {
						resultado = mediator.incluir(cliente);
						msgSucesso = "Inclusão realizada com sucesso!";
					} else {
						resultado = mediator.alterar(cliente);
						msgSucesso = "Alteração realizada com sucesso!";
					}
					
					if (resultado.isValidado()) {
						JOptionPane.showMessageDialog(null, msgSucesso);
						resetarTela();
					} else {
						exibirErros(resultado);
					}

				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(null, "Data de cadastro em formato inválido. Use dd/mm/yyyy.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex.getMessage());
				}
			}
		});
		
		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String cpfCnpj = removerNaoDigitos(txtCPFCNPJ.getText());
				
				int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir este cliente?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
				if (confirm != JOptionPane.YES_OPTION) {
					return;
				}

				ResultadoMediator resultado = mediator.excluir(cpfCnpj);
				if(resultado.isValidado()){
					JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
					resetarTela();
				} else {
					JOptionPane.showMessageDialog(null, "Exclusão não pôde ser realizada.");
				}
			}
		});
		
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetarTela();
			}
		});
	}
	
	private void adicionarMascarasAosCampos() {
		txtDataCadastro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtDataCadastro.setText(removerNaoDigitos(txtDataCadastro.getText()));
			}
			@Override
			public void focusLost(FocusEvent e) {
				String data = removerNaoDigitos(txtDataCadastro.getText());
				if (data.length() == 8) {
					String dataFormatada = data.substring(0, 2) + "/" +
										   data.substring(2, 4) + "/" +
										   data.substring(4, 8);
					txtDataCadastro.setText(dataFormatada);
				}
			}
		});

		txtCPFCNPJ.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCPFCNPJ.setText(removerNaoDigitos(txtCPFCNPJ.getText()));
			}
			@Override
			public void focusLost(FocusEvent e) {
				String texto = removerNaoDigitos(txtCPFCNPJ.getText());
				if (texto.length() == 11) {
					String cpfFormatado = texto.substring(0, 3) + "." +
										  texto.substring(3, 6) + "." +
										  texto.substring(6, 9) + "-" +
										  texto.substring(9, 11);
					txtCPFCNPJ.setText(cpfFormatado);
				} else if (texto.length() == 14) {
					String cnpjFormatado = texto.substring(0, 2) + "." +
										   texto.substring(2, 5) + "." +
										   texto.substring(5, 8) + "/" +
										   texto.substring(8, 12) + "-" +
										   texto.substring(12, 14);
					txtCPFCNPJ.setText(cnpjFormatado);
				}
			}
		});
	}

	private Cliente montarClienteDaTela() throws DateTimeParseException {
		String cpfCnpj = removerNaoDigitos(txtCPFCNPJ.getText());
		String nome = txtNome.getText();
		LocalDate dataCadastro = LocalDate.parse(txtDataCadastro.getText(), formatter);

		String email = txtEmail.getText();
		String celular = txtCelular.getText();
		boolean ehZap = chkEhZap.getSelection();
		Contato contato = new Contato(email, celular, ehZap);

		return new Cliente(cpfCnpj, nome, contato, dataCadastro);
	}

	private void popularTela(Cliente cliente) {
		txtNome.setText(cliente.getNome());
		txtDataCadastro.setText(cliente.getDataCadastro().format(formatter));
		
		if (cliente.getContato() != null) {
			txtEmail.setText(cliente.getContato().getEmail() != null ? cliente.getContato().getEmail() : "");
			txtCelular.setText(cliente.getContato().getCelular() != null ? cliente.getContato().getCelular() : "");
			chkEhZap.setSelection(cliente.getContato().isEhZap());
		}
	}

	private void limparCampos() {
		txtCPFCNPJ.setText("");
		txtNome.setText("");
		txtEmail.setText("");
		txtCelular.setText("");
		txtDataCadastro.setText("");
		chkEhZap.setSelection(false);
	}

	private void resetarTela() {
		limparCampos();
		controlarEstadoTela("INICIAL");
	}
	
	private void exibirErros(ResultadoMediator resultado) {
		StringBuilder sb = new StringBuilder("Foram encontrados os seguintes erros:\n\n");
		for (int i = 0; i < resultado.getMensagensErro().tamanho(); i++) {
			sb.append("- ").append(resultado.getMensagensErro().buscar(i)).append("\n");
		}
		JOptionPane.showMessageDialog(null, sb.toString(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
	}

	private void controlarEstadoTela(String estado) {
		boolean habilitarBusca = estado.equals("INICIAL");
		boolean habilitarEdicao = estado.equals("EDICAO");

		txtCPFCNPJ.setEnabled(habilitarBusca);
		txtNome.setEnabled(habilitarEdicao);
		txtEmail.setEnabled(habilitarEdicao);
		txtCelular.setEnabled(habilitarEdicao);
		txtDataCadastro.setEnabled(habilitarEdicao);
		chkEhZap.setEnabled(habilitarEdicao);

		btnNovo.setEnabled(habilitarBusca);
		btnBuscar.setEnabled(habilitarBusca);
		btnIncluirAlterar.setEnabled(habilitarEdicao);
		btnCancelar.setEnabled(habilitarEdicao);
		btnExcluir.setEnabled(false); 
	}
}