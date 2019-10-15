package kata.ex01.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 車種.
 *
 * @author kawasima
 */
public enum VehicleFamily {
    /** 普通車 */
    STANDARD,
    /** 軽自動車 */
    MINI,
    /** 二輪車 */
    MOTORCYCLE,
    /** その他 */
    OTHER
}