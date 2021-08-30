package ArvoreAVL;

class Node<T>{      
    T elemento;  
    int h;    
    Node<T> esquerda;  
    Node<T> direita;  
      
    public Node(){  
        esquerda = null;  
        direita = null;  
        elemento = null;  
        h = 0;  
    }  
    
    public Node(T elemento){  
        esquerda = null;  
        direita = null;  
        this.elemento = elemento;  
        h = 0;  
    }    

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Node<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node<T> esquerda) {
        this.esquerda = esquerda;
    }

    public Node<T> getDireita() {
        return direita;
    }

    public void setDireita(Node<T> direita) {
        this.direita = direita;
    }
}  