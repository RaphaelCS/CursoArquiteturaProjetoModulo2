package banco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente {

    private final LocalDate dataCadastro;
    private String id;
    private TipoPessoaEnum tipoPessoaEnum;
    private String nome;
    private StatusEnum statusEnum;

    private ContaCorrente contaCorrente;

    public Cliente(String id, TipoPessoaEnum tipoPessoaEnum, String nome) {
        this.id = id;
        this.tipoPessoaEnum = tipoPessoaEnum;
        this.nome = nome;
        dataCadastro = LocalDate.now();
        statusEnum = StatusEnum.ATIVO;
    }



    public Cliente(String id, TipoPessoaEnum tipoPessoaEnum, String nome, Integer numeroConta, BigDecimal saldo) {
        this.id = id;
        this.tipoPessoaEnum = tipoPessoaEnum;
        this.nome = nome;
        dataCadastro = LocalDate.now();
        statusEnum = StatusEnum.ATIVO;
        contaCorrente = new ContaCorrente(numeroConta,saldo);
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        if(getStatus().equals(StatusEnum.INATIVO)){
            System.out.println("Cliente inativado");
            return;
        }
        this.contaCorrente = contaCorrente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoPessoaEnum getTipoPessoa() {
        return tipoPessoaEnum;
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoaEnum) {
        this.tipoPessoaEnum = tipoPessoaEnum;
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

    public StatusEnum getStatus() {
        return statusEnum;
    }

    public void setStatus(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", tipoPessoa=" + tipoPessoaEnum +
                ", nome='" + nome + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", status=" + statusEnum +
                '}';
    }
}
