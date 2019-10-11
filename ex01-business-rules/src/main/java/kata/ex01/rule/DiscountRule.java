package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.Rule;
import kata.ex01.model.VehicleFamily;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author kawasima
 */
public class DiscountRule {

    private List<Rule> rules;

    public static class Builder
    {
        private HighwayDrive drive;
        private List<Rule> rules;

        public Builder(HighwayDrive drive)
        {
            this.drive = drive;
            this.rules = new ArrayList<>();
        }
        /** 休日判定ルール **/
        public Builder setHolidayRule()
        {
            this.rules.add(RuleHelper.holidayRule(this.drive.getEnteredAt().toLocalDate(),this.drive.getExitedAt().toLocalDate()));
            return this;
        }
        /** 車種判定ルール **/
        public Builder setVehicleFamilyRule(EnumSet<VehicleFamily> vehicleFamilies)
        {
            this.rules.add(RuleHelper.vehicleFamilyRule(this.drive.getVehicleFamily(), vehicleFamilies));
            return this;
        }
        /** 地方部判定ルール **/
        public Builder setRuralRule()
        {
            this.rules.add(RuleHelper.ruralRule(this.drive.getRouteType()));
            return this;
        }
        /** 割引時間判定ルール **/
        public Builder setDiscountTime(int startHour, int endHour)
        {
            LocalTime startTime = LocalTime.of(startHour, 0);
            LocalTime endTime = LocalTime.of(endHour, 0);
            this.rules.add(RuleHelper.discountTimeRule(startTime, endTime, this.drive.getEnteredAt(),this.drive.getExitedAt()));
            return this;
        }
        /** 平日割引時間判定ルール **/
        public Builder setWeekdayDiscountTime(int startHour, int endHour)
        {
            LocalTime startTime = LocalTime.of(startHour, 0);
            LocalTime endTime = LocalTime.of(endHour, 0);
            this.rules.add(RuleHelper.weekDayDiscountTimeRule(startTime, endTime, this.drive.getEnteredAt(),this.drive.getExitedAt()));
            return this;
        }
        /** 割引ルール「インスタンスを返す **/
        public DiscountRule build()
        {
            return new DiscountRule(this);
        }
    }

    private DiscountRule(Builder builder) {
        this.rules = builder.rules;
    }

    public boolean isDiscount()
    {
        for (Rule rule : rules) { if(rule.getIsRule() == false) return false; }
        return true;
    }
}
