package ArvoreAVL;

class AVLTree<T extends Comparable<T> >{  
    private Node<T> raizArvore;       
  
    public AVLTree(){  
        raizArvore = null;  
    }    
     
    public void inserir(T elemento)  {  
        raizArvore = inserirValor(elemento, raizArvore);  
    }
    
    //Metodo de insercao dos nodos recursivamente  
    private Node<T> inserirValor(T elemento, Node<T> node){  
        //checa se o nó é uma raiz ou folha 
        if (node == null)  
            node = new Node<T>(elemento);  
        //Inserir elementos na esquerda da raiz
        else if (elemento.compareTo(node.elemento) < 0 ){    
            node.esquerda = inserirValor( elemento, node.esquerda );  
            if( getAltura( node.esquerda ) - getAltura( node.direita) == 2 )  
                if( elemento.compareTo(node.esquerda.elemento) < 0)  
                    node = rotacaoLL(node);
                else 
                    node = duplaLL(node); 
                      
        }  
         //Inserir elementos na direita da raiz
        else if( elemento.compareTo(node.elemento) > 0 ){  
            node.direita = inserirValor( elemento, node.direita );  
            if( getAltura( node.direita ) - getAltura( node.esquerda ) == 2 )  
                if(elemento.compareTo(node.direita.elemento)>0)
                    node = rotacaoRR( node );  
                else  
                    node = duplaRR( node );
        }  
        else  
            ;     
        node.h = getMaiorAltura( getAltura( node.esquerda ), getAltura( node.direita ) ) + 1; // atualiaza a altura do nodo 
          
        return node;  
    }  
     
    private int getAltura(Node<T> node ){  
        return node == null ? -1 : node.h;  
    }  
        
    private int getMaiorAltura(int esqAltura, int dirAltura){  
        return esqAltura > dirAltura ? esqAltura : dirAltura;  
    }  
      
    // Rotacao simples a direita        
    private Node<T> rotacaoLL(Node<T> node2) {  
        Node<T> node1 = node2.esquerda;  
        node2.esquerda = node1.direita;  
        node1.direita = node2;  
        node2.h = getMaiorAltura( getAltura( node2.esquerda ), getAltura( node2.direita ) ) + 1;  
        node1.h = getMaiorAltura( getAltura( node1.esquerda), node2.h ) + 1;  
        return node1;  
    }  
  
    // Rotacao simples a esquerda        
    private Node<T> rotacaoRR(Node<T> node1)  {  
        Node<T> node2 = node1.direita;  
        node1.direita = node2.esquerda;  
        node2.esquerda = node1;  
        node1.h = getMaiorAltura( getAltura( node1.esquerda ), getAltura( node1.direita) ) + 1;  
        node2.h = getMaiorAltura( getAltura( node2.direita ), node1.h ) + 1;  
        return node2;  
    }  
  
    //Rotacao DuplaLL =  RotacaoRR + RotacaoLL 
    private Node<T> duplaLL(Node<T> node3){  
        node3.esquerda = rotacaoRR( node3.esquerda );  
        return rotacaoLL( node3 );  
    }  
  
    //Rotacao Dupla RR = RotacaoLL + RotacaoRR
    private Node<T> duplaRR(Node<T> node1){  
        node1.direita = rotacaoLL( node1.direita );  
        return rotacaoRR( node1 );  
    }      
     
    public int ObterQtdNodes(){  
        return ObterQtdNodes(raizArvore);  
    }
    
    private int ObterQtdNodes(Node<T> head){  
        if (head == null)  
            return 0;  
        else {  
            int tam = 1;  
            tam = tam + ObterQtdNodes(head.esquerda);  
            tam = tam + ObterQtdNodes(head.direita);  
            return tam;  
        }  
    }
    
    public Node<T> buscar(Node<T> raiz, T chave) {
        if (raiz == null || raiz.getElemento().compareTo(chave) == 0){
            if(raiz == null)
                System.err.println("Elemento não encontrado");
            return raiz;
        }
        return (raiz.getElemento().compareTo(chave) > 0) ? buscar(raiz.getEsquerda(), chave) : buscar(raiz.getDireita(), chave);
    }
    
    public Node<T> remover(Node<T> raiz, T elemento){
        if(raiz == null)
            return raiz;
        
        if(elemento.compareTo(raiz.getElemento()) < 0)
            raiz.setEsquerda(remover(raiz.getEsquerda(), elemento));
        
        else if(elemento.compareTo(raiz.getElemento()) > 0)
            raiz.setDireita(remover(raiz.getDireita(), elemento));
        
        // caso o node tenha sido encontrado
        else{
            // node possui nenhum ou 1 filho
            if((raiz.getEsquerda() == null) || (raiz.getEsquerda() == null)){
                Node<T> aux;
                aux = raiz.getEsquerda() == null ? raiz.getEsquerda() : raiz.getDireita();
                
                // nenhum filho
                if(aux == null){
                    aux = raiz;
                    raiz = null;
                }
                // 1 filho
                else
                    raiz = aux;
                
                aux = null;
            }
            // node possui dois filhos
            else{
                Node<T> aux = menorNode(raiz.getDireita());
                raiz.setElemento(aux.getElemento());
                raiz.setDireita(remover(raiz.getDireita(), aux.getElemento()));
            }
        }
        
        // caso o node seja a raiz
        if(raiz == null){
            return raiz;    
        }
        
        // atualizar a altura do node atual
        raiz.h = getMaiorAltura(getAltura(raiz.getEsquerda()), getAltura(raiz.getDireita())) + 1;
        
        // checar o balanceamento
        int fb = FB(raiz);
        
        // possiveis casos de desbalanceamento:
        if(fb > 1 && FB(raiz.getEsquerda()) >= 0){
            return rotacaoRR(raiz);
        }
        if(fb > 1 && FB(raiz.getEsquerda()) < 0){
            raiz.setEsquerda(rotacaoLL(raiz.getEsquerda()));
            return rotacaoRR(raiz);
        }
        if(fb < -1 && FB(raiz.getDireita()) <= 0){
            return rotacaoLL(raiz);
        }
        if(fb < -1 && FB(raiz.getDireita()) > 0){
            raiz.setDireita(rotacaoRR(raiz.getDireita()));
            return rotacaoLL(raiz);
        }
        
        return raiz;
    } 
    
    private int FB(Node<T> node){
        if(node == null)
            return 0;
        return getAltura(node.getEsquerda()) - getAltura(node.getDireita());
    }
    
    private Node<T> menorNode(Node<T> no) {
        Node<T> atual = no;
        while (atual.getEsquerda() != null)
            atual = atual.getEsquerda();
        return atual;
    }
    
    public void imprimir(Node<T> node, String tab, boolean condicao){
        if (node != null) {
            System.out.print(tab);
            if (condicao) {
                System.out.print(" |-> ");
                tab += "\t";
            } else {
                System.out.print(" |-> ");
                tab += "\t ";
            }

            System.out.println(node.elemento.toString());

            imprimir(node.esquerda, tab, false);
            imprimir(node.direita, tab, true);
        }
    }
    
    public void imprimirOrdenado(Node<T> raiz){
        if(raiz.getEsquerda() == null){
            System.out.println(raiz.getElemento());
            return;
        }
        imprimirOrdenado(raiz.getEsquerda());
        System.out.println(raiz.getElemento());
        imprimirOrdenado(raiz.getDireita());
    }
    
    public Node<T> getRaiz() {
        return raizArvore;
    }
}
    
