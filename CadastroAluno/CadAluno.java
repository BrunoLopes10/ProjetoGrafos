import java.util.Scanner;

public class CadAluno {

    String nome;
    int idade;

    public CadAluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("--------------------");
        System.out.print("Digite quantos alunos irá cadastrar: ");

        int qAlunos = scan.nextInt();

        CadAluno[] alunos = new CadAluno[qAlunos];

        for (int i = 0; i < alunos.length; i++) {
            System.out.print("Nome do aluno " + (i + 1) + ": ");
            String nome = scan.next();
            System.out.print("Idade do aluno " + (i + 1) + ": ");
            int idade = scan.nextInt();
            alunos[i] = new CadAluno(nome, idade);
        }

        for (int i = 0; i < alunos.length; i++) {
            System.out.println(alunos[i].nome + ", " + alunos[i].idade + " anos");
        }

        scan.close();
    }
}
