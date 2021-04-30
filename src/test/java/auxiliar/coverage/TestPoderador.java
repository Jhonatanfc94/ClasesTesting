package auxiliar.coverage;

public class TestPoderador {
    public static void main(String[] args) {
        String[][] navegatoresTest={{"login","chrome"},{"login","firefox"},{"transactions","chrome"}};
        Ponderador ponderador=new Ponderador(navegatoresTest);
        ponderador.go();
        ponderador.imprimirResultado();
    }
}
