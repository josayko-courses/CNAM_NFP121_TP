package question2;

import question1.PilePleineException;
import question1.PileVideException;

public interface PileI {

  public static final int CAPACITE_PAR_DEFAUT = 6;

  public void empiler(Object o) throws PilePleineException;

  public Object depiler() throws PileVideException;

  public Object sommet() throws PileVideException;

  public int capacite();

  public int taille();

  public boolean estVide();

  public boolean estPleine();

  @Override
  public boolean equals(Object o);

  @Override
  public int hashCode();

  @Override
  public String toString();
}