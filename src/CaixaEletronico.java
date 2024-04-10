import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
public class CaixaEletronico {
    ArrayList<Conta> contas = new ArrayList<Conta>();
    private Cliente cliente;

    public void executa() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0; 
   
        do { 
           System.out.println("1. Criar Conta");
           System.out.println("2. Consultar Saldo");
           System.out.println("3. Depositar");
           System.out.println("4. Sacar");
           System.out.println("5. Transferir");
           System.out.println("6. Listar contas");
           System.out.println("7. Remover Conta");
           System.out.println("8. Filtrar Conta pelo nome do Cliente");
           System.out.println("9. Sair");

   
           System.out.print("Escolha uma opção: ");
           opcao = scanner.nextInt();
            
            switch (opcao) {
                
                case 1: 
                    criarConta();
                    break;
                case 2:
                    consultaSaldo(); 
                    break;

                case 3: 
                    depositar();
                    break;
                case 4: 
                    sacar();
                    break;
                case 5: 
                    transferir ();
                    break;
                case 6:
                    listarContas();
                    break;
                case 7: 
                    excluiConta();
                    break;
                case 8: 
                    filtrarConta();
                    break;
                case 9: 
                    break;
            }

        } while (opcao != 9);
    }

    public void criarConta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do titular da conta: ");
        String nomeCliente = scanner.nextLine();
        System.out.println("Agora, digite o cpf: ");
        String cpfCliente = scanner.nextLine();
        cliente = new Cliente(nomeCliente, cpfCliente);
    
        System.out.println("Digite o limite mínimo da conta: ");
        double limiteMinimo = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha pendente
    
        System.out.println("Deseja fazer um depósito inicial na conta? Tecle S para sim e N para não. ");
        String escolha = scanner.nextLine();
        if (escolha.equals("S")) { // Corrigindo a comparação de strings
            System.out.println("Digite o valor do saldo inicial: ");
            Double saldoInicial = scanner.nextDouble();
            Conta novaConta = new Conta(cliente, limiteMinimo, saldoInicial);
            contas.add(novaConta);
    
        } else if (escolha.equals("N")) { // Corrigindo a comparação de strings
            Conta novaConta = new Conta(cliente, limiteMinimo);
            contas.add(novaConta);

        }
    }
    public void listarContas () {
        for (Conta conta: contas) {
            System.out.println("Número da conta: " + conta.getNumeroDaConta() + " " + "cliente: " + conta.getCliente().getNome());
        }
    }
        public void depositar () {
            Scanner scanner = new Scanner (System.in);
            System.out.println("Digite o número da conta a ser depositado: ");
            int numConta = scanner.nextInt();
            System.out.println("Digite o valor a ser depositado: ");
            double valor = scanner.nextDouble();
            for ( Conta conta : contas) {
                if (conta.getNumeroDaConta() == numConta){
                    conta.deposito(valor);
                }
            }            
        }
        public void sacar() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite um valor a ser sacado: ");
            double valor = scanner.nextDouble();
            System.out.println("Agora, digite o número da conta que será realizado o saque: ");
            int numContaSaque = scanner.nextInt();
            for (Conta conta : contas){ 
                if (conta.getNumeroDaConta() == numContaSaque){
                    if (valor <= conta.getLimite()) {
                        conta.saque(valor);
                        System.out.println("Saque realizado com sucesso.");
                    } else {
                        System.out.println("Não foi possível fazer o saque. Verifique o limite mínimo.");
                    }
                }   
            }
        }
        
        public void consultaSaldo () {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o numero da conta a ter o saldo consultado: ");
            int numContaSaldo = scanner.nextInt();
            for (Conta conta : contas){
                if (conta.getNumeroDaConta() == numContaSaldo) {
                    System.out.println("Saldo da conta: " + conta.getSaldo());
                
                }
            }
        }

        public void transferir() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o valor de transferência: ");
            double valorTransfere = scanner.nextDouble();
            System.out.println("Digite o número da conta de destino: ");
            int contaDestino = scanner.nextInt();
            System.out.println("Digite o número da conta de origem: ");
            int contaOrigem = scanner.nextInt();
        
            for (Conta conta : contas){
                for (Conta conta2 : contas) {
                    if (conta.getNumeroDaConta() == contaDestino){
                        if (conta2.getNumeroDaConta() == contaOrigem) {
                            conta2.saque(valorTransfere);
                            conta.deposito(valorTransfere);
                        }
                    }
                }
            }
        
        }
        public void excluiConta () {
            Scanner scanner = new Scanner(System.in); 
            System.out.println("Digite o número da conta a ser removida: ");
            int numConta = scanner.nextInt(); 
            Iterator <Conta> iterator = contas.iterator();
            while (iterator.hasNext()) {
                Conta conta = iterator.next();
                if (conta.getNumeroDaConta() == numConta) {
                    if (conta.getSaldo() > 0) {
                        System.out.println("Não é possivel cancelar a conta pois ela possui um saldo de " + conta.getSaldo());
                    } else if (conta.getSaldo() < 0) {
                        System.out.println("Não é possível fazer a remoção de uma conta com saldo negativo. ");
                    } else if (conta.getSaldo() == 0){
                         iterator.remove();
                         
                    }
                }
            }
        }
        public void filtrarConta () {
            Scanner scanner = new Scanner (System.in); 
            System.out.println("Digite o nome do cliente que deseja buscar a(s) conta(s): ");
            String nomeCliente = scanner.nextLine();
            for (Conta conta : contas) {
                if (conta.getCliente().getNome().contains(nomeCliente)){
                    System.out.println(conta.getNumeroDaConta() + " " + conta.getCliente().getNome());
                }
            }

        }
    }

    


