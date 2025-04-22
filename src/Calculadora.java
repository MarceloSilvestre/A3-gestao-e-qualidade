import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {

        //esse programa serve para informar o valor que um professor ficticio ganha, de acordo com a sua modalidade de trabalho sejam elas horista,clt ou pj

        // Criação de um novo scanner para ler entradas do teclado, que será utilizado ao longo de todo o programa
        Scanner teclado = new Scanner(System.in);

        // Solicitação do nome do professor
        System.out.println("Digite seu nome ");
        String nome;
        nome = teclado.next();

        // escolha do tipo de vínculo empregatício
        System.out.println("Digite que tipo de professor você é digite: 1 se é um professor horista, 2 se é um professor clt ou 3 se é um professor pj");
        int TipoDeProf;
        TipoDeProf = teclado.nextInt();

        // Declaração de constantes
        int HORISTA;
        int CLT;
        int PJ;

        HORISTA =1;
        CLT = 2;
        PJ = 3;

        // Lógica de decisão
        if(TipoDeProf == CLT){
            //professor clt
            System.out.println("digite seu salário mensal ");
            double salarioCLT;
            salarioCLT = teclado.nextDouble();
            System.out.print("O professor ");
            System.out.print(nome);
            System.out.print(" recebe um salário de R$ ");
            System.out.print(salarioCLT);
            System.out.print(" por mês");
        }
        else if(TipoDeProf == HORISTA){
            //professor horista
            System.out.println("digite o número de horas trabalhadas");
            Double horasTrabalhadas;
            horasTrabalhadas = teclado.nextDouble();
            System.out.println("digite o valor recebido por hora de trabalho");
            double valorHora;
            valorHora = teclado.nextDouble();
            double salarioHorista = horasTrabalhadas * valorHora;
            System.out.print("O professor ");
            System.out.print(nome);
            System.out.print(" recebe um salário de R$ ");
            System.out.print(salarioHorista);
            System.out.print(" por mês");
        }
        else if(TipoDeProf == PJ){
            //professor pj
            System.out.println("digite o valor do seu contrato com a institução");
            double salarioPJ;
            salarioPJ = teclado.nextDouble();
            System.out.print("O professor ");
            System.out.print(nome);
            System.out.print(" recebe um salário de R$ ");
            System.out.print(salarioPJ);
            System.out.print(" por mês");
        }
        else {
            // Mensagem de erro
            System.out.println("este número não é equivalente a um tipo de professor");
        }
        //fechando o teclado
        teclado.close();
    }
}