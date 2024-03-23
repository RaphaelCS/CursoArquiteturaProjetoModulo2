package banco;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("CONTA PESSOA FÍSICA");
        Cliente cliente = new Cliente(1d,TipoPessoa.FISICA,"Raphael");
        Conta conta = new Conta(1,new BigDecimal(1000),cliente);
        conta.deposito(BigDecimal.valueOf(500));
        conta.saque(BigDecimal.valueOf(200));
        conta.consultaSaldo();


        System.out.println("CONTA PESSOA JURÍDICA");
        Cliente cliente1 = new Cliente(2d,TipoPessoa.JURIDICA,"Caixa");
        Conta conta1 = new Conta(2, new BigDecimal("50000"),cliente1);
        conta1.deposito(BigDecimal.valueOf(5000));
        conta1.saque(BigDecimal.valueOf(10000));
        conta1.consultaSaldo();

        conta.transferencia(BigDecimal.valueOf(100),conta1);
        conta.consultaSaldo();
        conta1.consultaSaldo();
    }
}
