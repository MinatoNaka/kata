package kata.ex01.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kawasima
 */
public class Rule implements Serializable {
    private boolean isRule;
    public boolean getIsRule() { return this.isRule; }
    public void setIsRule(boolean value){ this.isRule = value;}
}
