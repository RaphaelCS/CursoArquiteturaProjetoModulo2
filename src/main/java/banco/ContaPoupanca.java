package banco;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Integer numero, BigDecimal saldo, Cliente cliente)  {
        super(numero, saldo, TipoContaEnum.POUPANCA, cliente);

        if(cliente.getTipoPessoa().equals(TipoPessoaEnum.JURIDICA)){
            throw new RuntimeException("Conta poupança só pode ser aberta apenas para pessoa física");
        }
    }
}
