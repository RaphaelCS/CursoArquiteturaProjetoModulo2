package banco.service;

import banco.enums.TipoPessoaEnum;
import banco.model.Conta;
import banco.model.ContaCorrente;
import banco.model.ContaInvestimento;

import java.math.BigDecimal;

public class ContaInvestimentoServiceImpl extends ContaInvestimento implements ContaService {
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

    public BigDecimal getRendimento(ContaInvestimento contaInvestimento) {
        BigDecimal rendimento;
        if(contaInvestimento.getCliente().getTipoPessoa().equals(TipoPessoaEnum.JURIDICA)){
            rendimento =  contaInvestimento.getSaldo().multiply(BigDecimal.valueOf(0.02));
        }else{
            rendimento = contaInvestimento.getSaldo().multiply(BigDecimal.valueOf(0.01));
        }
        System.out.println("Rendimento do investimento: "+ rendimento);
        return rendimento;
    }

    public ContaInvestimento investir(BigDecimal valor, ContaCorrente contaCorrente){
        ContaInvestimento conta = obterContaInvestimento(contaCorrente);
        transferencia(contaCorrente,conta,valor);
        System.out.println("Investimento efetuado!");
        return conta;
    }
}
