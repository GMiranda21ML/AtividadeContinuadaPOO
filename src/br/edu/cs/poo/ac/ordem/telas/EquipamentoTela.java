package br.edu.cs.poo.ac.ordem.telas;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import javax.swing.JOptionPane;
import br.edu.cs.poo.ac.ordem.entidades.Desktop;
import br.edu.cs.poo.ac.ordem.entidades.Notebook;
import br.edu.cs.poo.ac.ordem.mediators.EquipamentoMediator;
import br.edu.cs.poo.ac.ordem.mediators.ResultadoMediator;

public class EquipamentoTela {

	protected Shell shell;
	
	private Combo comboTipo;
	private Text txtSerial;
	private Text txtDescricao;
	private Text txtValorEstimado;
	private Button radioEhNovoNao;
	private Button radioEhNovoSim;
	
	private Group groupNotebook;
	private Button radioDadosSensiveisNao;
	private Button radioDadosSensiveisSim;
	
	private Group groupDesktop;
	private Button radioServidorNao;
	private Button radioServidorSim;
	
	private Button btnNovo;
	private Button btnBuscar;
	private Button btnIncluirAlterar;
	private Button btnExcluir;
	private Button btnCancelar;
	
	private EquipamentoMediator mediator;

	public static void main(String[] args) {
		try {
			EquipamentoTela window = new EquipamentoTela();
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

	protected void createContents() {
		mediator = EquipamentoMediator.getInstancia();
		shell = new Shell();
		shell.setSize(875, 485);
		shell.setText("Tela Equipamento");
		
		Label lblTipo = new Label(shell, SWT.NONE);
		lblTipo.setBounds(10, 10, 160, 25);
		lblTipo.setText("Notebook/Desktop");
		
		comboTipo = new Combo(shell, SWT.READ_ONLY);
		comboTipo.setItems(new String[] {"Notebook", "Desktop"});
		comboTipo.setBounds(10, 40, 150, 28);
		
		Label lblSerial = new Label(shell, SWT.NONE);
		lblSerial.setBounds(10, 85, 75, 21);
		lblSerial.setText("Serial:");
		
		txtSerial = new Text(shell, SWT.BORDER);
		txtSerial.setBounds(10, 115, 225, 26);
		
		btnNovo = new Button(shell, SWT.NONE);
		btnNovo.setBounds(350, 40, 90, 30);
		btnNovo.setText("Novo");
		
		btnBuscar = new Button(shell, SWT.NONE);
		btnBuscar.setBounds(475, 40, 90, 30);
		btnBuscar.setText("Buscar");

		Label lblDescricao = new Label(shell, SWT.NONE);
		lblDescricao.setBounds(10, 160, 80, 21);
		lblDescricao.setText("Descrição:");
		
		txtDescricao = new Text(shell, SWT.BORDER);
		txtDescricao.setBounds(10, 190, 225, 26);
		
		Label lblEhNovo = new Label(shell, SWT.NONE);
		lblEhNovo.setBounds(60, 235, 75, 21);
		lblEhNovo.setText("É novo");
		
		radioEhNovoNao = new Button(shell, SWT.RADIO);
		radioEhNovoNao.setBounds(10, 270, 90, 17);
		radioEhNovoNao.setText("Não");
		
		radioEhNovoSim = new Button(shell, SWT.RADIO);
		radioEhNovoSim.setBounds(100, 270, 90, 17);
		radioEhNovoSim.setText("Sim");
		
		Label lblValorEstimado = new Label(shell, SWT.NONE);
		lblValorEstimado.setBounds(10, 315, 125, 21);
		lblValorEstimado.setText("Valor Estimado:");
		
		txtValorEstimado = new Text(shell, SWT.BORDER);
		txtValorEstimado.setBounds(10, 345, 225, 26);
		
		groupNotebook = new Group(shell, SWT.NONE);
		groupNotebook.setText("Notebook");
		groupNotebook.setBounds(350, 85, 280, 100);
		
		Label lblCarregaDadosSensiveis = new Label(groupNotebook, SWT.NONE);
		lblCarregaDadosSensiveis.setBounds(10, 25, 210, 27);
		lblCarregaDadosSensiveis.setText("Carrega dados sensíveis");
		
		radioDadosSensiveisNao = new Button(groupNotebook, SWT.RADIO);
		radioDadosSensiveisNao.setBounds(10, 60, 90, 17);
		radioDadosSensiveisNao.setText("Não");
		
		radioDadosSensiveisSim = new Button(groupNotebook, SWT.RADIO);
		radioDadosSensiveisSim.setBounds(120, 60, 90, 17);
		radioDadosSensiveisSim.setText("Sim");
		
		groupDesktop = new Group(shell, SWT.NONE);
		groupDesktop.setText("Desktop");
		groupDesktop.setBounds(350, 195, 280, 100);
		
		Label lblServidor = new Label(groupDesktop, SWT.NONE);
		lblServidor.setBounds(10, 25, 80, 23);
		lblServidor.setText("É Servidor");
		
		radioServidorNao = new Button(groupDesktop, SWT.RADIO);
		radioServidorNao.setBounds(10, 60, 90, 17);
		radioServidorNao.setText("Não");
		
		radioServidorSim = new Button(groupDesktop, SWT.RADIO);
		radioServidorSim.setBounds(120, 60, 90, 17);
		radioServidorSim.setText("Sim");
		
		btnIncluirAlterar = new Button(shell, SWT.NONE);
		btnIncluirAlterar.setBounds(350, 382, 90, 30);
		btnIncluirAlterar.setText("Incluir");
		
		btnExcluir = new Button(shell, SWT.NONE);
		btnExcluir.setBounds(475, 382, 90, 30);
		btnExcluir.setText("Excluir");
		
		btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setBounds(600, 382, 90, 30);
		btnCancelar.setText("Cancelar");
		
		adicionarListeners();
		resetarTela();
	}
	
	private void adicionarListeners() {
		comboTipo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarVisibilidadeGrupos();
			}
		});

