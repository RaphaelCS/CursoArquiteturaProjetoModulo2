package banco.model;

import banco.enums.TipoContaEnum;
import banco.enums.TipoPessoaEnum;
import banco.service.BancoDadosContasService;

import java.math.BigDecimal;

public class ContaInvestimento extends Conta {

    private ContaInvestimento(Integer numero, BigDecimal saldo, Cliente cliente) {
        super(numero, saldo, TipoContaEnum.INVESTIMENTO, cliente);
        BancoDadosContasService.gravarConta(this);
    }

    public ContaInvestimento(){}

    public ContaInvestimento obterContaInvestimento(ContaCorrente contaCorrente){
        Conta conta = BancoDadosContasService.ObterConta(contaCorrente.getNumero(), TipoContaEnum.INVESTIMENTO);
        if(conta==null){
            conta = new ContaInvestimento(contaCorrente.getNumero(),BigDecimal.valueOf(0),contaCorrente.getCliente());
            System.out.println("Conta investimento criada!");
        }
        return (ContaInvestimento) conta;
    }

}
