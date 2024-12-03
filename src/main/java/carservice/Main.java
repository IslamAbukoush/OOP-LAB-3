package carservice;

import carservice.carloader.CarLoader;
import carservice.sheduler.Scheduler;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Scheduler scheduler = new Scheduler("C:\\Users\\iabuk\\OneDrive\\Desktop\\OOP-LAB-3\\src\\main\\resources\\queue", 9, 10);
        scheduler.start();
    }
}