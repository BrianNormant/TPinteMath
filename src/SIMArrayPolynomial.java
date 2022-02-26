import javax.swing.JOptionPane;

/**
 * La classe <b>SIMArrayPolynomial</b> repr�sente une application bas�e sur le projet d'int�gration de <u>Samuel Langevin</u>
 * fond� sur la d�riv�e et l'int�grale d'un polyn�me repr�sent� dans un tableau de coefficient.
 * 
 * @author Samuel Langevin et Simon Vezina
 * @since 2021-10-28
 * @version 2022-02-22
 */
public class SIMArrayPolynomial {

  /**
   * La constante <b>FUNCTION_ZERO</b> repr�sente la fonction f(x) = 0.0 en cha�ne de caract�re correspondant � {@value}.
   */
  private static final String FUNCTION_ZERO = "0.0";
  
  /**
   * 
   * M�thode main r�aliser l'analyse d'un polyn�me repr�sent� dans un tableau de coefficients de valeurs.
   * @param args
   */
  public static void main(String[] args) {
	  
    
    // Lecture des donn�es.
    double[] array = readArrayPolynomial();
    double a = readDouble("Donnez la valeur de la borne inf�rieure (a) de l'intervalle d'int�gration.");
    double b = readDouble("Donnez la valeur de la borne sup�rieure (b) de l'intervalle d'int�gration.");

    // Affichage de la fonction, de son degr� et de l'intervalle d'int�gration de la fonction.
    System.out.println("f(x) = " + arrayPolynomialToString(array, 'x'));
    System.out.println("Le degr� est " + degrePol(array));
    System.out.println("Intervalle d'int�gration [a, b] : [" + a + ", " + b +"]" );

    
    // �crivez le restant du programme afin d'atteindre les objectifs de ce travail.
    // ...
    
   
  
  }

  
  
  
  /**
   * M�thode pour faire la lecture d'un nombre de type double.
   * 
   * @param message Le message qui sera affich� pour guider l'utilisateur de la m�thode lors de l'ex�cution de cette m�thode.
   * @return Le nombre de type double lu.
   */
  public static double readDouble(String message) {
	  
    boolean succes;
    double value = 0.0;
    
    //-------------------------
    // Lecture du nombre double
    //-------------------------
    succes = false;

    do {
    	try {

    		value = Double.parseDouble(JOptionPane.showInputDialog(message));
    		succes = true;       

    	}catch(NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Le format de l'entr�e n'est pas de type double. Recommencez!");
    	}
    } while (!succes);

    
    return value;
  }
  
  
  
  
  
  /**
   * M�thode pour r�aliser la lecture du polyn�me � partir du clavier.
   * 
   * @return Le polyn�me.
   */
  public static double[] readArrayPolynomial() {
	  
    double[] array = null;
    boolean succes;
    
    //-------------------------------------------
    // Lecture de la taille maximale du polyn�me.
    //-------------------------------------------
    succes = false;
    
    do {
    	try {

    		int size = Integer.parseInt(JOptionPane.showInputDialog("Indentifiez la puissance maximale de votre polyn�me"));

    		// Validation de la taille qui doit �tre positif.
    		if(size < 0)
    			throw new NumberFormatException();

    		// Allocation de la taille du tableau.
    		array = new double[size+1];
    		succes = true;

    	}catch(NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Le format de l'entr�e de la puissance maximale n'est pas valide. Recommencez!");
    	}
    } while (!succes);
    
   
    
    //--------------------------
    // Lecture des coefficients.
    //--------------------------
    for(int i = 0; i < array.length; i++) {
    	
      succes = false;
     
      do {
    	  try {

    		  String expression = JOptionPane.showInputDialog("Indentifiez le coefficient de votre polyn�me de puissance " + i);

    		  if(expression.equals(""))
    			  array[i] = 0.0;
    		  else
    			  array[i] = Double.parseDouble(expression);

    		  succes = true;

    	  }catch(NumberFormatException e) {
    		  JOptionPane.showMessageDialog(null, "Le format de l'entr�e de votre coefficient n'est pas valide. Recommencez!");
    	  }
      } while (!succes);
      
    }
    
    return array;
  }
  
  
  
  
  
