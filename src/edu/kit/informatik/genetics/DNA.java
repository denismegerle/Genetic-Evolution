package edu.kit.informatik.genetics;

/**
 * DNA of E.
 * 
 * @author 男子
 *
 * @param <E>
 */
public abstract class DNA {
  
  public abstract DNA create();
  
  public abstract DNA crossover(DNA other);
  
  public abstract DNA mutate();
  
  // fitness between [0, 1]
  public abstract double fitness();
  
  public abstract void encode();
  
  public abstract void decode();
  
  public abstract void print();
}
