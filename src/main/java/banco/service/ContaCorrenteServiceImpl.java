package banco.service;

import banco.enums.TipoContaEnum;
import banco.enums.TipoPessoaEnum;
import banco.model.Conta;
import banco.model.ContaCorrente;

import java.math.BigDecimal;

public class ContaCorrenteServiceImpl extends ContaCorrente implements ContaService {

    @Override
    public void saque(Conta conta, BigDecimal valor) {
        if(getStatusCliente(conta.getCliente())){
            BigDecimal taxa = getTaxa(conta, valor);
            if (valor.add(taxa).compareTo(conta.getSaldo()) > 0) {
                System.out.println("Saldo insuficiente.");
                return;
            }
            conta.setSaldo(conta.getSaldo().subtract(valor).subtract(taxa));
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
            if(BancoDadosContasService.ObterConta(contaDestino.getNumero(),TipoContaEnum.CORRENTE)==null){
               return;
            }
            BigDecimal taxa = getTaxa(conta, valor);
            if (valor.add(taxa).compareTo(conta.getSaldo()) > 0) {
                System.out.println("Saldo insuficiente.");
                return;
            }
            conta.setSaldo(conta.getSaldo().subtract(valor).subtract(taxa));
            contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
            System.out.println("Transferencia efetuada!");
        }
    }

    @Override
    public BigDecimal consultaSaldo(Conta conta) {
        System.out.println("Saldo: " + conta.getSaldo());
        return conta.getSaldo();
    }

    public BigDecimal getTaxa(Conta conta, BigDecimal valor) {
        BigDecimal taxa = new BigDecimal(0);
        if (conta.getCliente().getTipoPessoa().equals(TipoPessoaEnum.JURIDICA)&&conta.getTipoConta().equals(TipoContaEnum.CORRENTE)) {
            taxa = valor.multiply(BigDecimal.valueOf(0.005));
        }
        return taxa;
    }
}
