package banco.conta;

import banco.cliente.Cliente;
import banco.enums.StatusEnum;
import banco.enums.TipoContaEnum;
import banco.enums.TipoPessoaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Conta {

    private final LocalDate dataCriacao = LocalDate.now();
    private Integer numero;
    private BigDecimal saldo;
    private Cliente cliente;

    private TipoContaEnum tipoConta;

    public Conta(Integer numero, BigDecimal saldo, TipoContaEnum tipoConta, Cliente cliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.cliente = cliente;
        this.tipoConta = tipoConta;
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

    public void transferencia(BigDecimal valor, Cliente clienteDestino) {
        if(cliente.getStatus().equals(StatusEnum.INATIVO)){
            System.out.println("Cliente inativado");
            return;
        }
        Conta contaDestino = BancoContas.getConta(clienteDestino,TipoContaEnum.CORRENTE);
        if(contaDestino==null){
            System.out.println("Conta destino inexistente!");
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
        if (cliente.getTipoPessoa().equals(TipoPessoaEnum.JURIDICA)&&tipoConta.equals(TipoContaEnum.CORRENTE)) {
            taxa = valor.multiply(BigDecimal.valueOf(0.005));
        }
        return taxa;
    }

    public BigDecimal consultaSaldo() {
        System.out.println("Saldo: " + saldo);
        return saldo;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "dataCriacao=" + dataCriacao +
                ", numero=" + numero +
                ", saldo=" + saldo +
                ", cliente=" + cliente +
                ", tipoConta=" + tipoConta +
                '}';
    }
}
