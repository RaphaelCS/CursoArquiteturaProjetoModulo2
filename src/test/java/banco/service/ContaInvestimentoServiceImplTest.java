package banco.service;

import banco.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContaInvestimentoServiceImplTest {

    Cliente clientePF;
    Cliente clientePJ;

    ContaInvestimentoServiceImpl contaService;

    ContaInvestimento contaInvestimento;

    @BeforeEach
    void init(){
        clientePF = new ClientePessoaFisica("1", "Raphael", 1,new BigDecimal(10000));
        clientePJ = new ClientePessoaJuridica("2","Caixa",2,new BigDecimal(100000));
        contaService = new ContaInvestimentoServiceImpl();

        contaInvestimento = contaService.investir(BigDecimal.valueOf(1000),clientePF.getContaCorrente());

    }

    @AfterEach
    void end(){
        contaInvestimento = null;
        BancoDadosContasService.listaContas.clear();
    }

    @Test
    void deposito() {
        contaService.deposito(contaInvestimento,BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(1500), contaService.consultaSaldo(contaInvestimento));
    }

    @Test
    void saquePF(){
        contaService.saque(contaInvestimento, BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(800), contaService.consultaSaldo(contaInvestimento));
    }

    @Test
    void saquePFSemSaldo(){
        contaService.saque(contaInvestimento, BigDecimal.valueOf(80000));
        assertEquals(BigDecimal.valueOf(1000), contaService.consultaSaldo(contaInvestimento));
    }

    @Test
    void transferencia(){
        contaService.transferencia(contaInvestimento, clientePF.getContaCorrente(), BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(900),contaService.consultaSaldo(contaInvestimento));
        assertEquals(BigDecimal.valueOf(9100),contaService.consultaSaldo(clientePF.getContaCorrente()));
    }

    @Test
    void transferenciaSemSaldo(){
        contaService.transferencia(contaInvestimento, clientePF.getContaCorrente(), BigDecimal.valueOf(20000));
        assertEquals(BigDecimal.valueOf(1000),contaService.consultaSaldo(contaInvestimento));
        assertEquals(BigDecimal.valueOf(9000),contaService.consultaSaldo(clientePF.getContaCorrente()));
    }
    
    @Test
    void consultaSaldo(){
        assertEquals(BigDecimal.valueOf(1000),contaService.consultaSaldo(contaInvestimento));
    }

    @Test
    void consultaRendimentoPF(){
        BigDecimal rendimento = contaService.getRendimento(contaInvestimento);
        assertEquals(BigDecimal.valueOf(10.0),rendimento.setScale(1));
    }

    @Test
    void consultaRendimentoPJ(){
        contaInvestimento = contaService.investir(BigDecimal.valueOf(1000),clientePJ.getContaCorrente());
        BigDecimal rendimento = contaService.getRendimento(contaInvestimento);
        assertEquals(BigDecimal.valueOf(20.0),rendimento.setScale(1));
    }
}