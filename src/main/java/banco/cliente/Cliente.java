package banco.cliente;

import banco.conta.ContaCorrente;
import banco.enums.StatusEnum;
import banco.enums.TipoPessoaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Cliente {

    private final LocalDate dataCadastro;
    private TipoPessoaEnum tipoPessoaEnum;
    private String nome;
    private StatusEnum statusEnum;

    private ContaCorrente contaCorrente;

    public Cliente(TipoPessoaEnum tipoPessoaEnum, String nome) {
        this.tipoPessoaEnum = tipoPessoaEnum;
        this.nome = nome;
        dataCadastro = LocalDate.now();
        statusEnum = StatusEnum.ATIVO;
    }

    public Cliente(TipoPessoaEnum tipoPessoaEnum, String nome, Integer numeroConta, BigDecimal saldo) {
        this.tipoPessoaEnum = tipoPessoaEnum;
        this.nome = nome;
        dataCadastro = LocalDate.now();
        statusEnum = StatusEnum.ATIVO;
        contaCorrente = new ContaCorrente(numeroConta,saldo,this);
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
                ", tipoPessoa=" + tipoPessoaEnum +
                ", nome='" + nome + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", status=" + statusEnum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dataCadastro, cliente.dataCadastro) && tipoPessoaEnum == cliente.tipoPessoaEnum && Objects.equals(nome, cliente.nome) && statusEnum == cliente.statusEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataCadastro, tipoPessoaEnum, nome, statusEnum);
    }
}