  /**
   * M�thode pour convertir en String l'expression d'un polyn�me repr�sent� dans un tableau
   * dans un ordre croissant de puissance.
   * 
   * @param array Le tableau des coefficients.
   * @param x Le caract�re de la variable.
   * @return Le String repr�sentant le polyn�me.
   */
  public static String arrayPolynomialToString(double[] array, char x) {
	  
    // Redimensionner le tableau en retirant les coefficients 0.0 pour la puissance la plus �lev�e.
    double[] resized_array = resizeArrayPolynomial(array);
    
    // Accumulateur des caract�res.
    StringBuffer buffer = new StringBuffer("");
    
    // Gestion des autres termes � afficher.
    for(int i = resized_array.length-1; i > -1; i--) {
    	
      // Affichage du terme en puissance si le coefficient n'est pas 0.0.
      if(resized_array[i] != 0.0) {
    	  
        // Mettre le signe.
        if(resized_array[i] < 0)
          buffer.append(" - ");
        else
          if(i != (resized_array.length-1))
            buffer.append(" + ");
        
        // Mettre le coefficient en valeur absolue, car le signe y est d�j�.
        // Cependant, nous n'allons pas afficher le terme "1.0" sauf pour la puissance 0.
        if(Math.abs(resized_array[i]) != 1.0 || i == 0)
          buffer.append(Math.abs(resized_array[i]));
        
        // Ajouter la variable x si le degr� n'est pas z�ro.     
        if(i != 0) {
        	
          // Mettre la variable x du polyn�me.
          buffer.append(x);
          
          // Mettre l'exposant au polyn�me s'il est sup�rieur � 1.
          if(i > 1) {  
            buffer.append("^");
            buffer.append(i);
          }
        }
      }
      
    }
    
    // Expression du polyn�me en String.
    String expression = buffer.toString();
    
    // Cas particulier : Le polyn�me f(x) = 0.0.
    if(expression.equals(""))    
      expression = FUNCTION_ZERO;
    
    // Cas g�n�ral, l'expression est valide.
    return expression; 
  }
  
  
  
  
 
  
  /**
   * M�thode permettant de redimensionner le tableau de coefficients repr�sentant un polyn�me dont les puissances sont en ordre croissant
   * afin de r�duire la taille du tableau si les coefficients des puissances les plus �lev�es du polyn�me sont nuls.
   * 
   * @param array Le tableau des coefficients du polyn�me
   * @return Le tabeau des coefficients du polyn�me redimensionn�.
   */
  public static double[] resizeArrayPolynomial(double[] array) {
	  
    // D�nombrer le nombre de terme "0.0" � retirer du polyn�me.
    int count  = 0;
    
    for(int i = array.length-1; i > -1; i--) {
    	
      if(array[i] == 0.0)
        count++;
      else
        break;
    }
    
    // Cas particulier : Il n'y a pas de redimension � r�aliser, donc on peut retourner la r�ponse.
    if(count == 0)
      return array;
    
    // Allocation de la m�moire au nouveau tableau redimensionn�.
    double[] resized_array = new double[array.length - count];
    
    // Cas particulier : Si la fonction est f(x) = 0.0, alors la nouvelle taille du tableau serait z�ro ce qui n'est pas valide.
    if(resized_array.length == 0)
      return new double[1];
    
    // Remplir le nouveau tableau (version rapide).
    System.arraycopy(array, 0, resized_array, 0, resized_array.length);
    
    return resized_array;
  }
  
  
  
  
  
  /**
   * M�thode pour �valuer un polyn�me f(x) repr�sent� en tableau de coefficients � une coordonn�e x donn�e.
   * 
   * @param array Le tableau des coefficients.
   * @param x La coordonn�e x o� la fonction est �valu�e.
   * @return La valeur de la fonction.
   */
  public static double evaluatePolynomial(double[] array, double x) {
	  
    //Initialisation
    double value = 0.0;
    
    //On additionne ensemble les �valuations pour chaque terme.
    for(int i = 0; i < array.length; i++)
      value += array[i] * Math.pow(x, (double) i);
    
    return value;
  }
    
  
 
  
  
  
  /**
   * M�thode pour d�terminer le degr� d'un polyn�me f(x).
   * Le r�sultat retourn� sera un entier.
   * 
   * @param array Le tableau des coefficients.
   * @return Le degr� du polyn�me.
   */
  public static int degrePol(double[] array) {
	  
	 
	  int k=array.length-1;
	  int degre=array.length-1;

	  //On teste les coefficients en partant des puissances les plus �lev�es.
	  //On trouve ainsi le premier terme non nul qui est associ� au degr� du polyn�me.
	  while(array[k]==0 && k!=0) {
		  degre=k-1;
		  k--;
	  }

	  return degre;
  }

  
  
  
  
  
  
