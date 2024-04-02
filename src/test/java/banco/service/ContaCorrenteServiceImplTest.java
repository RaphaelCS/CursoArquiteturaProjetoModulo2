package banco.service;

import banco.model.Cliente;
import banco.model.ClientePessoaFisica;
import banco.model.ClientePessoaJuridica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;

class ContaCorrenteServiceImplTest {

    Cliente clientePF;
    Cliente clientePJ;

    ContaService contaService;

    @BeforeEach
    void init(){
        clientePF = new ClientePessoaFisica("1", "Raphael", 1,new BigDecimal(10000));
        clientePJ = new ClientePessoaJuridica("2","Caixa",2,new BigDecimal(100000));
        contaService = new ContaCorrenteServiceImpl();
    }

    @Test
    void deposito() {
        contaService.deposito(clientePF.getContaCorrente(),BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(10500), contaService.consultaSaldo(clientePF.getContaCorrente()));
    }

    @Test
    void saquePF(){
        contaService.saque(clientePF.getContaCorrente(), BigDecimal.valueOf(2000));
        assertEquals(BigDecimal.valueOf(8000), contaService.consultaSaldo(clientePF.getContaCorrente()));
    }

    @Test
    void saquePFSemSaldo(){
        contaService.saque(clientePF.getContaCorrente(), BigDecimal.valueOf(80000));
        assertEquals(BigDecimal.valueOf(10000), contaService.consultaSaldo(clientePF.getContaCorrente()));
    }

    @Test
    void saquePJ(){
        contaService.saque(clientePJ.getContaCorrente(),BigDecimal.valueOf(10000));
        assertEquals(BigDecimal.valueOf(89950), contaService.consultaSaldo(clientePJ.getContaCorrente()).setScale(0));
    }
    @Test
    void saquePJSemSaldo(){
        contaService.saque(clientePJ.getContaCorrente(),BigDecimal.valueOf(2000000));
        assertEquals(BigDecimal.valueOf(100000), contaService.consultaSaldo(clientePJ.getContaCorrente()).setScale(0));
    }

    @Test
    void transferencia(){
        contaService.transferencia(clientePF.getContaCorrente(), clientePJ.getContaCorrente(), BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(9900),contaService.consultaSaldo(clientePF.getContaCorrente()));
        assertEquals(BigDecimal.valueOf(100100),contaService.consultaSaldo(clientePJ.getContaCorrente()));
    }

    @Test
    void transferenciaSemSaldo(){
        contaService.transferencia(clientePF.getContaCorrente(), clientePJ.getContaCorrente(), BigDecimal.valueOf(20000));
        assertEquals(BigDecimal.valueOf(10000),contaService.consultaSaldo(clientePF.getContaCorrente()));
        assertEquals(BigDecimal.valueOf(100000),contaService.consultaSaldo(clientePJ.getContaCorrente()));
    }

    @Test
    void transferenciaPJ(){
        contaService.transferencia(clientePJ.getContaCorrente(), clientePF.getContaCorrente(), BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(99899.50),contaService.consultaSaldo(clientePJ.getContaCorrente()).setScale(1));
        assertEquals(BigDecimal.valueOf(10100),contaService.consultaSaldo(clientePF.getContaCorrente()));
    }

    @Test
    void transferenciaPJSemSaldo(){
        contaService.transferencia(clientePJ.getContaCorrente(), clientePF.getContaCorrente(), BigDecimal.valueOf(500000));
        assertEquals(BigDecimal.valueOf(100000),contaService.consultaSaldo(clientePJ.getContaCorrente()).setScale(0));
        assertEquals(BigDecimal.valueOf(10000),contaService.consultaSaldo(clientePF.getContaCorrente()));
    }

    @Test
    void consultaSaldo(){
        assertEquals(BigDecimal.valueOf(100000),contaService.consultaSaldo(clientePJ.getContaCorrente()));
    }
}