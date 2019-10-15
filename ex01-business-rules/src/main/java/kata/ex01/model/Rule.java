package kata.ex01.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kawasima
 */
public class Rule implements Serializable {
    private boolean checked;
    public boolean getChecked() { return this.checked; }
    public Rule(boolean value) {
        this.checked = value;
    }
}
