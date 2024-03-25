package banco;

import java.util.ArrayList;

public class BancoContas {

    public static ArrayList<Conta> listaContas = new ArrayList<>();

    public static Conta buscarConta(Cliente cliente, TipoContaEnum tipoConta){
        for(int i = 0 ; i<listaContas.size();i++){
            Conta conta = listaContas.get(i);
            if(conta.getCliente().equals(cliente)){
                if(conta.getTipoConta().equals(tipoConta)){
                    return conta;
                }
            }
        }
        return null;
    }
}
