package edu.kit.informatik.genetics;

public class Population {

  private DNA[] population;
  private double[] fitness;
  private double[] genPool;
  
  private int populationSize;
  private double mutationRate;
  private DNA baseGen;
  
  public Population(int populationSize, double mutationRate, DNA baseGen) {
    if (populationSize < 1 || mutationRate > 1 || mutationRate < 0)
      throw new IllegalArgumentException("illegal arguments, pop size >= 1 and mutation in [0, 1]!");
    
    this.populationSize = populationSize;
    this.mutationRate = mutationRate;
    this.baseGen = baseGen;
    
    populate();
  }
  
  private void populate() {
    population = new DNA[populationSize];
    for (int i = 0; i < populationSize; i++)
      population[i] = baseGen.create();
    fitness = new double[populationSize];
    genPool = new double[populationSize];
  }
  
  /**
   * Updating fitness and relativating for calculation
   */
  public void updateFitness() {
    double totalFitness = 0.0;
    
    for (int i = 0; i < populationSize; i++) {
      fitness[i] = population[i].fitness();
      totalFitness += fitness[i];
    }
    
    for (int i = 0; i < populationSize; i++)
      fitness[i] /= totalFitness;
  }
  
  public void selection() {
     DNA[] newPopulation = new DNA[populationSize];
     
     for (int i = 0; i < populationSize; i++) {
       DNA selectedParentA = weightedSelect();
       DNA selectedParentB = weightedSelect();
       
//       selectedParentA.print();
//       selectedParentB.print();
       newPopulation[i] = selectedParentA.crossover(selectedParentB);
//       System.out.print("->");
//       newPopulation[i].print();
//       System.out.println();
     }
     
     population = newPopulation;
  }
  
  public void mutation() {
    for (int i = 0; i < populationSize; i++) {
      if (Math.random() < mutationRate)
        population[i] = population[i].mutate();
    }
  }
  
  public DNA weightedSelect() {
    int arraySpot = (int) Math.floor(Math.random() * populationSize);
    
    double r = Math.random();
    if (r < fitness[arraySpot])
      return population[arraySpot];
    else
      return weightedSelect();
  }
  
  
  
  
  
  
  /// jsut for test
  public void printGenPool() {
    System.out.print("[");
    for (int i = 0; i < populationSize; i++) {
      System.out.print(genPool[i] + ", ");
    }
    System.out.println("]");
  }
  
  public void printFitness() {
    System.out.print("[");
    for (int i = 0; i < populationSize; i++) {
      System.out.print(fitness[i] + ", ");
    }
    System.out.println("]");
  }
  
  public void printPopulation() {
    System.out.print("[");
    for (int i = 0; i < populationSize; i++)
      population[i].print();
    System.out.println("]");
  }
  
  public void printBest() {
    int min = 0;
    for (int i = 0; i < populationSize; i++)
      if (fitness[i] < fitness[min]) min = i;
    population[min].print();
  }
  
  public static void main(String[] args) {
    Population pop = new Population(200, 0.01, new Text());
    
    pop.printFitness();
    pop.printPopulation();
    
    for (int i = 0; i < 200; i++) {
      pop.updateFitness();
      pop.selection();
      pop.mutation();
      
      if (i % 10 == 0) {
        pop.printFitness();
        pop.printBest();
      }
    }
  }

}
