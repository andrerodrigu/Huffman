//  E uma classe de um nó da folha Árvore de Huffman

class Folha_Arvore extends Arvore_Huffman {
    
    public final char auxiliar; //Atribui a nó folha 
 
    public Folha_Arvore(int frequencia, char aux) {
        
        super(frequencia);
        
        auxiliar = aux;
        
    }
}