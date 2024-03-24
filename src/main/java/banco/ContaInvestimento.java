package banco;

import java.math.BigDecimal;

public class ContaInvestimento extends Conta{

    private BigDecimal rendimento;

    public ContaInvestimento(Integer numero, BigDecimal saldo, Cliente cliente) {
        super(numero, saldo, cliente);
        setTipoConta(TipoContaEnum.INVESTIMENTO);
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