  /**
   * M�thode pour d�terminer le nombre de z�ros d'un polyn�me de degr� 2 f(x).
   * Le r�sultat retourn� sera un entier.
   * 
   * @param array Le tableau des coefficients.
   * @return Le nombre de z�ros du polyn�me de degr� 2.
   */
  public static int numberZeroQuad(double[] array) {

	  //Initialisation
	  int nbZero;
	  
	  //On v�rifie que le polyn�me est bien de degr� 2. Sinon, le programme retourne 99 comme nombre de z�ros.
	  if(degrePol(array)==2) {

		  //Dans la formule quadratique, le discriminant est la valeur de l'expression sous la racine carr�e. 
		  //Le signe du discriminant nous indique le nombre de z�ros du polyn�me quadratique.
		  double discriminant=Math.pow(array[1],2)-4*array[2]*array[0];
		  
		  if(discriminant>0) {
			  nbZero = 2;

		  } else if (discriminant==0) {
			  nbZero = 1;

		  } else {
			  nbZero = 0;
		  }
		  
	  }else {
		  nbZero=99;
	  }
	  
	  return nbZero;
		  
  }
  
  
  
  
  /**
   * M�thode pour d�terminer la valeur des z�ros d'un polyn�me f(x) de degr� 2.
   * La m�thode retourne un tableau contenant les z�ros r�els du polyn�me, en ordre croissant.
   * 
   * @param array Le tableau des coefficients.
   * @return Le tableau des z�ros du polyn�me.
   */
  public static double[] valueZeroQuad(double[] array) {
	  
	  
	  //On initialise un tableau qui contiendra la valeur des z�ros.
	  double[] zeroTab=null;
	  
	  	  
	  //On v�rifie que le polyn�me est bien de degr� 2. Sinon, le programme s'arr�te.
	  if(degrePol(array)==2) {

		  // La taille du tableau d�pend du nombre de z�ros du polyn�me quadratique. 
		  int nbZero=numberZeroQuad(array);
		  zeroTab= new double[nbZero];

		  //Discriminant pour la formule quadratique.
		  double discriminant=Math.pow(array[1],2)-4*array[2]*array[0];
		  double temp = 0.0;

		  // La valeur des z�ros est donn�e par la formule quadratique.
		  if(nbZero==2) {
			  zeroTab[0] = (-array[1]+Math.pow(discriminant,0.5))/(2*array[2]);
			  zeroTab[1] = (-array[1]-Math.pow(discriminant,0.5))/(2*array[2]);

			  //On s'assure que les 2 z�ros sont en ordre croissant.
			  if(zeroTab[0]>zeroTab[1]) {
				  temp=zeroTab[0];
				  zeroTab[0]=zeroTab[1];
				  zeroTab[1]=temp;
			  }		

			  //S'il n'y a qu'un seul z�ro, la formule quadratique est plus simple.  
		  }else if(nbZero==1) {
			  zeroTab[0]=-array[1]/(2*array[2]);
		  }

		  //S'il n'y a pas de z�ro, le tableau est de taille 0 et il n'y a rien � mettre � l'int�rieur.

		  //S'il y a lieu, on remplace la valeur -0.0 par la valeur 0.0.
		  for(int i=0;i<zeroTab.length;i++) {
			  if(zeroTab[i]==-0.0) {
				  zeroTab[i]=0.0;
			  }
		  }
	  }
	  
	  
	  return zeroTab; 	
  }
  
  
  
  
  
  /**
   * M�thode r�alisant une int�grale ind�finie (ayant une constante=0) d'un tableau de coefficients repr�sentant un polyn�me dont les puissances sont en ordre croissant.
   * 
   * @param array Le tableau des coefficients.
   * @return Le tableau des coefficients de la fonction primitive dont la constante est 0.
   */
  public static double[] primitive0(double[] array) {
	  
    // Effacez la ligne ci-dessous (throw new ...) et �crivez le code appropri�.
    throw new RuntimeException("La m�thode public static double[] primitive0(double[] array) n'a pas �t� impl�ment�e.");

    
  }

  
  
  
  
  /**
   * M�thode pour �valuer l'int�grale d�finie d'un polyn�me f(x) entre x=a et x=b.
   * Si la fonction f est positive ou nulle sur l'intervalle, la valeur calcul�e repr�sente l'aire entre la courbe de f et l'axe des x.
   * 
   * @param array Le tableau des coefficient.
   * @param a La borne inf�rieure d'int�gration.
   * @param b La borne sup�rieure d'int�gration.
   * @return L'int�grale d�finie de la fonction.
   */
  public static double computeDefiniteIntegral(double[] array, double a, double b) {
       
    // Effacez la ligne ci-dessous (throw new ...) et �crivez le code appropri�.
    throw new RuntimeException("La m�thode public static double computeDefiniteIntegral(double[] array, double a, double b) n'a pas �t� impl�ment�e.");

    
  }

  
  

  
  
  /**
   * M�thode pour d�terminer le nombre de z�ros d'un polyn�me de degr� 2 f(x).
   * Le r�sultat retourn� sera un entier.
   * 
   * @param array Le tableau des coefficient.
   * @param a La borne inf�rieure d'int�gration.
   * @param b La borne sup�rieure d'int�gration.
   * @return Le nombre de z�ros du polyn�me de degr� 2.
   */
  public static String isAreaQuad(double[] array, double a, double b) {
	  
	  // Effacez la ligne ci-dessous (throw new ...) et �crivez le code appropri�.
	  throw new RuntimeException("La m�thode public static String isAreaQuad(double[] array, double a, double b) n'a pas �t� impl�ment�e.");

	  
  }

  
}
