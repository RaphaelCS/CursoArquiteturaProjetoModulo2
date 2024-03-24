package banco;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Integer numero, BigDecimal saldo, Cliente cliente) throws RuntimeException {
        super(numero, saldo, cliente);
        if(cliente.getTipoPessoa().equals(TipoPessoaEnum.JURIDICA)){
            throw new RuntimeException("Conta poupança só pode ser aberta apenas para pessoa física");
        }
        setTipoConta(TipoContaEnum.POUPANCA);
    }
}
