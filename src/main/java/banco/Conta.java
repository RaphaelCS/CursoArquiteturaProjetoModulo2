package banco;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Conta {

    private final LocalDate dataCriacao;
    private Integer numero;
    private BigDecimal saldo;
    private Cliente cliente;

    private  TipoContaEnum tipoConta;

    public Conta(Integer numero, BigDecimal saldo, Cliente cliente) {
        this.numero = numero;
        this.saldo = saldo;
        dataCriacao = LocalDate.now();
        this.cliente = cliente;
    }

    public TipoContaEnum getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoContaEnum tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public void saque(BigDecimal valor) {
        if(cliente.getStatus().equals(StatusEnum.INATIVO)){
            System.out.println("Cliente inativado");
            return;
        }
        BigDecimal taxa = getTaxa(cliente, valor);
        if (valor.add(taxa).compareTo(saldo) > 0) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        saldo = saldo.subtract(valor).subtract(taxa);
        System.out.println("Saque efetuado!");
    }

    public void deposito(BigDecimal valor) {
        if(cliente.getStatus().equals(StatusEnum.INATIVO)){
            System.out.println("Cliente inativado");
            return;
        }
        saldo = saldo.add(valor);
        System.out.println("Deposito efetuado!");
    }

    public void transferencia(BigDecimal valor, Conta contaDestino) {
        if(cliente.getStatus().equals(StatusEnum.INATIVO)){
            System.out.println("Cliente inativado");
            return;
        }
        BigDecimal taxa = getTaxa(cliente, valor);
        if (valor.add(taxa).compareTo(saldo) > 0) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        saldo = saldo.subtract(valor).subtract(taxa);
        contaDestino.saldo = contaDestino.saldo.add(valor);
        System.out.println("Transferencia efetuada!");
    }

    public BigDecimal getTaxa(Cliente cliente, BigDecimal valor) {
        BigDecimal taxa = new BigDecimal(0);
        if (cliente.getTipoPessoa().equals(TipoPessoaEnum.JURIDICA)) {
            taxa = valor.multiply(BigDecimal.valueOf(0.005));
        }
        return taxa;
    }

    public BigDecimal consultaSaldo() {
        System.out.println("Saldo: " + saldo);
        return saldo;
    }
}
