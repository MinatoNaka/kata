package kata.ex01.rule;

import kata.ex01.model.Rule;

import java.time.LocalDateTime;

/**
 * @author kawasima
 */
public class RuleBuilder {
    private static Rule build(boolean value)
    {
        var rule = new Rule();
        rule.setIsRule(value);
        return rule;
    }
    /** 平日判定ルール **/
    public static Rule weekdayRule(LocalDateTime start, LocalDateTime end)
    {
       return build(false);
    }

    /** 休日判定ルール **/
    public static Rule holidayRule(LocalDateTime start, LocalDateTime end)
    {
        return build(false);
    }

    /** 車種判定ルール **/
    public static Rule VehicleFamilyRule(LocalDateTime start, LocalDateTime end)
    {
        return build(false);
    }

    /** 地方部判定ルール **/
    public static Rule RuralRule(LocalDateTime start, LocalDateTime end)
    {
        return build(false);
    }

    /** 割引時間判定ルール **/
    public static Rule DiscountTimeRule(LocalDateTime start, LocalDateTime end)
    {
        return build(false);
    }
}
