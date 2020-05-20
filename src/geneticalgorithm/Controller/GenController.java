package geneticalgorithm.Controller;

import geneticalgorithm.Model.Genes;
import geneticalgorithm.Model.Individu;
import geneticalgorithm.Model.Target;
import geneticalgorithm.View.MainAppForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GenController {
    private ArrayList<Individu> population;
    private int generation;
    private boolean found;
    private Genes gen;
    private Target target;
    private int POPULATION_SIZE = 100;
    private MainAppForm form;
    
    public static void main(String[] args) {
        GenController gen = new GenController();
    }
    
    public void resetState(){
        found = false;
        generation =0;
        population = new ArrayList<>();
    }
    
    public GenController(){
        gen = new Genes();
        form = new MainAppForm();
        form.addRunBtnListener(new runBtnListener());
        form.setVisible(true);
    }
    
    public void createInitialPopulation(){
        for (int i = 0; i < POPULATION_SIZE; i++) {
            String gnome = gen.createGnome(target.getLength());
            Individu something = new Individu(gnome, target);
            population.add(something);
        }
    }
    
    public void crossOverOperate(){
        Random random = new Random();
        while(!found){
            population.sort(Comparator.comparing(Individu::getFitness));
            if(population.get(0).getFitness() <= 0){
                found = true;
                break;
            }
            
            ArrayList<Individu> newGeneration = new ArrayList<>();
            
            int s = (10*POPULATION_SIZE)/100;
            for (int i = 0; i < s; i++) {
                newGeneration.add(population.get(i));
            }
            
            s=(90*POPULATION_SIZE)/100;
            for (int i = 0; i < s; i++) {
                int lenght = population.size();
                int randomInt = random.nextInt(50);
                Individu parent1 = population.get(randomInt);
                randomInt = random.nextInt(50);
                Individu parent2 = population.get(randomInt);
                Individu offSpring =  parent1.mate(parent2);
                newGeneration.add(offSpring);
            }
            population = newGeneration;
            printProcess();
            generation++;
        }
        printProcess();
    }
    
    public void printProcess(){
        String input = "Generation: "+generation+" String: "+population.get(0).getChromosome()+" Fitness: "+population.get(0).getFitness();
        form.appendTextArea(input);
        System.out.println(input+"\n");
    }
    
    private class runBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            resetState();
            target = new Target(form.getTargetField());
            System.out.println(target.getTargetString());
            createInitialPopulation();
            crossOverOperate();
        }
        
    }
}
