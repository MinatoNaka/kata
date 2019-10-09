package kata.ex01;

import kata.ex01.model.Driver;
import kata.ex01.model.HighwayDrive;
import kata.ex01.rule.DiscountRule;
import kata.ex01.rule.HolidayDiscountImpl;
import kata.ex01.rule.MidnightDiscountImpl;
import kata.ex01.rule.WeekdayDiscountImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.VehicleFamily.STANDARD;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kawasima
 */
public class DiscountServiceTest {
    DiscountService discountService;

    private Driver driver(int usingCount) {
        Driver driver = new Driver();
        driver.setCountPerMonth(usingCount);
        return driver;
    }

    @BeforeEach
    void setUp() {
        List<DiscountRule> discountRules = new ArrayList<DiscountRule>();
        discountRules.add(new WeekdayDiscountImpl());
        discountRules.add(new HolidayDiscountImpl());
        discountRules.add(new MidnightDiscountImpl());
        discountService = new DiscountServiceImpl(discountRules);
    }

    //▽▽▽既存テスト▽▽▽//
    @Test
    public void test平日朝夕割引() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 6, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(50);
    }

    @Test
    public void test休日朝夕は休日割が適用される() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 2, 6, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }
    //△△△既存テスト△△△//

    @Test
    public void test平日朝夕割引50() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 6, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 10, 0));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(50);
    }

    @Test
    public void test平日朝夕割引30() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 6, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 10, 0));
        drive.setDriver(driver(9));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }

    @Test
    public void test休日割() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 4, 2, 6, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 2, 10, 0));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }

    @Test
    public void test深夜割引() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 1, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 5, 0));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }

    @Test
    public void test平日50と休日割と深夜割の全部適用() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 19, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 2, 7, 0));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(50);
    }

    @Test
    public void test平日と休日をまたいで休日割のみ適用() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 21, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 2, 7, 0));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(30);
    }

    @Test
    public void test平日朝夕で利用回数足りない() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 4, 1, 6, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 10, 0));
        drive.setDriver(driver(4));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(0);
    }
}
