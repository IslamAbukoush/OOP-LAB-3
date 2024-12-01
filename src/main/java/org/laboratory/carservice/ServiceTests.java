package org.laboratory.carservice;

import org.junit.jupiter.api.BeforeEach;
import org.laboratory.carservice.implementations.ElectricStation;
import org.laboratory.carservice.implementations.GasStation;
import org.laboratory.carservice.implementations.PeopleDinner;
import org.laboratory.carservice.implementations.RobotDinner;
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

        peopleDinner.serveDinner("Car1");
        peopleDinner.serveDinner("Car2");
        robotDinner.serveDinner("Car3");

        assertEquals(2, PeopleDinner.getPeopleServed());
        assertEquals(1, RobotDinner.getRobotsServed());
    }

    @Test
    void testRefuelableImplementations() {
        ElectricStation electricStation = new ElectricStation();
        GasStation gasStation = new GasStation();

        electricStation.refuel("Car1");
        electricStation.refuel("Car2");
        gasStation.refuel("Car3");

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

