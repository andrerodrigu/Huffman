//Nó da arvore

class No_Arvore extends Arvore_Huffman {
    
    //Sub-arvores
    public final Arvore_Huffman esq, dir; 
 
    public No_Arvore(Arvore_Huffman e, Arvore_Huffman d) {
        
        super(e.frequencia + d.frequencia);
        
        esq = e;
        
        dir = d;
        
    }
    
}