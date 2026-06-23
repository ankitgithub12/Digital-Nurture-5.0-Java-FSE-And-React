package patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern:
 * Defines a one-to-many dependency between objects so that when one object changes state,
 * all its dependents are notified and updated automatically.
 */
public class ObserverDemo {

    // --- Observer Interface (Subscriber) ---
    public interface Observer {
        void update(float temperature, float humidity);
    }

    // --- Subject Interface (Publisher) ---
    public interface Subject {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObservers();
    }

    // --- Concrete Subject (Publisher) ---
    public static class WeatherStation implements Subject {
        private final List<Observer> observers = new ArrayList<>();
        private float temperature;
        private float humidity;

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(temperature, humidity);
            }
        }

        // Trigger change in state and notify
        public void setMeasurements(float temperature, float humidity) {
            System.out.println("\nWeatherStation: Measurements updated -> Temp: " + temperature + "C, Humidity: " + humidity + "%");
            this.temperature = temperature;
            this.humidity = humidity;
            notifyObservers(); // Notify subscribers of state change
        }
    }

    // --- Concrete Observer 1 (Subscriber) ---
    public static class MobileDisplay implements Observer {
        private final String deviceOwner;

        public MobileDisplay(String owner) {
            this.deviceOwner = owner;
        }

        @Override
        public void update(float temp, float humidity) {
            System.out.println("MobileDisplay [" + deviceOwner + "]: Notified! Current Temp: " 
                    + temp + "C | Humidity: " + humidity + "%");
        }
    }

    // --- Concrete Observer 2 (Subscriber) ---
    public static class TVDisplay implements Observer {
        @Override
        public void update(float temp, float humidity) {
            System.out.println("TVDisplay [Newsroom]: Weather Alert! Temperature is now " 
                    + temp + "C with " + humidity + "% humidity.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Demo ===");

        WeatherStation station = new WeatherStation();

        // Create display observers
        Observer user1 = new MobileDisplay("Alice");
        Observer user2 = new MobileDisplay("Bob");
        Observer newsTv = new TVDisplay();

        // Register observers
        station.registerObserver(user1);
        station.registerObserver(user2);
        station.registerObserver(newsTv);

        // Update 1: All registered displays get update
        station.setMeasurements(25.5f, 65.0f);

        // Deregister Bob's mobile display
        System.out.println("\nBob unsubscribes from WeatherStation updates.");
        station.removeObserver(user2);

        // Update 2: Only Alice and News TV get updated
        station.setMeasurements(28.0f, 50.0f);
    }
}
