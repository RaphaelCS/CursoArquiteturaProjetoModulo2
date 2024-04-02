package banco.service;

import banco.enums.StatusEnum;
import banco.model.Cliente;
import banco.model.Conta;

import java.math.BigDecimal;

public interface ContaService {

    void saque(Conta conta, BigDecimal valor);

    void deposito(Conta conta, BigDecimal valor);

    void transferencia(Conta conta, Conta contaDestino, BigDecimal valor);

    BigDecimal consultaSaldo(Conta conta);

    default Boolean getStatusCliente(Cliente cliente){
        if(cliente.getStatus().equals(StatusEnum.INATIVO)){
            System.out.println("Cliente inativado");
            return false;
        }else return true;
    }


}
