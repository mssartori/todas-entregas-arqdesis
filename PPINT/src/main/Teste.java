package main;

import javax.swing.*;

public class Teste
{
   public static void main(String [] args) throws Exception
   {
      new security.TesteCrypto();
      idiomas.Internacionalizacao i = new idiomas.Internacionalizacao();
      int Indice = i.getIndice();
      view.LoginGUI loginGUI = new view.LoginGUI(Indice);
      loginGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}