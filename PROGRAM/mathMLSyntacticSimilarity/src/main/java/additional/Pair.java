
package additional;


import java.util.Objects;


 public class Pair<N, M> {         
    public final N n;
    public final M m;

    public Pair(N n, M m) {         
        this.n= n;
        this.m= m;
     }
    
    @Override
    public boolean equals(Object obj) {
        
        if(obj == this) 
            return true;
        
        if(obj == null) 
            return false;
        
        if (obj instanceof Pair<?,?>)
            if(n.equals(((Pair<?,?>)obj).n) && m.equals(((Pair<?,?>)obj).m)) 
                return true;
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.n);
        hash = 89 * hash + Objects.hashCode(this.m);
        return hash;
    }
    
 }