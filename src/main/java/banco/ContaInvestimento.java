package banco;

import java.math.BigDecimal;

public class ContaInvestimento extends Conta{

    private BigDecimal rendimento;

    private ContaInvestimento(Integer numero, BigDecimal saldo, Cliente cliente) {
        super(numero, saldo, cliente);
        setTipoConta(TipoContaEnum.INVESTIMENTO);
    }

    public static ContaInvestimento investir(BigDecimal valor, ContaCorrente pConta){
        Conta conta = BancoContas.buscarConta(pConta.getCliente(),TipoContaEnum.INVESTIMENTO);
        if(conta==null){
            conta = new ContaInvestimento(pConta.getNumero(),BigDecimal.valueOf(0),pConta.getCliente());
            System.out.println("Conta investimento criada!");
        }
        pConta.transferencia(valor,conta);
        System.out.println("Investimento efetuado!");
        return (ContaInvestimento) conta;
    }

    public BigDecimal getRendimento() {
        if(super.getCliente().getTipoPessoa().equals(TipoPessoaEnum.JURIDICA)){
            rendimento = consultaSaldo().multiply(BigDecimal.valueOf(0.02));
        }else{
            rendimento = consultaSaldo().multiply(BigDecimal.valueOf(0.01));
        }
        System.out.println("Rendimento do investimento: "+rendimento);
        return rendimento;
    }
}
