package kata.ex01.domain.rule;

import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;

import javax.management.openmbean.ArrayType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface EtcDiscountRule {
    /** 判定 **/
    public boolean is();
    /** インスタンス取得 **/
    public EtcDiscountRule getNewInstance();
    /** 休日ルール **/
    public EtcDiscountRule setHoliday(LocalDate enteredAt, LocalDate exitedAt);
    /** 車種ルール **/
    public EtcDiscountRule setVehicleFamily(VehicleFamily currentVehicleFamily, VehicleFamily[] targetVehicleFamilies);
    /** 地方部ルール **/
    public EtcDiscountRule setRural(RouteType routeType);
    /** 平日ルール **/
    public EtcDiscountRule setWeekDay(LocalDate enteredAt, LocalDate exitedAt);
    /** 適用時間ルール**/
    public EtcDiscountRule setTargetTimeSpan(LocalTime startTime, LocalTime endTime, LocalDateTime enteredAt, LocalDateTime exitedAt);
}
