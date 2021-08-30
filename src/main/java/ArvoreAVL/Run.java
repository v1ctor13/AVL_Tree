package ArvoreAVL;

public class Run  {  
     
    public static void main(String[] args)  {              
        
   
        AVLTree<Integer> arv = new AVLTree<>();   
        
        arv.inserir(8);
        arv.inserir(10);
        arv.inserir(9);
        arv.inserir(5);
        arv.inserir(3);

//       System.out.print("root anterior"+arv.getRaiz().elemento.toString()+"\n");
//       System.out.print("root esquerda"+arv.getRaiz().esquerda.elemento.toString()+"\n");
//       System.out.print("root direita"+arv.getRaiz().direita.elemento.toString()+"\n");
        
//       System.out.println(arv.ObterQtdNodes());
        
//        System.out.println(arv.buscar(arv.getRaiz(), 10));

//        System.out.println("Raiz: " + arv.getRaiz().getElemento());

//        arv.imprimirOrdenado(arv.getRaiz());

//        System.out.println("Raiz = " + arv.getRaiz().elemento.toString());
        
        

//        System.out.println("FB: " + arv.FB(arv.getRaiz()));

        arv.remover(arv.getRaiz(), 3);
        
        arv.imprimir(arv.getRaiz(), " ", false);
    }  
}  