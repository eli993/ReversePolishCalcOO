package reverse_polish_calc;

import java.util.*;

public class Calc02 {
   
    public static void main(String[] args) {

        Scanner tastiera = new Scanner(System.in);
      
        Stack stack;

        String program;
        
        int pc = 0;
      
        System.out.println("Inserisci un'espressione in forma postfissa:");
        program = tastiera.nextLine();
        
        if (checkString(program)){
            int n = program.length();
            stack = createStack(n);

            while (pc < n) {
                char istr = program.charAt(pc);

                if (Character.isDigit(istr)) {
                    push(stack, Character.getNumericValue(istr));
                }
                
                if (istr=='+') {
                    int op1 = pop(stack);
                    int op2 = pop(stack);
                    int sum = op1+op2;
                    push(stack, sum);
                }
                if (istr=='*') {
                    int op1 = pop(stack);
                    int op2 = pop(stack);
                    int pro = op1*op2;
                    push(stack, pro);
                }
                if(istr=='-') {
                    int op1 = pop(stack);
                    int op2 = pop(stack);
                    int dif = op2 - op1;
                    push(stack, dif);
                }
                if(istr=='/') {
                    int op1 = pop(stack);
                    int op2 = pop(stack);
                    int div = op2/op1;
                    push(stack, div);
                }
         
                if(istr=='%') {
                    int op1 = pop(stack);
                    int op2 = pop(stack);
                    int res = op2%op1;
                    push(stack, res);
                }   
                if (istr=='.') {
                   print(stack);
                }
                if(istr == '#'){
                    stampaPila(stack);
                }        
                if(istr == '$'){
                    scambiaTop(stack);
                }
                
                pc++;
        
            } //CHIUDE WHILE
        } // CHIUDE IF
        
        else{
            System.out.println("Errore.");
        }
    
    }
    
    public static Stack createStack(int n){
        return new Stack(n);
    }
    
    public static void push(Stack p, int el){
        p.s[p.num] = el;
        p.num++;
    }
    

    public static int pop(Stack p){
        p.num--;
        return p.s[p.num];
        
    }
    
    public static void print(Stack p){
        p.num--;
        System.out.println("Risultato: " +p.s[p.num]);
        
    }

    public static void stampaPila(Stack p){
        System.out.println("Lunghezza pila: " + (p.num+1));
        System.out.print("Contenuto pila: ");
        for (int i = 0; i < p.num+1; i++)
            System.out.print(p.s[i] + " ");
          
        System.out.println();
        
    }
    
    public static void scambiaTop (Stack p){
        int i = p.num;
        i--;
        int tmp = p.s[i];
        p.s[i] = p.s[i-1];
        p.s[i-1] =tmp;
    }
    
    public static boolean checkString (String str){
        Stack h = new Stack(str.length());
        
        //Il primo carattere della stringa deve essere un numero.
        if ( !Character.isDigit(str.charAt(0) ))
            return false;
            
        
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            
            //Se nella stringa è presente un carattere non permesso, dai errore.
            if (!Character.isDigit(c) && c!='+' && c!='-' &&  c!='*' && c!='/' 
                    && c!= '%' && c!= '.' && c!='#' && c!='$')
                return false;
            
            //Se c è un carattere, riempi lo stack di supporto
            if ( Character.isDigit(c) )
                push(h, Character.getNumericValue(c));
                        
            /*  
                Quando faccio la stampa, ho bisogno di almento un elemento nell'array.
                La stampa è distruttiva, quindi ho un elemento in meno nello stack.
            */
            else if(c=='.'){
                if(h.num<0)
                    return false;
                h.num--;
            }
                
            /*  
                Quando c'è un operatore binario, nello stack devono esserci almeno 2 numeri. 
                Inoltre, dopo l'uso dell'operatore, ho un elemento in meno nello stack.
            */
            else if (c=='+' || c=='-' ||  c=='*' ||c=='/' || c== '%' || c=='$'){             
                h.num--;
                if(h.num<1)
                    return false;
            }     
        }
        
        return true;
    }
    
}
