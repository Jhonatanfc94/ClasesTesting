package auxiliar.coverage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ponderador {
    //[[package,browser][package,browser]]
    String[][] browsersWeight={{"chrome","0.6"},{"firefox","0.4"}};


    String[][] packageWeight;
    List<String> allnamePackages;
    List<String> allPackageWeight;
    public Ponderador(String[][] navegadoresXML){
        this.packageWeight =navegadoresXML;
        allnamePackages=new ArrayList<>();
        allPackageWeight=new ArrayList<>();
    }

    public void go(){
        getWeightAndNames();
    }

    public void getWeightAndNames(){
        for(int i = 0; i< packageWeight.length; i++){
            for(int j = 0; j< packageWeight[i].length; j++){
                if(j==0){
                    allnamePackages.add(packageWeight[i][j]);
                }else{
                    packageWeight[i][j]= calcularValorLista(packageWeight[i][j]);
                }
            }
        }
        allnamePackages = allnamePackages.stream().distinct().collect(Collectors.toList());
        sumarValorLista();
    }
    public String calcularValorLista(String browser){
        String valorCalculado=null;
        for(int i = 0; i< browsersWeight.length; i++){
            if(browser.equals(browsersWeight[i][0])){
                valorCalculado=browsersWeight[i][1];
            }
        }
        return valorCalculado;
    }

    public void imprimirListaWeight(){
        System.out.println("PACKAGE | VALUE |");
        for(int i = 0; i< packageWeight.length; i++){
            for(int j = 0; j< packageWeight[i].length; j++){
                System.out.print(packageWeight[i][j]+" | ");
            }
            System.out.println();
        }
    }

    public void sumarValorLista(){
        for(String browserActual:allnamePackages){
            Double total=0.00;
            for(int i = 0; i< packageWeight.length; i++){
                if(packageWeight[i][0].equals(browserActual)){
                    total=total+Double.parseDouble(packageWeight[i][1]);
                }
            }
            allPackageWeight.add((total*100)+"");
        }
    }

    public void imprimirResultado(){
        System.out.println(allnamePackages);
        System.out.println(allPackageWeight);
    }
}
