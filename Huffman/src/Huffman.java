import java.util.*;

public class Huffman {

	public static void main(String[] args) {
		
        String entrada = "pneumoultramicroscopicossilicovulcanoconiotico"  + ""; //Significado: Doença pulmonar causada pela aspiração de cinzas vulcanicas. Maior palavra da lingua pórtuguesa
        
        //Percorre a entrada
        int[] conta_caracter = new int[256];
        for (char c : entrada.toCharArray())
            conta_caracter[c]++;
        
       
        Arvore_Huffman arvore = Cria_arvore(conta_caracter);
        
       
        System.out.println("TABELA DE CÓDIGOS");
        System.out.println("SÍMBOLO\tQUANTIDADE\tHUFFMAN CÓDIGO");
        Imprime_codigo(arvore, new StringBuffer());
        
        //Compactação
        String codificar = Codifica(arvore,entrada);
        
        System.out.println("\nTEXTO COMPACTADO");
        
        System.out.println(codificar); 
        
        int tamanhoEntrada = 8 * (entrada.length());
        int tamanhoSaida = codificar.length();
        int taxa = (tamanhoSaida*100)/tamanhoEntrada;
        
        System.out.println("Taxa de compactação = "+taxa+"%");

    }
    
    //Cria arvore    
    public static Arvore_Huffman Cria_arvore(int[] conta_caracter) {
    	
        PriorityQueue<Arvore_Huffman> arvore = new PriorityQueue<Arvore_Huffman>();
       
        for (int i = 0; i < conta_caracter.length; i++){
            
            if (conta_caracter[i] > 0){
                
                arvore.offer(new Folha_Arvore(conta_caracter[i], (char)i));
                
            }
            
        }
       
        while (arvore.size() > 1) {
            
            Arvore_Huffman a = arvore.poll(); 
            
            Arvore_Huffman b = arvore.poll(); 
            
            arvore.offer(new No_Arvore(a, b)); 
        }
        
        return arvore.poll();
        
    }
    
    //Codifica texto
    public static String Codifica(Arvore_Huffman arvore, String codifica){
        
    	assert arvore != null;
    	
    	String Cod_texto = "";
        
        for (char c : codifica.toCharArray()){
            
        	Cod_texto+=(getCodigo(arvore, new StringBuffer(),c));
                
        }
        
    	return Cod_texto;
        
    }
    
    //Imprime código
    public static void Imprime_codigo(Arvore_Huffman arvore, StringBuffer prefix) {
        
        assert arvore != null;
        
        if (arvore instanceof Folha_Arvore) {
            
            Folha_Arvore folha = (Folha_Arvore)arvore;
            
            System.out.println(folha.auxiliar + "\t" + folha.frequencia + "\t\t" + prefix);
 
        } else if (arvore instanceof No_Arvore) {
            
            No_Arvore node = (No_Arvore)arvore;
 
            prefix.append('0');
            
            Imprime_codigo(node.esq, prefix);
            
            prefix.deleteCharAt(prefix.length()-1);
 
            prefix.append('1');
            
            Imprime_codigo(node.dir, prefix);
            
            prefix.deleteCharAt(prefix.length()-1);
            
        }
    }
    
    public static String getCodigo(Arvore_Huffman tree, StringBuffer prefix, char w) {
        
        assert tree != null;
        
        if (tree instanceof Folha_Arvore) {
            
            Folha_Arvore folha = (Folha_Arvore)tree;
            
            if (folha.auxiliar == w ){
                
            	return prefix.toString();
                
            }
            
        } else if (tree instanceof No_Arvore) {
            
            No_Arvore no = (No_Arvore)tree;
 
            //Esquerda
            prefix.append('0');
            
            String esquerda = getCodigo(no.esq, prefix, w);
            
            prefix.deleteCharAt(prefix.length()-1);
 
            //Direita
            prefix.append('1');
            
            String direita = getCodigo(no.dir, prefix,w);
            
            prefix.deleteCharAt(prefix.length()-1);
            
            if (esquerda==null){
                
                return direita;
                
            }else{
                
                return esquerda;
                
            } 
           
            
        }
		return null;
    }

}
