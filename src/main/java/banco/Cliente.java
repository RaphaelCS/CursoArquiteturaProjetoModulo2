package banco;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente {

    private Double id;

    private TipoPessoa tipoPessoa;

    private String nome;
    private LocalDate dataCadastro;

    private Status status;

    private ArrayList<Conta> listaContas = new ArrayList<>();

    public ArrayList<Conta> getListaContas() {
        return listaContas;
    }

    public void setListaContas(ArrayList<Conta> listaContas) {
        this.listaContas = listaContas;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente(Double id, TipoPessoa tipoPessoa, String nome) {
        this.id = id;
        this.tipoPessoa = tipoPessoa;
        this.nome = nome;
        dataCadastro = LocalDate.now();
        status = Status.ATIVO;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", tipoPessoa=" + tipoPessoa +
                ", nome='" + nome + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", status=" + status +
                '}';
    }
}
