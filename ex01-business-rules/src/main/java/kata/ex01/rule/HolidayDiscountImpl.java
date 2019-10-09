package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.util.HolidayUtils;

import java.util.EnumSet;

import static kata.ex01.model.VehicleFamily.*;

public class HolidayDiscountImpl implements DiscountRule {
    @Override
    public int getDiscountRate(HighwayDrive drive) {
        if ((HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate()) || HolidayUtils.isHoliday(drive.getExitedAt().toLocalDate()))
                && EnumSet.of(STANDARD, MINI, MOTORCYCLE).contains(drive.getVehicleFamily())
                && drive.getRouteType() == RouteType.RURAL) {
            return 30;
        }

        return 0;
    }
}
