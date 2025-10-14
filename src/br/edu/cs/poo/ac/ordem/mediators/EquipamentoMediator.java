package br.edu.cs.poo.ac.ordem.mediators;

import br.edu.cs.poo.ac.ordem.daos.DesktopDAO;
import br.edu.cs.poo.ac.ordem.daos.NotebookDAO;
import br.edu.cs.poo.ac.ordem.entidades.Desktop;
import br.edu.cs.poo.ac.ordem.entidades.Notebook;
import br.edu.cs.poo.ac.utils.ListaString;
import br.edu.cs.poo.ac.utils.StringUtils;

public class EquipamentoMediator {

    private static EquipamentoMediator instancia;
    private DesktopDAO desktopDAO;
    private NotebookDAO notebookDAO;

    private EquipamentoMediator() {
        desktopDAO = new DesktopDAO();
        notebookDAO = new NotebookDAO();
    }

    public static EquipamentoMediator getInstancia() {
        if (instancia == null) {
            instancia = new EquipamentoMediator();
        }
        return instancia;
    }

    public ResultadoMediator incluirDesktop(Desktop desk) {
        ResultadoMediator res = validarDesktop(desk);
        if (!res.isValidado()) {
            return res;
        }

        boolean sucesso = desktopDAO.incluir(desk);
        if (!sucesso) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Serial do desktop já existente");
            return new ResultadoMediator(true, false, mensagens);
        }

        return new ResultadoMediator(true, true, new ListaString());
    }

    public ResultadoMediator alterarDesktop(Desktop desk) {
        ResultadoMediator res = validarDesktop(desk);
        if (!res.isValidado()) {
            return res;
        }

        boolean sucesso = desktopDAO.alterar(desk);
        if (!sucesso) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Serial do desktop não existente");
            return new ResultadoMediator(true, false, mensagens);
        }

        return new ResultadoMediator(true, true, new ListaString());
    }

    public ResultadoMediator excluirDesktop(String idTipoSerial) {
        if (StringUtils.estaVazia(idTipoSerial)) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Id do tipo + serial do desktop não informado");
            return new ResultadoMediator(false, false, mensagens);
        }

        boolean sucesso = desktopDAO.excluir(idTipoSerial);
        if (!sucesso) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Serial do desktop não existente");
            return new ResultadoMediator(true, false, mensagens);
        }

        return new ResultadoMediator(true, true, new ListaString());
    }

    public Desktop buscarDesktop(String idTipoSerial) {
        if (StringUtils.estaVazia(idTipoSerial)) {
            return null;
        }
        return desktopDAO.buscar(idTipoSerial);
    }

    public ResultadoMediator incluirNotebook(Notebook note) {
        ResultadoMediator res = validarNotebook(note);
        if (!res.isValidado()) {
            return res;
        }

        boolean sucesso = notebookDAO.incluir(note);
        if (!sucesso) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Serial do notebook já existente");
            return new ResultadoMediator(true, false, mensagens);
        }

        return new ResultadoMediator(true, true, new ListaString());
    }

    public ResultadoMediator alterarNotebook(Notebook note) {
        ResultadoMediator res = validarNotebook(note);
        if (!res.isValidado()) {
            return res;
        }

        boolean sucesso = notebookDAO.alterar(note);
        if (!sucesso) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Serial do notebook não existente");
            return new ResultadoMediator(true, false, mensagens);
        }

        return new ResultadoMediator(true, true, new ListaString());
    }

    public ResultadoMediator excluirNotebook(String idTipoSerial) {
        if (StringUtils.estaVazia(idTipoSerial)) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Id do tipo + serial do notebook não informado");
            return new ResultadoMediator(false, false, mensagens);
        }

        boolean sucesso = notebookDAO.excluir(idTipoSerial);
        if (!sucesso) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Serial do notebook não existente");
            return new ResultadoMediator(true, false, mensagens);
        }

        return new ResultadoMediator(true, true, new ListaString());
    }

    public Notebook buscarNotebook(String idTipoSerial) {
        if (StringUtils.estaVazia(idTipoSerial)) {
            return null;
        }
        return notebookDAO.buscar(idTipoSerial);
    }

    public ResultadoMediator validarDesktop(Desktop desk) {
        if (desk == null) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Desktop não informado");
            return new ResultadoMediator(false, false, mensagens);
        }
        DadosEquipamento dados = new DadosEquipamento(desk.getSerial(), desk.getDescricao(), desk.isEhNovo(), desk.getValorEstimado());
        return validar(dados);
    }

    public ResultadoMediator validarNotebook(Notebook note) {
        if (note == null) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Notebook não informado");
            return new ResultadoMediator(false, false, mensagens);
        }
        DadosEquipamento dados = new DadosEquipamento(note.getSerial(), note.getDescricao(), note.isEhNovo(), note.getValorEstimado());
        return validar(dados);
    }

    public ResultadoMediator validar(DadosEquipamento equip) {
        if (equip == null) {
            ListaString mensagens = new ListaString();
            mensagens.adicionar("Dados básicos do equipamento não informados");
            return new ResultadoMediator(false, false, mensagens);
        }

        ListaString mensagens = new ListaString();

        if (StringUtils.estaVazia(equip.getDescricao())) {
            mensagens.adicionar("Descrição não informada");
        } else if (equip.getDescricao().trim().length() < 10) {
            mensagens.adicionar("Descrição tem menos de 10 caracteres");
        } else if (equip.getDescricao().trim().length() > 150) {
            mensagens.adicionar("Descrição tem mais de 150 caracteres");
        }

        if (StringUtils.estaVazia(equip.getSerial())) {
            mensagens.adicionar("Serial não informado");
        }

        if (equip.getValorEstimado() <= 0) {
            mensagens.adicionar("Valor estimado menor ou igual a zero");
        }

        boolean validado = mensagens.tamanho() == 0;
        return new ResultadoMediator(validado, false, mensagens);
    }
}