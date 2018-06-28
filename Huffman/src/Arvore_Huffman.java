//Herança

abstract class Arvore_Huffman implements Comparable<Arvore_Huffman> {
    
    // Frequência
    public final int frequencia; 
    //
    public Arvore_Huffman(int f) { 
        
    	frequencia = f; 
        
    }
        
    public int compareTo(Arvore_Huffman arv) {
        
        return frequencia - arv.frequencia;
        
    }
    
}