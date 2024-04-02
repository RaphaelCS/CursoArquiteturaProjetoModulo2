package banco.service;

import banco.enums.StatusEnum;
import banco.model.Cliente;
import banco.model.Conta;

import java.math.BigDecimal;

public interface ContaService {

    public void saque(Conta conta, BigDecimal valor);

    public void deposito(Conta conta, BigDecimal valor);

    public void transferencia(Conta conta, Conta contaDestino, BigDecimal valor);

    public BigDecimal consultaSaldo(Conta conta);

    default Boolean getStatusCliente(Cliente cliente){
        if(cliente.getStatus().equals(StatusEnum.INATIVO)){
            System.out.println("Cliente inativado");
            return false;
        }else return true;
    }


}