		btnNovo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (txtSerial.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "O campo Serial é obrigatório.");
					return;
				}
				String idUnico = getIdUnicoFromTela();
				Object equipamento = buscarEquipamento(idUnico);

				if (equipamento != null) {
					JOptionPane.showMessageDialog(null, "Equipamento já existente!");
				} else {
					controlarEstadoTela("EDICAO");
					btnIncluirAlterar.setText("Incluir");
				}
			}
		});

		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (txtSerial.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "O campo Serial é obrigatório.");
					return;
				}
				String idUnico = getIdUnicoFromTela();
				Object equipamento = buscarEquipamento(idUnico);

				if (equipamento == null) {
					JOptionPane.showMessageDialog(null, "Equipamento não existente!");
				} else {
					popularTela(equipamento);
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
					Object equipamento = montarEquipamentoDaTela();
					ResultadoMediator resultado;
					String msgSucesso;

					boolean ehInclusao = btnIncluirAlterar.getText().equals("Incluir");
					
					if (ehInclusao) {
						msgSucesso = "Inclusão realizada com sucesso!";
					} else {
						msgSucesso = "Alteração realizada com sucesso!";
					}
					
					if (equipamento instanceof Notebook) {
						if (ehInclusao) {
							resultado = mediator.incluirNotebook((Notebook)equipamento);
						} else {
							resultado = mediator.alterarNotebook((Notebook)equipamento);
						}
					} else {
						if (ehInclusao) {
							resultado = mediator.incluirDesktop((Desktop)equipamento);
						} else {
							resultado = mediator.alterarDesktop((Desktop)equipamento);
						}
					}
					
					if (resultado.isValidado()) {
						JOptionPane.showMessageDialog(null, msgSucesso);
						resetarTela();
					} else {
						exibirErros(resultado);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Valor estimado deve ser um número válido.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex.getMessage());
				}
			}
		});

		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String idUnico = getIdUnicoFromTela();
				
				int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir este equipamento?", "Confirmação", JOptionPane.YES_NO_OPTION);
				if (confirm != JOptionPane.YES_OPTION) {
					return;
				}
				
				ResultadoMediator resultado;
				if (isNotebookSelecionado()) {
					resultado = mediator.excluirNotebook(idUnico);
				} else {
					resultado = mediator.excluirDesktop(idUnico);
				}

				if (resultado.isValidado()) {
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
	
	private Object montarEquipamentoDaTela() {
		String serial = txtSerial.getText().trim();
		String descricao = txtDescricao.getText().trim();
		boolean ehNovo = radioEhNovoSim.getSelection();
		double valorEstimado = Double.parseDouble(txtValorEstimado.getText().trim().replace(",", "."));
		
		if (isNotebookSelecionado()) {
			boolean carregaDadosSensiveis = radioDadosSensiveisSim.getSelection();
			return new Notebook(serial, descricao, ehNovo, valorEstimado, carregaDadosSensiveis);
		} else {
			boolean ehServidor = radioServidorSim.getSelection();
			return new Desktop(serial, descricao, ehNovo, valorEstimado, ehServidor);
		}
	}
	
	private void popularTela(Object equipamento) {
		if (equipamento instanceof Notebook) {
			Notebook note = (Notebook) equipamento;
			txtDescricao.setText(note.getDescricao());
			txtValorEstimado.setText(String.format("%.2f", note.getValorEstimado()).replace(".", ","));
			radioEhNovoSim.setSelection(note.isEhNovo());
			radioEhNovoNao.setSelection(!note.isEhNovo());
			radioDadosSensiveisSim.setSelection(note.isCarregaDadosSensiveis());
			radioDadosSensiveisNao.setSelection(!note.isCarregaDadosSensiveis());
		} else if (equipamento instanceof Desktop) {
			Desktop desk = (Desktop) equipamento;
			txtDescricao.setText(desk.getDescricao());
			txtValorEstimado.setText(String.format("%.2f", desk.getValorEstimado()).replace(".", ","));
			radioEhNovoSim.setSelection(desk.isEhNovo());
			radioEhNovoNao.setSelection(!desk.isEhNovo());
			radioServidorSim.setSelection(desk.isEhServidor());
			radioServidorNao.setSelection(!desk.isEhServidor());
		}
	}

	private void limparCampos() {
		comboTipo.select(0);
		txtSerial.setText("");
		txtDescricao.setText("");
		txtValorEstimado.setText("");
		radioEhNovoNao.setSelection(true);
		radioEhNovoSim.setSelection(false);
		radioDadosSensiveisNao.setSelection(true);
		radioDadosSensiveisSim.setSelection(false);
		radioServidorNao.setSelection(true);
		radioServidorSim.setSelection(false);
	}

	private void resetarTela() {
		limparCampos();
		controlarEstadoTela("INICIAL");
		atualizarVisibilidadeGrupos();
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

		comboTipo.setEnabled(habilitarBusca);
		txtSerial.setEnabled(habilitarBusca);
		
		txtDescricao.setEnabled(habilitarEdicao);
		txtValorEstimado.setEnabled(habilitarEdicao);
		radioEhNovoNao.setEnabled(habilitarEdicao);
		radioEhNovoSim.setEnabled(habilitarEdicao);
		groupNotebook.setEnabled(habilitarEdicao);
		groupDesktop.setEnabled(habilitarEdicao);
		
		btnNovo.setEnabled(habilitarBusca);
		btnBuscar.setEnabled(habilitarBusca);
		btnIncluirAlterar.setEnabled(habilitarEdicao);
		btnCancelar.setEnabled(habilitarEdicao);
		btnExcluir.setEnabled(false);
	}

	private void atualizarVisibilidadeGrupos() {
		boolean isNotebook = isNotebookSelecionado();
		groupNotebook.setVisible(isNotebook);
		groupDesktop.setVisible(!isNotebook);
	}
	
	private boolean isNotebookSelecionado() {
		return comboTipo.getSelectionIndex() == 0;
	}
	
	private String getIdTipoFromCombo() {
		if (isNotebookSelecionado()) {
			return "NO";
		} else {
			return "DE";
		}
	}
	
	private String getIdUnicoFromTela() {
		return getIdTipoFromCombo() + txtSerial.getText().trim();
	}

	private Object buscarEquipamento(String idUnico) {
		if (isNotebookSelecionado()) {
			return mediator.buscarNotebook(idUnico);
		} else {
			return mediator.buscarDesktop(idUnico);
		}
	}
}