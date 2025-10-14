package br.edu.cs.poo.ac.ordem.mediators;

import java.time.LocalDate;
import br.edu.cs.poo.ac.ordem.daos.ClienteDAO;
import br.edu.cs.poo.ac.ordem.entidades.Cliente;
import br.edu.cs.poo.ac.ordem.entidades.Contato;
import br.edu.cs.poo.ac.utils.ListaString;
import br.edu.cs.poo.ac.utils.StringUtils;
import br.edu.cs.poo.ac.utils.ValidadorCPFCNPJ;

public class ClienteMediator {
	private static ClienteMediator instancia;
	private ClienteDAO dao;
	
	private ClienteMediator() { 
		dao = new ClienteDAO(); 
	}
	
	public static ClienteMediator getInstancia() {
	    if (instancia == null) { 
	    	instancia = new ClienteMediator(); 
	    }
	    return instancia;
	}

    public ResultadoMediator incluir(Cliente cliente) {
	    ResultadoMediator res = validar(cliente);
	    if (!res.isValidado()) { return res; }
	    ListaString mensagens = new ListaString();
	    boolean sucesso = dao.incluir(cliente);
	    if (!sucesso) { mensagens.adicionar("CPF/CNPJ já existente"); }
	    return new ResultadoMediator(true, sucesso, mensagens);
	}
	
	public ResultadoMediator alterar(Cliente cliente) {
	    ResultadoMediator res = validar(cliente);
	    if (!res.isValidado()) { return res; }
	    ListaString mensagens = new ListaString();
	    boolean sucesso = dao.alterar(cliente);
	    if (!sucesso) { mensagens.adicionar("CPF/CNPJ inexistente"); }
	    return new ResultadoMediator(true, sucesso, mensagens);
	}
	
	public ResultadoMediator excluir(String cpfCnpj) {
	    ListaString mensagens = new ListaString();
	    if (StringUtils.estaVazia(cpfCnpj)) {
	        mensagens.adicionar("CPF/CNPJ não informado");
	        return new ResultadoMediator(false, false, mensagens);
	    }
	    boolean sucesso = dao.excluir(cpfCnpj);
	    if (!sucesso) { mensagens.adicionar("CPF/CNPJ inexistente"); }
	    return new ResultadoMediator(true, sucesso, mensagens);
	}
	
	public Cliente buscar(String cpfCnpj) {
	    if (StringUtils.estaVazia(cpfCnpj)) { return null; }
	    return dao.buscar(cpfCnpj);
	}
	
    public ResultadoMediator validar(Cliente cliente) {
        if (cliente == null) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Cliente não informado");
            return new ResultadoMediator(false, false, mensagens);
        }

        String erroCpfCnpj = validarCpfCnpj(cliente.getCpfCnpj());
        String erroNome = validarNome(cliente.getNome());
        String erroData = validarDataCadastro(cliente.getDataCadastro());
        
        String erroContatoNulo = null;
        ListaString errosContatoEspecificos = new ListaString();
        if (cliente.getContato() == null) {
            erroContatoNulo = "Contato não informado";
        } else {
            validarContato(cliente.getContato(), errosContatoEspecificos);
        }

        ListaString mensagensFinais = new ListaString();
        if (erroCpfCnpj != null) mensagensFinais.adicionar(erroCpfCnpj);
        if (erroNome != null) mensagensFinais.adicionar(erroNome);

        if (erroContatoNulo != null) {
            mensagensFinais.adicionar(erroContatoNulo);
            if (erroData != null) mensagensFinais.adicionar(erroData);
        } 
        else {
            if (erroData != null) mensagensFinais.adicionar(erroData);
            for (int i = 0; i < errosContatoEspecificos.tamanho(); i++) {
                mensagensFinais.adicionar(errosContatoEspecificos.buscar(i));
            }
        }

        boolean validado = mensagensFinais.tamanho() == 0;
        return new ResultadoMediator(validado, false, mensagensFinais);
    }
    
    private String validarCpfCnpj(String cpfCnpj) {
        if (StringUtils.estaVazia(cpfCnpj)) {
            return "CPF/CNPJ não informado";
        }
        cpfCnpj = cpfCnpj.trim();
        if (!isNumerico(cpfCnpj)) { 
            return "Não é CPF nem CNJP";
        }
        if (cpfCnpj.length() != 11 && cpfCnpj.length() != 14) {
            return "Não é CPF nem CNJP";
        }
        if (cpfCnpj.length() == 11 && !ValidadorCPFCNPJ.isCPF(cpfCnpj)) {
            return "CPF ou CNPJ com digito verificador inválido";
        }
        if (cpfCnpj.length() == 14 && !ValidadorCPFCNPJ.isCNPJ(cpfCnpj)) {
            return "CPF ou CNPJ com digito verificador inválido";
        }
        return null; 
    }

    private String validarNome(String nome) {
        if (StringUtils.estaVazia(nome)) {
            return "Nome não informado";
        }
        if (StringUtils.tamanhoExcedido(nome.trim(), 50)) {
            return "Nome tem mais de 50 caracteres";
        }
        return null;
    }

    private String validarDataCadastro(LocalDate data) {
        if (data == null) {
            return "Data do cadastro não informada";
        }
        if (data.isAfter(LocalDate.now())) {
            return "Data do cadastro não pode ser posterior é data atual";
        }
        return null;
    }

    private void validarContato(Contato contato, ListaString mensagens) {
        boolean emailInformado = !StringUtils.estaVazia(contato.getEmail());
        boolean celularInformado = !StringUtils.estaVazia(contato.getCelular());

        if (!celularInformado && !emailInformado) {
            mensagens.adicionar("Celular e e-mail não foram informados");
            return;
        }
        if (emailInformado && !StringUtils.emailValido(contato.getEmail().trim())) {
            mensagens.adicionar("E-mail está em um formato inválido");
        }
        if (celularInformado && !StringUtils.telefoneValido(contato.getCelular().trim())) {
            mensagens.adicionar("Celular está em um formato inválido");
        }
        if (contato.isEhZap() && !celularInformado) {
            mensagens.adicionar("Celular não informado e indicador de zap ativo");
        }
    }

    private boolean isNumerico(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}