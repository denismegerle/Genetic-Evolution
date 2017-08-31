package edu.kit.informatik.genetics;

import java.util.Random;

public class Text extends DNA {
  
  public char[] text;
  public static String target = "TOBEORNOTTOBE";
  
  public Text() {
    text = new char[target.length()];
    for (int i = 0; i < target.length(); i++)
      text[i] = randChar();
  }
  
  public char randChar() {
    return (char) (65 + (new Random().nextInt(26)));
  }
  
  public Text(char[] text) {
    this.text = text;
  }
  
  @Override
  public DNA crossover(DNA other) {
    int midpoint = (new Random()).nextInt(text.length);
    char[] newText = new char[target.length()];
    for (int i = 0; i < target.length(); i++) {
      if (i > midpoint) newText[i] = text[i];
      else newText[i] = ((Text) other).text[i];
    }
    return new Text(newText);
  }

  @Override
  public DNA mutate() {
    char[] newText = new char[target.length()];
    
    int changePoint = (new Random()).nextInt(text.length);
    text[changePoint] = randChar();
    
    return new Text(newText);
  }

  @Override
  public double fitness() {
    return difFrom(target) / (double) target.length();
  }
  
  public int difFrom(String target) {
    int score = 0;
    for (int i = 0; i < this.target.length(); i++) {
      if (this.text[i] == target.charAt(i))
        score++;
    }
    return score;
  }

  @Override
  public void encode() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void decode() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Text create() {
    return new Text();
  }

  @Override
  public void print() {
    System.out.print(new String(text) + ", ");
  }

}
