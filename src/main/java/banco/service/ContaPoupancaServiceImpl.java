package banco.service;

import banco.model.Conta;
import banco.model.ContaPoupanca;

import java.math.BigDecimal;

public class ContaPoupancaServiceImpl extends ContaPoupanca implements ContaService{
    @Override
    public void saque(Conta conta, BigDecimal valor) {
        if(getStatusCliente(conta.getCliente())){
            if (valor.compareTo(conta.getSaldo()) > 0) {
                System.out.println("Saldo insuficiente.");
                return;
            }
            conta.setSaldo(conta.getSaldo().subtract(valor));
            System.out.println("Saque efetuado!");
        }
    }

    @Override
    public void deposito(Conta conta, BigDecimal valor) {
        if(getStatusCliente(conta.getCliente())){
            conta.setSaldo(conta.getSaldo().add(valor));
            System.out.println("Deposito efetuado!");
        }
    }

    @Override
    public void transferencia(Conta conta, Conta contaDestino, BigDecimal valor) {
        if(getStatusCliente(conta.getCliente())){
            if (valor.compareTo(conta.getSaldo()) > 0) {
                System.out.println("Saldo insuficiente.");
                return;
            }
            conta.setSaldo(conta.getSaldo().subtract(valor));
            contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
            System.out.println("Transferencia efetuada!");
        }
    }

    @Override
    public BigDecimal consultaSaldo(Conta conta) {
        System.out.println("Saldo: " + conta.getSaldo());
        return conta.getSaldo();
    }
}
