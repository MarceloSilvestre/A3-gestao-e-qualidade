import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {

        //esse programa serve para informar o valor que um professor ficticio ganha, de acordo com a sua modalidade de trabalho sejam elas horista,clt ou pj
        
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("Digite seu nome: ");
            String nome = teclado.next();

            System.out.println("Digite o número equivalente ao tipo de professor você é:");
            System.out.println("1 - Horista");
            System.out.println("2 - CLT");
            System.out.println("3 - PJ");
            int TipoDeProf = teclado.nextInt();

            switch (TipoDeProf) {
                case 1:
                    System.out.println("Digite o número de horas trabalhadas:");
                    double horasTrabalhadas = teclado.nextDouble();

                    System.out.println("Digite o valor recebido por hora de trabalho:");
                    double valorHora = teclado.nextDouble();

                    double salarioHorista = horasTrabalhadas * valorHora;
                    System.out.println("O professor " + nome + " recebe um salário de R$ " + salarioHorista + " por mês");
                    break;

                case 2:
                    System.out.println("Digite seu salário mensal:");
                    double salarioCLT = teclado.nextDouble();
                    System.out.println("O professor " + nome + " recebe um salário de R$ " + salarioCLT + " por mês");
                    break;

                case 3:
                    System.out.println("Digite o valor do seu contrato com a instituição:");
                    double salarioPJ = teclado.nextDouble();
                    System.out.println("O professor " + nome + " recebe um salário de R$ " + salarioPJ + " por mês");
                    break;

                default:
                    System.out.println("Erro: Tipo de professor inválido.");
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar números corretamente.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
            teclado.close();
    }
}