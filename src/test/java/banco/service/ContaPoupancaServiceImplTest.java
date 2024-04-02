package banco.service;

import banco.model.Cliente;
import banco.model.ClientePessoaFisica;
import banco.model.ClientePessoaJuridica;
import banco.model.ContaPoupanca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;

class ContaPoupancaServiceImplTest {

    Cliente clientePF;
    Cliente clientePJ;

    ContaService contaService;

    ContaPoupanca contaPoupanca;

    @BeforeEach
    void init(){
        clientePF = new ClientePessoaFisica("1", "Raphael", 1,new BigDecimal(10000));
        clientePJ = new ClientePessoaJuridica("2","Caixa",2,new BigDecimal(100000));
        contaPoupanca = new ContaPoupanca(1,BigDecimal.valueOf(10000), (ClientePessoaFisica) clientePF);
        //contaPoupanca = new ContaPoupanca(1,BigDecimal.valueOf(10000), clientePJ); PJ não pode ter conta poupança
        contaService = new ContaPoupancaServiceImpl();
    }

    @Test
    void deposito() {
        contaService.deposito(contaPoupanca,BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(10500), contaService.consultaSaldo(contaPoupanca));
    }

    @Test
    void saquePF(){
        contaService.saque(contaPoupanca, BigDecimal.valueOf(2000));
        assertEquals(BigDecimal.valueOf(8000), contaService.consultaSaldo(contaPoupanca));
    }

    @Test
    void saquePFSemSaldo(){
        contaService.saque(contaPoupanca, BigDecimal.valueOf(80000));
        assertEquals(BigDecimal.valueOf(10000), contaService.consultaSaldo(contaPoupanca));
    }

    @Test
    void transferencia(){
        contaService.transferencia(contaPoupanca, clientePJ.getContaCorrente(), BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(9900),contaService.consultaSaldo(contaPoupanca));
        assertEquals(BigDecimal.valueOf(100100),contaService.consultaSaldo(clientePJ.getContaCorrente()));
    }

    @Test
    void transferenciaSemSaldo(){
        contaService.transferencia(contaPoupanca, clientePJ.getContaCorrente(), BigDecimal.valueOf(20000));
        assertEquals(BigDecimal.valueOf(10000),contaService.consultaSaldo(contaPoupanca));
        assertEquals(BigDecimal.valueOf(100000),contaService.consultaSaldo(clientePJ.getContaCorrente()));
    }

    @Test
    void consultaSaldo(){
        assertEquals(BigDecimal.valueOf(100000),contaService.consultaSaldo(clientePJ.getContaCorrente()));
    }
}