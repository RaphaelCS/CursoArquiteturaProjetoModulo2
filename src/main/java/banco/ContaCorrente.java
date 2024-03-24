package banco;

import java.math.BigDecimal;

public class ContaCorrente extends Conta{

    public ContaCorrente(Integer numero, BigDecimal saldo) {
        super(numero, saldo, null);
        setTipoConta(TipoContaEnum.CORRENTE);
    }

    public ContaInvestimento investir(BigDecimal valor, ContaCorrente pConta){
        for(int i = 0 ; i<BancoContas.listaContas.size();i++){
            Conta conta = BancoContas.listaContas.get(i);
            if(conta.getCliente().equals(pConta.getCliente())){
                if(conta.getTipoConta().equals(TipoContaEnum.INVESTIMENTO)){
                    pConta.transferencia(valor,conta);
                    return (ContaInvestimento) conta;
                }
            }
        }
        ContaInvestimento contaInvestimento = new ContaInvestimento(pConta.getNumero(),BigDecimal.valueOf(0),pConta.getCliente());
        pConta.transferencia(valor,contaInvestimento);
        return contaInvestimento;
    }

}
