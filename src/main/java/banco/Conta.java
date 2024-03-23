package banco;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conta {

    private Integer numero;
    private BigDecimal saldo;

    private LocalDate dataCriacao;

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Conta(Integer numero, BigDecimal saldo, Cliente cliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.cliente = cliente;
        dataCriacao = LocalDate.now();
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void saque(BigDecimal valor){
        BigDecimal taxa = getTaxa(cliente,valor);
        if (valor.add(taxa).compareTo(saldo)==1){
            System.out.println("Saldo insuficiente.");
            return ;
        }
        saldo = saldo.subtract(valor).subtract(taxa);
        System.out.println("Saque efetuado!");
    }

    public void deposito(BigDecimal valor){
        saldo = saldo.add(valor);
        System.out.println("Deposito efetuado!");
    }

    public void transferencia(BigDecimal valor, Conta contaDestino){
        BigDecimal taxa = getTaxa(cliente,valor);
        if (valor.add(taxa).compareTo(saldo)==1){
            System.out.println("Saldo insuficiente.");
            return;
        }
        saldo = saldo.subtract(valor).subtract(taxa);
        contaDestino.saldo = contaDestino.saldo.add(valor);
        System.out.println("Transferencia efetuada!");
    }

    public BigDecimal getTaxa(Cliente cliente, BigDecimal valor){
        BigDecimal taxa = new BigDecimal(0);
        if (cliente.getTipoPessoa().equals(TipoPessoa.JURIDICA)){
            taxa = valor.multiply(BigDecimal.valueOf(0.005));
        }
        return taxa;
    }

    public BigDecimal consultaSaldo(){
        System.out.println("Saldo: "+saldo);
        return saldo;
    }
}
