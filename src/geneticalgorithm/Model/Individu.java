package geneticalgorithm.Model;

import java.util.Random;

public class Individu {
    private String chromosome;
    private int fitness;
    private Target target;

    public Individu(String chromosome,Target target) {
        this.chromosome = chromosome;
        this.target = target;
        this.fitness = calculateFitness();
    }
    
    public Individu mate(Individu parent){
        String childChromosome = "";
        int length = chromosome.length();
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            float randomParent = random.nextFloat();
            
            if(randomParent < 0.45){
                childChromosome += chromosome.toCharArray()[i];
            }else if(randomParent < 0.90){
                childChromosome += parent.chromosome.toCharArray()[i];
            }else{
                Genes gen = new Genes();
                childChromosome += gen.mutatedGenChar();
            }
        }
        
        return new Individu(childChromosome, target);
    }
    
    private int calculateFitness(){
        int length = target.getLength();
        int fitness = 0;
        for (int i = 0; i < length; i++) {
            if(chromosome.toCharArray()[i] != target.getTargetStringtoChar()[i]){
                fitness++;
            }
        }
        return fitness;
    }

    public String getChromosome() {
        return chromosome;
    }

    public int getFitness() {
        return fitness;
    }
    
}
