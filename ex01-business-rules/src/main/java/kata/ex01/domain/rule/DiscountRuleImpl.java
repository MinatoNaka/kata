package kata.ex01.domain.rule;

import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author kawasima
 */
public class DiscountRuleImpl implements EtcDiscountRule  {

    private List<Boolean> judgmentResults = Arrays.asList();

    public DiscountRuleImpl() { }

    @Override
    public DiscountRuleImpl getNewInstance()
    {
        return new DiscountRuleImpl();
    }

    /** 割引適用判定 **/
    @Override
    public boolean is()
    {
        for(boolean judgment : judgmentResults){
            if (judgment == false) return false;
        }
        return true;
    }

    /** 休日判定 **/
    @Override
    public EtcDiscountRule setHoliday(LocalDate enteredAt, LocalDate exitedAt) {
        judgmentResults.add(false);
        return this;
    }

    /** 車種判定 **/
    @Override
    public DiscountRuleImpl setVehicleFamily(VehicleFamily currentVehicleFamily, VehicleFamily[] targetVehicleFamilies)
    {
        judgmentResults.add(false);
        return this;
    }

    /** 地方部判定 **/
    @Override
    public DiscountRuleImpl setRural(RouteType routeType)
    {
        judgmentResults.add(false);
        return this;
    }

    /** 平日判定 **/
    @Override
    public DiscountRuleImpl setWeekDay(LocalDate enteredAt, LocalDate exitedAt)
    {
        judgmentResults.add(false);
        return this;
    }

    /** 時間判定 **/
    @Override
    public DiscountRuleImpl setTargetTimeSpan(LocalTime startTime, LocalTime endTime, LocalDateTime enteredAt, LocalDateTime exitedAt)
    {
        judgmentResults.add(false);
        return this;
    }

}
