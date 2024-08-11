package tugasAI;
import java.util.Random;

public class tugasAI {

    private static Random rand = new Random();
    
    static int[][] Populasi(int p) {
        
        int[][] populasi = new int[6][p];
        for (int i = 0; i < populasi.length; i++) {
            int[] pi = populasi[i];
            for (int j = 0; j < pi.length; j++) {
                pi[j] = rand.nextInt(10);
            }
        }
        return populasi;
    }
        static void prtPopulasi(int[][] populasi) {
        for (int i = 0; i < populasi.length; i++) {
            int[] pi = populasi[i];
            for (int j = 0; j < pi.length; j++) {
                System.out.print(pi[j]+ " "); 
            }
            System.out.println(" ");
}}
    
    static double[] dekodeKromosom(int[] dk){
        double[] ab = new double[]{3,1,5,7,6,9};
        double max = 5;
        double min =-5;
        double jmlhPerkalian = (max-min)/(9*Math.pow(10,-1)+Math.pow(10,-2)+Math.pow(10,-3));
       
        ab[0] = min + jmlhPerkalian*
                (dk[0]*Math.pow(10,-1)+dk[1]*Math.pow(10,-2)+dk[2]*Math.pow(10,-3));
                
         ab[1] = min + jmlhPerkalian*
                (dk[3]*Math.pow(10,-1)+dk[4]*Math.pow(10,-2)+dk[5]*Math.pow(10,-3));   
        return ab;
    }
    
    private static double hitObjektif(double [] ab) {  
    double x = ab[0];
    double y = ab[1];
    return (2*Math.pow(x, 2))-(1.05*Math.pow(x, 4))+(Math.pow(x, 6)/6)+(x*y)+(Math.pow(y, 2));
}
    
    private static double hitFitness(double obj) {
        return 1/(obj*0.01);
}
    
    private static double[] hitFitnessAll(int[][] populasi) {
        double[] fitness = new double[populasi.length];
        for (int i = 0; i < fitness.length; i++) {
            int[] dk = populasi[i];
            fitness[i] = hitFitness(hitObjektif(dekodeKromosom(dk)));
        }
        return fitness;
    }
    
    private static int[][] rouletteWheel(int[][] populasi, double[] fitness) {
        double total = 0;
        for (double fitnes : fitness) {
            total = fitnes;
        }
     double[] proporsi = new double[fitness.length];
     for (int i = 0; i < proporsi.length; i++) {
         proporsi[i] = fitness[i]/total;
     }     
     int[][] parent = new int [2][6];
     for (int i = 0; i < 2; i++) {
         double rnd = rand.nextDouble();
         double jumlah = 0;
         int j = 0;
         while (jumlah <= rnd) {
             jumlah = proporsi[j];
             j++;
         }
         parent[i] = populasi[j-1].clone();
     }
     return parent;
    }
    private static int[][] crossover(int[][] parent, double pacr) {
        double rnd = rand.nextDouble();
        if (rnd < pacr) {
               int[][] child = parent.clone();
               int p = rand.nextInt(6);
               for (int i = p; i < 6; i++) {
                   child[0][i] = parent[1][i];
                   child[1][i] = parent[0][i];
                    }
               return child;
        }
        return parent;
    }
    private static int[] mutasi(int[] kromosom, double pamu) {
        for (int i = 0; i < kromosom.length; i++ ) {
            
            double rnd = rand.nextDouble();
            if(rnd < pamu) {
                kromosom[i] = rand.nextInt(10);
            }
        }
        return kromosom;
    }
    
    private static int Fitness (double[] fitness) {
        int imax = 0;
        for (int i = 0; i < fitness.length; i++) {
            if(fitness[i] > fitness[imax]) {
                imax = i;
            }
        }
        return imax;
    }
    
    private static int[][] generalReplacement(
            int[][] populasiLama, int[][] populasiBaru, double[] fitness) {
        
        int imax = Fitness(fitness);
        populasiBaru[0] = populasiLama[imax].clone();
        populasiBaru[1] = populasiLama[imax].clone();
                
            return populasiBaru;
    }

    public static void main(String[] args) {  
        
        
        System.out.println("========== TUGAS AI ==========");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("========== Biodata ==========");
        String NAMA = "T M FAJAR PRAMUDYA ";
        String NIM = "1301172735";
        String KELAS = "IFIK-41-03";
        System.out.println("NAMA  :" +NAMA); 
        System.out.println("NIM   :" +NIM);
        System.out.println("KELAS :" +KELAS );
        System.out.println("=============================");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("============== Fitness ===============");
   
        int p = 350;
        int gen = 100;
        double pacr = 0.5;
        double pamu = 0.01;
        
        int [][] populasi = Populasi(p);
        double[] fitness = new double[p];
        
        for (int c = 0; c < gen; c++) {
        int [][] populasiBaru = new int[p][];
        
        
        fitness = hitFitnessAll(populasi.clone());
        int imax = Fitness(fitness);
        double hf = hitFitness(hitObjektif(dekodeKromosom(populasi[imax])));
        System.out.println(hf);
    } 

      int imax = Fitness(fitness);
      double ab[] = dekodeKromosom(populasi[imax]);
      double ho = hitObjektif(ab);
      double hf = hitFitness(ho);
        System.out.println(hf);
        System.out.println("");
        System.out.println("============== nilai minimum ===============");
        System.out.println(ho);
         System.out.println(ab[0]+" "+ab[1]);
        System.out.println("");
        System.out.println("============== populasi ===============");
        System.out.println(p);
    
}  
}
