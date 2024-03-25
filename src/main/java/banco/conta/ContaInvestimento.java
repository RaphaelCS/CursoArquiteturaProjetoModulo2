package banco.conta;

import banco.cliente.Cliente;
import banco.enums.TipoContaEnum;
import banco.enums.TipoPessoaEnum;

import java.math.BigDecimal;

public class ContaInvestimento extends Conta{

    private ContaInvestimento(Integer numero, BigDecimal saldo, Cliente cliente) {
        super(numero, saldo, TipoContaEnum.INVESTIMENTO, cliente);
        BancoContas.gravarConta(this);
    }

    public static ContaInvestimento investir(BigDecimal valor, ContaCorrente contaCorrente){
        Conta conta = BancoContas.getConta(contaCorrente.getCliente(),TipoContaEnum.INVESTIMENTO);
        if(conta==null){
            conta = new ContaInvestimento(contaCorrente.getNumero(),BigDecimal.valueOf(0),contaCorrente.getCliente());
            System.out.println("Conta investimento criada!");
        }
        contaCorrente.transferencia(valor,conta.getCliente());
        System.out.println("Investimento efetuado!");
        return (ContaInvestimento) conta;
    }

    public BigDecimal getRendimento() {
        BigDecimal rendimento;
        if(super.getCliente().getTipoPessoa().equals(TipoPessoaEnum.JURIDICA)){
            rendimento = consultaSaldo().multiply(BigDecimal.valueOf(0.02));
        }else{
            rendimento = consultaSaldo().multiply(BigDecimal.valueOf(0.01));
        }
        System.out.println("Rendimento do investimento: "+ rendimento);
        return rendimento;
    }
}
