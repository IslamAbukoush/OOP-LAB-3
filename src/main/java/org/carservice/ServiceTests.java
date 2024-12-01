package org.carservice;

import org.junit.jupiter.api.BeforeEach;
import org.carservice.implementations.ElectricStation;
import org.carservice.implementations.GasStation;
import org.carservice.implementations.PeopleDinner;
import org.carservice.implementations.RobotDinner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceTests {
    @BeforeEach
    void resetCounters() {
        PeopleDinner.resetCounter();
        RobotDinner.resetCounter();
        ElectricStation.resetCounter();
        GasStation.resetCounter();
    }

    @Test
    void testDineableImplementations() {
        PeopleDinner peopleDinner = new PeopleDinner();
        RobotDinner robotDinner = new RobotDinner();

        peopleDinner.serveDinner(1);
        peopleDinner.serveDinner(2);
        robotDinner.serveDinner(3);

        assertEquals(2, PeopleDinner.getPeopleServed());
        assertEquals(1, RobotDinner.getRobotsServed());
    }

    @Test
    void testRefuelableImplementations() {
        ElectricStation electricStation = new ElectricStation();
        GasStation gasStation = new GasStation();

        electricStation.refuel(1);
        electricStation.refuel(2);
        gasStation.refuel(3);

        assertEquals(2, ElectricStation.getElectricCarsServed());
        assertEquals(1, GasStation.getGasCarsServed());
    }

    @Test
    void testCombinedService() {
        PeopleDinner peopleDinner = new PeopleDinner();
        RobotDinner robotDinner = new RobotDinner();
        ElectricStation electricStation = new ElectricStation();
        GasStation gasStation = new GasStation();

        peopleDinner.serveDinner("Car1");
        robotDinner.serveDinner("Car2");
        electricStation.refuel("Car3");
        gasStation.refuel("Car4");

        assertEquals(1, PeopleDinner.getPeopleServed());
        assertEquals(1, RobotDinner.getRobotsServed());
        assertEquals(1, ElectricStation.getElectricCarsServed());
        assertEquals(1, GasStation.getGasCarsServed());
    }
}

