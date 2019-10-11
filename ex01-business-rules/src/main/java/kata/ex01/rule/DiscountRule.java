package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.Rule;
import kata.ex01.model.VehicleFamily;

import java.util.List;

/**
 * @author kawasima
 */
public class DiscountRule {
    public List<Rule> rules;

    public DiscountRule() { }

    public boolean isDiscount()
    {
        for (Rule rule : rules) { if(rule.getIsRule() == false) return false; }
        return true;
    }

    public DiscountRuleBuilder build (HighwayDrive drive)
    {
        return new DiscountRuleBuilder(this, drive);
    }

    public class DiscountRuleBuilder
    {
        private HighwayDrive drive;
        private DiscountRule discountRule;

        public DiscountRuleBuilder(DiscountRule discountRule, HighwayDrive drive)
        {
            this.drive = drive;
            this.discountRule = discountRule;
        }
        /** 平日判定ルール **/
        public DiscountRuleBuilder setWeekdayRule() {
            discountRule.rules.add(RuleBuilder.weekdayRule(drive.getEnteredAt(), drive.getExitedAt()));
            return this;
        }
        /** 休日判定ルール **/
        public DiscountRuleBuilder setHolidayRule()
        {
            discountRule.rules.add(RuleBuilder.holidayRule(this.drive.getEnteredAt(),this.drive.getExitedAt()));
            return this;
        }
        /** 車種判定ルール **/
        public DiscountRuleBuilder setVehicleFamilyRule(VehicleFamily[] vehicleFamilies)
        {
            discountRule.rules.add(RuleBuilder.VehicleFamilyRule(this.drive.getEnteredAt(),this.drive.getExitedAt()));
            return this;
        }
        /** 地方部判定ルール **/
        public DiscountRuleBuilder setRuralRule()
        {
            discountRule.rules.add(RuleBuilder.RuralRule(this.drive.getEnteredAt(),this.drive.getExitedAt()));
            return this;
        }
        /** 割引時間判定ルール **/
        public DiscountRuleBuilder setDiscountTime(int startHour, int endHour)
        {
            discountRule.rules.add(RuleBuilder.DiscountTimeRule(this.drive.getEnteredAt(),this.drive.getExitedAt()));
            return this;
        }
        /** 割引ルールのインスタンスを返す **/
        public DiscountRule getResult()
        {
            return this.discountRule;
        }
    }


}
